package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.service.dto.NewUserDto;

import java.util.List;

public interface ClerkService {
   List<NewUserDto> getAllClecks();
   NewUserDto findClerkById(Long id);
   void updateClerk(NewUserDto newUserDto);
   void addNewClerk(NewUserDto newUserDto);
}
