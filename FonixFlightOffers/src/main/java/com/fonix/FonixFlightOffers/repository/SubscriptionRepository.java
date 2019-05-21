package com.fonix.FonixFlightOffers.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fonix.FonixFlightOffers.entity.SubscriptionEntity;
import com.fonix.FonixFlightOffers.model.UserSubscription;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, String>{
	
	@Modifying
	@Query("update SubscriptionEntity u set u.mailSentDate = :mailSentDate where u.email = :email")
	void setMailSentDateById(@Param("mailSentDate")LocalDate mailSentDate, @Param("email")String email);

	@Query("select u from SubscriptionEntity u where u.origin = :origin and u.destination = :destination")
	List<SubscriptionEntity> findUsersSubscribed(@Param("origin") String origin,@Param("destination") String destination);
}
