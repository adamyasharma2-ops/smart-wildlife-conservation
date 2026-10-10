package com.wildguard.protectedarea.config;

import com.wildguard.protectedarea.entity.Habitat;
import com.wildguard.protectedarea.entity.ProtectedArea;
import com.wildguard.protectedarea.entity.WaterSource;
import com.wildguard.protectedarea.entity.Zone;
import com.wildguard.protectedarea.service.HabitatService;
import com.wildguard.protectedarea.service.ProtectedAreaService;
import com.wildguard.protectedarea.service.WaterSourceService;
import com.wildguard.protectedarea.service.ZoneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProtectedAreaService areaService;
    private final ZoneService zoneService;
    private final HabitatService habitatService;
    private final WaterSourceService waterSourceService;

    public DataSeeder(ProtectedAreaService areaService, ZoneService zoneService, HabitatService habitatService, WaterSourceService waterSourceService) {
        this.areaService = areaService;
        this.zoneService = zoneService;
        this.habitatService = habitatService;
        this.waterSourceService = waterSourceService;
    }

    @Override
    public void run(String... args) {
        if (areaService.findAll().isEmpty()) {
            ProtectedArea pa1 = new ProtectedArea();
            pa1.setAreaCode("PA-001");
            pa1.setName("Jim Corbett National Park");
            pa1.setAreaType("NATIONAL_PARK");
            pa1.setState("Uttarakhand");
            pa1.setTotalAreaKm2(520.8);
            pa1.setStatus("ACTIVE");
            pa1.setEstablishedDate(LocalDate.of(1936, 8, 8));
            areaService.save(pa1);

            ProtectedArea pa2 = new ProtectedArea();
            pa2.setAreaCode("PA-002");
            pa2.setName("Ranthambore Tiger Reserve");
            pa2.setAreaType("TIGER_RESERVE");
            pa2.setState("Rajasthan");
            pa2.setTotalAreaKm2(1334.0);
            pa2.setStatus("ACTIVE");
            pa2.setEstablishedDate(LocalDate.of(1980, 11, 1));
            areaService.save(pa2);

            ProtectedArea pa3 = new ProtectedArea();
            pa3.setAreaCode("PA-003");
            pa3.setName("Kaziranga National Park");
            pa3.setAreaType("NATIONAL_PARK");
            pa3.setState("Assam");
            pa3.setTotalAreaKm2(430.0);
            pa3.setStatus("ACTIVE");
            pa3.setEstablishedDate(LocalDate.of(1974, 2, 11));
            areaService.save(pa3);

            Zone z1 = new Zone();
            z1.setZoneCode("Z-001");
            z1.setName("Dhikala Zone");
            z1.setProtectedAreaId(pa1.getId());
            z1.setProtectedAreaName(pa1.getName());
            z1.setZoneType("CORE");
            z1.setStatus("ACTIVE");
            zoneService.save(z1);

            Zone z2 = new Zone();
            z2.setZoneCode("Z-002");
            z2.setName("Bijrani Zone");
            z2.setProtectedAreaId(pa1.getId());
            z2.setProtectedAreaName(pa1.getName());
            z2.setZoneType("BUFFER");
            z2.setStatus("ACTIVE");
            zoneService.save(z2);

            Zone z3 = new Zone();
            z3.setZoneCode("Z-003");
            z3.setName("Zone 1");
            z3.setProtectedAreaId(pa2.getId());
            z3.setProtectedAreaName(pa2.getName());
            z3.setZoneType("CORE");
            z3.setStatus("ACTIVE");
            zoneService.save(z3);

            Zone z4 = new Zone();
            z4.setZoneCode("Z-004");
            z4.setName("Zone 6");
            z4.setProtectedAreaId(pa2.getId());
            z4.setProtectedAreaName(pa2.getName());
            z4.setZoneType("BUFFER");
            z4.setStatus("ACTIVE");
            zoneService.save(z4);
            
            Zone z5 = new Zone();
            z5.setZoneCode("Z-005");
            z5.setName("Central Range");
            z5.setProtectedAreaId(pa3.getId());
            z5.setProtectedAreaName(pa3.getName());
            z5.setZoneType("CORE");
            z5.setStatus("ACTIVE");
            zoneService.save(z5);

            Zone z6 = new Zone();
            z6.setZoneCode("Z-006");
            z6.setName("Western Range");
            z6.setProtectedAreaId(pa3.getId());
            z6.setProtectedAreaName(pa3.getName());
            z6.setZoneType("BUFFER");
            z6.setStatus("ACTIVE");
            zoneService.save(z6);

            Habitat h1 = new Habitat();
            h1.setZoneId(z1.getId());
            h1.setZoneName(z1.getName());
            h1.setProtectedAreaId(pa1.getId());
            h1.setProtectedAreaName(pa1.getName());
            h1.setHabitatType("DECIDUOUS_FOREST");
            h1.setCondition("EXCELLENT");
            habitatService.save(h1);

            Habitat h2 = new Habitat();
            h2.setZoneId(z5.getId());
            h2.setZoneName(z5.getName());
            h2.setProtectedAreaId(pa3.getId());
            h2.setProtectedAreaName(pa3.getName());
            h2.setHabitatType("GRASSLAND");
            h2.setCondition("GOOD");
            habitatService.save(h2);
            
            Habitat h3 = new Habitat();
            h3.setZoneId(z3.getId());
            h3.setZoneName(z3.getName());
            h3.setProtectedAreaId(pa2.getId());
            h3.setProtectedAreaName(pa2.getName());
            h3.setHabitatType("SCRUBLAND");
            h3.setCondition("FAIR");
            habitatService.save(h3);
            
            Habitat h4 = new Habitat();
            h4.setZoneId(z6.getId());
            h4.setZoneName(z6.getName());
            h4.setProtectedAreaId(pa3.getId());
            h4.setProtectedAreaName(pa3.getName());
            h4.setHabitatType("WETLAND");
            h4.setCondition("GOOD");
            habitatService.save(h4);

            WaterSource w1 = new WaterSource();
            w1.setName("Ramganga River");
            w1.setZoneId(z1.getId());
            w1.setZoneName(z1.getName());
            w1.setProtectedAreaId(pa1.getId());
            w1.setProtectedAreaName(pa1.getName());
            w1.setSourceType("RIVER");
            w1.setCondition("GOOD");
            waterSourceService.save(w1);

            WaterSource w2 = new WaterSource();
            w2.setName("Padam Talao");
            w2.setZoneId(z3.getId());
            w2.setZoneName(z3.getName());
            w2.setProtectedAreaId(pa2.getId());
            w2.setProtectedAreaName(pa2.getName());
            w2.setSourceType("LAKE");
            w2.setCondition("FAIR");
            waterSourceService.save(w2);
            
            WaterSource w3 = new WaterSource();
            w3.setName("Brahmaputra River Bank");
            w3.setZoneId(z5.getId());
            w3.setZoneName(z5.getName());
            w3.setProtectedAreaId(pa3.getId());
            w3.setProtectedAreaName(pa3.getName());
            w3.setSourceType("RIVER");
            w3.setCondition("GOOD");
            waterSourceService.save(w3);
            
            WaterSource w4 = new WaterSource();
            w4.setName("Donga Waterhole");
            w4.setZoneId(z6.getId());
            w4.setZoneName(z6.getName());
            w4.setProtectedAreaId(pa3.getId());
            w4.setProtectedAreaName(pa3.getName());
            w4.setSourceType("WATERHOLE");
            w4.setCondition("SEASONAL");
            waterSourceService.save(w4);
        }
    }
}
