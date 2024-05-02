package com.xim.ourcloset.controllers;

import com.xim.ourcloset.models.Collezione;
import com.xim.ourcloset.models.LoginModel;
import com.xim.ourcloset.models.Modello;
import com.xim.ourcloset.models.User;
import com.xim.ourcloset.repositories.ModelloDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/model")
@CrossOrigin("*")
public class ModelloController {
    @Autowired
    ModelloDAO modelloDAO;

    @RequestMapping(value = "/getmodelbyauth", method = RequestMethod.GET)
    public ResponseEntity<List<Modello>> getModelsByAuth() {
        // All'interno del tuo metodo
        final Logger log = LoggerFactory.getLogger(ModelloController.class);


        // Istruzioni per ottenere le collezioni dell'utente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginModel userlogin = (LoginModel) authentication.getPrincipal();
        User user = userlogin.getUser();

        List<Modello> modelli = modelloDAO.findByCollezione_Artigiano_Idart(user.getArtigiano().getIdart());

        // Log per confermare che le collezioni sono state ottenute correttamente
        log.info("Modelli ottenute con successo");


        return ResponseEntity.ok(modelli);
    }

    @RequestMapping(value = "/postmodel", method = RequestMethod.POST)
    public ResponseEntity<?> createCollection(@RequestBody Modello modello) {
        modelloDAO.save(modello);
        return ResponseEntity.ok("hai creato la collezione" + modello.getNomemodello());

    }
}
