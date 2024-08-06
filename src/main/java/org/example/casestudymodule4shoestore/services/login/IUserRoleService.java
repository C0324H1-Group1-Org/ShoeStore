package org.example.casestudymodule4shoestore.services.login;

import org.example.casestudymodule4shoestore.models.AppRole;
import org.example.casestudymodule4shoestore.models.AppUser;

public interface IUserRoleService {
    void save(AppUser appUser, AppRole appRole);
}
