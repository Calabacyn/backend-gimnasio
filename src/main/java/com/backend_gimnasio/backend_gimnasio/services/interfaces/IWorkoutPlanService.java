package com.backend_gimnasio.backend_gimnasio.services.interfaces;

import com.backend_gimnasio.backend_gimnasio.model.dtos.WorkoutPlanDTO;

import java.util.List;

public interface IWorkoutPlanService {
    List<WorkoutPlanDTO> getAllWorkoutPlans();
}
