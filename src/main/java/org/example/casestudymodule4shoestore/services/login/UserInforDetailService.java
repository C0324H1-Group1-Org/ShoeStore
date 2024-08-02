package org.example.casestudymodule4shoestore.services.login;

import org.example.casestudymodule4shoestore.dtos.login.UserInfoUserDetails;
import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.UserRole;
import org.example.casestudymodule4shoestore.repositories.login.IUserRepository;
import org.example.casestudymodule4shoestore.repositories.login.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInforDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUserName(username);
//        Lấy tất cả role của AppUser
        List<UserRole> userRoles = userRoleRepository.findAllByAppUser(appUser);
        UserInfoUserDetails infoUserDetails = new UserInfoUserDetails(appUser, userRoles);
        return infoUserDetails;
    }
}
