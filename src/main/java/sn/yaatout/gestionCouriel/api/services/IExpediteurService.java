package sn.yaatout.gestionCouriel.api.services;

import sn.yaatout.gestionJournaux.model.Expediteur;

import java.util.List;

public interface IExpediteurService {

    List<Expediteur> getAllExpediteur();

    Expediteur updateExpediteur(Expediteur expediteur);

    void deleteExpediteur(Long expediteurId);

    Expediteur addExpediteur(Expediteur expediteur);

    Expediteur getExpediteurById(Long expediteurId);
}
