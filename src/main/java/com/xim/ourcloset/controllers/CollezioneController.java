package com.xim.ourcloset.controllers;

import com.xim.ourcloset.models.Artigiano;
import com.xim.ourcloset.models.Collezione;
import com.xim.ourcloset.models.LoginModel;
import com.xim.ourcloset.models.User;
import com.xim.ourcloset.repositories.CollezioneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Controller
@RequestMapping("/collection")
@CrossOrigin("*")
public class CollezioneController {
    @Autowired
    CollezioneDAO collezioneDAO;

    @RequestMapping(value = "/getcoll", method = RequestMethod.GET)
    public ResponseEntity<?> getCollections() {

       List<Collezione> colleziones=collezioneDAO.findAll();



        return ResponseEntity.ok(colleziones);

    }

    @RequestMapping(value = "/getcollbyauth", method = RequestMethod.GET)
    public ResponseEntity<List<Collezione>> getCollectionsByAuth() {
        // All'interno del tuo metodo
        final Logger log = LoggerFactory.getLogger(CollezioneController.class);


            // Istruzioni per ottenere le collezioni dell'utente
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LoginModel userlogin = (LoginModel) authentication.getPrincipal();
            User user = userlogin.getUser();

            List<Collezione> collezioni = collezioneDAO.findByArtigiano_Idart(user.getArtigiano().getIdart());

            // Log per confermare che le collezioni sono state ottenute correttamente
            log.info("Collezioni ottenute con successo");


        return ResponseEntity.ok(collezioni);
    }


    @RequestMapping(value = "/postcoll", method = RequestMethod.POST)
    public ResponseEntity<?> createCollection(@RequestBody Collezione collezione) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginModel userlogin = (LoginModel) authentication.getPrincipal();

        User user = userlogin.getUser();


        collezione.setArtigiano(user.getArtigiano());
        collezioneDAO.save(collezione);



        return ResponseEntity.ok("hai creato la collezione" + collezione.getNomeCollezione());

    }


}
