package com.autentica.mvc.transformers.impl;

import com.autentica.mvc.dao.impl.CarDaoImpl;
import com.autentica.mvc.dao.impl.UserDaoImpl;
import com.autentica.mvc.models.database.Car;
import com.autentica.mvc.models.database.Reservation;
import com.autentica.mvc.models.database.User;
import com.autentica.mvc.models.form.FormModelReservation;
import com.autentica.mvc.transformers.ReservationsTransformer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mkl on 3/8/2017.
 */
public class ReservationsTransformerImpl implements ReservationsTransformer {

    @Override
    public FormModelReservation transformToFormModel(Reservation databaseReservation) {
        FormModelReservation formModelReservation = new FormModelReservation();

        formModelReservation.setId(databaseReservation.getId());

        formModelReservation.setDateReservedFrom(extractDateFromTimestamp(databaseReservation.getReservedFrom()));
        formModelReservation.setTimeReservedFrom(extractTimeFromTimestamp(databaseReservation.getReservedFrom()));
        formModelReservation.setDateReservedTill(extractDateFromTimestamp(databaseReservation.getReservedTill()));
        formModelReservation.setTimeReservedTill(extractTimeFromTimestamp(databaseReservation.getReservedTill()));

        return formModelReservation;
    }


    @Override
    public Reservation transformToDatabaseModel(FormModelReservation formReservation) {


        Reservation databaseReservation = new Reservation();
        databaseReservation.setId(formReservation.getId());
        databaseReservation.setReservedFrom(convertStringToTimeStamp(formReservation.getDateReservedFrom(), formReservation.getTimeReservedFrom()));
        databaseReservation.setReservedTill(convertStringToTimeStamp(formReservation.getDateReservedTill(), formReservation.getTimeReservedTill()));

        return databaseReservation;
    }

    //Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]
    private Timestamp convertStringToTimeStamp(String stringDate, String stringTime) {

        String dateTimeReservedFrom = stringDate + " " + stringTime + ":01.00001";
        Timestamp timeStamp = Timestamp.valueOf(dateTimeReservedFrom);
        return timeStamp;

    }

    private String extractDateFromTimestamp(Timestamp reservedFrom) {
        Date date = new Date(reservedFrom.getTime());
        String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return stringDate;
    }

    private String extractTimeFromTimestamp(Timestamp reservedFrom) {
        Date date = new Date(reservedFrom.getTime());
        String time = new SimpleDateFormat("hh:mm").format(date);
        return time;
    }


}
