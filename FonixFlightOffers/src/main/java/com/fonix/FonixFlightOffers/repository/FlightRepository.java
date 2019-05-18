package com.fonix.FonixFlightOffers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonix.FonixFlightOffers.entity.FlightDetailEntity;

public interface FlightRepository extends JpaRepository<FlightDetailEntity, Integer>{

}
