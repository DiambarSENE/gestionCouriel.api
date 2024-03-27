//package sn.yaatout.gestionCouriel.api.services.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import sn.yaatout.api.yaatoutusersapi.exceptions.AppException;
//import sn.yaatout.api.yaatoutusersapi.mappers.UserDtoMapper;
//import sn.yaatout.api.yaatoutusersapi.models.UserDto;
//import sn.yaatout.api.yaatoutusersapi.repository.UserRepository;
//import sn.yaatout.api.yaatoutusersapi.services.ILoginService;
//import sn.yaatout.spi.users.model.Login;
//import sn.yaatout.spi.users.model.User;
//
//import java.nio.CharBuffer;
//
//@RequiredArgsConstructor
//@Service
//public class LoginServiceImpl implements ILoginService {
//    private  final UserRepository userRepository;
//    private final UserDtoMapper userDtoMapper;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//      public User createLogin(Login login) {
//        UserDto userDto = userRepository.findByEmail(login.getEmail());
//                //.orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
//        if (passwordEncoder.matches(CharBuffer.wrap(login.getPassword()), userDto.getPassword())) {
//            return userDtoMapper.userDtoToUser(userDto);
//        }
//        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
//    }
//
//    @Override
//    public User createLogout() {
//        return null;
//    }
//
//
//}