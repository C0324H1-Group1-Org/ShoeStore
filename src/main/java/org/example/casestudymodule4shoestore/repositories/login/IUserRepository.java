package org.example.casestudymodule4shoestore.repositories.login;


import org.example.casestudymodule4shoestore.models.login.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String username);
}