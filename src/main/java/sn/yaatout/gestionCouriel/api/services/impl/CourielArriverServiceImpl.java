package sn.yaatout.gestionCouriel.api.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.yaatout.gestionCouriel.api.mappers.CourielArriverMapper;
import sn.yaatout.gestionCouriel.api.models.CourielArriverDto;
import sn.yaatout.gestionCouriel.api.models.ErrorDto;
import sn.yaatout.gestionCouriel.api.repository.CategoryRepository;
import sn.yaatout.gestionCouriel.api.repository.CourielArriverRepository;

import sn.yaatout.gestionCouriel.api.services.ICourielArriverService;
import sn.yaatout.gestionJournaux.model.CourielArriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourielArriverServiceImpl implements ICourielArriverService {

    private final CourielArriverRepository repository;
    private final CourielArriverMapper courielMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public CourielArriver addCourielArriver(CourielArriver couriel) {
        CourielArriverDto courielArriverDto = courielMapper.toCourielArriverDto(couriel);

        repository.save(courielArriverDto);
        return courielMapper.toCourielArriver(courielArriverDto);
    }
      //        CategoryDto categoryDto = categoryRepository.findByNom(couriel.getCategorieNom());
     //        courielDto.setCategorie(categoryDto);
    //        courielArriverDto.setDateDarriver(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    @Override
    public List<CourielArriver> getAllCourielsArriver() {
        return courielMapper.toListCourielArriver(repository.findAll());
    }

    @Override
    public List<CourielArriver> findCourrielsArriverToday() {
        return courielMapper.toListCourielArriver(repository.findCourrielsToday());
    }

    @Override
    public List<CourielArriver> findCourrielsArriverNotToday() {
        return courielMapper.toListCourielArriver(repository.findCourrielsNotToday());
    }

    @Override
    public CourielArriver updateCourielArriver(CourielArriver couriel) {
        CourielArriverDto courielArriverDto = repository.findByid(couriel.getId());
        if(courielArriverDto == null){
            new ErrorDto("Couriel n'existe pas");
            return null;
        }else {
            courielArriverDto.setDateDarriver(couriel.getDateDarriver());
            courielArriverDto.setDateCorrespondance(couriel.getDateCorrespondance());
            courielArriverDto.setDateReponse(couriel.getDateReponse());
            courielArriverDto.setExpediteur(couriel.getExpediteur());
            courielArriverDto.setNumeroCorrespondance(couriel.getNumeroCorrespondance());
            courielArriverDto.setObjet(couriel.getObjet());
            courielArriverDto.setNumeroReponse(couriel.getNumeroReponse());
            courielArriverDto.setVerifier(couriel.getVerifier());
            if(couriel.getFilePdf() != null){
                courielArriverDto.setFilePdf(couriel.getFilePdf());
            }
            repository.save(courielArriverDto);
            return courielMapper.toCourielArriver(courielArriverDto);
        }
    }
    @Override
    public List<CourielArriver> getAllCourielArriverByUser(Long userId) {
        return courielMapper.toListCourielArriver(repository.findAllByid(userId));
    }

    @Override
    public void deleteCourielArriver(Long courielId) {
       repository.deleteById(courielId);
    }

    @Override
    public CourielArriver getCourielArriverById(Long courielId) {

        return courielMapper.toCourielArriver(repository.findByid(courielId));
    }


}
