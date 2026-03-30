package de.abdul.ihk.lernapp.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benutzer_id")
    private Long benutzerId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwort;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "benutzer")
    private List<Ergebnis> ergebnisse = new ArrayList<>();

    public Benutzer() {
    }

    // Getter / Setter
    public Long getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(Long benutzerId) {
        this.benutzerId = benutzerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ergebnis> getErgebnisse() {
        return ergebnisse;
    }

    public void setErgebnisse(List<Ergebnis> ergebnisse) {
        this.ergebnisse = ergebnisse;
    }
}
