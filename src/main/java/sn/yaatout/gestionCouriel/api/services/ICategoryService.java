package sn.yaatout.gestionCouriel.api.services;

import sn.yaatout.gestionJournaux.model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAllCategorys();

    Category updateCategory(Category role);

    void deleteCategory(Long categoryId);

    Category addCategory(Category category);

    Category getCategoryById(Long categoryId);
}
