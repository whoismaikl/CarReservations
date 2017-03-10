package com.autentica.mvc.transformers;

import com.autentica.mvc.models.database.Reservation;
import com.autentica.mvc.models.form.FormModelReservation;

/**
 * Created by mkl on 3/8/2017.
 */
public interface ReservationsTransformer {

    FormModelReservation transformToFormModel(Reservation databaseReservation);
    Reservation transformToDatabaseModel(FormModelReservation formReservation);
}
