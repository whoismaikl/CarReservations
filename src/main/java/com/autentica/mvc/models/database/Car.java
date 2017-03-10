package com.autentica.mvc.models.database;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mkl on 3/6/2017.
 */
@Entity
@Table(name = "cars", schema = "CarRentService")
public class Car {

    @javax.persistence.Id
    @Column(name="id",columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String brand;
    private String model;
    private String registrationNumber;
    private Long currentReservationId;
    private Timestamp timeAdded;
    private String userAdded;
    private Timestamp timeModified;
    private String userModified;

    @Id
    @Column(name = "Id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Brand", nullable = true, length = 30)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "Model", nullable = true, length = 50)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "RegistrationNumber", nullable = true, length = 10)
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Basic
    @Column(name = "CurrentReservationId", nullable = true)
    public Long getCurrentReservationId() {
        return currentReservationId;
    }

    public void setCurrentReservationId(Long currentReservationId) {
        this.currentReservationId = currentReservationId;
    }

    @Basic
    @Column(name = "TimeAdded", nullable = true)
    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    @Basic
    @Column(name = "UserAdded", nullable = true, length = 30)
    public String getUserAdded() {
        return userAdded;
    }

    public void setUserAdded(String userAdded) {
        this.userAdded = userAdded;
    }

    @Basic
    @Column(name = "TimeModified", nullable = true)
    public Timestamp getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(Timestamp timeModified) {
        this.timeModified = timeModified;
    }

    @Basic
    @Column(name = "UserModified", nullable = true, length = 30)
    public String getUserModified() {
        return userModified;
    }

    public void setUserModified(String userModified) {
        this.userModified = userModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car that = (Car) o;

        if (id != that.id) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (registrationNumber != null ? !registrationNumber.equals(that.registrationNumber) : that.registrationNumber != null)
            return false;
        if (currentReservationId != null ? !currentReservationId.equals(that.currentReservationId) : that.currentReservationId != null)
            return false;
        if (timeAdded != null ? !timeAdded.equals(that.timeAdded) : that.timeAdded != null) return false;
        if (userAdded != null ? !userAdded.equals(that.userAdded) : that.userAdded != null) return false;
        if (timeModified != null ? !timeModified.equals(that.timeModified) : that.timeModified != null) return false;
        if (userModified != null ? !userModified.equals(that.userModified) : that.userModified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (registrationNumber != null ? registrationNumber.hashCode() : 0);
        result = 31 * result + (currentReservationId != null ? currentReservationId.hashCode() : 0);
        result = 31 * result + (timeAdded != null ? timeAdded.hashCode() : 0);
        result = 31 * result + (userAdded != null ? userAdded.hashCode() : 0);
        result = 31 * result + (timeModified != null ? timeModified.hashCode() : 0);
        result = 31 * result + (userModified != null ? userModified.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return id + " | " + brand + " | " + model + " | " + registrationNumber;
    }
}
