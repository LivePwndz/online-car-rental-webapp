package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.RoleName;
import miu.edu.cs.cs425.carRentalWebApp.service.ClerkService;
import miu.edu.cs.cs425.carRentalWebApp.service.UserService;
import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewUserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClerkServiceImpl implements ClerkService {
    private final UserService userService;

    public ClerkServiceImpl(UserService userService){
        this.userService = userService;
    }
    @Override
    public List<NewUserDto> getAllClecks() {
        return userService.getUserByRole(RoleName.CLERK);
    }

    @Override
    public NewUserDto findClerkById(Long id) {
        return userService.getUserDtoById(id);
    }

    @Override
    public void updateClerk(NewUserDto clerkDto) {
        userService.updateUser(clerkDto);
    }

    @Override
    public void addNewClerk(NewUserDto userDto) {
        userService.addUser(userDto, RoleName.CLERK);
    }

}
