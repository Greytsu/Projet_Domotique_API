package com.example.ProjetDomotiqueAPI.security;

import com.example.ProjetDomotiqueAPI.models.utilisateur.Utilisateur;
import com.example.ProjetDomotiqueAPI.models.utilisateur.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public MyUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optUtilisateur = utilisateurRepository.findByUsername(username);
        if(optUtilisateur.isPresent()){
            Utilisateur user = optUtilisateur.get();

            System.out.println(user.getU_Login());
            System.out.println(user.getRole());

            UserDetails userDetails = User.builder()
                    .username(user.getU_Login())
                    .password(user.getU_Password())
                    .roles(user.getRole().name())
                    .authorities(user.getRole().getGrantedAuthorities())
                    .build();

            return userDetails;

        }
        throw new UsernameNotFoundException("Username not found : " + username);
    }
}
