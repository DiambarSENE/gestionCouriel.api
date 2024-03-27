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
import sn.yaatout.gestionCouriel.api.services.ICourielDepartService;
import sn.yaatout.gestionJournaux.api.*;
import sn.yaatout.gestionJournaux.model.CourielDepart;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Date;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://178.16.129.206:8081")
//@CrossOrigin(origins = "http://depinfo-dev.tech:8081")
//@CrossOrigin(origins = "http://localhost:8081")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/couriel")
@Slf4j
@RequiredArgsConstructor
@RestController
public class ControllerCourielDepart implements CourielDepartApi, CourielsDepartApi,
        CourielsDepartTodayApi, CourielsDepartNotTodayApi, CourielFichierPdfApi {

    //public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/pdfFile";
    public static String uploadDir = "/static/pdfFiles";

    private final ICourielDepartService iCourielService;


    @PostMapping("/addCourielDepart")
    @Override
    public ResponseEntity<CourielDepart> createCourielDepart(@RequestParam("numeroDordre") Long numeroDordre,
        @RequestParam("nombreDePieces") Long nombreDePieces, @RequestParam("dateDuDepart") String dateDuDepart,
         @RequestParam("destinataire") String destinataire, @RequestParam("objet") String objet,
       @RequestParam(value = "numeroArChive", required = false) Long numeroArChive,
       @RequestParam(value = "observations", required = false) String observations,
       @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "verifier", required = false) Boolean verifier) {

        CourielDepart couriel = new CourielDepart();
        if (file != null){
        String originalFilename = file.getOriginalFilename();
        couriel.setFilePdf(originalFilename);
        Path fileNameAndPath = Paths.get(uploadDir, originalFilename);
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
        couriel.setDateDuDepart(dateDuDepart);
        couriel.setDestinataire(destinataire);
        couriel.setObjet(objet);
        couriel.setNombreDePieces(nombreDePieces);
        couriel.setNumeroArChive(numeroArChive);
        couriel.setNumeroDordre(numeroDordre);
        couriel.setObservations(observations);
        couriel.setVerifier(verifier);
        return new ResponseEntity<>(iCourielService.addCourielDepart(couriel), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourielDepart/{courielId}")
    @Override
    public ResponseEntity<String> deleteCourielDepart(@PathVariable Long courielId) {
        iCourielService.deleteCourielDepart(courielId);
        return new ResponseEntity<>("Couriel portant l'identifiant "+ courielId+ " supprimer avec success", HttpStatus.OK);

    }

    @GetMapping("/getCourielDepartById/{courielId}")
    @Override
    public ResponseEntity<CourielDepart> getCourielDepartById(@PathVariable Long courielId) {
        return new ResponseEntity<>(iCourielService.getCourielDepartById(courielId), HttpStatus.OK);
    }
    @PutMapping("/updateCourielDepart")
    @Override
    public ResponseEntity<CourielDepart> updateCourielDepart(@RequestParam("id")Long id, @RequestParam("numeroDordre") Long numeroDordre,
            @RequestParam("nombreDePieces") Long nombreDePieces, @RequestParam("dateDuDepart") String dateDuDepart,
            @RequestParam("destinataire") String destinataire, @RequestParam("objet") String objet,
            @RequestParam(value = "numeroArChive", required = false) Long numeroArChive,
            @RequestParam(value = "observations", required = false) String observations,
            @RequestParam(value = "file", required = false) MultipartFile file,  @RequestParam(value = "verifier", required = false) Boolean verifier) {
        CourielDepart couriel = new CourielDepart();
        if (file != null){
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
        couriel.setDateDuDepart(dateDuDepart);
        couriel.setDestinataire(destinataire);
        couriel.setObjet(objet);
        couriel.setNombreDePieces(nombreDePieces);
        couriel.setNumeroArChive(numeroArChive);
        couriel.setNumeroDordre(numeroDordre);
        couriel.setObservations(observations);
        couriel.setVerifier(verifier);
        return new ResponseEntity<>(iCourielService.updateCourielDepart(couriel), HttpStatus.OK);
    }

    @GetMapping("/getFilePdfDepart/{filePdf}")
    @Override
    public ResponseEntity<Resource> getFIlePdf(@PathVariable String filePdf) {
        Resource resource = chargerPdf(filePdf);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }


    @GetMapping("/getAllCourielsDepart")
    @Override
    public ResponseEntity<List<CourielDepart>> getAllCourielsDepart() {
        return new ResponseEntity<>(iCourielService.getAllCourielsDepart(), HttpStatus.OK);
    }

    @GetMapping("/getAllCourrielsDepartToday")
    @Override
    public ResponseEntity<List<CourielDepart>> findCourrielsDepartToday() {
        return new ResponseEntity<>(iCourielService.findCourrielsDepartToday(), HttpStatus.OK);
    }
    @GetMapping("/getAllCourielsDepartNotToday")
    @Override
    public ResponseEntity<List<CourielDepart>> findCourrielsDepartNotToday() {
        return new ResponseEntity<>(iCourielService.findCourrielsDepartNotToday(), HttpStatus.OK);
    }

    public Resource chargerPdf(String filePdf) {
        if (filePdf == null || filePdf.trim().isEmpty()){
            return null;
        }
        try {
            //chemin vers le fichier pdf
            //   String uploadDir = System.getProperty("user.dir") + "/src/main/resources/pdfFile/"+filePdf;
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
