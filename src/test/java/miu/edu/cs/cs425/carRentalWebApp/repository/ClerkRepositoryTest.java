package miu.edu.cs.cs425.carRentalWebApp.repository;

import miu.edu.cs.cs425.carRentalWebApp.model.Clerk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
@SpringBootTest
public class ClerkRepositoryTest {
    @Autowired
    private ClerkRepository clerkRepository;

    private Clerk initCleak() {
        Clerk clerk = new Clerk();
        clerk.setFirstName("Hey");
        clerk.setMiddleName("midle");
        clerk.setLastName("Last");
        clerk.setEmail("email");
        clerk.setCreateDate(LocalDateTime.now());
        clerk.setLastUpdate(LocalDateTime.now());
        return clerk;
    }
    @Test
    public void shouldAddClerk() {
        Clerk clerk = initCleak();
        //TODO Set values of car.
        Clerk savedClerk = clerkRepository.save(clerk);
        Assertions.assertEquals(savedClerk.getId(), 1 );
    }
    @Test
    public void shouldNotaddClerkWithNullFirstName() {
        try {
            Clerk clerk = initCleak();
            clerk.setFirstName(null);
            //TODO Set values of car.
            Clerk savedClerk = clerkRepository.save(clerk);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("First name can't be empty"));
        }
    }

    @Test
    public void shouldNotaddClerkWithNullLastName() {
        try {
            Clerk clerk = initCleak();
            clerk.setLastName(null);
            //TODO Set values of car.
            Clerk savedClerk = clerkRepository.save(clerk);
        } catch (Exception ex) {
            Assertions.assertTrue(ex.getMessage().contains("Last Name can't be empty"));
        }
    }
    @Test
    public void shouldNotaddClerkWithNullEmail(){
        try{
            Clerk clerk = initCleak();
            clerk.setEmail(null);
            Clerk savedClerk = clerkRepository.save(clerk);
        }catch (Exception e){
            Assertions.assertTrue(e.getMessage().contains("email can't be empty"));
        }
    }
    @Test
    public void shouldNotaddClerkWithNullCreateDate(){
        try{
            Clerk clerk = initCleak();
            clerk.setCreateDate(null);
            Clerk savedClerk = clerkRepository.save(clerk);
        }catch (Exception e){
            Assertions.assertTrue(e.getMessage().contains("Create date can't be null"));
        }

    }
    @Test
    public void shouldNotaddClerkWithNullLastUpdate(){
        try{
            Clerk clerk = initCleak();
            clerk.setLastUpdate(null);
            Clerk savedClerk = clerkRepository.save(clerk);
        }catch(Exception ex){
            Assertions.assertTrue(ex.getMessage().contains("Last Update date can't be null"));
        }
    }
}
