package de.abdul.ihk.lernapp.domain;

import jakarta.persistence.*;

@Entity
public class Ergebnis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ergebnis_id")
    private Long ergebnisId;

    @Column(name = "richtige_antworten", nullable = false)
    private int richtigeAntworten;

    @Column(name = "gesamt_fragen", nullable = false)
    private int gesamtFragen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "benutzer_id")
    private Benutzer benutzer;     // <-- ganz normaler Entity-Typ

    @ManyToOne(optional = false)
    @JoinColumn(name = "thema_id")
    private Thema thema;

    public Ergebnis() {
    }

    // Getter / Setter
    public Long getErgebnisId() {
        return ergebnisId;
    }

    public void setErgebnisId(Long ergebnisId) {
        this.ergebnisId = ergebnisId;
    }

    public int getRichtigeAntworten() {
        return richtigeAntworten;
    }

    public void setRichtigeAntworten(int richtigeAntworten) {
        this.richtigeAntworten = richtigeAntworten;
    }

    public int getGesamtFragen() {
        return gesamtFragen;
    }

    public void setGesamtFragen(int gesamtFragen) {
        this.gesamtFragen = gesamtFragen;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema thema) {
        this.thema = thema;
    }
}
