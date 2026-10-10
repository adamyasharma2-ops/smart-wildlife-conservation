package com.wildguard.wildlife.config;

import com.wildguard.wildlife.entity.Animal;
import com.wildguard.wildlife.entity.AnimalObservation;
import com.wildguard.wildlife.entity.Species;
import com.wildguard.wildlife.service.AnimalObservationService;
import com.wildguard.wildlife.service.AnimalService;
import com.wildguard.wildlife.service.SpeciesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {
    private final SpeciesService speciesService;
    private final AnimalService animalService;
    private final AnimalObservationService observationService;

    public DataSeeder(SpeciesService speciesService, AnimalService animalService, AnimalObservationService observationService) {
        this.speciesService = speciesService;
        this.animalService = animalService;
        this.observationService = observationService;
    }

    @Override
    public void run(String... args) {
        if (speciesService.findAll().isEmpty()) {
            Species s1 = new Species();
            s1.setCommonName("Bengal Tiger");
            s1.setScientificName("Panthera tigris tigris");
            s1.setConservationStatus("ENDANGERED");
            s1.setDescription("Apex forest predator vital for ecological equilibrium and deer population control.");
            s1.setHabitat("Dense moist deciduous sal forests, alluvial riverine belts");
            speciesService.save(s1);

            Species s2 = new Species();
            s2.setCommonName("Asian Elephant");
            s2.setScientificName("Elephas maximus");
            s2.setConservationStatus("ENDANGERED");
            s2.setDescription("Keystone megaherbivore essential for forest corridor creation and seed dispersal.");
            s2.setHabitat("Grassland corridors, tropical evergreen canopy forests");
            speciesService.save(s2);

            Species s3 = new Species();
            s3.setCommonName("Indian Rhinoceros");
            s3.setScientificName("Rhinoceros unicornis");
            s3.setConservationStatus("VULNERABLE");
            s3.setDescription("Great one-horned grazer roaming floodplain meadows.");
            s3.setHabitat("Alluvial flood plains and tall elephant grass");
            speciesService.save(s3);

            Species s4 = new Species();
            s4.setCommonName("Snow Leopard");
            s4.setScientificName("Panthera uncia");
            s4.setConservationStatus("VULNERABLE");
            s4.setDescription("Solitary high-altitude predator adapted to cliff ledges.");
            s4.setHabitat("Subalpine scrub, scree slopes, high mountain passes");
            speciesService.save(s4);

            Species s5 = new Species();
            s5.setCommonName("Indian Vulture");
            s5.setScientificName("Gyps indicus");
            s5.setConservationStatus("CRITICALLY_ENDANGERED");
            s5.setDescription("Crucial scavenger bird preventing carcass contamination.");
            s5.setHabitat("Rocky cliffs, semi-arid woodlands, open scrub");
            speciesService.save(s5);

            Species s6 = new Species();
            s6.setCommonName("Sloth Bear");
            s6.setScientificName("Melursus ursinus");
            s6.setConservationStatus("VULNERABLE");
            s6.setDescription("Nocturnal insectivorous bear species foraging in dry deciduous tracts.");
            s6.setHabitat("Teak and sal dry forest understories");
            speciesService.save(s6);

            // Animals
            Animal a1 = new Animal();
            a1.setTagId("TR-CORB-01"); a1.setName("Raja (Alpha)"); a1.setSpeciesId(s1.getId()); a1.setSpeciesName(s1.getCommonName());
            a1.setSex("MALE"); a1.setEstimatedAge(6); a1.setHealthStatus("HEALTHY"); a1.setConservationStatus(s1.getConservationStatus());
            a1.setCurrentAreaName("Jim Corbett National Park"); a1.setLatitude(29.5300); a1.setLongitude(78.7747);
            a1.setLastObservedDate(LocalDate.now().minusDays(2)); animalService.save(a1);

            Animal a2 = new Animal();
            a2.setTagId("TR-RANTH-08"); a2.setName("Maya (Arrowhead)"); a2.setSpeciesId(s1.getId()); a2.setSpeciesName(s1.getCommonName());
            a2.setSex("FEMALE"); a2.setEstimatedAge(4); a2.setHealthStatus("HEALTHY"); a2.setConservationStatus(s1.getConservationStatus());
            a2.setCurrentAreaName("Ranthambore Tiger Reserve"); a2.setLatitude(26.0173); a2.setLongitude(76.5026);
            a2.setLastObservedDate(LocalDate.now().minusDays(1)); animalService.save(a2);

            Animal a3 = new Animal();
            a3.setTagId("ELE-CORB-14"); a3.setName("Airavata"); a3.setSpeciesId(s2.getId()); a3.setSpeciesName(s2.getCommonName());
            a3.setSex("MALE"); a3.setEstimatedAge(22); a3.setHealthStatus("HEALTHY"); a3.setConservationStatus(s2.getConservationStatus());
            a3.setCurrentAreaName("Jim Corbett National Park"); a3.setLatitude(29.5350); a3.setLongitude(78.7800);
            a3.setLastObservedDate(LocalDate.now().minusDays(4)); animalService.save(a3);

            Animal a4 = new Animal();
            a4.setTagId("RH-KAZ-03"); a4.setName("Gauri"); a4.setSpeciesId(s3.getId()); a4.setSpeciesName(s3.getCommonName());
            a4.setSex("FEMALE"); a4.setEstimatedAge(8); a4.setHealthStatus("INJURED"); a4.setConservationStatus(s3.getConservationStatus());
            a4.setCurrentAreaName("Kaziranga National Park"); a4.setLatitude(26.5775); a4.setLongitude(93.1711);
            a4.setLastObservedDate(LocalDate.now().minusDays(3)); a4.setNotes("Limping observed in right front leg, monitoring team dispatched"); animalService.save(a4);

            Animal a5 = new Animal();
            a5.setTagId("SL-HIM-02"); a5.setName("Himani"); a5.setSpeciesId(s4.getId()); a5.setSpeciesName(s4.getCommonName());
            a5.setSex("FEMALE"); a5.setEstimatedAge(5); a5.setHealthStatus("HEALTHY"); a5.setConservationStatus(s4.getConservationStatus());
            a5.setCurrentAreaName("Jim Corbett National Park"); a5.setLatitude(29.5400); a5.setLongitude(78.7900);
            a5.setLastObservedDate(LocalDate.now().minusDays(6)); animalService.save(a5);

            Animal a6 = new Animal();
            a6.setTagId("SB-CORB-05"); a6.setName("Baloo"); a6.setSpeciesId(s6.getId()); a6.setSpeciesName(s6.getCommonName());
            a6.setSex("MALE"); a6.setEstimatedAge(7); a6.setHealthStatus("HEALTHY"); a6.setConservationStatus(s6.getConservationStatus());
            a6.setCurrentAreaName("Jim Corbett National Park"); a6.setLatitude(29.5210); a6.setLongitude(78.7610);
            a6.setLastObservedDate(LocalDate.now().minusDays(2)); animalService.save(a6);

            // Field Observations
            AnimalObservation obs1 = new AnimalObservation();
            obs1.setAnimalId(a1.getId()); obs1.setAnimalTagId(a1.getTagId()); obs1.setObserverName("Ravi Kumar (Range Officer)");
            obs1.setObservationDate(LocalDate.now().minusDays(2)); obs1.setLocation("Dhikala Ramganga Reservoir Shore");
            obs1.setBehavior("Resting near high reeds after successful river crossing; robust vitality"); obs1.setHealthNote("Optimal"); observationService.save(obs1);

            AnimalObservation obs2 = new AnimalObservation();
            obs2.setAnimalId(a4.getId()); obs2.setAnimalTagId(a4.getTagId()); obs2.setObserverName("Priya Sharma (Veterinary Officer)");
            obs2.setObservationDate(LocalDate.now().minusDays(3)); obs2.setLocation("Kaziranga Bagori Marsh Border");
            obs2.setBehavior("Slow feeding on hyacinth; cautious right leg weight-bearing"); obs2.setHealthNote("Superficial wound cleaning underway"); observationService.save(obs2);

            AnimalObservation obs3 = new AnimalObservation();
            obs3.setAnimalId(a3.getId()); obs3.setAnimalTagId(a3.getTagId()); obs3.setObserverName("Amit Singh (Head Forester)");
            obs3.setObservationDate(LocalDate.now().minusDays(4)); obs3.setLocation("Bijrani Corridor Crossing");
            obs3.setBehavior("Leading matriarch herd of 11 through seasonal trail; highly alert"); obs3.setHealthNote("Excellent physical condition"); observationService.save(obs3);
        }
    }
}
