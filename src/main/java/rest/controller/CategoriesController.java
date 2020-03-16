package rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.model.categories.CategoriesRequestModel;
import rest.model.categories.CategoriesResponseModel;
import service.CategoriesService;

import java.util.List;

@RestController
public class CategoriesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesController.class);

    //region Dependencies
    private final CategoriesService categoriesService;
    //endregion

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping(value = "/category")
    public ResponseEntity<CategoriesResponseModel> create(@RequestBody CategoriesRequestModel requestModel) {
        LOGGER.info("Creating category - {}", requestModel);
        final CategoriesResponseModel categoriesResponseModel = categoriesService.create(requestModel);
        return ResponseEntity.ok(categoriesResponseModel);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<CategoriesResponseModel>> getAll() {
        LOGGER.info("Getting all categories");
        final List<CategoriesResponseModel> categories = categoriesService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<CategoriesResponseModel> getById(@PathVariable Long id) {
        LOGGER.info("Getting category by id - {}", id);
        final CategoriesResponseModel responseModel = categoriesService.getById(id);
        return ResponseEntity.ok(responseModel);
    }

    @PutMapping(value = "/category/{id}")
    public ResponseEntity<CategoriesResponseModel> getById(@PathVariable Long id, @RequestBody CategoriesRequestModel requestModel) {
        LOGGER.info("Requested to update category with id - {} requestModel - {}", id, requestModel);
        final CategoriesResponseModel responseModel = categoriesService.update(id, requestModel);
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping(value = "category/{id}")
    public void delete(@PathVariable Long id) {
        LOGGER.info("Requested to delete category with id - {}", id);
        categoriesService.delete(id);
    }
}
