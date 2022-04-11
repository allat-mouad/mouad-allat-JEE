package ma.enset.etudiantsmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//creat id and autoincrement
    private long id;
    @Column(length = 50)
    @NotEmpty
    @Size(min=4,max=70)
    private String nom;
    @Size(min=4,max=70)
    private String email;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private boolean en_regle;


}
