package sn.yaatout.gestionCouriel.api.services;

import sn.yaatout.gestionJournaux.model.CourielArriver;

import java.util.List;

public interface ICourielArriverService {
    List<CourielArriver> getAllCourielsArriver();

    List<CourielArriver> findCourrielsArriverToday();

    List<CourielArriver> findCourrielsArriverNotToday();
    CourielArriver updateCourielArriver(CourielArriver couriel);

    List<CourielArriver> getAllCourielArriverByUser(Long userId);

    void deleteCourielArriver(Long courielId);

    CourielArriver addCourielArriver(CourielArriver couriel);

    CourielArriver getCourielArriverById(Long courielId);

}
