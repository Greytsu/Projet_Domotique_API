package com.example.ProjetDomotiqueAPI.security;

import com.example.ProjetDomotiqueAPI.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.ProjetDomotiqueAPI.security.ApplicationUserPermission.*;
import static com.example.ProjetDomotiqueAPI.security.ApplicationUserRole.*;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/ProjetDomotique/api/v1/authentification/**").permitAll()

                //utilisateur
                //.antMatchers("/ProjetDomotique/admin/**").hasAnyRole(SUPER_ADMIN.name(),ADMIN.name())
                .antMatchers(HttpMethod.GET, "/ProjetDomotique/admin/api/v1/utilisateur/**").hasAnyAuthority(UTILISATEUR_WRITE.getPermissions())
                //.antMatchers(HttpMethod.POST, "/ProjetDomotique/admin/api/v1/utilisateur/**").hasAnyAuthority(UTILISATEUR_WRITE.getPermissions())
                .antMatchers(HttpMethod.POST, "/ProjetDomotique/admin/api/v1/utilisateur/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/ProjetDomotique/admin/api/v1/utilisateur/**").hasAnyAuthority(UTILISATEUR_WRITE.getPermissions())
                .antMatchers(HttpMethod.DELETE, "/ProjetDomotique/admin/api/v1/utilisateur/**").hasAnyAuthority(UTILISATEUR_WRITE.getPermissions())

                //appareil
                .antMatchers(HttpMethod.GET, "/ProjetDomotique/api/v1/appareil/**").hasAnyAuthority(APPAREIL_READ.getPermissions())
                .antMatchers(HttpMethod.POST, "/ProjetDomotique/api/v1/appareil/**").hasAnyAuthority(APPAREIL_WRITE.getPermissions())
                .antMatchers(HttpMethod.PUT, "/ProjetDomotique/api/v1/appareil/**").hasAnyAuthority(APPAREIL_WRITE.getPermissions())
                .antMatchers(HttpMethod.DELETE, "/ProjetDomotique/api/v1/appareil/**").hasAnyAuthority(APPAREIL_WRITE.getPermissions())

                //autorisations
                .antMatchers(HttpMethod.GET, "/ProjetDomotique/api/v1/autorisations/**").hasAnyAuthority(AUTORISATION_READ.getPermissions())
                .antMatchers(HttpMethod.POST, "/ProjetDomotique/api/v1/autorisations/**").hasAnyAuthority(AUTORISATION_WRITE.getPermissions())
                .antMatchers(HttpMethod.PUT, "/ProjetDomotique/api/v1/autorisations/**").hasAnyAuthority(AUTORISATION_WRITE.getPermissions())
                .antMatchers(HttpMethod.DELETE, "/ProjetDomotique/api/v1/autorisations/**").hasAnyAuthority(AUTORISATION_WRITE.getPermissions())

                //donneeReference
                .antMatchers(HttpMethod.GET, "/ProjetDomotique/api/v1/donneeReference/**").hasAnyAuthority(DONNEE_REF_READ.getPermissions())
                .antMatchers(HttpMethod.POST, "/ProjetDomotique/api/v1/donneeReference/**").hasAnyAuthority(DONNEE_REF_WRITE.getPermissions())
                .antMatchers(HttpMethod.PUT, "/ProjetDomotique/api/v1/donneeReference/**").hasAnyAuthority(DONNEE_REF_WRITE.getPermissions())
                .antMatchers(HttpMethod.DELETE, "/ProjetDomotique/api/v1/donneeReference/**").hasAnyAuthority(DONNEE_REF_WRITE.getPermissions())

                //piece
                .antMatchers(HttpMethod.GET, "/ProjetDomotique/api/v1/piece/**").hasAnyAuthority(PIECE_READ.getPermissions())
                .antMatchers(HttpMethod.POST, "/ProjetDomotique/api/v1/piece/**").hasAnyAuthority(PIECE_WRITE.getPermissions())
                .antMatchers(HttpMethod.PUT, "/ProjetDomotique/api/v1/piece/**").hasAnyAuthority(PIECE_WRITE.getPermissions())
                .antMatchers(HttpMethod.DELETE, "/ProjetDomotique/api/v1/piece/**").hasAnyAuthority(PIECE_WRITE.getPermissions())

                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic();

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
