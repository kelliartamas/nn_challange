package com.nn.challange.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nn.challange.Entities.PayOut;

@Repository
public interface PayOutRepository extends JpaRepository<PayOut, Long> {
}
