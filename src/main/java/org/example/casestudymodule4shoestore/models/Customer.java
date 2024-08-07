package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;
    private String email;

    @OneToOne
    @JoinColumn(name="user_id")
    private AppUser appUser;
}



