package com.fonix.FonixFlightOffers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fonix.FonixFlightOffers.entity.CrawlerFeedEntity;
import com.fonix.FonixFlightOffers.entity.FlightDetailEntity;
import com.fonix.FonixFlightOffers.model.OfferDetail;

public interface CrawlerFeedRepository extends JpaRepository<CrawlerFeedEntity, FlightDetailEntity>{

	@Query("select c from CrawlerFeedEntity c where c.flightDetail = :flightDetail")
	List<CrawlerFeedEntity> getByFlightDetails(@Param("flightDetail") FlightDetailEntity flightDetail);
}
