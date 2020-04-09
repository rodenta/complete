package hello.servce;

import hello.model.Customer;
import hello.exception.CustomerNotCreatedException;
import hello.exception.CustomerNotFoundException;
import hello.mapper.CustomerMapper;
import hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	// Implement methods for each controller method. Note that each of them
	// has to call different method from Service.

	public List<Customer> getAllCustomers() {
		List<Customer> result = new ArrayList<Customer>();
		customerRepository.findAll().forEach(result::add);
		return result;
	}

	public Customer findById(Long id) throws CustomerNotFoundException {
		Optional<Customer> optional = customerRepository.findById(id);
		return optional.orElseThrow(() -> new CustomerNotFoundException("Customer id: " + id));
	}

	public List<Customer> findByFirstName(String firstName) {
		List<Customer> result = new ArrayList<Customer>();
		customerRepository.findByFirstName(firstName).forEach(result::add);
		return result;
	}

	public List<Customer> findByLastName(String lastName) {
		List<Customer> result = new ArrayList<Customer>();
		customerRepository.findByLastName(lastName).forEach(result::add);
		return result;
	}

	public Customer createCustomer(Customer customer) throws CustomerNotCreatedException{
		if (customer.getId() == null) {
			return customerRepository.save(customer);
		} else {
			throw new CustomerNotCreatedException("");
		}		
	}

	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException{
		if (customerRepository.existsById(customer.getId())) {
			return customerRepository.save(customer);
		} else {
			throw new CustomerNotFoundException("Customer id:" + customer.getId());
		}
	}

	public void deleteById(Long id) throws CustomerNotFoundException {
		if (customerRepository.existsById(id)) {
			customerRepository.deleteById(id);
		} else {
			throw new CustomerNotFoundException("Customer id:" + id);
		}
	}

	public void deleteByFirstName(String firstName) throws CustomerNotFoundException{
		List <Customer> customers = findByFirstName(firstName);
		if(!customers.isEmpty()) {
			customerRepository.deleteAll(customers);
		} else {
			throw new CustomerNotFoundException("Customer :" + firstName);
		}		
	}

	public void deleteByLastName(String lastName) throws CustomerNotFoundException{
		List <Customer> customers = findByLastName(lastName);
		if(!customers.isEmpty()) {
			customerRepository.deleteAll(customers);
		} else {
			throw new CustomerNotFoundException("Customer :" + lastName);
		}
	}

}
