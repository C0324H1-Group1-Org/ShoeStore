package org.example.casestudymodule4shoestore.repositories.login;

import org.example.casestudymodule4shoestore.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
