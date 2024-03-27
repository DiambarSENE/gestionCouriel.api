package sn.yaatout.gestionCouriel.api.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sn.yaatout.gestionCouriel.api.exceptions.AppException;
import sn.yaatout.gestionCouriel.api.mappers.DestinateurMapper;
import sn.yaatout.gestionCouriel.api.models.DestinateurDto;
import sn.yaatout.gestionCouriel.api.models.ErrorDto;
import sn.yaatout.gestionCouriel.api.repository.DestinateurRepository;
import sn.yaatout.gestionCouriel.api.services.IDestinateurService;
import sn.yaatout.gestionJournaux.model.Destinateur;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DestinateurServiceImpl implements IDestinateurService {

    private final DestinateurRepository repository;
    private final DestinateurMapper destinateurMapper;

    @Override
    public Destinateur addDestinateur(Destinateur destinateur) {
        DestinateurDto destinateurDto1 = repository.findByNom(destinateur.getNom());
        if (destinateurDto1 == null){
            DestinateurDto destinateurDto = destinateurMapper.toDestinateurDto(destinateur);
            destinateurDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            repository.save(destinateurDto);
            return destinateurMapper.toDestinateur(destinateurDto);
        }
        throw new AppException("Destinateur éxiste déja", HttpStatus.BAD_REQUEST);

    }
    @Override
    public List<Destinateur> getAllDestinateurs() {
        return destinateurMapper.toListDestinateur(repository.findAll());
    }


    @Override
    public Destinateur updateDestinateur(Destinateur destinateur) {
        DestinateurDto destinateurDto = repository.findByid(destinateur.getId());
        if(destinateurDto == null){
            //new ErrorDto("Destinateu n'existe pas");
            throw new AppException("Destinateur n'existe pas", HttpStatus.BAD_REQUEST);
        }else {
            destinateurDto.setNom(destinateur.getNom());
//            destinateurDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            destinateurDto.setDescription(destinateur.getDescription());
            repository.save(destinateurDto);
            return destinateurMapper.toDestinateur(destinateurDto);
        }
    }

    @Override
    public void deleteDestinateur(Long destinateurId) {
       repository.deleteById(destinateurId);
    }

    @Override
    public Destinateur getDestinateurById(Long categoryId) {

        return destinateurMapper.toDestinateur(repository.findByid(categoryId));
    }
}
