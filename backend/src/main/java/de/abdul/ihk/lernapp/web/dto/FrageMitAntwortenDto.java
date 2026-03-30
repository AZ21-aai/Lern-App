package de.abdul.ihk.lernapp.web.dto;

import java.util.List;

public class FrageMitAntwortenDto {

    private Long frageId;
    private String frageText;
    private List<AntwortDto> antworten;

    public FrageMitAntwortenDto(Long frageId, String frageText, List<AntwortDto> antworten) {
        this.frageId = frageId;
        this.frageText = frageText;
        this.antworten = antworten;
    }

    public Long getFrageId() {
        return frageId;
    }

    public String getFrageText() {
        return frageText;
    }

    public List<AntwortDto> getAntworten() {
        return antworten;
    }
}
