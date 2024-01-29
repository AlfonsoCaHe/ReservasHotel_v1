package org.iesalandalus.programacion.reservashotel.controlador;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista){
        try{
            if(modelo == null || vista == null){
                throw new NullPointerException("ERROR: El modelo y la vista no pueden ser nulos.");
            }
            this.modelo = modelo;
            this.vista = vista;
            vista.setControlador(this);
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public void comenzar(){
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar(){
        modelo.terminar();
        vista.terminar();
    }

    public void insertar(Huesped huesped)throws OperationNotSupportedException {
        try{
            modelo.insertar(huesped);
        }catch(IllegalArgumentException | OperationNotSupportedException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public Huesped buscar(Huesped huesped){
        Huesped h = null;
        try{
            h = modelo.buscar(huesped);
        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return h;
    }

    public void borrar(Huesped huesped){
        try{
            modelo.borrar(huesped);
        }catch(IllegalArgumentException | NullPointerException | OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }

    public Huesped[] getHuespedes(){
        return modelo.getHuespedes();
    }

    public void insertar(Habitacion habitacion)throws OperationNotSupportedException {
        try{
            modelo.insertar(habitacion);
        }catch(IllegalArgumentException | OperationNotSupportedException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public Habitacion buscar(Habitacion habitacion){
        Habitacion h = null;
        try{
            h = modelo.buscar(habitacion);
        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return h;
    }

    public void borrar(Habitacion habitacion){
        try{
            modelo.borrar(habitacion);
        }catch(IllegalArgumentException | NullPointerException | OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }

    public Habitacion[] getHabitaciones(){
        return modelo.getHabitaciones();
    }

    public Habitacion[] getHabitaciones(TipoHabitacion tipoHabitacion){
        Habitacion[] h = null;
        try{
            h = modelo.getHabitaciones(tipoHabitacion);
        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return h;
    }

    public void insertar(Reserva reserva)throws OperationNotSupportedException {
        try{
            modelo.insertar(reserva);
        }catch(IllegalArgumentException | OperationNotSupportedException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public Reserva buscar(Reserva reserva){
        Reserva r = null;
        try{
            r = modelo.buscar(reserva);
        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return r;
    }

    public void borrar(Reserva reserva){
        try{
            modelo.borrar(reserva);
        }catch(IllegalArgumentException | NullPointerException | OperationNotSupportedException e){
            System.out.println(e.getMessage());
        }
    }

    /*Crea el método realizarCheckin, que una vez localizada la reserva a la que realizar el checkin, deberá llamar al método
    correspondiente del controlador. Para localizar la reserva deberá preguntarse por el huesped de la misma, obtener su lista
    de reservas y establecer la fecha y hora de checkin de la reserva correspondiente. Hay que tener en cuenta que un huesped
    puede haber realizado varias reservas para el mismo día. Además, en caso de intentar hacer checkin de una reserva no
    existente en el día indicado para el huésped, la aplicación deberá informar con algún mensaje de lo sucedido.*/
    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha){
        try{
            modelo.realizarCheckIn(reserva, fecha);
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha){
        try{
            modelo.realizarCheckOut(reserva, fecha);
        }catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    public Reserva[] getReservas(){
        return modelo.getReservas();
    }

    public Reserva[] getReservas(Huesped huesped){
        Reserva[] r = null;
        try{
            r = modelo.getReservas(huesped);
        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return r;
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion){
        Reserva[] r = null;
        try{
            r = modelo.getReservas(tipoHabitacion);
        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return r;
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion){
        Reserva[] r = null;
        try{
            r = modelo.getReservasFuturas(habitacion);
        }catch(IllegalArgumentException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return r;
    }
}

