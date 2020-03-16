package service;

import exception.CategoriesNotFoundException;
import exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import persistence.model.Categories;
import persistence.model.Product;
import persistence.repository.CategoriesRepository;
import persistence.repository.ProductRepository;
import rest.model.categories.CategoriesResponseModel;
import rest.model.product.ProductRequestModel;
import rest.model.product.ProductResponseModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoriesRepository categoriesRepository;

    public ProductService(ProductRepository productRepository, CategoriesRepository categoriesRepository) {
        this.productRepository = productRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public ProductResponseModel create(ProductRequestModel requestModel) {
        LOGGER.info("Creating product - {}", requestModel);
        final Product product = buildProductFrom(requestModel);
        final Categories category = categoriesRepository.findById(requestModel.getCategoriesId())
                .orElseThrow(() -> new CategoriesNotFoundException(String.format("category not found for id - {}%d", requestModel.getCategoriesId())));
        product.setCategories(category);
        final Product save = productRepository.save(product);
        return buildProductResponseModelFrom(save);
    }

    public ProductResponseModel getById(Long id) {
        LOGGER.info("Geting product by id - {}", id);
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("product not found for id - {}%d", id)));
        return buildProductResponseModelFrom(product);
    }

    public List<ProductResponseModel> getAll() {
        LOGGER.info("Getting all products");
        final List<Product> all = productRepository.findAll();
        return all.stream()
                .map((each -> buildProductResponseModelFrom(each))).collect(Collectors.toList());
    }

    public List<ProductResponseModel> getAllByCategoryId(Long categoryId) {
        LOGGER.info("Getting all products by cage id - {}", categoryId);
        final List<Product> all = productRepository.findAllByCategoryId(categoryId);
        return all.stream()
                .map((each -> buildProductResponseModelFrom(each))).collect(Collectors.toList());
    }

    public ProductResponseModel update(Long id, ProductRequestModel requestModel) {
        LOGGER.info("Requested to update product with id - {} requestModel - {}", id, requestModel);
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format("product not found for id - {}%d", id)));
        final Product updated = updateProductProperties(product, requestModel);
        final Product save = productRepository.save(updated);
        return buildProductResponseModelFrom(save);
    }

    public void delete(Long id) {
        LOGGER.info("Requested to delete product with id - {}", id);
        productRepository.deleteById(id);
    }

    private Product buildProductFrom(ProductRequestModel requestModel) {
        final Product product = new Product();
        product.setName(requestModel.getName());
        product.setPrice(requestModel.getPrice());
        return product;
    }

    private Product updateProductProperties(Product product, ProductRequestModel requestModel) {
        product.setName(requestModel.getName());
        product.setPrice(requestModel.getPrice());
        final Categories categories = categoriesRepository.findById(requestModel.getCategoriesId())
                .orElseThrow(() -> new CategoriesNotFoundException(String.format("Category not found for id - {}%d", requestModel.getCategoriesId())));
        product.setCategories(categories);
        return product;
    }

    private ProductResponseModel buildProductResponseModelFrom(Product product) {
        final ProductResponseModel productResponseModel = new ProductResponseModel();
        productResponseModel.setId(product.getId());
        productResponseModel.setName(product.getName());
        CategoriesResponseModel categoriesResponseModel = new CategoriesResponseModel();
        categoriesResponseModel.setId(product.getCategories().getId());
        categoriesResponseModel.setName(product.getCategories().getName());
        productResponseModel.setCategories(categoriesResponseModel);
        return productResponseModel;
    }
}
