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

<<<<<<< HEAD

}
=======
    @OneToOne
    @JoinColumn(name="user_id")
    private AppUser appUser;
}


>>>>>>> 73754e9580eac9bd18a457a02a0f2af790b9374b
