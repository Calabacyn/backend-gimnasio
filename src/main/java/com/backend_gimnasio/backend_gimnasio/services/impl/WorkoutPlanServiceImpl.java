package  com.backend_gimnasio.backend_gimnasio.services.impl;


import com.backend_gimnasio.backend_gimnasio.model.dtos.WorkoutPlanDTO;
import com.backend_gimnasio.backend_gimnasio.services.interfaces.IWorkoutPlanService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutPlanServiceImpl implements IWorkoutPlanService {

    private final List<WorkoutPlanDTO> plans = new ArrayList<>();

    public WorkoutPlanServiceImpl() {
        plans.add(new WorkoutPlanDTO(1L, 1L, "Plan A","Plan B", "Description Plan A", LocalDate.now(), 1L, "Lara", LocalDate.now().plusMonths(1)));
        plans.add(new WorkoutPlanDTO(2L, 2L, "Plan B","Plan B", "Description Plan B", LocalDate.now(), 2L, "Lara", LocalDate.now().plusMonths(2)));
    }



    @Override
    public List<WorkoutPlanDTO> findAll() {
        return plans;
    }

    @Override
    public Optional<WorkoutPlanDTO> findById(Long id) {
        return plans.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public WorkoutPlanDTO create(WorkoutPlanDTO plan) {
        plan.setId((long) (plans.size() + 1));
        plans.add(plan);
        return plan;
    }

    @Override
    public Optional<WorkoutPlanDTO> update(Long id, WorkoutPlanDTO plan) {
        for (int i = 0; i < plans.size(); i++) {
            if (plans.get(i).getId().equals(id)) {
                plan.setId(id);
                plans.set(i, plan);
                return Optional.of(plan);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return plans.removeIf(p -> p.getId().equals(id));
    }
}
