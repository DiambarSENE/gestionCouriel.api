package sn.yaatout.gestionCouriel.api.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sn.yaatout.gestionCouriel.api.exceptions.AppException;
import sn.yaatout.gestionCouriel.api.mappers.CategoryMapper;
import sn.yaatout.gestionCouriel.api.mappers.ExpediteurMapper;
import sn.yaatout.gestionCouriel.api.models.CategoryDto;
import sn.yaatout.gestionCouriel.api.models.ErrorDto;
import sn.yaatout.gestionCouriel.api.models.ExpediteurDto;
import sn.yaatout.gestionCouriel.api.repository.CategoryRepository;
import sn.yaatout.gestionCouriel.api.repository.ExpediteurRepository;
import sn.yaatout.gestionCouriel.api.services.ICategoryService;
import sn.yaatout.gestionCouriel.api.services.IExpediteurService;
import sn.yaatout.gestionJournaux.model.Category;
import sn.yaatout.gestionJournaux.model.Expediteur;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExpediteurServiceImpl implements IExpediteurService {

    private final ExpediteurRepository repository;
    private final ExpediteurMapper expediteurMapper;

    @Override
    public Expediteur addExpediteur(Expediteur category) {
        ExpediteurDto expediteurDto1 = repository.findByNom(category.getNom());
        if (expediteurDto1 == null){
            ExpediteurDto expediteurDto = expediteurMapper.toExpediteurDto(category);
            expediteurDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            repository.save(expediteurDto);
            return expediteurMapper.toExpediteur(expediteurDto);
        }
        throw new AppException("Expéditeur existe déja", HttpStatus.BAD_REQUEST);

    }
    @Override
    public List<Expediteur> getAllExpediteur() {
        return expediteurMapper.toListExpediteur(repository.findAll());
    }


    @Override
    public Expediteur updateExpediteur(Expediteur expediteur) {
        ExpediteurDto expediteurDto = repository.findByid(expediteur.getId());
        if(expediteurDto == null){
            //new ErrorDto("expediteur n'existe pas");
            throw new AppException("expediteur n'existe pas", HttpStatus.BAD_REQUEST);
        }else {
            expediteurDto.setNom(expediteur.getNom());
//            expediteurDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            expediteurDto.setDescription(expediteur.getDescription());
            repository.save(expediteurDto);
            return expediteurMapper.toExpediteur(expediteurDto);
        }
    }

    @Override
    public void deleteExpediteur(Long expediteurId) {
       repository.deleteById(expediteurId);
    }

    @Override
    public Expediteur getExpediteurById(Long expediteurId) {

        return expediteurMapper.toExpediteur(repository.findByid(expediteurId));
    }
}
