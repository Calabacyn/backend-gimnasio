package com.backend_gimnasio.backend_gimnasio.services.impl;

import com.backend_gimnasio.backend_gimnasio.model.dtos.WorkoutPlanDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.WorkoutPlan;
import com.backend_gimnasio.backend_gimnasio.model.mappers.WorkoutPlanMapper;
import com.backend_gimnasio.backend_gimnasio.repositories.WorkoutPlanRepository;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IWorkoutPlanService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutPlanServiceImpl implements IWorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlanServiceImpl(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    @Override
    public List<WorkoutPlanDTO> getAllWorkoutPlans() {
        List<WorkoutPlan> plans = workoutPlanRepository.findAll();
        return plans.stream()
                .map(WorkoutPlanMapper::toDTO)
                .collect(Collectors.toList());
    }
}
