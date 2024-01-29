package org.iesalandalus.programacion.reservashotel.dominio;

public enum Regimen {
    SOLO_ALOJAMIENTO("SOLO_ALOJAMIENTO", 0),
    ALOJAMIENTO_DESAYUNO("ALOJAMIENTO_DESAYUNO", 15),
    MEDIA_PENSION("MEDIA_PENSIÓN", 30),
    PENSION_COMPLETA("PENSIÓN_COMPLETA", 50);
    private String cadenaAMostrar;
    private Double incrementoPrecio;

    private Regimen(String cadenaAMostrar, double incrementoPrecio){
        this.cadenaAMostrar = cadenaAMostrar;
        this.incrementoPrecio = incrementoPrecio;
    }

    public Double getIncrementoPrecio() {
        return incrementoPrecio;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
