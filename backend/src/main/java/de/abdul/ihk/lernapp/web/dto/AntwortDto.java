package de.abdul.ihk.lernapp.web.dto;

public class AntwortDto {
    private Long antwortId;
    private String antwortText;

    public AntwortDto(Long antwortId, String antwortText) {
        this.antwortId = antwortId;
        this.antwortText = antwortText;
    }

    public Long getAntwortId() {
        return antwortId;
    }

    public String getAntwortText() {
        return antwortText;
    }
}
