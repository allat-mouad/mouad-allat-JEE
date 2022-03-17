package ma.enset.hospital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//creat id and autoincrement
    private long id;
    @Temporal(TemporalType.DATE)
    private Date dateConsulation;
    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private  RendezVous rendezVous;


}
