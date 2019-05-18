package com.fonix.FonixFlightOffers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonix.FonixFlightOffers.entity.CrawlerFeedEntity;
import com.fonix.FonixFlightOffers.entity.FlightDetailEntity;

public interface CrawlerFeedRepository extends JpaRepository<CrawlerFeedEntity, FlightDetailEntity>{


}
