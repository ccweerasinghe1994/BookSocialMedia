package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    @Value("${application.mailing.frontend}")
    private String activationUrl;


    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role user was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Account Activation"
        );
        
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }
}
