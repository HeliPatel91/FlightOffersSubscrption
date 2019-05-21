package com.fonix.FonixFlightOffers.repository;

import java.time.LocalDate;
import java.util.SortedSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fonix.FonixFlightOffers.entity.CrawlerFeedEntity;
import com.fonix.FonixFlightOffers.entity.FlightDetailEntity;

public interface FlightRepository extends JpaRepository<FlightDetailEntity, Integer>{

	@Query("select f from FlightDetailEntity f where f.origin = :origin and f.destination = :destination")
	FlightDetailEntity findByOriginAndDestination(@Param("origin") String origin,@Param("destination") String destination);
	
}
