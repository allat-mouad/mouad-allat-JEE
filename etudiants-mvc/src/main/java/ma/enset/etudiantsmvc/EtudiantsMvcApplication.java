package ma.enset.etudiantsmvc;

import ma.enset.etudiantsmvc.entities.Etudiant;
import ma.enset.etudiantsmvc.entities.Genre;
import ma.enset.etudiantsmvc.repositories.EtudiantRepository;
import ma.enset.etudiantsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class EtudiantsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtudiantsMvcApplication.class, args);
    }
    //@Bean
    CommandLineRunner start(EtudiantRepository etudiantRepository){

        return args -> {
            Stream.of("alex","islam","karim","imane").forEach(name->{
                Etudiant etudiant=new Etudiant();
                etudiant.setNom(name);
                etudiant.setEn_regle(false);
                etudiant.setGenre(Math.random()>0.5? Genre.FEMININ:Genre.MASCULIN);
                etudiant.setDateNaissance(new Date());
                etudiant.setEmail(name+"@gmail.com");
                etudiantRepository.save(etudiant);
            });
            for (Etudiant p:etudiantRepository.findAll()) {
                //System.out.println(p.getNom());
            }
        };
    }
    @Bean //au demarage cree  un objet PasswordEncoder et place dans ton contexte
    PasswordEncoder mypasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

   // @Bean
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
