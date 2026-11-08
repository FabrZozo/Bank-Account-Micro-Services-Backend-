package fabrice.customerservice2.repository;

import fabrice.customerservice2.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
