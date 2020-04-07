package hello.repository;

import java.util.List;

import hello.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;


public interface CustomerRepository extends Repository<Customer, Long> {
	
    //TODO: Add code that will be necessary to implement all methods in service
	
	List<Customer> findAll();
	Customer findById(Long id);
	List<Customer> findByFirstName(String firstname);
	List<Customer> findByLastName(String lastname);
	Customer save(Customer customer);
	Customer update(Long id, Customer customer);
	Customer deleteById(Long id);
	Customer deleteByFirstName(String firstName);
	Customer deleteByLastName(String lastName);
}
