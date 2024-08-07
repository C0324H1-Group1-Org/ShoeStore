package org.example.casestudymodule4shoestore.services.login;

import org.example.casestudymodule4shoestore.dtos.customer.CustomerDto;
import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.VerificationToken;
import org.example.casestudymodule4shoestore.repositories.login.IUserRepository;
import org.example.casestudymodule4shoestore.repositories.login.IVerificationTokenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.UUID;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IVerificationTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;


    public void registerUser(AppUser user) {
        String token = UUID.randomUUID().toString();
        createVerificationToken(user, token);
        sendVerificationEmail(user, token);
    }

    private void createVerificationToken(AppUser user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setAppUser(user);
        verificationToken.setExpiryTime(calculateExpiryDate(60 * 24)); // 24 hours
        tokenRepository.save(verificationToken);
    }

    private void sendVerificationEmail(AppUser user, String token) {
        String subject = "Hoàn tất đăng ký";
        String confirmationUrl = "http://localhost:8080/confirm?token=" + token;
        String message = "Để xác nhận tài khoản của bạn, vui lòng nhấp vào đây: " + confirmationUrl;
        emailService.sendEmail(user.getUserName(), subject, message);
    }

    public void confirmUser(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken != null) {
            AppUser user = verificationToken.getAppUser();
            user.setEnabled(true);
            userRepository.save(user);
        }
    }

    private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return LocalDateTime.ofInstant(cal.toInstant(), ZoneId.systemDefault());
    }

    @Override
    public void verifyUser(AppUser user) {
        userRepository.save(user);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
