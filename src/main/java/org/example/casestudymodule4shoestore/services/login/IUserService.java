package org.example.casestudymodule4shoestore.services.login;

import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.VerificationToken;

public interface IUserService {
    void verifyUser(AppUser user);
    VerificationToken getVerificationToken(String token);
}
