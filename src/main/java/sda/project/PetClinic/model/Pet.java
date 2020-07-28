package sda.project.PetClinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pets")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pet_names")
    @NotEmpty (message = "We hope he has a name")
    private String petName;


    @Column(name = "pet_races")
    @NotEmpty (message = "Is it a stray?")
    private String petRace;

    @Column (name = "pet_weight")
    private double petWeight;

    @Column (name = "birth_date")
    private Date dataOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client aClient;

    public void setClient(Client aClient) {
        this.aClient = aClient;
    }



    @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "petCategory_id")
    private PetCategory petCategory;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pet")
    private Consultation consultation;

}
