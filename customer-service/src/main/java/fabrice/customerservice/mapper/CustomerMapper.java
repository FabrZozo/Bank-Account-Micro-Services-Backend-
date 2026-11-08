package fabrice.customerservice.mapper;

import fabrice.customerservice.dtos.CustomerDto;
import fabrice.customerservice.entities.Customer;

public class CustomerMapper {


    public static CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder().firstName(customer.getFirstname()).lastName(customer.getLastname()).email(customer.getEmail()).build();
    }

    public static Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder().firstname(customerDto.getFirstName()).lastname(customerDto.getLastName()).email(customerDto.getEmail()).build();
    }
}
