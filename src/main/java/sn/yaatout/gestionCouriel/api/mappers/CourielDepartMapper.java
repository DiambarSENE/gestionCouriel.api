package sn.yaatout.gestionCouriel.api.mappers;

import org.mapstruct.Mapper;
import sn.yaatout.gestionCouriel.api.models.CourielDepartDto;
import sn.yaatout.gestionJournaux.model.CourielDepart;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourielDepartMapper {
    CourielDepartDto toCourielDepartDto(CourielDepart couriel);
    List<CourielDepartDto> toListCourielDepartDto(List<CourielDepart> list);

    CourielDepart toCourielDepart(CourielDepartDto couriel);
    List<CourielDepart> toListCourielDepart(List<CourielDepartDto> list);
}
