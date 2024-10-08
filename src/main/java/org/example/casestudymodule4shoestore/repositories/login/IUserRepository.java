package org.example.casestudymodule4shoestore.repositories.login;


import org.example.casestudymodule4shoestore.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUserName(String username);
}