package com.wildguard.wildlife.repository;

import com.wildguard.wildlife.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByTagId(String tagId);
    List<Animal> findBySpeciesId(Long speciesId);
    List<Animal> findByHealthStatus(String healthStatus);
    List<Animal> findByCurrentAreaName(String areaName);
    long countByHealthStatus(String healthStatus);
    @Query("SELECT a FROM Animal a WHERE LOWER(a.tagId) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(a.name) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(a.speciesName) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Animal> searchByTagOrNameOrSpecies(@Param("q") String query);
}
