package com.wildguard.wildlife.service;

import com.wildguard.wildlife.repository.AnimalRepository;
import com.wildguard.wildlife.repository.SpeciesRepository;
import com.wildguard.wildlife.repository.AnimalObservationRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {
    private final AnimalRepository animalRepository;
    private final SpeciesRepository speciesRepository;
    private final AnimalObservationRepository observationRepository;
    private final RestClient restClient;

    @Value("${services.protected-area.url:http://localhost:8082}")
    private String protectedAreaUrl;
    @Value("${services.patrol.url:http://localhost:8083}")
    private String patrolUrl;
    @Value("${services.incident.url:http://localhost:8084}")
    private String incidentUrl;

    public DashboardService(AnimalRepository animalRepository, SpeciesRepository speciesRepository, AnimalObservationRepository observationRepository) {
        this.animalRepository = animalRepository;
        this.speciesRepository = speciesRepository;
        this.observationRepository = observationRepository;
        this.restClient = RestClient.builder().build();
    }

    public Map<String, Object> getDashboardData() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalAnimals", animalRepository.count());
        data.put("totalSpecies", speciesRepository.count());
        data.put("healthyAnimals", animalRepository.countByHealthStatus("HEALTHY"));
        data.put("injuredAnimals", animalRepository.countByHealthStatus("INJURED"));
        data.put("criticalAnimals", animalRepository.countByHealthStatus("CRITICAL"));
        data.put("recentObservations", observationRepository.findTop10ByOrderByObservationDateDesc());

        // Fetch downstream stats gracefully
        data.put("totalAreas", fetchStat(protectedAreaUrl + "/api/protected-areas/stats", "areas"));
        data.put("totalZones", fetchStat(protectedAreaUrl + "/api/protected-areas/stats", "zones"));
        data.put("activePatrols", fetchStat(patrolUrl + "/api/patrols/stats", "activePatrols"));
        data.put("unresolvedIncidents", fetchStat(incidentUrl + "/api/incidents/stats", "unresolved"));
        data.put("criticalIncidents", fetchStat(incidentUrl + "/api/incidents/stats", "critical"));
        return data;
    }

    private Object fetchStat(String url, String key) {
        try {
            Map response = restClient.get().uri(url).retrieve().body(Map.class);
            if (response != null && response.containsKey(key)) {
                return response.get(key);
            }
        } catch (Exception e) {
            // Graceful fallback
        }
        return "N/A";
    }
}
