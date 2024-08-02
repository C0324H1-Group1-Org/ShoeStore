package org.example.casestudymodule4shoestore.services.user;

import org.example.casestudymodule4shoestore.models.login.AppUser;
import org.example.casestudymodule4shoestore.repositories.login.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void save(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public boolean userExists(String name) {
        return userRepository.findByUserName(name) != null;
    }
}
