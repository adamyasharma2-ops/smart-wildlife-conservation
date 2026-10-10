package com.wildguard.wildlife.repository;

import com.wildguard.wildlife.entity.AnimalObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnimalObservationRepository extends JpaRepository<AnimalObservation, Long> {
    List<AnimalObservation> findByAnimalIdOrderByObservationDateDesc(Long animalId);
    List<AnimalObservation> findTop10ByOrderByObservationDateDesc();
}
