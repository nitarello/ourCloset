package com.xim.ourcloset.controllers;

import com.xim.ourcloset.models.Artigiano;
import com.xim.ourcloset.models.LoginModel;
import com.xim.ourcloset.models.Role;
import com.xim.ourcloset.models.User;
import com.xim.ourcloset.repositories.ArtigianoDAO;
import com.xim.ourcloset.repositories.CollezioneDAO;
import com.xim.ourcloset.repositories.LoginDAO;
import com.xim.ourcloset.repositories.UserDAO;
import com.xim.ourcloset.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SocialController {


    @Autowired
    JwtService jwtService;

    @Autowired
    LoginDAO loginDAO;

    @Autowired
    UserDAO userDAO;

    //USER- USER

    @Autowired
    CollezioneDAO collezioneDAO;

    @Autowired
    ArtigianoDAO artigianoDAO;

    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public ResponseEntity<User> getUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginModel userlogin = (LoginModel) authentication.getPrincipal();

        userlogin.setRole(Role.ADMIN);

        User user=userlogin.getUser();

        return ResponseEntity.ok(user);


    }

    //API PER OTTENERE ARTIGIANO DAL TOKEN
    @RequestMapping(value = "/getartigiano", method = RequestMethod.GET)
    public ResponseEntity<Artigiano> getArtigiano() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginModel userlogin = (LoginModel) authentication.getPrincipal();

        User user=userlogin.getUser();
        Artigiano artigiano=user.getArtigiano();



        return ResponseEntity.ok(artigiano);

    }

    //API PER DIVENTARE ARTIGIANO
    @RequestMapping(value = "/beartigiano", method = RequestMethod.POST)
    public ResponseEntity<String> beArtigiano(@RequestBody Artigiano artigiano) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginModel userlogin = (LoginModel) authentication.getPrincipal();

        userlogin.setRole(Role.ADMIN);

        User user=userlogin.getUser();





        artigianoDAO.save(artigiano);



        user.setArtigiano(artigiano);
        userDAO.save(user);


        return ResponseEntity.ok("ciao da loggato " + userlogin.getUsername() + " e sei " + userlogin.getRole() );

    }




    //ARTIGIANO-ADMIN
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<String> helloAdmin() {

        return ResponseEntity.ok("ciao da loggato - admin");
    }


}
