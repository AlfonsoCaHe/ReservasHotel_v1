package org.iesalandalus.programacion.reservashotel.dominio;

import java.time.LocalDate;
import java.lang.String;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Huesped {
    private String ER_TELEFONO = "[0-9]{9}";
    private String ER_CORREO = "([a-z0-9._]+)@([a-z]+(.com|.org|.es))";
    private String ER_DNI = "([0-9]{8})([a-hj-np-zA-HJ-NP-Z])";
    public String FORMATO_FECHA = "(0[1-9]|[1-3][0-9])/(0[1-9]|1[0-2])/([0-9]{4})";
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;

    /*Crea el constructor con parámetros que hará uso de los métodos de modificación.*/
    public Huesped(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento){
        try {
            setNombre(nombre);
            setTelefono(telefono);
            setCorreo(correo);
            setDni(dni);
            setFechaNacimiento(fechaNacimiento);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /*Crea el constructor copia*/
    public Huesped (Huesped huesped){
        try{
            this.nombre = huesped.getNombre();
            this.telefono = huesped.getTelefono();
            this.correo = huesped.getCorreo();
            this.dni = huesped.getDni();
            this.fechaNacimiento = huesped.getFechaNacimiento();
        }catch(NullPointerException ne){
            throw new NullPointerException("ERROR: No es posible copiar un huésped nulo.");
        }

    }

    /*
    Este método debe normalizar un nombre eliminando caracteres en blanco de sobra y poniendo en mayúsculas la primera letra de
    cada palabra y en minúsculas las demás.*/
    private String formateaNombre(String nombre){
        nombre = nombre.trim();//Primero limpiamos los espacios en blanco al principio y al final
        String nombreformateado = "";//Creamos una variable que nos permita almacenar el nombre correctamente formateado
        String[] arrayauxiliar = nombre.split("\s");
        for(int i = 0; i < arrayauxiliar.length; i++) {//Recorremos cada una de las palabras y establecemos su formato correcto
            if(arrayauxiliar[i] != ""){//Eliminamos los espacios adicionales
                String auxiliarminus = arrayauxiliar[i].toLowerCase();//Todas las letras pasan a ser minúsculas
                String auxiliarmayus = arrayauxiliar[i].toUpperCase();//Todas las letras mayúsculas
                nombreformateado += auxiliarminus.replaceFirst(".", "" + auxiliarmayus.charAt(0)) + " ";//Sustituimos la primera letra de la cadena de minúsculas por la letra mayúscula de la cadena de mayúsculas y añadimos un espacio para separar las palabras.
            }
        }
        nombreformateado = nombreformateado.trim();//Reformateamos para eliminar el último caracter espacio
        if(nombreformateado.equals(""))//Si al formatear el nombre, obtenemos una cadena vacía
            throw new IllegalArgumentException("ERROR: El nombre de un huésped no puede estar vacío.");
        return nombreformateado;
    }

    /*Este método hará uso de los grupos de las expresiones regulares (para ello has debido definir la expresión regular con grupos)
     para quedarse con el número por un lado y con la letra por otro. Para saber si la letra es válida puedes seguir las
     instrucciones del siguiente enlace: Comprobar letra dni.*/
    private boolean comprobarLetraDni(String dni){
        boolean valido = false;

        Pattern patron = Pattern.compile(ER_DNI);//Creamos un patron mediante una expresión regular por grupos
        Matcher acomplamiento = patron.matcher(dni);

        String valor = null;//Creamos una variable para guardar el primer grupo
        String letra = null;//Creamos una variable para guardar el segundo grupo (LETRA)

        if(acomplamiento.matches()){
            valor = acomplamiento.group(1);
            letra = acomplamiento.group(2);
        }

        int valorletra = 0;//Creamos una variable auxiliar para calcular la suma de los dígitos del DNI
        valorletra = Integer.parseInt(valor);

        valorletra = valorletra % 23;//Realizamos el cálculo del módulo sobre 23 para comprobar que la letra coincida

        switch (valorletra) {
            case 0:
                valido = letra.matches("[tT]");
                break;
            case 1:
                valido = letra.matches("[rR]");
                break;
            case 2:
                valido = letra.matches("[wW]");
                break;
            case 3:
                valido = letra.matches("[aA]");
                break;
            case 4:
                valido = letra.matches("[gG]");
                break;
            case 5:
                valido = letra.matches("[mM]");
                break;
            case 6:
                valido = letra.matches("[yY]");
                break;
            case 7:
                valido = letra.matches("[fF]");
                break;
            case 8:
                valido = letra.matches("[pP]");
                break;
            case 9:
                valido = letra.matches("[dD]");
                break;
            case 10:
                valido = letra.matches("[xX]");
                break;
            case 11:
                valido = letra.matches("[bB]");
                break;
            case 12:
                valido = letra.matches("[nN]");
                break;
            case 13:
                valido = letra.matches("[jJ]");
                break;
            case 14:
                valido = letra.matches("[zZ]");
                break;
            case 15:
                valido = letra.matches("[sS]");
                break;
            case 16:
                valido = letra.matches("[qQ]");
                break;
            case 17:
                valido = letra.matches("[vV]");
                break;
            case 18:
                valido = letra.matches("[hH]");
                break;
            case 19:
                valido = letra.matches("[lL]");
                break;
            case 20:
                valido = letra.matches("[cC]");
                break;
            case 21:
                valido = letra.matches("[kK]");
                break;
            case 22:
                valido = letra.matches("[eE]");
        }

        if(!valido){
            throw new IllegalArgumentException("ERROR: La letra del dni del huésped no es correcta.");
        }

        return valido;
    }

   /*Crea los métodos de acceso y modificación de cada atributo con la visibilidad adecuada, teniendo en cuenta que un nombre
   estará compuesto de palabras separadas por un espacio y cada palabra comenzará con una mayúscula y continuará con minúsculas.
   El DNI, el teléfono y el correo deben también tener un formato válido. Además debes comprobar que la letra del DNI sea
   correcta. Debes crear las constantes para las expresiones regulares que luego utilizarás en los métodos de modificación.
   Los métodos de modificación lanzarán las excepciones adecuadas en caso de que el valor que se pretenda asignar al atributo no
   sea adecuado. */

    public String getNombre(){
        return this.nombre;
    }
    public String getTelefono() {
        return this.telefono;
    }
    public String getCorreo(){
        return this.correo;
    }
    public String getDni(){
        return this.dni;
    }
    public LocalDate getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    public void setNombre(String nombre){
        try{
            if(nombre.isEmpty()){
                throw new IllegalArgumentException("ERROR: El nombre de un huésped no puede estar vacío.");
            }
            this.nombre = formateaNombre(nombre);//Formateamos el nombre introducido
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: El nombre de un huésped no puede ser nulo.");
        }
    }
    public void setTelefono(String telefono) {
        try{
            if (telefono.matches(ER_TELEFONO)) {//Comprobamos el formato del teléfono, si válido añadimos
                this.telefono = telefono;
            }else{
                throw new IllegalArgumentException("ERROR: El teléfono del huésped no tiene un formato válido.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: El teléfono de un huésped no puede ser nulo.");
        }
    }
    public void setCorreo(String correo){
        try{
            if(correo.matches(ER_CORREO)){//Comprobamos el formato del correo, si válido añadimos
                this.correo = correo;
            }else{
                throw new IllegalArgumentException("ERROR: El correo del huésped no tiene un formato válido.");
            }
        }catch(NullPointerException e) {
            throw new NullPointerException("ERROR: El correo de un huésped no puede ser nulo");
        }
    }
    private void setDni(String dni){
        boolean valido = false;
        try{
            if(dni.matches(ER_DNI))//Comprobamos el formato del dni
                if(comprobarLetraDni(dni))//comprobamos que la letra es correcta
                    valido = true;//Si válido añadimos

        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: El dni de un huésped no puede ser nulo.");
        }
        if(valido){
            this.dni = dni;
        }else{
            throw new IllegalArgumentException("ERROR: El dni del huésped no tiene un formato válido.");
        }
    }
    private void setFechaNacimiento(LocalDate fechaNacimiento){
        try{
            if(fechaNacimiento.isAfter(LocalDate.now())){
                throw new IllegalArgumentException("ERROR: La fecha de nacimiento no puede ser hoy o posterior.");
            }
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha = fechaNacimiento.format(pattern);
            boolean valido = false;//Variable que controla si la fecha es válida.
            if(fecha.matches(FORMATO_FECHA)) {
                Pattern patron = Pattern.compile(FORMATO_FECHA);
                Matcher acoplamiento = patron.matcher(fecha);
                String dia = "";
                String mes = "";
                String ano = "";
                if (acoplamiento.matches()) {
                    dia = acoplamiento.group(3);
                    mes = acoplamiento.group(2);
                    ano = acoplamiento.group(1);
                }

                if (!mes.matches("02")) {//Como el formato de la fecha es correcto, si el mes no es febrero no tendremos que realizar más comprobaciones
                    this.fechaNacimiento = fechaNacimiento;
                    valido = true;
                } else {//Si por el contrario la fecha pertenece a febrero
                    if (Integer.parseInt(ano) % 4 == 0) {//Comprobamos que el año sea bisiesto
                        if (Integer.parseInt(dia) < 30) {
                            this.fechaNacimiento = fechaNacimiento;
                            valido = true;
                        }
                    } else {
                        if (Integer.parseInt(dia) < 29) {//Si el año no es bisiesto
                            this.fechaNacimiento = fechaNacimiento;
                            valido = true;
                        }
                    }
                }
                if(!valido){
                    System.err.println("La fecha introducida no es válida.");
                }
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: La fecha de nacimiento de un huésped no puede ser nula.");
        }
    }

    /*Crea el método getIniciales. Este método devolverá las iniciales del nombre.*/
    private String getIniciales(){
        String iniciales = "";
        String[] array = nombre.split(" ");
        for(int i = 0; i < array.length; i++){
            iniciales += array[i].charAt(0);
        }
        return iniciales;
    }



    /*Crea los métodos equals y hashCode, sabiendo que dos huéspedes se considerarán iguales si su DNI es el mismo*/
    @Override
    public boolean equals(Object obj) {
        Huesped huesped = (Huesped)obj;
        return huesped.getDni().equals(this.dni);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /*Crea el método toString que devuelva la cadena que esperan los tests. */
    public String toString(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = fechaNacimiento.format(pattern);
        String cadena = String.format("nombre=%s (%s), DNI=%s, correo=%s, teléfono=%s, fecha nacimiento=%s", this.nombre, getIniciales(), this.dni, this.correo, this.telefono, fecha);
        return cadena;
    }
}