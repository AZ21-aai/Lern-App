package de.abdul.ihk.lernapp.repo;

import de.abdul.ihk.lernapp.domain.Ergebnis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErgebnisRepository extends JpaRepository<Ergebnis, Long> {
}
