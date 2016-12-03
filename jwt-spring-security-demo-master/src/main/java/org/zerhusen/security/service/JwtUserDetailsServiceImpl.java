package org.zerhusen.security.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerhusen.model.security.Authority;
import org.zerhusen.model.security.AuthorityName;
import org.zerhusen.model.security.User;
import org.zerhusen.security.JwtUserFactory;
import org.zerhusen.security.repository.UserRepository;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	//The user should be fetchedfrom the LDAP rather,hardcoding for now
    	User user = new User();
    	user.setUsername("ben");
    	user.setFirstname("BenH");
    	user.setLastname("AlexH");
    	Authority authority = new Authority();
    	authority.setName(AuthorityName.ROLE_ADMIN);
    	user.setAuthorities(Stream.of(authority).collect(Collectors.toList()));
    	user.setEnabled(Boolean.TRUE);

       // User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
