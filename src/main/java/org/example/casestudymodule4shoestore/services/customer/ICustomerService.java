package org.example.casestudymodule4shoestore.services.customer;

import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.Customer;

public interface ICustomerService {

    void saveInfoCustomer(Customer customer);

    void saveAccountCustomer(AppUser appUser);

    boolean phoneAndEmailExists(String email, String phone);
}
