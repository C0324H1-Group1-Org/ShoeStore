package org.example.casestudymodule4shoestore.repositories.login;

import org.example.casestudymodule4shoestore.models.login.AppUser;
import org.example.casestudymodule4shoestore.models.login.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByAppUser(AppUser appUser);
}