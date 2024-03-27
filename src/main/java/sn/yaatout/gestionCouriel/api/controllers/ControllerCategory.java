package sn.yaatout.gestionCouriel.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.yaatout.gestionCouriel.api.services.ICategoryService;
import sn.yaatout.gestionJournaux.api.CategorieApi;
import sn.yaatout.gestionJournaux.api.CategoriesApi;
import sn.yaatout.gestionJournaux.model.Category;
import sn.yaatout.gestionJournaux.model.Role;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://178.16.129.206:8081")
//@CrossOrigin(origins = "http://depinfo-dev.tech:8081")
//@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/category")
@Slf4j
@RequiredArgsConstructor
@RestController
public class ControllerCategory implements CategorieApi, CategoriesApi {
    private final ICategoryService iCategoryService;

    @PostMapping("/addCategory")
    @Override
    public ResponseEntity<Category> createCategory(@RequestBody @Valid Category category) {
        return new ResponseEntity<>(iCategoryService.addCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{categorieId}")
    @Override
    public ResponseEntity<String> deleteCategory(@PathVariable Long categorieId) {
        iCategoryService.deleteCategory(categorieId);
        return new ResponseEntity<>("Category portant l'identifiant "+ categorieId+ " supprimer avec success", HttpStatus.OK);

    }

    @GetMapping("/getCategoryById/{categorieId}")
    @Override
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categorieId) {

        return new ResponseEntity<>(iCategoryService.getCategoryById(categorieId), HttpStatus.OK);
    }

    @PutMapping("/updateCategory")
    @Override
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid Category category) {

        return new ResponseEntity<>(iCategoryService.updateCategory(category), HttpStatus.OK);
    }

    @GetMapping("/getAllCategorys")
    @Override
    public ResponseEntity<List<Category>> getAllCategory() {

        return new ResponseEntity<>(iCategoryService.getAllCategorys(), HttpStatus.OK);
    }



}
