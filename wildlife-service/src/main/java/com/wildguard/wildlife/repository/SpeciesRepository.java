package com.wildguard.wildlife.repository;

import com.wildguard.wildlife.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    Optional<Species> findByScientificName(String scientificName);
    List<Species> findByConservationStatus(String status);
    List<Species> findByCommonNameContainingIgnoreCase(String query);
}
