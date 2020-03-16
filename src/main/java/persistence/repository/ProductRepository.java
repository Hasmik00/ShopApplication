package persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import persistence.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);
}
