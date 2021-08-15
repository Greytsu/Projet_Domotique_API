package com.example.ProjetDomotiqueAPI.security.authentification;

import com.example.ProjetDomotiqueAPI.models.utilisateur.UtilisateurService;
import com.example.ProjetDomotiqueAPI.security.MyUserDetailsService;
import com.example.ProjetDomotiqueAPI.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ProjetDomotique/api/v1/authentification")
public class AuthentificationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UtilisateurService utilisateurService;


    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthentificationReq authenticationRequest) throws Exception{

        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        String username = authenticationRequest.getLogin();

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthentificationRep(jwt, utilisateurService.findUserByUsername(username).orElse(null)));
    }

    @GetMapping
    public String HelloRessource(){
        return "Hello World !!";
    }

}
