package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Vista;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class MainApp {
    public static int CAPACIDAD = 10;
    private static Vista vista;
    private static Modelo modelo;
    private static Controlador controlador;

    /*Crea el método main que nos mostrar? el men? de la aplicaci?n, nos pedir? una opci?n y la ejecutar? mientras no elijamos
    la opci?n salir. En caso de salir, la aplicaci?n mostrar? un mensaje de despedida.*/
    public static void main(String[] args) {
        vista = new Vista();
        modelo = new Modelo();
        controlador = new Controlador(modelo, vista);
        vista.comenzar();
        vista.terminar();
    }
}
