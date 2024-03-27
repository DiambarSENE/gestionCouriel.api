package sn.yaatout.gestionCouriel.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sn.yaatout.gestionCouriel.api.config.UserAuthenticationProvider;
import sn.yaatout.gestionCouriel.api.services.IUserService;
import sn.yaatout.gestionJournaux.api.UseApi;
import sn.yaatout.gestionJournaux.api.UserApi;
import sn.yaatout.gestionJournaux.api.UsersApi;
import sn.yaatout.gestionJournaux.model.User;
import org.springframework.core.io.Resource;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "http://178.16.129.206:8081")
//@CrossOrigin(origins = "http://depinfo-dev.tech:8081")
//@CrossOrigin(origins = "http://localhost:8081")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class ControllerUser implements UserApi, UsersApi, UseApi {
    //public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/photoProfile";
    private final IUserService iUserService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/ajouter")
    @Override
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User user1 = iUserService.createUser(user);
        //user1.setToken(userAuthenticationProvider. createToken(user.getEmail()));
        //return ResponseEntity.created(URI.create("/users/" + user1.getId())).body(user1);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

   // @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/delete/{userId}")
    @Override
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        iUserService.deleteUser(userId);
        return new ResponseEntity<>("Utilisateur avec l'identifiant " + userId + " supprimer avec success", HttpStatus.OK);
    }
    @GetMapping("getUserByEmail/{email}")
    @Override
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(iUserService.getUserByEmail(email), HttpStatus.OK);
    }
    @GetMapping("getUserById/{userId}")
    @Override
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(iUserService.getUserById(userId), HttpStatus.OK);
    }

    @PatchMapping("/updatePassword/{email}/{password}/{newPassword}")
    @Override
    public ResponseEntity<User> updatePassword(@PathVariable String email, @PathVariable String password,@PathVariable String newPassword) {
        User result = iUserService.updatePassword(email, password, newPassword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PatchMapping("/updateProfilePhoto")
//    @Override
//    public ResponseEntity<User> uploadProfile(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {
//        String image = null;
//        if (file != null) {
//            String originalFilename = file.getOriginalFilename();
//            image = originalFilename;
//            Path fileNameAndPath = Paths.get(uploadDir, originalFilename);
//            try {
//                Files.write(fileNameAndPath, file.getBytes());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        User result = iUserService.updatePhotoProfile(id, image);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @PatchMapping("/update")
    @Override
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user) {
        User result = iUserService.updateUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    @Override
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(iUserService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/connexion")
    @Override
    public ResponseEntity<User> createLogin(@RequestBody @Valid User login) {
        User user = iUserService.createLogin(login);
        user.setToken(userAuthenticationProvider.createToken(user.getEmail()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/deconnexion")
    @Override
    public ResponseEntity<String> createLogout() {
        return null;
    }


//    @GetMapping("/users/{photo}")
//    public ResponseEntity<Resource> getImage(@PathVariable String photo) throws MalformedURLException {
//        Resource file = new UrlResource(Paths.get("src/main/resources/static/photoProfile"+ photo).toUri());
//        if (file.exists() || file.isReadable()) {
//            return ResponseEntity.ok().body(file);
//        } else {
//            throw new RuntimeException("Could not read the file!");
//        }
//    }

}
