package org.iesalandalus.programacion.reservashotel.dominio;

public class Habitacion {
    /* Adem�s, debes crear las constantes indicadas en el diagrama de clases y que luego utilizar�s en los m�todos de modificaci�n.
    Los m�todos de modificaci�n lanzar�n las excepciones adecuadas en caso de que el valor que se pretenda asignar al atributo no
    sea adecuado.
    En el uso de las constantes debes tener presente que el hotel tiene un m�ximo de tres plantas, con un n�mero m�ximo de 15
    habitaciones por planta (por tanto, 15 puertas por planta) y que el precio de una habitaci�n (independientemente del tipo que
    sea) oscila entre 40? y 150?.
    */
    public static double MIN_PRECIO_HABITACION = 40;
    public static double MAX_PRECIO_HABITACION = 150;
    public static int MIN_NUMERO_PUERTA = 0;
    public static int MAX_NUMERO_PUERTA = 15;
    public static int MIN_NUMERO_PLANTA = 0;
    public static int MAX_NUMERO_PLANTA = 3;
    private String identificador;
    private int planta;
    private int puerta;
    private double precio;
    private TipoHabitacion tipoHabitacion;

    /*Crea los constructores con par�metros que har�n uso de los m�todos de modificaci�n.*/
    public Habitacion(int planta, int puerta, double precio, TipoHabitacion tipoHabitacion){
        setPlanta(planta);
        setPuerta(puerta);
        setPrecio(precio);
        setTipoHabitacion(tipoHabitacion);
        setIdentificador();
    }

    public Habitacion(int planta, int puerta, double precio){
        setPlanta(planta);
        setPuerta(puerta);
        setPrecio(precio);
        setIdentificador();
        this.tipoHabitacion = TipoHabitacion.SUITE;//Valor por defecto para el tipo de habitaci�n.
    }

    /*Crea el constructor copia.*/
    public Habitacion(Habitacion habitacion){
        try {
            this.planta = habitacion.getPlanta();
            this.puerta = habitacion.getPuerta();
            this.precio = habitacion.getPrecio();
            this.tipoHabitacion = habitacion.getTipoHabitacion();
            this.identificador = habitacion.getIdentificador();
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No es posible copiar una habitaci�n nula.");
        }
    }

    /*Crea los m�todos de acceso y modificaci�n de cada atributo con la visibilidad adecuada, teniendo en cuenta que el identificador
    de una habitaci�n ser� el n�mero de planta seguido del n�mero de puerta.
    */
    public String getIdentificador() {
        return identificador;
    }

    private void setIdentificador(){
        this.identificador = "" + this.planta + this.puerta;
    }

    private void setIdentificador(String identificador){
        if(identificador.isEmpty()){
            throw new IllegalArgumentException("ERROR: El indentificador no puede ser vac�o.");
        }
        this.identificador=identificador;
    }

    public int getPlanta(){
        return planta;
    }

    private void setPlanta(int planta){
        Integer numero = Integer.parseInt(""+planta);//Comprobamos que el int planta no sea null
        if(numero == null){
            throw new NullPointerException("ERROR: El n�mero de la planta no puede ser nulo");
        }

        boolean valido = false;
        if(planta > MIN_NUMERO_PLANTA)
            if(planta <= MAX_NUMERO_PLANTA){
                this.planta = planta;
                valido = true;
            }
        if(!valido)
            throw new IllegalArgumentException("ERROR: No se puede establecer como planta de una habitaci�n un valor menor que 0 ni mayor que 3.");

    }

    public int getPuerta(){
        return puerta;
    }

    private void setPuerta(int puerta){
        Integer numero = Integer.parseInt("" + puerta);//Comprobamos que el int puerta no sea null
        if (numero == null) {
            throw new NullPointerException("ERROR: El n�mero de la puerta no puede ser nulo");
        }

        boolean valido = false;
        if ((puerta >= MIN_NUMERO_PUERTA) && (puerta < MAX_NUMERO_PUERTA)) {
            this.puerta = puerta;
            valido = true;
        }
        if (!valido){
            throw new IllegalArgumentException("ERROR: No se puede establecer como puerta de una habitaci�n un valor menor que 0 ni mayor que 15.");
        }
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        Double numero = Double.parseDouble("" + precio);//Comprobamos que el double precio no sea null
        if (numero == null) {
            throw new NullPointerException("ERROR: El precio de la habitaci�n no puede ser nulo");
        }

        boolean valido = false;
        if ((precio >= MIN_PRECIO_HABITACION) && (precio <= MAX_PRECIO_HABITACION)) {
            this.precio = precio;
            valido = true;
        }
        if (!valido){
            throw new IllegalArgumentException("ERROR: No se puede establecer como precio de una habitaci�n un valor menor que 40.0 ni mayor que 150.0.");
        }
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        try{
            boolean valido = false;
            for(TipoHabitacion aux : TipoHabitacion.values()) {
                if(tipoHabitacion.equals(aux)) {
                    this.tipoHabitacion = tipoHabitacion;
                    valido = true;
                }
            }
            if(!valido){
                throw new IllegalArgumentException("");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No se puede establecer un tipo de habitaci�n nula.");
        }
    }

    /*Una habitaci�n ser� igual a otra si su identificador es el mismo. Bas�ndote en ello crea los m�todos equals y hashCode.*/
    @Override
    public boolean equals(Object obj) {
        Habitacion habitacion = (Habitacion)obj;
        return habitacion.getIdentificador().equals(this.identificador);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    /*Crea el m�todo toString que devuelva la cadena que esperan los tests.*/
    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitaci�n=%s, tipo habitaci�n=%s", getIdentificador(), getPlanta(), getPuerta(), getPrecio(), getTipoHabitacion());
    }
}
