package sn.yaatout.gestionCouriel.api.mappers;

import org.mapstruct.Mapper;
import sn.yaatout.gestionCouriel.api.models.DestinateurDto;
import sn.yaatout.gestionJournaux.model.Destinateur;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DestinateurMapper {
    DestinateurDto toDestinateurDto(Destinateur destinateur);
    List<DestinateurDto> toListDestinateurDto(List<Destinateur> list);

    Destinateur toDestinateur(DestinateurDto destinateur);
    List<Destinateur> toListDestinateur(List<DestinateurDto> list);
}
