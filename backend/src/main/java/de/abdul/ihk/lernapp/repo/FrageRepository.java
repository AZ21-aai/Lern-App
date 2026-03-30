package de.abdul.ihk.lernapp.repo;

import de.abdul.ihk.lernapp.domain.Frage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FrageRepository extends JpaRepository<Frage, Long> {

    // Alle Fragen zu einem Thema
    List<Frage> findByThema_ThemaId(Long themaId);
}
