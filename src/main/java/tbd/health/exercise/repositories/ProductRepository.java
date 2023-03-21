package tbd.health.exercise.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tbd.health.exercise.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findProductById(long id);
}
