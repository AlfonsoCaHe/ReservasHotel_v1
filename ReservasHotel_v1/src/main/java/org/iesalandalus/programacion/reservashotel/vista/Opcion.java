package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {
    INSERTAR_HUESPED("Insertar huésped."),
    BUSCAR_HUESPED("Buscar huésped."),
    BORRAR_HUESPED("Borrar huésped."),
    MOSTRAR_HUESPEDES("Mostrar huéspedes."),
    INSERTAR_HABITACION("Insertar habitación."),
    BUSCAR_HABITACION("Buscar habitación."),
    BORRAR_HABITACION("Borrar habitación."),
    MOSTRAR_HABITACIONES("Mostrar habitaciones."),
    INSERTAR_RESERVA("Insertar reserva."),
    ANULAR_RESERVA("Anular reserva."),
    MOSTRAR_RESERVAS("Mostrar reservas."),
    CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad."),
    REALIZAR_CHECKIN("Realizar CheckIn."),
    REALIZAR_CHECKOUT("Realizar CheckOut."),
    SALIR("Salir.");

    private String cadenaAMostrar;
    private Opcion(String cadenaAMostrar){
        this.cadenaAMostrar = cadenaAMostrar;
    }

    @Override
    public String toString() {
        return (ordinal()+1)+".- "+cadenaAMostrar;
    }
}