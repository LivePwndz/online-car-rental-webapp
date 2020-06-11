package miu.edu.cs.cs425.carRentalWebApp.service;

import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClerkService {
   List<Clerk> getAllClerck();
   Clerk findClerkById(Long id);
   void updateClerk(Clerk clerk);
   void addNewClerk(Clerk clerk);
}
