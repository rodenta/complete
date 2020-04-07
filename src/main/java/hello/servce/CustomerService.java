package hello.servce;

import hello.model.Customer;
import hello.mapper.CustomerMapper;
import hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    //TODO: Implement methods for each controller method. Note that each of them has to call different method from Service.
    
    @Transactional
    public List<Customer> getAllCustomers() {
    	return customerRepository.findAll();
    }
    
    public Customer findById(Long id) {
    	if (customerRepository.findById(id) != null) {
    		return customerRepository.findById(id);
    	} 	else {
    		throw new IllegalArgumentException("Customer not found, id: " + id);    
    	}    				
    }
    
    public List<Customer> findByFirstName(String firstName) {
    	  if (customerRepository.findByFirstName(firstName) != null) {
              return customerRepository.findByFirstName(firstName);
          } else {
              throw new IllegalArgumentException("Customer " + firstName + " not found.");
          }  
    }
    
    public List<Customer> findByLastName(String lastName) {
  	  if (customerRepository.findByLastName(lastName) != null) {
            return customerRepository.findByLastName(lastName);
        } else {
            throw new IllegalArgumentException("Customer " + lastName + " not found.");
        }  
    }
    
    public Customer addCustomer(Customer customer) {
    	 return customerRepository.save(customer);	
    }
    
    public Customer updateCustomer (Long id, Customer customer) {
    	return customerRepository.update(id, customer);    	
    }
    
    public Customer deleteById (Long id) {
    	return customerRepository.delete(id);
    }
    
}
