package com.ZTI2018.securitytest.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.ZTI2018.securitytest.models.AppUser;
import com.ZTI2018.securitytest.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private WebApplicationContext applicationContext;
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService() {
        super();
    }

    @PostConstruct
    public void completeSetup() {
        userRepository = applicationContext.getBean(UserRepository.class);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AppUserDetails(appUser);
    }
    
    public boolean saveUser(String mail, String username, String password)
    {
    	AppUser newUser = new AppUser();
    	newUser.setMail(mail);
    	newUser.setUsername(username);
    	newUser.setPassword(passwordEncoder.encode(password));
    	userRepository.save(newUser);
    	return true;
    }
}
