package hello.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import hello.model.Customer;
import hello.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
	
	@Autowired
    private ModelMapper modelMapper;

    // Implement method that maps entity to dto
	
	public CustomerDto entityToDto (Customer customer){
		
		modelMapper = new ModelMapper();
		CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class); 
		return customerDto;
	}

    // Implement method that maps dto to entity

	public Customer dtoToEntity (CustomerDto customerDto) {
		
		modelMapper = new ModelMapper();
		Customer customer = modelMapper.map(customerDto, Customer.class); 
		return customer;
	}
	
    // Implement method that maps List of entities to List of dto
	
	public List<CustomerDto> entityListToDto (List<Customer> customers){
		
		List<CustomerDto> customersDto = new ArrayList<CustomerDto>();
		for (Customer customer : customers) {
			customersDto.add(entityToDto(customer));
		}
		return customersDto;
	}

    // Implement method that maps List of dto to List of entities
	
	public List<Customer> dtoListToEntity (List<CustomerDto> customersDto){
		
		List<Customer> customers = new ArrayList<Customer>();
		for (CustomerDto customerDto : customersDto) {
			customers.add(dtoToEntity(customerDto));
		}
		return customers;
	}

    //TODO: Implement method that updates entity with dto data
	
	
}
