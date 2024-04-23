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

public class SocialController {


    @Autowired
    JwtService jwtService;

    @Autowired
    LoginDAO loginDAO;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/demo", method = RequestMethod.POST)
    public ResponseEntity<String> hello(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        token=token.substring(7);
        String username=jwtService.extractUsername(token);
        Optional<LoginModel> loginModel= loginDAO.findByUsername(username);
        User user = loginModel.get().getUser();   // prendo token, tolgo "bearer ",estraggo l'userrname, lo cerco in Login e ottengo l'user, Dopo lo setterò al post

        // Ottenere la data corrente
        LocalDate currentDate = LocalDate.now();


        return ResponseEntity.ok(token + user.getNome());
    }


}
