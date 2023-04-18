package com.example.training.filter;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.training.entities.User;
import com.example.training.jwtutility.JwtUtility;
import com.example.training.services.CustomUserDetailsService;
import com.example.training.services.GroupUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private CustomUserDetailsService service;

    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

        String username= null;
        String token = null;
     String authorizationHeader = request.getHeader("Authorization");
     if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
        token = authorizationHeader.substring(7);
        username = jwtUtility.extractUsername(token);
         System.out.println("token " + token);
         System.out.println("username " + username);
     }
     if(username != null && SecurityContextHolder.getContext().getAuthentication()== null){
        UserDetails userDetails = service.loadUserByUsername(username);
         System.out.println("userdetails - - " + userDetails );
        if(jwtUtility.validateToken(token, userDetails)){
            //UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails ,userDetails.getPassword(), userDetails.getAuthorities() );
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println("userdetails " + userDetails);
            System.out.println("userdetails password" + userDetails.getPassword());
            System.out.println("userdetails Authorities" + userDetails.getAuthorities().toString());
        }
     }
        filterChain.doFilter(request,response);
    }

/*
@Override
protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
    String username= null;
    String token = null;
    GroupUserDetails userDtls  = new GroupUserDetails();
    String authorizationHeader = request.getHeader("Authorization");
    if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
        token = authorizationHeader.substring(7);
        username = jwtUtility.extractUsername(userDtls.getUsername());
        System.out.println("username --- " +username);
    }
    if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
        UserDetails userDetails = service.loadUserByUsername(username);
        if(jwtUtility.validateToken(token, userDetails)){
            System.out.println("token --- line no 66" );
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDtls,null,userDtls.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            System.out.println("token --- line no 69" );

        }
    }
    filterChain.doFilter(request,response);
    System.out.println("line 74");
}
*/
}
