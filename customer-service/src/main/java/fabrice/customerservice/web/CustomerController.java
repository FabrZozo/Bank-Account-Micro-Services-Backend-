package fabrice.customerservice.web;

import fabrice.customerservice.dtos.CustomerDto;
import fabrice.customerservice.exceptionHandling.CustomerException;
import fabrice.customerservice.service.CustomerService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Customers")
public class CustomerController {


    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/getCustomerById/{id}")
    public  ResponseEntity<CustomerDto> getCustomerById(@PathVariable long id) throws CustomerException {
       return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @GetMapping("/getCustomerByName/{name}")
    public  ResponseEntity<List<CustomerDto>> getCustomerByName(@PathVariable String name) throws CustomerException{
        return ResponseEntity.ok(customerService.getCustomerByName(name));
    }
    @PostMapping("/addCustomer")
    public ResponseEntity<Void> addCustomer(@RequestBody CustomerDto customerDto) throws CustomerException{
        customerService.addCustomer(customerDto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) throws CustomerException{
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id ,@RequestBody CustomerDto customerDto) throws CustomerException{
        CustomerDto customerDto1= customerService.updateCustomer(id,customerDto);
        return ResponseEntity.ok(customerDto1);
    }
}
