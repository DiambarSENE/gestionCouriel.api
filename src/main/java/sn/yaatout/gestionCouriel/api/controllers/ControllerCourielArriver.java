package sn.yaatout.gestionCouriel.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sn.yaatout.gestionCouriel.api.services.ICourielArriverService;
import sn.yaatout.gestionJournaux.api.*;
import sn.yaatout.gestionJournaux.model.CourielArriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://localhost:8081")
//@CrossOrigin(origins = "http://depinfo-dev.tech:8081")
//@CrossOrigin(origins = "http://178.16.129.206:8081")
@RequestMapping("/couriel")
@Slf4j
@RequiredArgsConstructor
@RestController
public class ControllerCourielArriver implements CourielArriverApi, CourielsArriverApi, CourielsArriverTodayApi, CourielsArriverNotTodayApi, CourielFichierPdfApi {

     //public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/pdfFile";
    public static String uploadDir =  "/static/pdfFiles";
    private final ICourielArriverService iCourielService;

    @PostMapping("/addCourielArriver")
    @Override
    public ResponseEntity<CourielArriver> createCourielArriver(@RequestParam("dateDarriver") String dateDarriver,
         @RequestParam("dateCorrespondance") String dateCorrespondance, @RequestParam("numeroCorrespondance") Long numeroCorrespondance,
         @RequestParam("expediteur") String expediteur, @RequestParam("objet") String objet, @RequestParam(value = "dateReponse", required = false) String dateReponse, @RequestParam(value = "numeroReponse", required = false) Long numeroReponse,
         @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "verifier", required = false) Boolean verifier) {


        CourielArriver couriel = new CourielArriver();
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            couriel.setFilePdf(originalFilename);
            Path fileNameAndPath = Paths.get(uploadDir, originalFilename);
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        couriel.setDateDarriver(dateDarriver);
        couriel.setDateCorrespondance(dateCorrespondance);
        couriel.setDateReponse(dateReponse);
        couriel.setExpediteur(expediteur);
        couriel.setNumeroCorrespondance(numeroCorrespondance);
        couriel.setNumeroReponse(numeroReponse);
        couriel.setObjet(objet);
        couriel.setVerifier(verifier);

        return new ResponseEntity<>(iCourielService.addCourielArriver(couriel), HttpStatus.OK);
    }



    @DeleteMapping("/deleteCourielArriver/{courielId}")
    @Override
    public ResponseEntity<String> deleteCourielArriver(@PathVariable Long courielId) {
        iCourielService.deleteCourielArriver(courielId);
        return new ResponseEntity<>("Couriel portant l'identifiant "+ courielId+ " supprimer avec success", HttpStatus.OK);

    }

    @GetMapping("/getCourielArriverById/{courielId}")
    @Override
    public ResponseEntity<CourielArriver> getCourielArriverById(@PathVariable Long courielId) {
        return new ResponseEntity<>(iCourielService.getCourielArriverById(courielId), HttpStatus.OK);
    }

    @PutMapping("/updateCourielArriver")
    @Override
    public ResponseEntity<CourielArriver> updateCourielArriver(@RequestParam("id") Long id,@RequestParam("dateDarriver") String dateDarriver,
           @RequestParam("dateCorrespondance") String dateCorrespondance, @RequestParam("numeroCorrespondance") Long numeroCorrespondance,
           @RequestParam("expediteur") String expediteur, @RequestParam("objet") String objet, @RequestParam(value = "dateReponse", required = false) String dateReponse,
           @RequestParam(value = "numeroReponse", required = false) Long numeroReponse,
           @RequestParam(value = "file", required = false) MultipartFile file,  @RequestParam(value = "verifier", required = false) Boolean verifier) {
        CourielArriver couriel = new CourielArriver();
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            couriel.setFilePdf(originalFilename);
            Path fileNameAndPath = Paths.get(uploadDir, originalFilename);
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        couriel.setId(id);
        couriel.setDateDarriver(dateDarriver);
        couriel.setDateCorrespondance(dateCorrespondance);
        couriel.setDateReponse(dateReponse);
        couriel.setExpediteur(expediteur);
        couriel.setNumeroCorrespondance(numeroCorrespondance);
        couriel.setNumeroReponse(numeroReponse);
        couriel.setObjet(objet);
        couriel.setVerifier(verifier);
        return new ResponseEntity<>(iCourielService.updateCourielArriver(couriel), HttpStatus.OK);
    }

    @GetMapping("/getFilePdfArriver/{filePdf}")
    @Override
    public ResponseEntity<Resource> getFIlePdf(@PathVariable String filePdf) {
        Resource resource = chargerPdf(filePdf);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }


    @GetMapping("/getAllCourielsArriver")
    @Override
    public ResponseEntity<List<CourielArriver>> getAllCourielsArriver() {
        return new ResponseEntity<>(iCourielService.getAllCourielsArriver(), HttpStatus.OK);
    }

    @GetMapping("/getAllCourrielsArriverToday")
    @Override
    public ResponseEntity<List<CourielArriver>> findCourrielsArriverToday() {
        return new ResponseEntity<>(iCourielService.findCourrielsArriverToday(), HttpStatus.OK);
    }
    @GetMapping("/getAllCourielsArriverNotToday")
    @Override
    public ResponseEntity<List<CourielArriver>> findCourrielsArriverNotToday() {
        return new ResponseEntity<>(iCourielService.findCourrielsArriverNotToday(), HttpStatus.OK);
    }

    public Resource chargerPdf(String filePdf) {
        if (filePdf == null || filePdf.trim().isEmpty()){
            return null;
        }
        try {
            //chemin vers le fichier pdf
           //String uploadDir = System.getProperty("user.dir") + "/src/main/resources/pdfFile/"+filePdf;
           String uploadDir = "/static/pdfFiles/"+filePdf;
            Path pdfPath = Paths.get(uploadDir);
            // Vérification de l'existence du fichier avant de le retourner
            if (!Files.exists(pdfPath) || !Files.isReadable(pdfPath)){
                return null;
            }
            // Si le fichier existe et est lisible
            return new FileSystemResource(pdfPath);
        }catch  (Exception e) {
            e.printStackTrace();
            return null;
        }

    }





}
