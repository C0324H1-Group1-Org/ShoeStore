package org.example.casestudymodule4shoestore.services.customer;

import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.Customer;
import org.example.casestudymodule4shoestore.repositories.customer.ICustomerRepo;
import org.example.casestudymodule4shoestore.repositories.login.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICustomerRepo customerRepo;


    @Override
    public void saveInfoCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public void saveAccountCustomer(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public boolean phoneAndEmailExists(String email, String phone) {
        return customerRepo.findCustomerByEmailOrPhone(email,phone) != null;
    }

    public Customer findCustomerByUserName(String username){
        return customerRepo.findCustomerByEmail(username);
    }



}
