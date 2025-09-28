package com.backend_gimnasio.backend_gimnasio.controllers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.WorkoutPlanDTO;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IWorkoutPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {

    private final IWorkoutPlanService workoutPlanService;

    public WorkoutPlanController(IWorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    @GetMapping
    public List<WorkoutPlanDTO> getAllPlans() {
        return workoutPlanService.getAllWorkoutPlans();
    }
}
