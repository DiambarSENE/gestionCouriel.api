package sn.yaatout.gestionCouriel.api.mappers;

import org.mapstruct.Mapper;
import sn.yaatout.gestionCouriel.api.models.UserDto;
import sn.yaatout.gestionJournaux.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);
    List<UserDto> listUserToListUserDto(List<User> list);

    User userDtoToUser(UserDto userDto);

    List<User> listUserDtoToListUser(List<UserDto> list);
}
