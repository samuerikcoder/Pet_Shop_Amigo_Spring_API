package com.example.demo.repository;

import com.example.demo.model.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {

}
