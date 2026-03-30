package de.abdul.ihk.lernapp.repo;

import de.abdul.ihk.lernapp.domain.Antwort;
import de.abdul.ihk.lernapp.domain.Frage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AntwortRepository extends JpaRepository<Antwort, Long> {

    List<Antwort> findByFrage(Frage frage);
}
