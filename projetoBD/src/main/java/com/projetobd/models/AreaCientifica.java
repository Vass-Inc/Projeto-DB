package com.projetobd.models;

public class AreaCientifica {
    private int idArea;
    private String area;

    public AreaCientifica(int idArea, String area) {
        this.idArea = idArea;
        this.area = area;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
