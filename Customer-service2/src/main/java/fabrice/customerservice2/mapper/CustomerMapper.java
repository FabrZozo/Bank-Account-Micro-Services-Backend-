package fabrice.customerservice2.mapper;

import fabrice.customerservice2.dtos.CustomerDto;
import fabrice.customerservice2.entities.Customer;

public class CustomerMapper {


    public static CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder().firstname(customer.getFirstname()).lastname(customer.getLastname()).email(customer.getEmail()).build();
    }

    public static Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder().firstname(customerDto.getFirstname()).lastname(customerDto.getLastname()).email(customerDto.getEmail()).build();
    }
}
