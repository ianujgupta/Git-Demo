package com.example.training.security;

import com.example.training.filter.JwtFilter;
import com.example.training.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

import static org.hibernate.criterion.Restrictions.and;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService){
        this.userDetailsService= userDetailsService;
    }
    //Authentication
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin();
        http.authorizeRequests().mvcMatchers(HttpMethod.POST, "/login", "/users/register").permitAll()
//        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/products").hasAnyRole("USER", "ADMIN")//.hasAnyAuthority("ROLE_ADMIN")
//                .mvcMatchers(HttpMethod.GET, "/products/**").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.PUT, "/products/update").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
//                .mvcMatchers(HttpMethod.POST, "/products/add").hasRole("ADMIN").anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors(corsCustomizer -> {
            CorsConfigurationSource configurationSource = request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedMethods(List.of("GET", "POST"));
                corsConfiguration.setAllowedOrigins(List.of("localhost:4200"));
                return corsConfiguration;
            };
            corsCustomizer.configurationSource(configurationSource);
        });
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//              .authorizeRequests().antMatchers("/api/auth/**" ,"/users/register" ).permitAll().and()
//              .authorizeRequests().antMatchers("/products/**").authenticated()//.hasAnyRole("ADMIN","USER")
//                .and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .authorizeRequests().antMatchers(HttpMethod.PUT,"/products/update").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
//                .antMatchers("/products").hasAnyRole()
//                .antMatchers("/users/**").permitAll()
//                //.authorizeHttpRequests().antMatchers("/products/add","/products/update" , "/products/deletebyid/**").hasAnyAuthority("ADMIN")
//                //.antMatchers("/products/**").hasAnyAuthority("ADMIN","USER").anyRequest().authenticated()
//                //.and().authorizeRequests().antMatchers("/products/add").hasAnyRole("ADMIN")
//                .and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//    //Authorization
 /*   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.authorizeHttpRequests().antMatchers("/user/**").hasAnyRole("ADMIN","USER")
                .and().authorizeHttpRequests().antMatchers("/authenticate").permitAll()
                .and().authorizeHttpRequests().antMatchers("/").permitAll()
                .anyRequest()
                .authenticated().and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
*/

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//               http.authorizeHttpRequests().antMatchers("/api/auth/**" ,"users/register").permitAll()
//                       .and().authorizeHttpRequests().antMatchers("/products/**").permitAll().anyRequest().authenticated()
//                       .and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

        @Bean
        public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    public Authentication authenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        return authenticationProvider().setAuserDetailsService);
//
//    }

}
