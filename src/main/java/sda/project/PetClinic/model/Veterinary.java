package sda.project.PetClinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "veterinaries")
public class Veterinary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "You forgot to type the first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "You forgot to type the last name")
    private String lastName;

    @Column
    @NotNull(message = "Please select the speciality") // o sa fie un drop-down
    private String speciality;
    @Column
    private boolean isVaccinated;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "veterinary")
    private Consultation consultation;

}
