package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import javax.naming.OperationNotSupportedException;

public class Huespedes {

    private int capacidad;
    private int tamano;
    private Huesped[] coleccionHuespedes;

    /*Crea el constructor con parámetros que creará una lista de la capacidad indicada en el parámetro e inicializará los atributos
    de la clase a los valores correspondientes.
    */
    public Huespedes(int capacidad){
        try{
            if(capacidad > 0) {
                this.tamano = 0;
                this.capacidad = capacidad;
                coleccionHuespedes = new Huesped[capacidad];
                for (int i = 0; i < capacidad; i++) {
                    coleccionHuespedes[i] = null;
                }
            }else{
                throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: La capacidad no puede ser nula.");
        }

    }

    /*El método get devolverá una copia profunda de la colección haciendo uso del método copiaProfundaHuespedes.*/
    public Huesped[] get(){
        return copiaProfundaHuespedes();
    }

    private Huesped[] copiaProfundaHuespedes(){
        Huesped[] copiaHuespedes = new Huesped[tamano];
        for(int i = 0; i < tamano; i++){
            copiaHuespedes[i] = new Huesped(coleccionHuespedes[i].getNombre(), coleccionHuespedes[i].getDni(), coleccionHuespedes[i].getCorreo(), coleccionHuespedes[i].getTelefono(), coleccionHuespedes[i].getFechaNacimiento());
        }
        return copiaHuespedes;
    }

    public int getTamano(){
        return tamano;
    }

    public int getCapacidad(){
        return capacidad;
    }

    /*Se permitirán insertar huéspedes no nulos al final de la colección sin admitir repetidos.*/
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        try{
            if(huesped != null) {
                if (tamano > 0) {//Si tamano es cero, significa que todavía no hay ningún huésped, por lo que se insertará directamente
                    if (buscar(huesped) == null) {
                        if (tamano < capacidad) {//Si queda espacio en la capacidad
                            coleccionHuespedes[tamano] = huesped;//Insertamos al final del array
                            tamano++;//Incrementamos el tamaño actual del array
                        } else {
                            throw new OperationNotSupportedException("ERROR: No se aceptan más huéspedes.");
                        }
                    } else {
                        throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
                    }
                } else {
                    coleccionHuespedes[tamano] = huesped;
                    tamano++;
                }
            }else{
                throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }
    }

    private int buscarIndice(Huesped huesped){
        if(huesped == null)
            throw new NullPointerException("ERROR: No se puede buscar un huésped nulo.");
        int indice = -1;//Establecemos el valor de índice en -1 para evaluar si existe un huesped con dicho índice
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if (coleccionHuespedes[i].equals(huesped)) {
                encontrado = true;
                indice = i;
            }
        }
        return indice;
    }

    private boolean tamanoSuperado(int indice){
        return true;
    }

    private boolean capacidadSuperada(int indice){
        boolean capacidadSuperada = (tamano < capacidad);
        return capacidadSuperada;
    }

    /*El método buscar devolverá un huésped si éste se encuentra en la colección y null en caso contrario.*/
    public Huesped buscar(Huesped huesped){
        if(huesped == null)
            throw new NullPointerException("ERROR: No se puede buscar un huésped nulo.");
        Huesped huespedEncontrado = null;
        boolean encontrado = false;
        for(int i = 0; i < tamano && !encontrado; i++){
            if(coleccionHuespedes[i].equals(huesped)) {
                encontrado = true;
                huespedEncontrado = new Huesped(huesped.getNombre(), huesped.getDni(), huesped.getCorreo(), huesped.getTelefono(), huesped.getFechaNacimiento());
            }
        }
        return huespedEncontrado;
    }

    /*El método borrar, si el huésped se encuentra en la colección, lo borrará y desplazará los elementos hacia la izquierda para
    dejar el array compactado.*/
    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        try{
            int indice = buscarIndice(huesped);
            if(indice != -1){
                coleccionHuespedes[indice] = null;//Borramos al huésped
                desplazarUnaPosicionHaciaIzquierda(indice);//Compactamos el array desde la posición del huésped borrado
            }else{
                throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }

    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        while(indice < tamano - 1){
            coleccionHuespedes[indice] = coleccionHuespedes [indice + 1];
            indice++;
        }
        coleccionHuespedes[indice] = null;//Se borra el último registro de huéspede para evitar duplicados
        tamano--;//Se reduce el número de huéspedes
    }
}
