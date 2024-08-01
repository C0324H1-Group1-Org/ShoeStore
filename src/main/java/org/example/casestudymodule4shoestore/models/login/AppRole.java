package org.example.casestudymodule4shoestore.models.login;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "app_role",
        uniqueConstraints = {
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "role_name") })
public class AppRole {

    //    ROLE_ADMIN
//    ROLE_USER
    @Id
    @GeneratedValue
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "role_name", length = 30, nullable = false)
    private String roleName;

}