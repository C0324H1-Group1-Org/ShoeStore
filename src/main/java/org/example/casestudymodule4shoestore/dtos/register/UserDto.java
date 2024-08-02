package org.example.casestudymodule4shoestore.dtos.register;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "Tài khoản không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Tài khoản chưa đúng định dạng")
    private String name;

    @NotNull(message = "Mật khẩu không được để trống")
    @Size(min = 5, max = 45, message = "Độ dài tối thiểu 5, tối đa 45 ký tự")
    private String password;

    private boolean enabled;
}
