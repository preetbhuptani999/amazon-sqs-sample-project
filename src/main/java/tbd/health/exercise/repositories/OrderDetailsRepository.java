package tbd.health.exercise.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tbd.health.exercise.domain.OrderDetails;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
}
