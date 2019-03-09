package it.myti.academy.backend.controller;

import it.myti.academy.backend.model.Evento;
import it.myti.academy.backend.model.UnitaLogistica;
import it.myti.academy.backend.model.UnitaLogisticaDettaglio;
import it.myti.academy.backend.model.Utente;
import it.myti.academy.backend.repository.EventoRepository;
import it.myti.academy.backend.repository.UnitaLogisticaRepository;
import it.myti.academy.backend.repository.UtenteRepository;
import it.myti.academy.backend.service.EventoService;
import it.myti.academy.backend.service.UnitaLogisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EventiController {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    EventoService eventi;

    @GetMapping("/eventi/utente/{id}")
    public List<Evento> getByUtente(@PathVariable("id") long id, @RequestParam Map<String,String> params) {
        final Utente utente = utenteRepository.findById(id).get();
        if (utente != null) {
            Long spedizione = null;
            Long ul = null;
            try{
                spedizione = params.get("idSpedizione") != null ? Long.parseLong(params.get("idSpedizione")) : null;
                ul = params.get("idUnitaLogistica") != null ? Long.parseLong(params.get("idUnitaLogistica")) : null;
            } catch (NumberFormatException e){
                spedizione = null;
                ul = null;
            }
            return eventi.getEventiByUtente(utente, spedizione, ul);
        }
        return null;
    }

}