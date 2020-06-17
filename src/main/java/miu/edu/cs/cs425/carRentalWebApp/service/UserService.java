package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.RoleName;
import miu.edu.cs.cs425.carRentalWebApp.model.User;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewUserDto;

import java.util.List;

public interface UserService {
    NewUserDto getUserDtoById(Long id);
    NewUserDto addUser(NewUserDto newUserDto, RoleName roleName);
    NewUserDto updateUser(NewUserDto newCustomerDto);
    void deleteCustomerById(Long id);
    List<NewUserDto> getUserByRole(RoleName clerk);
    User getUserById(Long userId);
}
