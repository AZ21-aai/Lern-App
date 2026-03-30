package de.abdul.ihk.lernapp.web.dto;

public class ErgebnisDto {
    private Long benutzerId;
    private Long themaId;
    private int richtigeAntworten;
    private int gesamtFragen;

    public Long getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(Long benutzerId) {
        this.benutzerId = benutzerId;
    }

    public Long getThemaId() {
        return themaId;
    }

    public void setThemaId(Long themaId) {
        this.themaId = themaId;
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
}
