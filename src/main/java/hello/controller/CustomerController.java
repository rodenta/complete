package hello.controller;


//import hello.model.Customer;
import hello.dto.CustomerDto;
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
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    CustomerMapper customerMapper;


    @GetMapping("/")
    public List<CustomerDto> hello() {
        return Collections.emptyList();
    }
    
    // All logic has to be implemented in service!

    // Create GET method that retrieves all customers
    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
    	try {
    		List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
        	customersDto = customerMapper.entityListToDto(customerService.getAllCustomers());
        	if (customersDto.isEmpty()) {
        		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        	}        		
        	return new ResponseEntity<>(customersDto, HttpStatus.OK);
        	}catch (Exception e) {
        		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    	
    }
    
    //Create GET method that gets customer by its ID
    
    @GetMapping("/{id}")
    public CustomerDto findById(@PathVariable("id") Long id) {
        CustomerDto customerDto = customerMapper.entityToDto(customerService.findById(id));
        return customerDto;
    }

    // Create GET method that gets customer by search key (name, surname, etc.)
    
    @GetMapping("/findByFirstName/{first-name}")
    @ResponseBody
    public List<CustomerDto> findByFirstName (@RequestParam(value = "first-name", required = false) String firstName, HttpServletRequest request, HttpServletResponse response) {
	     List<CustomerDto> customersDto = customerMapper.entityListToDto(customerService.findByFirstName(firstName));
	     return customersDto;
	}
    
    @GetMapping("/findByLastName/{lastname-name}")
    @ResponseBody
    public List<CustomerDto> findByLastName (@RequestParam(value = "last-name", required = false) String lastName, HttpServletRequest request, HttpServletResponse response) {
	     List<CustomerDto> customersDto = customerMapper.entityListToDto(customerService.findByLastName(lastName));
	     return customersDto;
	}
    
    // Create POST method to saves new customer

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            Customer cust = customerService.addCustomer(new Customer(customerDto.getFirstName(), customerDto.getLastName()));
            return new ResponseEntity<>(customerMapper.entityToDto(cust), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    // Create PUT method to update existing customer. Note! If user tries to update not existing customer, throw an exception
  
    @PutMapping("/")
    public ResponseEntity<CustomerDto> putCustomer(@RequestBody CustomerDto customerDto) throws Exception {
        Customer cust = customerService.findById(customerDto.getId());
        if (cust != null) {
        	CustomerDto custDto = customerMapper.entityToDto(customerService.updateCustomer(cust.getId(), cust));
        	return new ResponseEntity<>(custDto, HttpStatus.OK);
        	} else {
            throw new Exception("Customer doesn't exist.");
        }
    }
    
    // Create DELETE method that deletes customer by id
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
    	try {
    		customerMapper.entityToDto(customerService.deleteById(id));
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    // Create DELETE method that deletes customer by any other key
    
    @DeleteMapping("/deleteByFirstName/{first-name}")
    public ResponseEntity<HttpStatus> deleteByFirstName(@PathVariable("first-name") String firstName) {
    	try {
    		customerMapper.entityToDto(customerService.deleteByFirstName(firstName));
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    

}
