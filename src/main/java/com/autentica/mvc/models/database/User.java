package com.autentica.mvc.models.database;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created by mkl on 3/6/2017.
 */
@Entity
@Table(name = "users", schema = "CarRentService")
public class User {

    @javax.persistence.Id
    @Column(name="id",columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private Long currentReservationId;
    private Timestamp timeAdded;
    private String userAdded;
    private Timestamp timeModified;
    private String userModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Surname", nullable = true, length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

        User that = (User) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
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
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (currentReservationId != null ? currentReservationId.hashCode() : 0);
        result = 31 * result + (timeAdded != null ? timeAdded.hashCode() : 0);
        result = 31 * result + (userAdded != null ? userAdded.hashCode() : 0);
        result = 31 * result + (timeModified != null ? timeModified.hashCode() : 0);
        result = 31 * result + (userModified != null ? userModified.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return   id + " | " + name + " | " + surname;
    }
}
