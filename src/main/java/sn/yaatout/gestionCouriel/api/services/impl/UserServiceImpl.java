package sn.yaatout.gestionCouriel.api.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.yaatout.gestionCouriel.api.exceptions.AppException;
import sn.yaatout.gestionCouriel.api.mappers.UserMapper;
import sn.yaatout.gestionCouriel.api.models.UserDto;
import sn.yaatout.gestionCouriel.api.repository.RoleRepository;
import sn.yaatout.gestionCouriel.api.repository.UserRepository;
import sn.yaatout.gestionCouriel.api.services.IUserService;
import sn.yaatout.gestionJournaux.model.User;

import java.nio.CharBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private  final RoleRepository repository;


    @Override
    public User createUser(User user) {
        UserDto userDto1 = userRepository.findByEmail(user.getEmail());
        if(userDto1 == null){
            UserDto userDto = userMapper.userToUserDto(user);
            //userDto.setRoles( addRoleToUser(user.getRole()) );
            userDto.setRole( user.getRole() );
            //userDto.setMatricule(user.getRole().substring(0,2).toUpperCase()+user.getId());
            userDto.setPassword(encoder.encode(CharBuffer.wrap(user.getNom())));
            userDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            return userMapper.userDtoToUser(userRepository.save(userDto));
        }else {
            throw new AppException("L'adresse électronique existe déjà", HttpStatus.BAD_REQUEST);
        }
    }

//    private List<RoleDto> addRoleToUser(String roles) {
//        String [] cates = roles.split(",");
//        List<RoleDto> roleDto = new ArrayList<>();
//        for (String nom : cates){
//            RoleDto r = repository.findByNom(nom);
//            if (r.getNom().isEmpty()){
//                //throw new UserNotExistsException("Aucun user n'est disponible");
//            }else {
//                roleDto.add(r);
//            }
//        }
//        return roleDto;
//    }

    @Override
    public void deleteUser(long userId) {
       userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(long userId) {
        UserDto userDto = userRepository.findByid(userId);
        if (userDto != null) {
            return userMapper.userDtoToUser(userDto);
        }else {
            throw new AppException("L'identifiant specifie n'existe pas", HttpStatus.BAD_REQUEST);
        }
    }
//    @Override
//    public User getUserById(long userId) {
//        return userRepository.findByid(userId)
//                .map(userDtoMapper::userDtoToUser)
//                .orElse(null);
//    }

    @Override
    public User updateUser(User user) {
       // User user1 = null;
        UserDto userDto = userRepository.findByid(user.getId());
        if (userDto != null) {
            userDto.setNom(user.getNom());
            userDto.setPrenom(user.getPrenom());
            userDto.setMatricule(user.getMatricule());
            userDto.setTelephone(user.getTelephone());
//            userDto.setDateCreation(user.getDateCreation());
            userDto.setEmail(user.getEmail());
            userDto.setAdresse(user.getAdresse());
            userDto.setRole(user.getRole());
//            if(user.getRole() != null){
//                userDto.setRoles(addRoleToUser(user.getRole()));
//            }
            userDto.setDateCreation(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            userRepository.save(userDto);
           return userMapper.userDtoToUser(userDto);
        }else{
          throw new AppException("L'identifiant de l'utilisateur n'existe pas", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.listUserDtoToListUser(userRepository.findAll());
    }

    @Override
    public User updatePassword(String email, String password, String newPassword) {
       // User user1 = null;
        UserDto userDto = userRepository.findByEmail(email);
        if (userDto == null) {
            throw new AppException("Adresse email n'existe pas", HttpStatus.BAD_REQUEST);
        } else if (passwordEncoder.matches(CharBuffer.wrap(password), userDto.getPassword())) {
            userDto.setPassword(encoder.encode(CharBuffer.wrap(newPassword)));
            return  userMapper.userDtoToUser(userRepository.save(userDto));
        }else {
            throw new AppException("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
        }

    }

//    @Override
//    public User findByEmail(String email) {
//       UserDto userDto = userRepository.findByEmail(email)
//               .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
//       return userDtoMapper.userDtoToUser(userDto);
//    }

    @Override
    public User getUserByEmail(String email) {
        UserDto userDto = userRepository.findByEmail(email);
        if (userDto == null){
            throw new AppException("Adresse email n'existe pas", HttpStatus.NOT_FOUND);
        }else{
            //.orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
            return userMapper.userDtoToUser(userDto);
        }

    }

    @Override
    public User createLogin(User user) {
        UserDto userDto = userRepository.findByEmail(user.getEmail());
        if(userDto == null){
            throw new AppException("Adresse email n'existe pas", HttpStatus.BAD_REQUEST);
            //.orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        }else if (passwordEncoder.matches(CharBuffer.wrap(user.getPassword()), userDto.getPassword())) {
            return userMapper.userDtoToUser(userDto);
        }else {
            throw new AppException("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public User createLogout() {
        return null;
    }

//    @Override
//    public User updatePhotoProfile(Long id, String image) {
//        User user = null;
//        UserDto userDto = userRepository.findByid(id);
//        if (userDto != null) {
//            userDto.setPhotoProfil(image);
//            userRepository.save(userDto);
//            user =  userMapper.userDtoToUser(userDto);
//        }
//        return user;
//    }

}
