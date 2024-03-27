package sn.yaatout.gestionCouriel.api.services;


import sn.yaatout.gestionJournaux.model.User;

import java.util.List;

public interface IUserService {
    User createUser(User user) ;
    void deleteUser(long userId) ;
    User getUserById(long userId) ;
    User updateUser(User user) ;
    List<User> getAllUser() ;
    User updatePassword(String email, String password, String newPassword) ;
    User getUserByEmail(String email);
    User createLogin(User user);
    User createLogout();
//    User updatePhotoProfile(Long id, String image);

}
