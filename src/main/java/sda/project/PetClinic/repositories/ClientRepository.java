package sda.project.PetClinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.project.PetClinic.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
