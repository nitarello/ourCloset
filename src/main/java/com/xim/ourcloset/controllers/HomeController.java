package com.xim.ourcloset.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xim.ourcloset.dto.RegisterDto;
import com.xim.ourcloset.models.AuthenticationResponse;
import com.xim.ourcloset.models.LoginModel;
import com.xim.ourcloset.models.User;
import com.xim.ourcloset.repositories.LoginDAO;
import com.xim.ourcloset.repositories.UserDAO;

import com.xim.ourcloset.services.AuthenticationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/rest/home")
@CrossOrigin("*")
public class HomeController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    LoginDAO loginDAO;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AuthenticationService authenticationService;


    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        // Logga l'eccezione con dettagli completi
        logger.error("Errore interno del server: ", e);

        // Ritorna un messaggio generico al client
        return new ResponseEntity<>("Si è verificato un errore interno del server. Riprovare più tardi.", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResponseEntity<String> regUser(@RequestBody RegisterDto registerRequest) {


        try {


            authenticationService.register(registerRequest);


            return ResponseEntity.ok("Utente e dati di accesso registrati con successo controller.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la registrazioneee."+e);
        }
    }





    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginModel loginModel){

        return ResponseEntity.ok(authenticationService.authenticate(loginModel));
    }
}
