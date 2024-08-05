package org.example.casestudymodule4shoestore.dtos.customer;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.casestudymodule4shoestore.models.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NotBlank(message = "Tên bắt buộc")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Chưa đúng định dạng")
    private String lastName;

    @NotBlank(message = "Họ bắt buộc")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Chưa đúng định dạng")
    private String firstName;

    @NotNull(message = "Giới tính bắt buộc")
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.MALE;

    @NotBlank(message = "Số điện thoại bắt buộc")
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại không hợp lệ")
    private String phone;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email bắt buộc")
    private String email;

    @NotBlank(message = "Mật khẩu bắt buộc")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!.]).{8,}$", message = "Mật khẩu không hợp lệ")
    private String password;

    private boolean enabled;
}
