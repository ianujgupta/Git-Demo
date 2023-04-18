package com.example.training.services;

import com.example.training.entities.User;
import com.example.training.exceptions.UserNotFoundException;
import com.example.training.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public CustomUserDetailsService(){
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("inside loadUserByUsername");

        //User user = repos.findByUsername(username);
        //Optional<User> user = repos.findByUsername(username);
       /* User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }*/
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User Not Found with username" + username));
        //return user.map(GroupUserDetails::new).get();

        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        System.out.println(" in Cust sevice userdetails " + user.getUsername());
        System.out.println("userdetails password" + user.getPassword());
        System.out.println("userdetails Authorities" + user.getRoles());
        System.out.println("userdetails Authorities" + user.getRoles().toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getRoles());
    }
}
