package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
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
    @Bean
    CommandLineRunner start(PatientRepository patientRepository){

        return args -> {
            Stream.of("mouad","amine","karim","carlos").forEach(name->{
                Patient patient=new Patient();
                patient.setNom(name);
                patient.setMalade(false);
                patient.setDateNaissance(new Date());
                patient.setScore(12);
                patientRepository.save(patient);
            });
            for (Patient p:patientRepository.findAll()) {
                System.out.println(p.getNom());
            }
    };
    }
}
