package com.example.repository;

import com.example.domain.PingEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PingRepository extends MongoRepository<PingEntity,String> {
    
}
