package sn.yaatout.gestionCouriel.api.services;

import sn.yaatout.gestionJournaux.model.Destinateur;

import java.util.List;

public interface IDestinateurService {

    List<Destinateur> getAllDestinateurs();

    Destinateur updateDestinateur(Destinateur destinateur);

    void deleteDestinateur(Long destinateurId);

    Destinateur addDestinateur(Destinateur destinateur);

    Destinateur getDestinateurById(Long destinateurId);
}
