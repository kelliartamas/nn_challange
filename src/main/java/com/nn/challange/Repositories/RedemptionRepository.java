package com.nn.challange.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nn.challange.Entities.Redemption;

@Repository
public interface RedemptionRepository extends JpaRepository<Redemption, Long> {
}
