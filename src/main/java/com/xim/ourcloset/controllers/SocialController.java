package com.xim.ourcloset.controllers;

import com.xim.ourcloset.models.LoginModel;
import com.xim.ourcloset.models.User;
import com.xim.ourcloset.repositories.LoginDAO;
import com.xim.ourcloset.repositories.UserDAO;
import com.xim.ourcloset.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class SocialController {


    @Autowired
    JwtService jwtService;

    @Autowired
    LoginDAO loginDAO;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok("ciao da loggato");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<String> helloAdmin() {

        return ResponseEntity.ok("ciao da loggato - admin");
    }


}
