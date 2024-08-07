package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token", columnDefinition = "VARCHAR(255)")
    private String token;
    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private AppUser appUser;
    private LocalDateTime expiryTime;
}
