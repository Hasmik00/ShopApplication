package service;

import exception.CategoriesNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import persistence.model.Categories;
import persistence.repository.CategoriesRepository;
import rest.model.categories.CategoriesRequestModel;
import rest.model.categories.CategoriesResponseModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesService.class);

    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public CategoriesResponseModel create(CategoriesRequestModel categoriesRequestModel) {
        LOGGER.info("creating category - {}", categoriesRequestModel);
        final Categories categories = buildCategoriesFrom(categoriesRequestModel);
        final Categories saved = categoriesRepository.save(categories);
        LOGGER.info("Successfully created categories - {}", categoriesRequestModel);
        return buildCategoriesResponseModelFrom(saved);
    }

    public CategoriesResponseModel getById(Long id) {

        LOGGER.info("Getting Category by id - {}", id);

        final Categories category = categoriesRepository.findById(id)
                .orElseThrow(() -> new CategoriesNotFoundException(String.format("Categorie not found for id - {}%d", id)));
        return buildCategoriesResponseModelFrom(category);

    }

    public List<CategoriesResponseModel> getAll() {
        LOGGER.info("Requested to get all categories");
        final List<Categories> all = categoriesRepository.findAll();
        final List<CategoriesResponseModel> categorieResponseModels = all.stream()
                .map((each -> buildCategoriesResponseModelFrom(each))).collect(Collectors.toList());
        return categorieResponseModels;
    }

    public CategoriesResponseModel update(Long id, CategoriesRequestModel requestModel) {
        LOGGER.info("Requested to update categorie with id - {} requestModel - {}", id, requestModel);
        final Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new CategoriesNotFoundException(String.format("Animal not found for id - {}%d", id)));
        final Categories updatedCategorie = updateCategoriesProperties(categories, requestModel);
        final Categories updated = categoriesRepository.save(updatedCategorie);
        return buildCategoriesResponseModelFrom(updated);
    }

    public void delete(Long id) {
        LOGGER.info("Requested to delete category with id - {}", id);
        categoriesRepository.deleteById(id);
    }


    private Categories buildCategoriesFrom(CategoriesRequestModel requestModel) {
        final Categories categories = new Categories();
        categories.setName(requestModel.getName());
        return categories;
    }

    private Categories updateCategoriesProperties(Categories categories, CategoriesRequestModel requestModel) {
        categories.setName(requestModel.getName());
        return categories;
    }

    private CategoriesResponseModel buildCategoriesResponseModelFrom(Categories categories) {
        final CategoriesResponseModel responseModel = new CategoriesResponseModel();
        responseModel.setId(categories.getId());
        responseModel.setName(categories.getName());
        return responseModel;
    }
}
