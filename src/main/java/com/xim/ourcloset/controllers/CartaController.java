package com.xim.ourcloset.controllers;

import com.xim.ourcloset.models.Carta;
import com.xim.ourcloset.models.Collezione;
import com.xim.ourcloset.models.LoginModel;
import com.xim.ourcloset.models.User;
import com.xim.ourcloset.repositories.CartaDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/card")
@CrossOrigin("*")
public class CartaController {

    @Autowired
    CartaDAO cartaDAO;
    // Praticamente è la get armadio
    @RequestMapping(value = "/getcardsbyauth", method = RequestMethod.GET)
    public ResponseEntity<List<Carta>> getCardsByAuth() {
        // All'interno del tuo metodo
        final Logger log = LoggerFactory.getLogger(CartaController.class);


        // Istruzioni per ottenere le collezioni dell'utente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginModel userlogin = (LoginModel) authentication.getPrincipal();
        User user = userlogin.getUser();

        List<Carta> carte = cartaDAO.findByUser_Userid(user.getUserid());

        // Log per confermare che le collezioni sono state ottenute correttamente
        log.info("Modelli ottenute con successo");


        return ResponseEntity.ok(carte);
    }

    @RequestMapping(value = "/getcard/{cardId}", method = RequestMethod.GET)
    public ResponseEntity<Carta> getCardById(@PathVariable(value="cardId") int id) {
        Carta carta = cartaDAO.findById(id);
        return ResponseEntity.ok(carta);
    }

    @RequestMapping(value = "/getcardsbymodello/{modelId}", method = RequestMethod.GET)
    public ResponseEntity<List<Carta>> getCardsByModelId(@PathVariable(value="modelId") int id) {
        List<Carta> carta = cartaDAO.findByModello_Idmodello(id);
        return ResponseEntity.ok(carta);
    }

    @RequestMapping(value = "/postcarta", method = RequestMethod.POST)
    public ResponseEntity<?> createCarta(@RequestBody Carta carta) {
        // Istruzioni per ottenere le collezioni dell'utente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginModel userlogin = (LoginModel) authentication.getPrincipal();
        User user = userlogin.getUser();
        carta.setUser(user);
        cartaDAO.save(carta);

        return ResponseEntity.ok(carta);

    }
}
