package com.autentica.mvc.models.form;

/**
 * Created by mkl on 3/8/2017.
 */
public class FormModelReservation {

    private long id;
    private String user;
    private String car;
  //  private long userId;
    private String dateReservedFrom;
    private String timeReservedFrom;
    private String dateReservedTill;
    private String timeReservedTill;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getDateReservedFrom() {
        return dateReservedFrom;
    }

    public void setDateReservedFrom(String dateReservedFrom) {
        this.dateReservedFrom = dateReservedFrom;
    }

    public String getTimeReservedFrom() {
        return timeReservedFrom;
    }

    public void setTimeReservedFrom(String timeReservedFrom) {
        this.timeReservedFrom = timeReservedFrom;
    }

    public String getDateReservedTill() {
        return dateReservedTill;
    }

    public void setDateReservedTill(String dateReservedTill) {
        this.dateReservedTill = dateReservedTill;
    }

    public String getTimeReservedTill() {
        return timeReservedTill;
    }

    public void setTimeReservedTill(String timeReservedTill) {
        this.timeReservedTill = timeReservedTill;
    }



}
