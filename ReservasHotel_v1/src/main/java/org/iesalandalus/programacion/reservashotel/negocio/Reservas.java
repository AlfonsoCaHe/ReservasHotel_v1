package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class Reservas {
    private int capacidad;
    private int tamano;
    private Reserva[] coleccionReservas;

    /*Crea el constructor con parámetros que creará una lista de la capacidad indicada en el parámetro e inicializará los atributos de la clase a los valores correspondientes.*/
    public Reservas(int capacidad) {
        try {
            if (capacidad > 0) {//Si la capacidad es al menos 1
                coleccionReservas = new Reserva[capacidad];
                for (int i = 0; i < capacidad; i++) {
                    coleccionReservas[i] = null;
                }
                this.tamano = 0;
                this.capacidad = capacidad;
            } else {
                throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("ERROR: La capacidad no puede ser nula.");
        }
    }

    /*Crea el método get que devolverá una copia profunda de la colección haciendo uso del método copiaProfundaReservas.*/
    public Reserva[] get() {
        return copiaProfundaReservas();
    }

    /*private Reserva[] copiaProfundaReservas() {
        Reserva[] copiaReservas = new Reserva[tamano];
        for (int i = 0; i < tamano; i++) {
                copiaReservas[i] = new Reserva(coleccionReservas[i].getHuesped(), coleccionReservas[i].getHabitacion(), coleccionReservas[i].getRegimen(), coleccionReservas[i].getFechaInicioReserva(), coleccionReservas[i].getFechaFinReserva(), coleccionReservas[i].getNumeroPersonas());
        }
        return copiaReservas;
    }*/

    private Reserva[] copiaProfundaReservas() {
        Reserva[] copiaReservas = new Reserva[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaReservas[i] = new Reserva(coleccionReservas[i]);
        }
        return copiaReservas;
    }

    public int getTamano() {
        return this.tamano;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    /*Se permitirán insertar reservas no nulas al final de la colección sin admitir repetidos.*/
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        try {
            if (reserva != null) {
                if (tamano > 0) {//Si tamano es cero, significa que todavía no hay ningún huésped, por lo que se insertará directamente
                    if (buscar(reserva) == null) {
                        if (tamano < capacidad) {//Si queda espacio en la capacidad
                            coleccionReservas[tamano] = reserva;//Insertamos al final del array
                            tamano++;//Incrementamos el tamaño actual del array
                        } else {
                            throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
                        }
                    } else {
                        throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
                    }
                } else {
                    coleccionReservas[tamano] = reserva;
                    tamano++;
                }
            } else {
                throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
    }

    private int buscarIndice(Reserva reserva) {
        if (reserva == null)
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
        int indice = -1;//Establecemos el valor de índice en -1 para evaluar si existe una reserva con dicho índice
        boolean encontrado = false;
        for (int i = 0; i < tamano && !encontrado; i++) {
            if (coleccionReservas[i].equals(reserva)) {
                encontrado = true;
                indice = i;
            }
        }
        return indice;
    }

    private boolean tamanoSuperado(int indice) {
        return true;
    }

    private boolean capacidadSuperada(int indice) {
        return true;
    }

    /*El método buscar devolverá una reserva si ésta se encuentra en la colección y null en caso contrario.*/
    public Reserva buscar(Reserva reserva) {
        if (reserva == null)
            throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
        Reserva reservaEncontrada = null;
        boolean encontrado = false;
        for (int i = 0; i < tamano && !encontrado; i++) {
            if (coleccionReservas[i].equals(reserva)) {
                encontrado = true;
                reservaEncontrada = new Reserva(reserva.getHuesped(), reserva.getHabitacion(), reserva.getRegimen(), reserva.getFechaInicioReserva(), reserva.getFechaFinReserva(), reserva.getNumeroPersonas());
                //reservaEncontrada = coleccionReservas[i];
            }
        }
        return reservaEncontrada;
    }

    /*El método borrar, si la reserva se encuentra en la colección, la borrará y desplazará los elementos hacia la izquierda
    para dejar el array compactado.
    */
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        try {
            int indice = buscarIndice(reserva);
            if (indice != -1) {
                coleccionReservas[indice] = null;//Borramos la reserva
                desplazarUnaPosicionHaciaIzquierda(indice);//Compactamos el array desde la posición de la reserva borrada
            } else {
                throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }

    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        while (indice < tamano - 1) {
            coleccionReservas[indice] = coleccionReservas[indice + 1];
            indice++;
        }
        coleccionReservas[indice] = null;//Se borra el último registro de una reserva para evitar duplicados
        tamano--;//Se reduce el número de reservas
    }

    /*El método getReservas que está sobrecargado y devolverá una colección de las reservas realizadas por el huésped pasado
    por parámetro o una colección de las reservas realizadas para el tipo de habitación indicada como parámetro.
    */
    public Reserva[] getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");
        }
        int reservasHuesped = 0;
        for (int i = 0; i < tamano; i++) {//Calculamos el tamaño del array que vamos a devolver
            if (coleccionReservas[i].getHuesped().equals(huesped)) {
                reservasHuesped++;
            }
        }

        Reserva[] copiaReservas = new Reserva[reservasHuesped];//Creamos el array que devolveremos, lo haremos de este modo para que sea compacto
        int j = 0;//Usaremos esta variable para el índice del array que devolveremos
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].getHuesped().equals(huesped)) {
                copiaReservas[j] = new Reserva(coleccionReservas[i].getHuesped(), coleccionReservas[i].getHabitacion(), coleccionReservas[i].getRegimen(), coleccionReservas[i].getFechaInicioReserva(), coleccionReservas[i].getFechaFinReserva(), coleccionReservas[i].getNumeroPersonas());
                j++;//Desplazamos el índice del array que devolveremos
            }
        }
        return copiaReservas;
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
        int reservasDelTipo = 0;
        for (int i = 0; i < tamano; i++) {//Calculamos el tamaño del array que vamos a devolver
            if (coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasDelTipo++;
            }
        }

        Reserva[] copiaReservas = new Reserva[reservasDelTipo];//Creamos el array que devolveremos, lo haremos de este modo para que sea compacto
        int j = 0;//Usaremos esta variable para el índice del array que devolveremos
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                copiaReservas[j] = new Reserva(coleccionReservas[i].getHuesped(), coleccionReservas[i].getHabitacion(), coleccionReservas[i].getRegimen(), coleccionReservas[i].getFechaInicioReserva(), coleccionReservas[i].getFechaFinReserva(), coleccionReservas[i].getNumeroPersonas());
                j++;//Desplazamos el índice del array que devolveremos
            }
        }
        return copiaReservas;
    }


    /*El método getReservasFuturas que devolverá una colección de las reservas realizadas para la habitación indicada como
    parámetro y que sean posteriores a la fecha de hoy.
    */
    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }
        int reservasHabitacion = 0;
        for (int i = 0; i < tamano; i++) {//Calculamos el tamaño del array que vamos a devolver
            if (coleccionReservas[i].getHabitacion().equals(habitacion)) {//Si la habitación coincide
                if (coleccionReservas[i].getFechaInicioReserva().atStartOfDay().isAfter(LocalDateTime.now())) {//Si la fecha es posterior a hoy
                    reservasHabitacion++;
                }
            }
        }

        Reserva[] copiaReservas = new Reserva[reservasHabitacion];//Creamos el array que devolveremos, lo haremos de este modo para que sea compacto
        int j = 0;//Usaremos esta variable para el índice del array que devolveremos
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].getHabitacion().equals(habitacion)) {
                if (coleccionReservas[i].getFechaInicioReserva().atStartOfDay().isAfter(LocalDateTime.now())) {
                    copiaReservas[j] = new Reserva(coleccionReservas[i].getHuesped(), coleccionReservas[i].getHabitacion(), coleccionReservas[i].getRegimen(), coleccionReservas[i].getFechaInicioReserva(), coleccionReservas[i].getFechaFinReserva(), coleccionReservas[i].getNumeroPersonas());
                    j++;//Desplazamos el índice del array que devolveremos
                }
            }
        }
        return copiaReservas;
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
        try {
            int indice = buscarIndice(reserva);
            //reserva.setCheckIn(fecha);
            coleccionReservas[indice].setCheckIn(fecha);
        }catch(NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
        try {
            int indice = buscarIndice(reserva);
            //reserva.setCheckOut(fecha);
            coleccionReservas[indice].setCheckOut(fecha);
        }catch(NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}