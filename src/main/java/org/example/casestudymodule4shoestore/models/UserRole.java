package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "user_id", "role_id" }) })
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private AppRole appRole;

    public UserRole(AppUser appUser, AppRole appRole) {
        this.appUser = appUser;
        this.appRole = appRole;
    }

    public UserRole() {
    }
}