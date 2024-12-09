package com.nn.challange.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nn.challange.Entities.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
