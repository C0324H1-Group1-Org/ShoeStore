package org.example.casestudymodule4shoestore.repositories.login;

import org.example.casestudymodule4shoestore.models.AppRole;
import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;


public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByAppUser(AppUser appUser);
}