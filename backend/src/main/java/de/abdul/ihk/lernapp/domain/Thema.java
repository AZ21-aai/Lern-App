package de.abdul.ihk.lernapp.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "thema")
public class Thema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thema_id")
    private Long themaId;

    @Column(nullable = false)
    private String titel;

    // Getter und Setter
    public Long getThemaId() {
        return themaId;
    }

    public void setThemaId(Long themaId) {
        this.themaId = themaId;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}
