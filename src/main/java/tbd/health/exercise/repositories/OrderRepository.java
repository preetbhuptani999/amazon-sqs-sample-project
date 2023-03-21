package tbd.health.exercise.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tbd.health.exercise.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
