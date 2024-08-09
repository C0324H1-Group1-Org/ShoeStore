package org.example.casestudymodule4shoestore.services.login;


import org.example.casestudymodule4shoestore.models.AppUser;
import org.example.casestudymodule4shoestore.models.VerificationToken;
import org.example.casestudymodule4shoestore.repositories.login.IUserRepository;
import org.example.casestudymodule4shoestore.repositories.login.IVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.UUID;

@Service
public class UserService implements IUserService {
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
        String message = "<html><head><style>" +
                "body {font-family: Arial, sans-serif;}" +
                ".container {width: 100%; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}" +
                ".header {text-align: center; background-color: #f44336; color: white; padding: 20px 0;}" +
                ".header img {width: 50px;}" +
                ".content {text-align: center; padding: 20px;}" +
                ".content h1 {margin-top: 0;}" +
                ".button {display: inline-block; padding: 15px 25px; background-color: #f44336; color: white; text-decoration: none; border-radius: 5px; margin-top: 20px;}" +
                ".footer {text-align: center; padding: 20px; font-size: 14px; color: #888;}" +
                ".footer img {width: 25px; margin: 0 5px;}" +
                ".footer a {color: #888; text-decoration: none;}" +
                "</style></head><body>" +
                "<div class=\"container\">" +
                "<div class=\"header\"></div>" +
                "<div class=\"content\">" +
                "<h1>Xác thực Email</h1>" +
                "<p>Chào " + user.getUserName() + ",</p>" +
                "<p>Chào mừng bạn đến với Shoe Store. Chỉ cần nhấp vào nút bên dưới để xác minh địa chỉ email của bạn. Liên kết sẽ hết hạn sau 24 giờ.</p>" +
                "<a href=\"" + confirmationUrl + "\" class=\"button\">Xác minh địa chỉ email của tôi</a>" +
                "</div>" +
                "<div class=\"footer\">" +
                "<p>Nhà số 23, Lô TT-01, Khu đô thị MonCity, P. Hàm Nghi, Hà Nội</p>" +
                "</div>" +
                "</div></body></html>";
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
}
