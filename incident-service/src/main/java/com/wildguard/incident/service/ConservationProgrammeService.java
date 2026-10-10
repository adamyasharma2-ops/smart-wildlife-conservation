package com.wildguard.incident.service;

import com.wildguard.incident.entity.ConservationProgramme;
import com.wildguard.incident.entity.ProgrammeActivity;
import com.wildguard.incident.repository.ConservationProgrammeRepository;
import com.wildguard.incident.repository.ProgrammeActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConservationProgrammeService {
    
    private final ConservationProgrammeRepository programmeRepository;
    private final ProgrammeActivityRepository activityRepository;
    
    public ConservationProgrammeService(ConservationProgrammeRepository programmeRepository, ProgrammeActivityRepository activityRepository) {
        this.programmeRepository = programmeRepository;
        this.activityRepository = activityRepository;
    }
    
    public List<ConservationProgramme> getAll() {
        return programmeRepository.findAll();
    }
    
    public Optional<ConservationProgramme> getById(Long id) {
        return programmeRepository.findById(id);
    }
    
    @Transactional
    public ConservationProgramme save(ConservationProgramme programme) {
        if (programme.getId() == null && (programme.getProgrammeCode() == null || programme.getProgrammeCode().isEmpty())) {
            long count = programmeRepository.count() + 1;
            programme.setProgrammeCode(String.format("CP-%03d", count));
        }
        if (programme.getStatus() == null) {
            programme.setStatus("PLANNED");
        }
        return programmeRepository.save(programme);
    }
    
    @Transactional
    public void delete(Long id) {
        programmeRepository.deleteById(id);
    }
    
    public List<ConservationProgramme> getByStatus(String status) {
        return programmeRepository.findByStatus(status);
    }
    
    public List<ProgrammeActivity> getActivitiesByProgrammeId(Long programmeId) {
        return activityRepository.findByProgrammeId(programmeId);
    }
    
    @Transactional
    public ProgrammeActivity addActivity(Long programmeId, ProgrammeActivity activity) {
        activity.setProgrammeId(programmeId);
        if (activity.getStatus() == null) {
            activity.setStatus("PENDING");
        }
        ProgrammeActivity saved = activityRepository.save(activity);
        recalculateProgress(programmeId);
        return saved;
    }
    
    @Transactional
    public ProgrammeActivity updateActivity(Long activityId, ProgrammeActivity updated) {
        ProgrammeActivity existing = activityRepository.findById(activityId).orElseThrow();
        existing.setActivityName(updated.getActivityName());
        existing.setDescription(updated.getDescription());
        existing.setTargetDate(updated.getTargetDate());
        existing.setCompletedDate(updated.getCompletedDate());
        existing.setStatus(updated.getStatus());
        existing.setProgressPercent(updated.getProgressPercent());
        existing.setNotes(updated.getNotes());
        ProgrammeActivity saved = activityRepository.save(existing);
        recalculateProgress(existing.getProgrammeId());
        return saved;
    }
    
    @Transactional
    public ProgrammeActivity updateActivityStatus(Long activityId, String status, int progressPercent) {
        ProgrammeActivity existing = activityRepository.findById(activityId).orElseThrow();
        existing.setStatus(status);
        existing.setProgressPercent(progressPercent);
        ProgrammeActivity saved = activityRepository.save(existing);
        recalculateProgress(existing.getProgrammeId());
        return saved;
    }
    
    @Transactional
    public void recalculateProgress(Long programmeId) {
        List<ProgrammeActivity> activities = activityRepository.findByProgrammeId(programmeId);
        if (activities.isEmpty()) return;
        
        int totalProgress = 0;
        for (ProgrammeActivity activity : activities) {
            totalProgress += activity.getProgressPercent();
        }
        
        int avg = totalProgress / activities.size();
        
        ConservationProgramme programme = programmeRepository.findById(programmeId).orElseThrow();
        programme.setCompletionPercentage(avg);
        if (avg == 100) {
            programme.setStatus("COMPLETED");
        } else if (avg > 0 && "PLANNED".equals(programme.getStatus())) {
            programme.setStatus("ACTIVE");
        }
        programmeRepository.save(programme);
    }
}
