package com.czerwo.armybuilder.auth;

import com.czerwo.armybuilder.models.TokenRepository;
import com.czerwo.armybuilder.models.data.Token;
import com.czerwo.armybuilder.security.ApplicationUserRole;
import com.czerwo.armybuilder.services.MailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public ApplicationUserService(ApplicationUserRepository applicationUserRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.applicationUserRepository = applicationUserRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserRepository.getApplicationUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    public Optional<ApplicationUser> getUser(String username){
        return applicationUserRepository.getApplicationUserByUsername(username);
    }


    public void addUser(ApplicationUser applicationUser){
        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        applicationUser.setApplicationUseRole(ApplicationUserRole.USER);
        applicationUserRepository.save(applicationUser);
        sendToken(applicationUser);
    }

    private void sendToken(ApplicationUser applicationUser){
        String tokenValue = UUID.randomUUID().toString();

        Token token = new Token();
        token.setValue(tokenValue);
        token.setApplicationUser(applicationUser);
        tokenRepository.save(token);

        String url = "https://armybuilderapp.herokuapp.com/token?value=" + tokenValue;

        try {
            mailService.sendMail(applicationUser.getEmail(), "Potwierdź", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


}
