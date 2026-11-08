package fabrice.customerservice2.service;

import fabrice.customerservice2.dtos.CustomerDto;
import fabrice.customerservice2.entities.Customer;
import fabrice.customerservice2.exceptionHandling.CustomerException;
import fabrice.customerservice2.mapper.CustomerMapper;
import fabrice.customerservice2.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

   private CustomerRepository customerRepository;


   public CustomerService(CustomerRepository customerRepository) {

       this.customerRepository = customerRepository;

   }
   public List<Customer> getAllCustomers() {
       return customerRepository.findAll();
   }
   public CustomerDto getCustomerById(long id) throws CustomerException {
       if(id<0) throw new CustomerException("Customer id can't be negative");
       if (getAllCustomers().isEmpty() || ! customerRepository.findById(id).isPresent()) {
           throw new CustomerException("Customer with id " + id + " not found");
       }
       return CustomerMapper.toCustomerDto(customerRepository.findById(id).get());
   }
   public List<CustomerDto> getCustomerByName(String name) throws CustomerException {
       return  getAllCustomers().stream().map(CustomerMapper::toCustomerDto).filter(e->e.getFirstName().equals(name)|| e.getLastName().equals(name)).collect(Collectors.toList());
   }

   public void addCustomer(CustomerDto customerDto) throws CustomerException {
       if(customerDto==null) throw new CustomerException("Customer DTO can't be null");
       if(getAllCustomers().stream().map(CustomerMapper::toCustomerDto).anyMatch(e->e.getFirstName().equals(customerDto.getFirstName()) && e.getLastName().equals(customerDto.getLastName()))) {
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
