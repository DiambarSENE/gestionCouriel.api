package sn.yaatout.gestionCouriel.api.services;

import sn.yaatout.gestionJournaux.model.CourielDepart;

import java.util.List;

public interface ICourielDepartService {
    List<CourielDepart> getAllCourielsDepart();

    List<CourielDepart> findCourrielsDepartToday();

    List<CourielDepart> findCourrielsDepartNotToday();
    CourielDepart updateCourielDepart(CourielDepart couriel);

    List<CourielDepart> getAllCourielDepartByUser(Long userId);

    void deleteCourielDepart(Long courielId);

    CourielDepart addCourielDepart(CourielDepart couriel);

    CourielDepart getCourielDepartById(Long courielId);

}
