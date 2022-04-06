package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import ma.enset.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    //@Bean
    CommandLineRunner start(PatientRepository patientRepository){

        return args -> {
            Stream.of("mouad","amine","karim","carlos").forEach(name->{
                Patient patient=new Patient();
                patient.setNom(name);
                patient.setMalade(false);
                patient.setDateNaissance(new Date());
                patient.setScore(120);
                patientRepository.save(patient);
            });
            for (Patient p:patientRepository.findAll()) {
                System.out.println(p.getNom());
            }
    };
    }

    //@Bean
    CommandLineRunner start(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mouad","1234","1234");
            securityService.saveNewUser("tomas","1234","1234");
            securityService.saveNewUser("karim","1234","1234");
            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.AddRoleToUser("mouad","ADMIN");
            securityService.AddRoleToUser("mouad","USER");
            securityService.AddRoleToUser("tomas","USER");
            securityService.AddRoleToUser("karim","USER");

        };
}
}
