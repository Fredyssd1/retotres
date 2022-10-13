/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo10.app.rents.service;

import com.grupo10.app.rents.interfaces.IQuadbikeRepository;
import com.grupo10.app.rents.entities.Category;
import com.grupo10.app.rents.entities.Client;
import com.grupo10.app.rents.interfaces.ICategoryRepository;
import com.grupo10.app.rents.entities.Quadbike;
import com.grupo10.app.rents.entities.Reservation;
import com.grupo10.app.rents.repository.ClientRepository;
import com.grupo10.app.rents.repository.QuadbikeRepository;
import com.grupo10.app.rents.repository.ReservationRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Andres
 */
@Service
public class ReservationService {

    @Autowired
    ReservationRepository repository;

    @Autowired
    QuadbikeRepository quadbikeRepository; 
    
    @Autowired
    ClientRepository clientRepository;

    public Iterable<Reservation> get() {
        Iterable<Reservation> response = repository.getAll();
        return response;
    }
    
    public Optional<Reservation> get(Integer id) {
        Optional<Reservation> response = repository.findById(id);
        return response;
    }

    public Reservation create(@RequestBody Reservation request) {

        Optional<Quadbike> quad = quadbikeRepository.findById(request.getQuadbike().getId());
        if (!quad.isEmpty()) {
            request.setQuadbike(quad.get());
        }

        Optional<Client> cli;
        cli = clientRepository.findById(request.getClient().getIdClient());
        if (!cli.isEmpty()) {
            request.setClient(cli.get());

        }

        return repository.save(request);

    }
    
    public Reservation update(Reservation reservation) {

        Reservation reservationToUpdate = new Reservation();

        if (repository.existsById(reservation.getIdReservation())) {
            reservationToUpdate = reservation;
            repository.save(reservationToUpdate);
        }

        return reservationToUpdate;

    }
}
