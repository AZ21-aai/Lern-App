package de.abdul.ihk.lernapp.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "frage")
public class Frage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frage_id")
    private Long frageId;

    @Column(name = "frage_text", nullable = false)
    private String frageText;

    @ManyToOne
    @JoinColumn(name = "thema_id", nullable = false)
    private Thema thema;

    // Getter/Setter
    public Long getFrageId() {
        return frageId;
    }

    public void setFrageId(Long frageId) {
        this.frageId = frageId;
    }

    public String getFrageText() {
        return frageText;
    }

    public void setFrageText(String frageText) {
        this.frageText = frageText;
    }

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema thema) {
        this.thema = thema;
    }
}
