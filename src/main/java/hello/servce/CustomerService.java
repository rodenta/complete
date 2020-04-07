package hello.servce;

import hello.model.Customer;
import hello.exception.CustomerNotFoundException;
import hello.mapper.CustomerMapper;
import hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	// TODO: Implement methods for each controller method. Note that each of them
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
/*
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Long id, Customer customer) {

		customerRepository.deleteById(id);
		return customerRepository.save(customer);
	}

	public Customer deleteById(Long id) {
		if (customerRepository.findById(id) != null) {
			return customerRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("Customer not found, id: " + id);
		}
	}

	public Customer deleteByFirstName(String firstName) {
		if (customerRepository.findByFirstName(firstName) != null) {
			return customerRepository.deleteByFirstName(firstName);
		} else {
			throw new IllegalArgumentException("Customer " + firstName + " not found.");
		}
	}

	public Customer deleteByLastName(String lastName) {
		if (customerRepository.findByFirstName(lastName) != null) {
			return customerRepository.deleteByFirstName(lastName);
		} else {
			throw new IllegalArgumentException("Customer " + lastName + " not found.");
		}
	}
*/
}
