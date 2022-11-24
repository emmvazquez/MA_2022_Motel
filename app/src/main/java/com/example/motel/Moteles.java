package com.example.motel;

public class Moteles {
    int idMotel;
    String snombre,sregion,smunicipio,sdireccion, sprecios,
            shorarios,sservicios,stelefono,spaginaweb;

    public Moteles() {
    }

    public Moteles(int idMotel, String snombre, String sregion, String smunicipio, String sdireccion,
                   String sprecios, String shorarios, String sservicios, String stelefono, String spaginaweb) {
        this.idMotel = idMotel;
        this.snombre = snombre;
        this.sregion = sregion;
        this.smunicipio = smunicipio;
        this.sdireccion = sdireccion;
        this.sprecios = sprecios;
        this.shorarios = shorarios;
        this.sservicios = sservicios;
        this.stelefono = stelefono;
        this.spaginaweb = spaginaweb;

    }

    public int getIdMotel() {
        return idMotel;
    }

    public void setIdMotel(int idMotel) {
        this.idMotel = idMotel;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public String getSregion() {
        return sregion;
    }

    public void setSregion(String sregion) {
        this.sregion = sregion;
    }

    public String getSmunicipio() {
        return smunicipio;
    }

    public void setSmunicipio(String smunicipio) {
        this.smunicipio = smunicipio;
    }

    public String getSdireccion() {
        return sdireccion;
    }

    public void setSdireccion(String sdireccion) {
        this.sdireccion = sdireccion;
    }

    public String getSprecios() {
        return sprecios;
    }

    public void setSprecios(String sprecios) {
        this.sprecios = sprecios;
    }

    public String getShorarios() {
        return shorarios;
    }

    public void setShorarios(String shorarios) {
        this.shorarios = shorarios;
    }

    public String getSservicios() {
        return sservicios;
    }

    public void setSservicios(String sservicios) {
        this.sservicios = sservicios;
    }

    public String getStelefono() {
        return stelefono;
    }

    public void setStelefono(String stelefono) {
        this.stelefono = stelefono;
    }

    public String getSpaginaweb() {
        return spaginaweb;
    }

    public void setSpaginaweb(String spaginaweb) {
        this.spaginaweb = spaginaweb;
    }
}
