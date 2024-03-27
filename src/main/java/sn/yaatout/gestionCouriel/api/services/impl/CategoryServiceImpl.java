package sn.yaatout.gestionCouriel.api.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sn.yaatout.gestionCouriel.api.exceptions.AppException;
import sn.yaatout.gestionCouriel.api.mappers.CategoryMapper;
import sn.yaatout.gestionCouriel.api.models.CategoryDto;
import sn.yaatout.gestionCouriel.api.models.ErrorDto;
import sn.yaatout.gestionCouriel.api.repository.CategoryRepository;
import sn.yaatout.gestionCouriel.api.services.ICategoryService;
import sn.yaatout.gestionJournaux.model.Category;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category addCategory(Category category) {
        CategoryDto categoryDto1 = repository.findByNom(category.getNom());
        if (categoryDto1 == null){
            CategoryDto categoryDto = categoryMapper.toCategoryDto(category);
            categoryDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            repository.save(categoryDto);
            return categoryMapper.toCategory(categoryDto);
        }
        throw new AppException("Catégorie éxiste déja", HttpStatus.BAD_REQUEST);

    }
    @Override
    public List<Category> getAllCategorys() {
        return categoryMapper.toListCategory(repository.findAll());
    }


    @Override
    public Category updateCategory(Category category) {
        CategoryDto categoryDto = repository.findByid(category.getId());
        if(categoryDto == null){
            //new ErrorDto("Category n'existe pas");
            throw new AppException("Categorie n'existe pas", HttpStatus.BAD_REQUEST);
        }else {
            categoryDto.setNom(category.getNom());
            categoryDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            categoryDto.setDescription(category.getDescription());
            repository.save(categoryDto);
            return categoryMapper.toCategory(categoryDto);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
       repository.deleteById(categoryId);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryMapper.toCategory(repository.findByid(categoryId));
    }
}
