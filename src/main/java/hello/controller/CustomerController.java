package hello.controller;

import hello.dto.CustomerDto;
import hello.exception.CustomerNotCreatedException;
import hello.exception.CustomerNotFoundException;
import hello.mapper.CustomerMapper;
import hello.model.Customer;
import hello.servce.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
// @RequestMapping("customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerMapper customerMapper;

	@GetMapping("/")
	public List<CustomerDto> hello() {
		return Collections.emptyList();
	}

	// All logic has to be implemented in service!

	// Create GET method that retrieves all customers
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		if (!customers.isEmpty()) {
			return new ResponseEntity<>(customerMapper.entityListToDto(customers), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	// Create GET method that gets customer by its ID
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDto> findById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(customerMapper.entityToDto(customerService.findById(id)), HttpStatus.ACCEPTED);
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Create GET method that gets customer by search key (name, surname, etc.)

	@GetMapping("/customers/findByFirstName/{first-name}")
	public ResponseEntity<List<CustomerDto>> findByFirstName(@PathVariable("first-name") String firstName) {
		List<Customer> customers = customerService.findByFirstName(firstName);
		if (!customers.isEmpty()) {
			return new ResponseEntity<>(customerMapper.entityListToDto(customers), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/customers/findByLastName/{last-name}")
	public ResponseEntity<List<CustomerDto>> findByLastName(@PathVariable("last-name") String lastName) {
		List<Customer> customers = customerService.findByLastName(lastName);
		if (!customers.isEmpty()) {
			return new ResponseEntity<>(customerMapper.entityListToDto(customers), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Create POST method to saves new customer

	@PostMapping("/customers")
	public ResponseEntity<HttpStatus> createCustomer(@RequestBody CustomerDto customerDto) {
		try {
			customerService.createCustomer(customerMapper.dtoToEntity(customerDto));
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (CustomerNotCreatedException e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Create PUT method to update existing customer. Note! If user tries to update
	// not existing customer, throw an exception

	@PutMapping("/customers")
	public ResponseEntity<HttpStatus> putCustomer(@RequestBody CustomerDto customerDto) { 
		try {
			Customer customer = customerMapper.dtoToEntity(customerDto);
			customerService.updateCustomer(customer);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	

	// Create DELETE method that deletes customer by id

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
		try {
			customerService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (CustomerNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Create DELETE method that deletes customer by any other key

	@DeleteMapping("/customers/deleteByFirstName/{first-name}")
	public ResponseEntity<HttpStatus> deleteByFirstName(@PathVariable("first-name") String firstName) {
		try {
			customerService.deleteByFirstName(firstName);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (CustomerNotFoundException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/customers/deleteByLastName/{last-name}")
	public ResponseEntity<HttpStatus> deleteByLastName(@PathVariable("last-name") String lastName) {
		try {
			customerService.deleteByLastName(lastName);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (CustomerNotFoundException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
