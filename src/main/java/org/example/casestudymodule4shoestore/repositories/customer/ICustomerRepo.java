package org.example.casestudymodule4shoestore.repositories.customer;

import org.example.casestudymodule4shoestore.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer,Long> {
    Customer findCustomerByEmailOrPhone(String email, String phone);
    Customer findCustomerByEmail(String email);
}
