package miu.edu.cs.cs425.carRentalWebApp.service.serviceImp;

import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import miu.edu.cs.cs425.carRentalWebApp.repository.ClerkRepository;
import miu.edu.cs.cs425.carRentalWebApp.service.ClerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClerkServiceImpl implements ClerkService {

    private ClerkRepository clerkRepository;
    @Autowired
    public ClerkServiceImpl(ClerkRepository clerkRepository){
        this.clerkRepository=clerkRepository;
    }
    @Override
    public List<Clerk> getAllClerck() {
        return clerkRepository.findAll();
    }

    @Override
    public Clerk findClerkById(Long id) {
        return clerkRepository.findById(id).orElse(null);
    }

    @Override
    public void updateClerk(Clerk clerk) {
        clerkRepository.save(clerk);
    }

    @Override
    public void addNewClerk(Clerk clerk) {
        clerkRepository.save(clerk);
    }
}
