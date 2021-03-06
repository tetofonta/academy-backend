package it.myti.academy.backend.controller;

import it.myti.academy.backend.model.Collo;
import it.myti.academy.backend.model.Utente;
import it.myti.academy.backend.repository.UtenteRepository;
import it.myti.academy.backend.service.ColloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by david at 2019-03-07
 */
@RestController
@RequestMapping("/colli")
public class ColliController {

    private final ColloService colloService;

    private final UtenteRepository utenteRepository;

    @Autowired
    public ColliController(ColloService colloService, UtenteRepository utenteRepository) {
        this.colloService = colloService;
        this.utenteRepository = utenteRepository;
    }

    @GetMapping("/")
    public List<Collo> getByUtente() {
        List<Collo> returnValue = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        final Utente utente = utenteRepository.findByUsername(username);

        if (utente != null)
            returnValue = colloService.getSpedizioniAttiveByUtente(utente);

        return returnValue;
    }

}
