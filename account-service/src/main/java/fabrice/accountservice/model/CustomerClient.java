package fabrice.accountservice.model;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="Customer-service2")
public interface CustomerClient {
    @CircuitBreaker(name= "customerService",fallbackMethod = "getDefaultCustomer")
    @GetMapping("Customers/getCustomerById/{id}")
    Customer getCustomerById(@PathVariable Long id);

    @GetMapping("Customers/getAllCustomers")
    List<Customer> getListCustomers();

    default Customer getDefaultCustomer(Long id, Exception ex){
       Customer customer = new Customer();
       customer.setId(id);
       customer.setFirstName("Not available");
       customer.setLastName("Not available");
       customer.setEmail("Not available");
       return customer;
    }
}
