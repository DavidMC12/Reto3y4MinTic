package com.usa.ciclo3.reto3.service;

import com.usa.ciclo3.reto3.model.Reservation;
import com.usa.ciclo3.reto3.repository.CountClient;
import com.usa.ciclo3.reto3.repository.ReservationRepository;
import com.usa.ciclo3.reto3.repository.StatusReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation){
        if (reservation.getIdReservation() == null){
            return reservationRepository.save(reservation);
        }
        else{
            Optional<Reservation> tmpReservation = reservationRepository.getReservation(reservation.getIdReservation());
            if (tmpReservation.isEmpty()){
                return reservationRepository.save(reservation);
            }
            else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if (reservation.getIdReservation() != null){
            Optional<Reservation> tpmReservation = reservationRepository.getReservation(reservation.getIdReservation());
            if (!tpmReservation.isEmpty()){
                if (reservation.getStartDate()!=null){
                    tpmReservation.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate()!=null){
                    tpmReservation.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus()!=null){
                    tpmReservation.get().setStatus(reservation.getStatus());
                }
                if (reservation.getCloud()!=null){
                    tpmReservation.get().setCloud(reservation.getCloud());
                }
                if (reservation.getClient()!=null){
                    tpmReservation.get().setClient(reservation.getClient());
                }
                if (reservation.getScore()!=null){
                    tpmReservation.get().setScore(reservation.getScore());
                }
                return reservationRepository.save(tpmReservation.get());
            }
            else{
                return reservation;
            }
        }
        else{
            return reservation;
        }
    }

    public boolean deleteReservation(int id){
        Boolean result = getReservation(id).map(reservation -> {reservationRepository.delete(reservation); return true;}).orElse(false);
        return result;
    }

    public StatusReservation reportStatusService (){
        List<Reservation>completed= reservationRepository.ReservationStatusRepository("completed");
        List<Reservation>cancelled= reservationRepository.ReservationStatusRepository("cancelled");

        return new StatusReservation(completed.size(), cancelled.size() );
    }

    public List<Reservation> reportTimeService (String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");

        Date datoUno = new Date();
        Date datoDos = new Date();

        try{
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return reservationRepository.ReservationStatusRepository(datoUno, datoDos);
        }else{
            return new ArrayList<>();

        }
    }

    public List<CountClient> reportClientService(){
        return reservationRepository.getClientRepository();
    }


}
