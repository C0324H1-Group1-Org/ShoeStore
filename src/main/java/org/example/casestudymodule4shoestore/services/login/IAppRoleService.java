package org.example.casestudymodule4shoestore.services.login;

import org.example.casestudymodule4shoestore.models.AppRole;

public interface IAppRoleService {
    AppRole findByRoleName(String roleName);
}
