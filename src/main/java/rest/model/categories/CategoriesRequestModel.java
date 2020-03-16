package rest.model.categories;

import java.io.Serializable;
import java.util.Objects;

public class CategoriesRequestModel implements Serializable {

    private static final long serialVersionUID = -4700641834995100100L;

    private String name;

    public CategoriesRequestModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesRequestModel that = (CategoriesRequestModel) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CategoriesRequestModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
