package sn.yaatout.gestionCouriel.api.mappers;

import org.mapstruct.Mapper;
import sn.yaatout.gestionCouriel.api.models.ExpediteurDto;
import sn.yaatout.gestionJournaux.model.Expediteur;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpediteurMapper {
    ExpediteurDto toExpediteurDto(Expediteur expediteur);
    List<ExpediteurDto> toListExpediteurDto(List<Expediteur> list);

    Expediteur toExpediteur(ExpediteurDto expediteur);
    List<Expediteur> toListExpediteur(List<ExpediteurDto> list);
}
