package ma.enset.patientsmvc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor //lombok va cree les getter et setter et constructeur sans params
public class Patient {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//creat id and autoincrement
        private long id;
        @Column(length = 50)
        @NotEmpty
        @Size(min=4,max=70)
        private String nom;
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date dateNaissance;
        private boolean malade;
        @DecimalMin(value="100")
        private int score;


}
