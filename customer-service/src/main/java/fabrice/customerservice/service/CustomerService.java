package fabrice.customerservice.service;

import fabrice.customerservice.dtos.CustomerDto;
import fabrice.customerservice.entities.Customer;
import fabrice.customerservice.exceptionHandling.CustomerException;
import fabrice.customerservice.mapper.CustomerMapper;
import fabrice.customerservice.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

   private CustomerRepository customerRepository;

   public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
   }
   public List<CustomerDto> getAllCustomers() {
       return customerRepository.findAll().stream().map(CustomerMapper::toCustomerDto).collect(Collectors.toList());
   }
   public CustomerDto getCustomerById(long id) throws CustomerException {
       if(id<0) throw new CustomerException("Customer id can't be negative");
       if (getAllCustomers().isEmpty() || ! customerRepository.findById(id).isPresent()) {
           throw new CustomerException("Customer with id " + id + " not found");
       }
       return CustomerMapper.toCustomerDto(customerRepository.findById(id).get());
   }
   public List<CustomerDto> getCustomerByName(String name) throws CustomerException {
       return  getAllCustomers().stream().filter(e->e.getFirstName().equals(name)|| e.getLastName().equals(name)).collect(Collectors.toList());
   }

   public void addCustomer(CustomerDto customerDto) throws CustomerException {
       if(customerDto==null) throw new CustomerException("Customer DTO can't be null");
       if(getAllCustomers().stream().anyMatch(e->e.getFirstName().equals(customerDto.getFirstName()) && e.getLastName().equals(customerDto.getLastName()))) {
           throw new CustomerException("Customer already exists");
       }
       customerRepository.save(CustomerMapper.toCustomer(customerDto));
   }

   public void deleteCustomer(long id) throws CustomerException {

       Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerException("Customer with id " + id + " not found"));

       customerRepository.delete(customer);
   }

   public CustomerDto updateCustomer(long id ,CustomerDto customerDto) throws CustomerException {

       if (customerDto == null) {
           throw new CustomerException("Customer DTO can't be null");
       }
       Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerException("Customer with id " + id + " not found"));
       customer.setFirstname(customerDto.getFirstName());
       customer.setLastname(customerDto.getLastName());
       customer.setEmail(customerDto.getEmail());

       customerRepository.save(customer);
       return  CustomerMapper.toCustomerDto(customer);
   }

}
