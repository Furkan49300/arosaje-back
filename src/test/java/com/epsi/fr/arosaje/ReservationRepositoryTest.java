package com.epsi.fr.arosaje;

import com.epsi.fr.arosaje.bo.Reservation;
import com.epsi.fr.arosaje.dal.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationRepositoryTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Test
    public void testSaveReservation() {
        // Création d'une réservation fictive
        Reservation reservation = new Reservation();
        reservation.setId_reservation(1);
        reservation.setDateDebut(LocalDate.of(2024, 3, 15));
        reservation.setDateFin(LocalDate.of(2024, 3, 20));
        reservation.setEtat(true);

        // Définition du comportement du mock du repository
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        // Appel de la méthode du repository
        Reservation savedReservation = reservationRepository.save(reservation);

        // Vérification
        assertEquals(reservation, savedReservation);
    }

    @Test
    public void testFindAllReservations() {
        // Création de deux réservations fictives
        Reservation reservation1 = new Reservation();
        reservation1.setId_reservation(1);
        reservation1.setDateDebut(LocalDate.of(2024, 3, 15));
        reservation1.setDateFin(LocalDate.of(2024, 3, 20));
        reservation1.setEtat(true);

        Reservation reservation2 = new Reservation();
        reservation2.setId_reservation(2);
        reservation2.setDateDebut(LocalDate.of(2024, 3, 20));
        reservation2.setDateFin(LocalDate.of(2024, 3, 25));
        reservation2.setEtat(true);

        List<Reservation> reservations = List.of(reservation1, reservation2);

        // Définition du comportement du mock du repository
        when(reservationRepository.findAll()).thenReturn(reservations);

        // Appel de la méthode du repository
        List<Reservation> retrievedReservations = reservationRepository.findAll();

        // Vérification
        assertEquals(reservations, retrievedReservations);
    }
}
