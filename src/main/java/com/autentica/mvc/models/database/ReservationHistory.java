package com.autentica.mvc.models.database;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mkl on 3/6/2017.
 */
@Entity
@Table(name = "reservationshistory", schema = "CarRentService")
public class ReservationHistory {

    private long id;
    private long reservationId;
    private long carId;
    private long userId;
    private Timestamp reservedFrom;
    private Timestamp reservedTill;
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

    @Column(name = "ReservationId")
    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    @Column(name = "CarId")
    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Column(name = "UserId")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ReservedFrom", nullable = true)
    public Timestamp getReservedFrom() {
        return reservedFrom;
    }

    public void setReservedFrom(Timestamp reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    @Basic
    @Column(name = "ReservedTill", nullable = true)
    public Timestamp getReservedTill() {
        return reservedTill;
    }

    public void setReservedTill(Timestamp reservedTill) {
        this.reservedTill = reservedTill;
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

        ReservationHistory that = (ReservationHistory) o;

        if (id != that.id) return false;
        if (reservedFrom != null ? !reservedFrom.equals(that.reservedFrom) : that.reservedFrom != null) return false;
        if (reservedTill != null ? !reservedTill.equals(that.reservedTill) : that.reservedTill != null) return false;
        if (timeAdded != null ? !timeAdded.equals(that.timeAdded) : that.timeAdded != null) return false;
        if (userAdded != null ? !userAdded.equals(that.userAdded) : that.userAdded != null) return false;
        if (timeModified != null ? !timeModified.equals(that.timeModified) : that.timeModified != null) return false;
        if (userModified != null ? !userModified.equals(that.userModified) : that.userModified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (reservedFrom != null ? reservedFrom.hashCode() : 0);
        result = 31 * result + (reservedTill != null ? reservedTill.hashCode() : 0);
        result = 31 * result + (timeAdded != null ? timeAdded.hashCode() : 0);
        result = 31 * result + (userAdded != null ? userAdded.hashCode() : 0);
        result = 31 * result + (timeModified != null ? timeModified.hashCode() : 0);
        result = 31 * result + (userModified != null ? userModified.hashCode() : 0);
        return result;
    }

}
