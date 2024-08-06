package org.example.casestudymodule4shoestore.services.login;

import org.example.casestudymodule4shoestore.models.AppRole;
import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.UserRole;
import org.example.casestudymodule4shoestore.repositories.login.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements IUserRoleService{

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Override
    public void save(AppUser appUser, AppRole appRole) {
        userRoleRepository.save(new UserRole(appUser,appRole));
    }
}
