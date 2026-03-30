package de.abdul.ihk.lernapp.web;

import de.abdul.ihk.lernapp.domain.Antwort;
import de.abdul.ihk.lernapp.domain.Benutzer;
import de.abdul.ihk.lernapp.domain.Ergebnis;
import de.abdul.ihk.lernapp.domain.Frage;
import de.abdul.ihk.lernapp.domain.Thema;
import de.abdul.ihk.lernapp.repo.AntwortRepository;
import de.abdul.ihk.lernapp.repo.ErgebnisRepository;
import de.abdul.ihk.lernapp.repo.FrageRepository;
import de.abdul.ihk.lernapp.repo.ThemaRepository;
import de.abdul.ihk.lernapp.web.dto.AntwortDto;
import de.abdul.ihk.lernapp.web.dto.FrageMitAntwortenDto;
import de.abdul.ihk.lernapp.web.dto.ErgebnisDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final FrageRepository frageRepository;
    private final AntwortRepository antwortRepository;
    private final ErgebnisRepository ergebnisRepository;
    private final ThemaRepository themaRepository;

    public QuizController(FrageRepository frageRepository,
                          AntwortRepository antwortRepository,
                          ErgebnisRepository ergebnisRepository,
                          ThemaRepository themaRepository) {
        this.frageRepository = frageRepository;
        this.antwortRepository = antwortRepository;
        this.ergebnisRepository = ergebnisRepository;
        this.themaRepository = themaRepository;
    }

    // --- Themen laden ---
    @GetMapping("/themen")
    public List<Thema> getThemen() {
        return themaRepository.findAll();
    }

    // --- Fragen + Antworten zu einem Thema laden ---
    @GetMapping("/fragen")
    public List<FrageMitAntwortenDto> getFragen(@RequestParam Long themaId) {
        List<Frage> fragen = frageRepository.findByThema_ThemaId(themaId);

        return fragen.stream()
                .map(frage -> {
                    List<AntwortDto> antworten = antwortRepository.findByFrage(frage)
                            .stream()
                            .map(a -> new AntwortDto(a.getAntwortId(), a.getAntwortText()))
                            .collect(Collectors.toList());

                    return new FrageMitAntwortenDto(
                            frage.getFrageId(),
                            frage.getFrageText(),
                            antworten
                    );
                })
                .collect(Collectors.toList());
    }

    // --- Ergebnis speichern ---
    @PostMapping("/ergebnis")
    public Ergebnis saveErgebnis(@RequestBody ErgebnisDto dto) {
        Ergebnis e = new Ergebnis();
        e.setRichtigeAntworten(dto.getRichtigeAntworten());
        e.setGesamtFragen(dto.getGesamtFragen());

        // Benutzer zuordnen
        Benutzer b = new Benutzer();
        b.setBenutzerId(dto.getBenutzerId());
        e.setBenutzer(b);

        // Thema zuordnen
        Thema t = new Thema();
        t.setThemaId(dto.getThemaId());
        e.setThema(t);

        return ergebnisRepository.save(e);
    }
}
