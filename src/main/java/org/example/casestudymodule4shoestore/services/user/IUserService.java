package org.example.casestudymodule4shoestore.services.user;

import org.example.casestudymodule4shoestore.models.login.AppUser;

import java.util.Optional;

public interface IUserService {
    void save(AppUser appUser);

    boolean userExists(String name);

}
