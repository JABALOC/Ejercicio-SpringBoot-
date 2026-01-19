package com.hibernate.InstitutoMayor.dto;

public class AsignaturaDTO {

    private Long id;
    private String name;
    private Integer durationHours;

    public AsignaturaDTO(Long id, String name, Integer durationHours) {
        this.id = id;
        this.name = name;
        this.durationHours = durationHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }
}
