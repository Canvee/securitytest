package com.ZTI2018.securitytest.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ZTI2018.securitytest.models.AppUser;
import com.ZTI2018.securitytest.models.AppUserDTO;
import com.ZTI2018.securitytest.models.UserAuthority;
import com.ZTI2018.securitytest.repositories.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class RegistrationController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
    public ResponseEntity<?> signOff() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody AppUserDTO userdto) {
		
		if (userRepository.existsByUsername( userdto.getUsername() )) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }
		if (userRepository.existsByMail(userdto.getMail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
		AppUser user = new AppUser();
		//user.setId(3);
        user.setMail(userdto.getMail());
        user.setUsername(userdto.getUsername());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        
        List<UserAuthority> authorities = new ArrayList<>();
        UserAuthority authority = new UserAuthority("ROLE_USER");
        authority.setAppuser(user);
        authorities.add(authority);
        user.setAuthorities(authorities);
        
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());
        user.setLastLogin(currentTimestamp);
        
        userRepository.save(user);

        return new ResponseEntity<>("User created",HttpStatus.OK);
    }
}
