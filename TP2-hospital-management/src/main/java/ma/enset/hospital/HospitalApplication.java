package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.ConsultationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import ma.enset.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean
	CommandLineRunner start(IHospitalService hospitalService,PatientRepository patientRepository
	,MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository){
		return args -> {
			Stream.of("mouad","amine","karim","carlos").forEach(name->{
				Patient patient=new Patient();
				patient.setNom(name);
				patient.setMalade(false);
				patient.setDateNaissance(new Date());
				hospitalService.savePatient(patient);
			});

			Stream.of("ayman","hanan","yasmine").forEach(name->{
				Medecin medecin=new Medecin();
				medecin.setNom(name);
				medecin.setEmail(name+"@.com");
				medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
				hospitalService.saveMedecin(medecin);
			});
			Patient patient= patientRepository.findByNom("mouad");
			Medecin medecin= medecinRepository.findByNom("ayman");

			RendezVous rendezVous=new RendezVous();
			rendezVous.setDateRDV(new Date());
			rendezVous.setStatut(StatutRDV.DONE);
			rendezVous.setPatient(patient);
			rendezVous.setMedecin(medecin);
			hospitalService.saveRDV(rendezVous);

			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
			Consultation consultation=new Consultation();
			consultation.setDateConsulation(rendezVous1.getDateRDV());
			consultation.setRendezVous(rendezVous);
			hospitalService.saveConsultation(consultation);

		};
	}

}
