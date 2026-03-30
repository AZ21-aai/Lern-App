package de.abdul.ihk.lernapp.repo;

import de.abdul.ihk.lernapp.domain.Thema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemaRepository extends JpaRepository<Thema, Long> {
}
