package com.xim.ourcloset.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xim.ourcloset.dto.RegisterDto;
import com.xim.ourcloset.models.AuthenticationResponse;
import com.xim.ourcloset.models.LoginModel;
import com.xim.ourcloset.models.Role;
import com.xim.ourcloset.models.User;
import com.xim.ourcloset.repositories.LoginDAO;
import com.xim.ourcloset.repositories.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserDAO userDAO;

    private final LoginDAO loginRep;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    ObjectMapper objectMapper;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserDAO userDAO, LoginDAO userRep, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userDAO = userDAO;
        this.loginRep = userRep;

        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public void register(RegisterDto registerDati) {

        try {
        /*// check if user already exist. if exist than authenticate the user
        if(loginRep.findByUsername(logindati.getUsername()).isPresent()) {
            ResponseEntity.badRequest().body("già esiste username");

        }

        userDati.setLoginModel(logindati);
        userDAO.save(userDati);  //salvo user anagrafica

            logindati.setUser(userDati);
            logindati.setPassword(passwordEncoder.encode(logindati.getPassword()));
            loginRep.save(logindati);

        String jwt = jwtService.generateToken(logindati);

            ResponseEntity.ok("Utente e dati di accesso registrati con successooo." + jwt);*/

            User userino = new User();
            userino.setNome(registerDati.getNome());
            userino.setCognome(registerDati.getCognome());
            userino.setData_nascita(registerDati.getDataNascita());
            userino.setPropic(registerDati.getPropic());
            LoginModel loginDati = new LoginModel();
            loginDati.setUsername(registerDati.getUsername());
            loginDati.setPassword(passwordEncoder.encode(registerDati.getPassword()));

            //role di default user
            loginDati.setRole(Role.USER);

            userino.setLoginModel(loginDati);
            loginDati.setUser(userino);


            userDAO.save(userino);
            loginRep.save(loginDati);



        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la registrazione.");
        }

    }


    public AuthenticationResponse authenticate(LoginModel request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        LoginModel user = loginRep.findByUsername(request.getUsername()).orElseThrow();


        String jwt=jwtService.generateToken(user);
        return new AuthenticationResponse(jwt, "User loggato con successo" + jwt);

    }

}
