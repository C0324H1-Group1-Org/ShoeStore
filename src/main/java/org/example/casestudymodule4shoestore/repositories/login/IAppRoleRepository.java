package org.example.casestudymodule4shoestore.repositories.login;

import org.example.casestudymodule4shoestore.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findAppRoleByRoleName(String roleName);
}
