package org.iesalandalus.programacion.reservashotel.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reserva {
    public static int MAX_NUMERO_MESES_RESERVA = 6;//Aunque aparece como private en el diagrama, se ha modificado su visibilida para poder usarse en el test
    private final int MAX_HORAS_POSTERIORES_CHECKOUT = 12;
    public static String FORMATO_FECHA_RESERVA = "dd/MM/yyyy";
    public static String FORMATO_FECHA_HORA_RESERVA = "dd/MM/yyyy";//Aunque no se encuentras en el diagrama se ha añadido para poder pasar el test
    private Huesped huesped;
    private Habitacion habitacion;
    private Regimen regimen;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Double precio;
    private int numeroPersonas;

    /*Crea el constructor con parámetros que harán uso de los métodos de modificación.*/
    public Reserva(Huesped huesped, Habitacion habitacion, Regimen regimen, LocalDate fechaInicioReserva, LocalDate fechaFinReserva, int numeroPersonas){
        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setFechaInicioReserva(fechaInicioReserva);
        setFechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);
        this.checkIn = null;
        this.checkOut = null;
        this.precio = 0.0;
    }

    /*Crea el constructor copia.*/
    public Reserva (Reserva reserva){
        try {
            if(reserva != null){
                this.huesped = reserva.getHuesped();
                this.habitacion = reserva.getHabitacion();
                this.regimen = reserva.getRegimen();
                this.fechaInicioReserva = reserva.getFechaInicioReserva();
                this.fechaFinReserva = reserva.getFechaFinReserva();
                this.numeroPersonas = reserva.getNumeroPersonas();
                this.precio = reserva.getPrecio();
                if(reserva.getCheckIn() != null){
                    this.checkIn = reserva.getCheckIn();
                }
                if(reserva.getCheckOut() != null) {
                    this.checkOut = reserva.getCheckOut();
                }
            }else{
                throw new NullPointerException("ERROR: No es posible copiar una reserva nula.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: No es posible copiar una reserva nula.");
        }
    }

    /*Crea los m�todos de acceso y modificaci�n de cada atributo, teniendo en cuenta que:
    La fecha de inicio de la reserva no puede ser anterior al d�a que se intenta hacer la reserva.
    La fecha de inicio de la reserva no puede ser posterior al n�mero de meses indicado por la constante MAX_NUMERO_MESES_RESERVA.
    La fecha de fin de la reserva debe ser posterior a la de inicio.
    El n�mero de personas que se van a alojar en la habitaci�n no puede superar al n�mero m�ximo de personas que, por el tipo de habitaci�n reservada, se permiten alojar.
    El checkin no puede ser anterior al inicio de la reserva.
    El checkout no puede ser anterior al checkin.
    El checkout debe hacerse como m�ximo a las 12:00 horas del d�a en que finaliza la reserva. Para esto deber�s usar la constante MAX_HORAS_POSTERIOR_CHECKOUT.
    El atributo precio debe calcularse teniendo en cuenta el coste de la habitaci�n, el r�gimen de alojamiento y el n�mero de personas alojadas.
    */

    public Huesped getHuesped(){
        return huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Regimen getRegimen() {
        return regimen;
    }

    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public LocalDate getFechaFinReserva() {
        return fechaFinReserva;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public Double getPrecio() {
        return precio;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setHuesped(Huesped huesped) {
        try{
            if(huesped != null) {
                this.huesped = huesped;
            }else{
                throw new NullPointerException("ERROR: El huésped de una reserva no puede ser nulo.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: El huésped de una reserva no puede ser nulo.");
        }
    }

    public void setHabitacion(Habitacion habitacion) {
        try{
            if(habitacion != null) {
                this.habitacion = habitacion;
            }else{
                throw new NullPointerException("ERROR: La habitación de una reserva no puede ser nua.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: La habitación de una reserva no puede ser nula.");
        }
    }

    public void setRegimen(Regimen regimen){
        try{
            if(regimen != null) {
                this.regimen = regimen;
            }else{
                throw new NullPointerException("ERROR: El régimen de una reserva no puede ser nulo.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: El régimen de una reserva no puede ser nulo.");
        }
    }

    /*  La fecha de inicio de la reserva no puede ser anterior al d�a que se intenta hacer la reserva.
        La fecha de inicio de la reserva no puede ser posterior al n�mero de meses indicado por la constante MAX_NUMERO_MESES_RESERVA.
    */
    public void setFechaInicioReserva(LocalDate fechaInicioReserva){
        try{
            if(fechaInicioReserva.isAfter(LocalDate.now())){
                if(fechaInicioReserva.isBefore(LocalDate.now().plusMonths(MAX_NUMERO_MESES_RESERVA))){
                    this.fechaInicioReserva = fechaInicioReserva;
                }else{
                    throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser posterior a seis meses.");
                }
            }else{
                throw new IllegalArgumentException("ERROR: La fecha de inicio de la reserva no puede ser anterior al día de hoy.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");
        }
    }

    public void setFechaFinReserva(LocalDate fechaFinReserva){
        try{
            fechaInicioReserva.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA));
            if(fechaInicioReserva.isBefore(fechaFinReserva) && !fechaInicioReserva.isEqual(fechaFinReserva)) {
                this.fechaFinReserva = fechaFinReserva;
            }else{
                throw new IllegalArgumentException("ERROR: La fecha de fin de la reserva debe ser posterior a la de inicio.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");
        }
    }

    public void setCheckIn(LocalDateTime checkIn){
        try{
            if(fechaInicioReserva.atStartOfDay().isBefore(checkIn)){//Si la fecha de la reserva es anterior al checkIn
                this.checkIn = checkIn;
            }else{
                throw new IllegalArgumentException("ERROR: El checkin de una reserva no puede ser anterior a la fecha de inicio de la reserva.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: El checkin de una reserva no puede ser nulo.");
        }
    }

    /*  El checkout no puede ser anterior al checkin.
        El checkout debe hacerse como m�ximo a las 12:00 horas del d�a en que finaliza la reserva. Para esto deber�s usar la
        constante MAX_HORAS_POSTERIOR_CHECKOUT.
    */
    public void setCheckOut(LocalDateTime checkOut){
        try{
            if(checkOut != null){
                if(checkOut.isBefore(fechaFinReserva.atStartOfDay().plusDays(1))){
                    if(checkOut.isBefore(fechaFinReserva.atStartOfDay().plus(MAX_HORAS_POSTERIORES_CHECKOUT, ChronoUnit.HOURS))){
                        if(!checkOut.isBefore(checkIn)){
                            this.checkOut = checkOut;
                            setPrecio();
                        }else{
                            throw new IllegalArgumentException("ERROR: El checkout de una reserva no puede ser anterior al checkin.");
                        }
                    }else{
                        throw new IllegalArgumentException("ERROR: El checkout de una reserva puede ser como máximo 12 horas después de la fecha de fin de la reserva.");
                    }
                }else{
                    throw new IllegalArgumentException("ERROR: El checkout de una reserva puede ser como máximo 12 horas después de la fecha de fin de la reserva.");
                }
            }else{
                throw new NullPointerException("ERROR: El checkout de una reserva no puede ser nulo.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: El checkout de una reserva no puede ser nulo.");
        }
    }

    /*  El atributo precio debe calcularse teniendo en cuenta el coste de la habitaci�n, el r�gimen de alojamiento y el n�mero de
        personas alojadas.
    * */
    private void setPrecio() {
        Double costePorPersona = getHabitacion().getPrecio() + getRegimen().getIncrementoPrecio();//Calculamos el coste por persona de la habitaci�n teniendo en cuenta el r�gimen y el tipo de habitaci�n
        Double costeReservaPorDia = getNumeroPersonas() * costePorPersona;//Calculamos el coste total de la reserva por d�a
        Period period = Period.between(getFechaInicioReserva(), getFechaFinReserva());
        int totalDias = period.getDays();
        precio = costeReservaPorDia * totalDias;
    }

    /*El n�mero de personas que se van a alojar en la habitaci�n no puede superar al n�mero m�ximo de personas que, por el tipo de habitaci�n reservada, se permiten alojar.*/
    public void setNumeroPersonas(int numeroPersonas) {
        try{
            if(numeroPersonas <= habitacion.getTipoHabitacion().getNumeroMaximoPersonas()){
                if(numeroPersonas > 0) {//El n�mero de personas de una habitaci�n no puede exceder del m�ximo ni ser inferior a 1
                    this.numeroPersonas = numeroPersonas;
                }else{
                    throw new IllegalArgumentException("ERROR: El número de personas de una reserva no puede ser menor o igual a 0.");
                }
            }else{
                throw new IllegalArgumentException("ERROR: El número de personas de una reserva no puede superar al máximo de personas establacidas para el tipo de habitación reservada.");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("ERROR: El número de huéspedes de la habitación no puede ser nulo.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        Reserva reserva = (Reserva) obj;
        return reserva.getHabitacion().equals(this.habitacion) && reserva.getFechaInicioReserva().equals(this.fechaInicioReserva);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    /*Crea el método toString que devuelva la cadena que esperan los tests.*/
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA/*"dd/MM/yyyy"*/);
        String cadenaFechaInicioReserva = getFechaInicioReserva().format(pattern);
        String cadenaFechaFinReserva = getFechaFinReserva().format(pattern);
        String cadenaCheckIn ="No registrado";
        if(getCheckIn() != null){
            cadenaCheckIn = getCheckIn().format(pattern);
            cadenaCheckIn = getCheckIn().toLocalDate().format(pattern) + " " + getCheckIn().toLocalTime();
        }
        String cadenaCheckOut = "No registrado";
        if(getCheckOut() != null) {
            cadenaCheckOut = getCheckOut().format(pattern);
        }

        return String.format("Huesped: %s %s Habitación:%s - %s Fecha Inicio Reserva: %s Fecha Fin Reserva: %s Checkin: %s Checkout: %s Precio: %.2f Personas: %d", getHuesped().getNombre(), getHuesped().getDni(),
                getHabitacion().getIdentificador(),getHabitacion().getTipoHabitacion(), cadenaFechaInicioReserva,
                cadenaFechaFinReserva, cadenaCheckIn, cadenaCheckOut, getPrecio(), getNumeroPersonas());
    }
}