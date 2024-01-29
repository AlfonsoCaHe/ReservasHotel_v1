package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class Modelo {
    private int CAPACIDAD = 10;
    private Huespedes huespedes;
    private Habitaciones habitaciones;
    private Reservas reservas;

    public Modelo(){
        comenzar();
    }

    public void comenzar(){
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);
    }

    public void terminar(){
        System.out.println("************************************************************");
        System.out.println("\t\t\t\tEl modelo ha terminado.");
        System.out.println("************************************************************\n");
    }

    /*Crea los diferentes métodos insertar (para huesped, habitación y reserva).
    Crea los diferentes métodos buscar, cada uno de los cuales devolverá una nueva instancia del elemento encontrado si éste existe.
    Crea los diferentes métodos borrar (para huesped, habitación y reserva).
    Crea los diferentes métodos get, que deben devolver una nueva lista conteniendo nuevas instancias no una copia de los elementos.
    */
    public void insertar(Huesped huesped)throws OperationNotSupportedException {
        try{
            huespedes.insertar(huesped);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }catch(OperationNotSupportedException e){
            throw new OperationNotSupportedException(e.getMessage());
        }
    }

    public Huesped buscar(Huesped huesped){
        Huesped h;
        try{
            h = huespedes.buscar(huesped);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
        return h;
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException{
        try{
            huespedes.borrar(huesped);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }catch(OperationNotSupportedException e){
            throw new OperationNotSupportedException(e.getMessage());
        }
    }

    public Huesped[] getHuespedes(){
        return huespedes.get();
    }

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException{
        try{
            habitaciones.insertar(habitacion);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }catch(OperationNotSupportedException e){
            throw new OperationNotSupportedException(e.getMessage());
        }
    }

    public Habitacion buscar(Habitacion habitacion){
        Habitacion h;
        try {
            h = habitaciones.buscar(habitacion);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
        return h;
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException{
        try{
            habitaciones.borrar(habitacion);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }catch(OperationNotSupportedException e){
            throw new OperationNotSupportedException(e.getMessage());
        }
    }

    public Habitacion[] getHabitaciones(){
        return habitaciones.get();
    }

    public Habitacion[] getHabitaciones(TipoHabitacion tipoHabitacion){
        return habitaciones.get(tipoHabitacion);
    }

    public void insertar(Reserva reserva)throws OperationNotSupportedException{
        try{
            reservas.insertar(reserva);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }catch(OperationNotSupportedException e){
            throw new OperationNotSupportedException(e.getMessage());
        }
    }

    public Reserva buscar(Reserva reserva){
        Reserva r;
        try{
            r = reservas.buscar(reserva);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException{
        try{
            reservas.borrar(reserva);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }catch(OperationNotSupportedException e){
            throw new OperationNotSupportedException(e.getMessage());
        }
    }

    public Reserva[] getReservas(){
        return reservas.get();
    }

    public Reserva[] getReservas(Huesped huesped){
        Reserva[] r;
        try{
            int numReservasHuesped = 0;//Variable para saber cuantas reservas tiene un huesped
            for(int i = 0; i < reservas.getTamano(); i++){
                if(reservas.get()[i].getHuesped().getDni().equals(huesped.getDni())){
                    numReservasHuesped++;
                }
            }
            r = new Reserva[numReservasHuesped];//Creamos un array del tamaño de reservas del huesped
            int contador = 0;
            for(int i = 0; i < reservas.getTamano() && contador < numReservasHuesped; i++){//Recorremos las reservas y las vamos añadiendo al array
                if(reservas.get()[i].getHuesped().getDni().equals(huesped.getDni())){
                    r[contador] = reservas.get()[i];
                    contador++;
                }
            }
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion){
        Reserva[] r;
        try{
            r = reservas.getReservas(tipoHabitacion);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion){
        Reserva[] r;
        try{
            r = reservas.getReservasFuturas(habitacion);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha){
        try{
            reservas.realizarCheckin(reserva, fecha);
            //reservas.buscar(reserva).setCheckIn(fecha);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha){
        try{
            reservas.realizarCheckout(reserva, fecha);
            //reservas.buscar(reserva).setCheckOut(fecha);
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }catch(NullPointerException e){
            throw new NullPointerException(e.getMessage());
        }
    }
}
