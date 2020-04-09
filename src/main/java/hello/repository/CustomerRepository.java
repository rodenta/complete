package hello.repository;

import java.util.List;
import hello.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	//  Add code that will be necessary to implement all methods in service

	List<Customer> findByFirstName(String firstname);

	List<Customer> findByLastName(String lastname);	

}
