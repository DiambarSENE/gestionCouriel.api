package sn.yaatout.gestionCouriel.api.mappers;

import org.mapstruct.Mapper;
import sn.yaatout.gestionCouriel.api.models.CategoryDto;
import sn.yaatout.gestionJournaux.model.Category;


import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);
    List<CategoryDto> toListCategoryDto(List<Category> list);

    Category toCategory(CategoryDto category);
    List<Category> toListCategory(List<CategoryDto> list);
}
