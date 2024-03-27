package sn.yaatout.gestionCouriel.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.yaatout.gestionCouriel.api.services.IDestinateurService;
import sn.yaatout.gestionJournaux.api.DestinataireApi;
import sn.yaatout.gestionJournaux.api.DestinatairesApi;
import sn.yaatout.gestionJournaux.model.Destinateur;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://178.16.129.206:8081")
//@CrossOrigin(origins = "http://depinfo-dev.tech:8081")
//@CrossOrigin(origins = "http://localhost:8081")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/destinateur")
@Slf4j
@RequiredArgsConstructor
@RestController
public class ControllerDestinateur implements DestinataireApi, DestinatairesApi {
    private final IDestinateurService iDestinateurService;

    @PostMapping("/addDestinateur")
    @Override
    public ResponseEntity<Destinateur> createDestinateur(@RequestBody @Valid Destinateur destinateur) {
        return new ResponseEntity<>(iDestinateurService.addDestinateur(destinateur), HttpStatus.OK);
    }

    @DeleteMapping("/deleteDestinateur/{destinateurId}")
    @Override
    public ResponseEntity<String> deleteDestinateur(@PathVariable Long destinateurId) {
        iDestinateurService.deleteDestinateur(destinateurId);
        return new ResponseEntity<>("Destinateur portant l'identifiant "+destinateurId + " supprimer avec success", HttpStatus.OK);

    }


    @GetMapping("/getDestinateurById/{destinateurId}")
    @Override
    public ResponseEntity<Destinateur> getDestinataireById(@PathVariable Long destinateurId) {

        return new ResponseEntity<>(iDestinateurService.getDestinateurById(destinateurId), HttpStatus.OK);
    }

    @PutMapping("/updateDestinateur")
    @Override
    public ResponseEntity<Destinateur> updateDestinateur(@RequestBody @Valid Destinateur destinateur) {

        return new ResponseEntity<>(iDestinateurService.updateDestinateur(destinateur), HttpStatus.OK);
    }

    @GetMapping("/getAllDestinateurs")
    @Override
    public ResponseEntity<List<Destinateur>> getAllDestinataire() {

        return new ResponseEntity<>(iDestinateurService.getAllDestinateurs(), HttpStatus.OK);
    }


}
