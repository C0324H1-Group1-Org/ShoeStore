package org.example.casestudymodule4shoestore.dtos.login;

import lombok.Getter;
import lombok.Setter;
import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.Customer;
import org.example.casestudymodule4shoestore.models.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserInfoUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean enabled;
    private Customer customer;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(AppUser appUser, List<UserRole> userRoles,Customer customer) {
        username = appUser.getUserName();
        password = appUser.getEncrytedPassword();
        enabled = appUser.isEnabled();
        this.customer = customer;
        authorities = new ArrayList<>();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getAppRole().getRoleName());
                authorities.add(authority);
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
