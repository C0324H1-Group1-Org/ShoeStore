package org.example.casestudymodule4shoestore.services.login;

import org.example.casestudymodule4shoestore.models.AppRole;
import org.example.casestudymodule4shoestore.repositories.login.IAppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppRoleService implements IAppRoleService{

    @Autowired
    private IAppRoleRepository appRoleRepository;

    @Override
    public AppRole findByRoleName(String roleName) {
        return appRoleRepository.findAppRoleByRoleName(roleName);
    }
}
