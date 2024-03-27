package sn.yaatout.gestionCouriel.api.mappers;

import org.mapstruct.Mapper;
import sn.yaatout.gestionCouriel.api.models.CourielArriverDto;
import sn.yaatout.gestionJournaux.model.CourielArriver;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourielArriverMapper {
    CourielArriverDto toCourielArriverDto(CourielArriver couriel);
    List<CourielArriverDto> toListCourielArriverDto(List<CourielArriver> list);

    CourielArriver toCourielArriver(CourielArriverDto couriel);
    List<CourielArriver> toListCourielArriver(List<CourielArriverDto> list);
}
