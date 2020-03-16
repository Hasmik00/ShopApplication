package rest.model.product;

import java.io.Serializable;
import java.util.Objects;

public class ProductRequestModel implements Serializable {

    private static final long serialVersionUID = -2024158756168394347L;

    private String name;
    private String price;
    private Long categoriesId;

    public ProductRequestModel(String name, String price, Long categoriesId) {
        this.name = name;
        this.price = price;
        this.categoriesId = categoriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequestModel that = (ProductRequestModel) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(categoriesId, that.categoriesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, categoriesId);
    }

    @Override
    public String toString() {
        return "ProductRequestModel{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", categoriesId=" + categoriesId +
                '}';
    }
}
