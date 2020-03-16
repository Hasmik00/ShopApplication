package rest.model.categories;

import java.io.Serializable;
import java.util.Objects;

public class CategoriesResponseModel implements Serializable {

    private static final long serialVersionUID = 4995160063608869453L;

    private Long id;
    private String name;

    public CategoriesResponseModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoriesResponseModel() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesResponseModel that = (CategoriesResponseModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CategoriesResponseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
