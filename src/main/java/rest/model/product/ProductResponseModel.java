package rest.model.product;

import rest.model.categories.CategoriesResponseModel;

import java.io.Serializable;
import java.util.Objects;

public class ProductResponseModel implements Serializable {

    private static final long serialVersionUID = -3404791698266254133L;

    private Long id;
    private String name;
    private CategoriesResponseModel categories;

    public ProductResponseModel(Long id, String name, CategoriesResponseModel categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
    }

    public ProductResponseModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoriesResponseModel getCategories() {
        return categories;
    }

    public void setCategories(CategoriesResponseModel categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseModel that = (ProductResponseModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(categories, that.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categories);
    }

    @Override
    public String toString() {
        return "ProductResponseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }
}
