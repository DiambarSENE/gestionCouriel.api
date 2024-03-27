package sn.yaatout.gestionCouriel.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.yaatout.gestionCouriel.api.services.IExpediteurService;
import sn.yaatout.gestionJournaux.api.ExpediteurApi;
import sn.yaatout.gestionJournaux.api.ExpediteursApi;
import sn.yaatout.gestionJournaux.model.Expediteur;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://178.16.129.206:8081")
//@CrossOrigin(origins = "http://depinfo-dev.tech:8081")
//@CrossOrigin(origins = "http://localhost:8081")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/expediteur")
@Slf4j
@RequiredArgsConstructor
@RestController
public class ControllerExpediteur implements ExpediteurApi, ExpediteursApi {
    private final IExpediteurService iExpediteurService;

    @PostMapping("/addExpediteur")
    @Override
    public ResponseEntity<Expediteur> createExpediteur(@RequestBody @Valid Expediteur expediteur) {
        return new ResponseEntity<>(iExpediteurService.addExpediteur(expediteur), HttpStatus.OK);
    }

    @DeleteMapping("/deleteExpediteur/{expediteurId}")
    @Override
    public ResponseEntity<String> deleteExpediteur(@PathVariable Long expediteurId) {
        iExpediteurService.deleteExpediteur(expediteurId);
        return new ResponseEntity<>("Expediteur portant l'identifiant "+ expediteurId + " supprimer avec success", HttpStatus.OK);

    }

    @GetMapping("/getExpediteurById/{expediteurId}")
    @Override
    public ResponseEntity<Expediteur> getExpediteurById(@PathVariable Long expediteurId) {

        return new ResponseEntity<>(iExpediteurService.getExpediteurById(expediteurId), HttpStatus.OK);
    }

    @PutMapping("/updateExpediteur")
    @Override
    public ResponseEntity<Expediteur> updateExpediteur(@RequestBody @Valid Expediteur expediteur) {

        return new ResponseEntity<>(iExpediteurService.updateExpediteur(expediteur), HttpStatus.OK);
    }

    @GetMapping("/getAllExpediteurs")
    @Override
    public ResponseEntity<List<Expediteur>> getAllExpediteur() {

        return new ResponseEntity<>(iExpediteurService.getAllExpediteur(), HttpStatus.OK);
    }



}
