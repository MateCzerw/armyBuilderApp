package com.czerwo.armybuilder.security;

import com.czerwo.armybuilder.models.AdminRepository;
import com.czerwo.armybuilder.models.UserRepository;
import com.czerwo.armybuilder.models.data.Admin;
import com.czerwo.armybuilder.models.data.User;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    public UserRepositoryUserDetailsService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        Admin admin = adminRepository.findByUsername(username);

        if(user != null) return user;

        if(admin != null) return admin;



        throw new UsernameNotFoundException("User: " + username + " not found!");
    }
}
