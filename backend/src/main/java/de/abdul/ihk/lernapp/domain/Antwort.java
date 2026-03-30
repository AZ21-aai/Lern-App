package de.abdul.ihk.lernapp.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "antwort")
public class Antwort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "antwort_id")
    private Long antwortId;

    @Column(name = "antwort_text", nullable = false)
    private String antwortText;

    @Column(name = "ist_korrekt", nullable = false)
    private boolean istKorrekt;

    @ManyToOne
    @JoinColumn(name = "frage_id", nullable = false)
    private Frage frage;

    // Getter/Setter
    public Long getAntwortId() {
        return antwortId;
    }

    public void setAntwortId(Long antwortId) {
        this.antwortId = antwortId;
    }

    public String getAntwortText() {
        return antwortText;
    }

    public void setAntwortText(String antwortText) {
        this.antwortText = antwortText;
    }

    public boolean isIstKorrekt() {
        return istKorrekt;
    }

    public void setIstKorrekt(boolean istKorrekt) {
        this.istKorrekt = istKorrekt;
    }

    public Frage getFrage() {
        return frage;
    }

    public void setFrage(Frage frage) {
        this.frage = frage;
    }
}
