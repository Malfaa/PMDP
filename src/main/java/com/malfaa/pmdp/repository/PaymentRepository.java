package com.malfaa.pmdp.repository;

import java.util.List;

import com.malfaa.pmdp.model.Mentee;
import com.malfaa.pmdp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malfaa.pmdp.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
    List<Payment> findBySession(Session session);
    List<Payment> findByMentee(Mentee mentee);


}
