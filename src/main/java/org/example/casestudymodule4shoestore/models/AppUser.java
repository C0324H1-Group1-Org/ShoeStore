package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "user_name") })
public class AppUser {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", length = 36, nullable = false)
    private String userName;

    @Column(name = "encryted_password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;

}
