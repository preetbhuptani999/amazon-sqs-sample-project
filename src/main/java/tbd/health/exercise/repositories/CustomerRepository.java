package tbd.health.exercise.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tbd.health.exercise.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findCustomerById(long id);
}
