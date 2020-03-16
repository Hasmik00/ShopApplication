package rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.model.product.ProductRequestModel;
import rest.model.product.ProductResponseModel;
import service.ProductService;

import java.util.List;
@RestController
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ProductResponseModel> create(@RequestBody ProductRequestModel requestModel) {
        LOGGER.info("Creating product - {}", requestModel);
        final ProductResponseModel productResponseModel = productService.create(requestModel);
        return ResponseEntity.ok(productResponseModel);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<List<ProductResponseModel>> getAll() {
        LOGGER.info("Getting all products");
        final List<ProductResponseModel> productResponseModels = productService.getAll();
        return ResponseEntity.ok(productResponseModels);
    }

    @GetMapping(value = "/product/category/{categoryId}")
    public ResponseEntity<List<ProductResponseModel>> getByCategoryId(@PathVariable Long categoryId) {
        LOGGER.info("Getting all products by category id - {}", categoryId);
        final List<ProductResponseModel> productResponseModels = productService.getAllByCategoryId(categoryId);
        return ResponseEntity.ok(productResponseModels);
    }


    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductResponseModel> getById(@PathVariable Long id) {
        LOGGER.info("Getting products by id - {}", id);
        final ProductResponseModel responseModel = productService.getById(id);
        return ResponseEntity.ok(responseModel);
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<ProductResponseModel> getById(@PathVariable Long id, @RequestBody ProductRequestModel requestModel) {
        LOGGER.info("Requested to update product with id - {} requestModel - {}", id, requestModel);
        final ProductResponseModel responseModel = productService.update(id, requestModel);
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping(value = "/product/{id}")
    public void delete(@PathVariable Long id) {
        LOGGER.info("Requested to delete product with id - {}", id);
        productService.delete(id);
    }
}
