package com.example.training.services;

import com.example.training.entities.Role;
import com.example.training.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class GroupUserDetails implements UserDetails {
    private int id;
    private String username;
    private String password;
    private String email;
    private Role roles;
    private List<SimpleGrantedAuthority> authorities;

   public GroupUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.authorities = Arrays.stream(roles.getName().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

//    public GroupUserDetails(
//        int id,String username, String email, String password,
//            Collection<? extends GrantedAuthority> authorities
//    ) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = (Collection<GrantedAuthority>) authorities;
//    }

//    public static GroupUserDetails build(User user) {
//
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//
//        return new GroupUserDetails(
//                user.getId(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getPassword(),
//                user.getRoles());
//    }
    //public GroupUserDetails(int id, String username, String email, String password, List<GrantedAuthority> authorities) {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(" inside group authorities " +authorities );
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
