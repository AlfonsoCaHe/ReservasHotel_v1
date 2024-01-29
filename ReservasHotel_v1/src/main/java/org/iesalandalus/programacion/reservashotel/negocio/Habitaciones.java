package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;

public class Habitaciones {
    private int capacidad;
    private int tamano;

    private Habitacion[] coleccionHabitaciones;

    /*Crea el constructor con parámetros que creará una lista de la capacidad indicada en el parámetro e inicializará los atributos
    de la clase a los valores correspondientes. */
    public Habitaciones(int capacidad){
        try {
            if(capacidad > 0){//Si la capacidad es al menos 1
                coleccionHabitaciones = new Habitacion[capacidad];
                for (int i = 0; i < capacidad; i++) {
                    coleccionHabitaciones[i] = null;
                }
                this.tamano = 0;
                this.capacidad = capacidad;
            }else{
                throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: La capacidad no puede ser nula.");
        }
    }

    /*Crea el método get que está sobrecargado y devolverá
    El método sin parámetros, una copia profunda de la colección haciendo uso del método copiaProfundaHabitaciones.
    */
    public Habitacion[] get() {
        return copiaProfundaHabitaciones();
    }

    public int getTamano() {
        return this.tamano;
    }

    public int getCapacidad(){
        return this.capacidad;
    }

    private Habitacion[] copiaProfundaHabitaciones(){
        Habitacion[] copiaHabitaciones = new Habitacion[tamano];
        for(int i = 0; i < tamano; i++){
            copiaHabitaciones[i] = new Habitacion(coleccionHabitaciones[i].getPlanta(), coleccionHabitaciones[i].getPuerta(), coleccionHabitaciones[i].getPrecio(), coleccionHabitaciones[i].getTipoHabitacion());
        }
        return copiaHabitaciones;
    }

    /*
    El método con el parámetro TipoHabitacion, un copia profunda de la colección pero de solo aquellas habitaciones cuyo tipo sea
    el indicado como parámetro.
     */

    public Habitacion[] get(TipoHabitacion tipoHabitacion) {
        int habitacionesDelTipo = 0;
        for(int i = 0; i < tamano; i++){//Calculamos el tamaño del array que vamos a devolver
            if(coleccionHabitaciones[i].getTipoHabitacion().equals(tipoHabitacion)){
                habitacionesDelTipo++;
            }
        }

        Habitacion[] copiaHabitaciones = new Habitacion[habitacionesDelTipo];//Creamos el array que devolveremos, lo haremos de este modo para que sea compacto
        int j = 0;//Usaremos esta variable para el índice del array que devolveremos
        for(int i = 0; i < tamano; i++){
            if(coleccionHabitaciones[i].getTipoHabitacion().equals(tipoHabitacion)){
                copiaHabitaciones[j] = new Habitacion(coleccionHabitaciones[i].getPlanta(), coleccionHabitaciones[i].getPuerta(), coleccionHabitaciones[i].getPrecio(), coleccionHabitaciones[i].getTipoHabitacion());
                j++;//Desplazamos el índice del array que devolveremos
            }
        }
        return copiaHabitaciones;
    }

    /*Se permitirán insertar habitaciones no nulas al final de la colección sin admitir repetidos.*/
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        try{
            if(habitacion != null) {
                if (tamano > 0) {//Si tamano es cero, significa que todavía no hay ninguna habitación, por lo que se insertará directamente
                    if (buscar(habitacion) == null) {
                        if (tamano < capacidad) {//Si queda espacio en la capacidad
                            coleccionHabitaciones[tamano] = habitacion;//Insertamos al final del array
                            tamano++;//Incrementamos el tamaño actual del array
                        } else {
                            throw new OperationNotSupportedException("ERROR: No se aceptan más habitaciones.");
                        }
                    } else {
                        throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
                    }
                } else {
                    coleccionHabitaciones[tamano] = habitacion;
                    tamano++;
                }
            }else{
                throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }
    }

    private int buscarIndice(Habitacion habitacion){
        if(habitacion == null)
            throw new NullPointerException("ERROR: No se puede buscar una habitación nula.");
        int indice = -1;//Establecemos el valor de índice en -1 para evaluar si existe una habitacion con dicho índice
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if (coleccionHabitaciones[i].equals(habitacion)) {
                encontrado = true;
                indice = i;
            }
        }
        return indice;
    }

    private boolean tamanoSuperado(int indice){
        return true;
    }

    private boolean CapacidadSuperada(int indice){
        return true;
    }

    /*El método buscar devolverá una habitación si ésta se encuentra en la colección y null en caso contrario.*/
    public Habitacion buscar(Habitacion habitacion){
        if(habitacion == null)
            throw new NullPointerException("ERROR: No se puede buscar una habitación nula.");
        Habitacion habitacionEncontrada = null;
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if(coleccionHabitaciones[i].equals(habitacion)) {
                encontrado = true;
                habitacionEncontrada = new Habitacion(habitacion.getPlanta(),habitacion.getPuerta(),habitacion.getPrecio(), habitacion.getTipoHabitacion());
            }
        }
        return habitacionEncontrada;
    }

    /*El método borrar, si la habitación se encuentra en la colección, la borrará y desplazará los elementos hacia la izquierda para dejar el array compactado.*/
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        try{
            int indice = buscarIndice(habitacion);
            if(indice != -1){
                coleccionHabitaciones[indice] = null;//Borramos la habitación
                desplazarUnaPosicionHaciaIzquierda(indice);//Compactamos el array desde la posición de la habitación borrada
            }else{
                throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        while(indice < tamano - 1){
            coleccionHabitaciones[indice] = coleccionHabitaciones[indice + 1];
            indice++;
        }
        coleccionHabitaciones[indice] = null;//Se borra el último registro de habitaciones para evitar duplicados
        tamano--;//Se reduce el número de habitaciones
    }
}
