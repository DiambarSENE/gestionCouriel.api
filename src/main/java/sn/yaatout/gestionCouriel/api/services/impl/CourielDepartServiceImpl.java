package sn.yaatout.gestionCouriel.api.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.yaatout.gestionCouriel.api.mappers.CourielDepartMapper;
import sn.yaatout.gestionCouriel.api.models.CourielDepartDto;
import sn.yaatout.gestionCouriel.api.models.ErrorDto;
import sn.yaatout.gestionCouriel.api.repository.CategoryRepository;
import sn.yaatout.gestionCouriel.api.repository.CourielDepartRepository;
import sn.yaatout.gestionCouriel.api.services.ICourielDepartService;
import sn.yaatout.gestionJournaux.model.CourielDepart;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourielDepartServiceImpl implements ICourielDepartService {

    private final CourielDepartRepository repository;
    private final CourielDepartMapper courielMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CourielDepart addCourielDepart(CourielDepart couriel) {
        CourielDepartDto courielDepartDto = courielMapper.toCourielDepartDto(couriel);
////        CategoryDto categoryDto = categoryRepository.findByNom(couriel.getCategorieNom());
//        courielDto.setCategorie(categoryDto);
        repository.save(courielDepartDto);
        return courielMapper.toCourielDepart(courielDepartDto);
    }
    @Override
    public List<CourielDepart> getAllCourielsDepart() {
        return courielMapper.toListCourielDepart(repository.findAll());
    }

    @Override
    public List<CourielDepart> findCourrielsDepartToday() {
        return courielMapper.toListCourielDepart(repository.findCourrielsToday());
    }

    @Override
    public List<CourielDepart> findCourrielsDepartNotToday() {
        return courielMapper.toListCourielDepart(repository.findCourrielsNotToday());
    }

    @Override
    public CourielDepart updateCourielDepart(CourielDepart couriel) {
        CourielDepartDto courielDepartDto = repository.findByid(couriel.getId());
        if(courielDepartDto == null){
            new ErrorDto("Couriel depart n'existe pas");
            return null;
        }else {
            courielDepartDto.setDateDuDepart(couriel.getDateDuDepart());
            courielDepartDto.setDestinataire(couriel.getDestinataire());
            courielDepartDto.setNombreDePieces(couriel.getNombreDePieces());
            courielDepartDto.setNumeroArChive(couriel.getNumeroArChive());
            courielDepartDto.setObjet(couriel.getObjet());
            courielDepartDto.setObservations(couriel.getObservations());
            courielDepartDto.setVerifier(couriel.getVerifier());
            if (couriel.getFilePdf() != null) {
                courielDepartDto.setFilePdf(couriel.getFilePdf());
            }
            courielDepartDto.setNumeroDordre(couriel.getNumeroDordre());
            repository.save(courielDepartDto);
            return courielMapper.toCourielDepart(courielDepartDto);
        }
    }
    @Override
    public List<CourielDepart> getAllCourielDepartByUser(Long userId) {
        return courielMapper.toListCourielDepart(repository.findAllByid(userId));
    }

    @Override
    public void deleteCourielDepart(Long courielId) {
       repository.deleteById(courielId);
    }

    @Override
    public CourielDepart getCourielDepartById(Long courielId) {

        return courielMapper.toCourielDepart(repository.findByid(courielId));
    }


}
