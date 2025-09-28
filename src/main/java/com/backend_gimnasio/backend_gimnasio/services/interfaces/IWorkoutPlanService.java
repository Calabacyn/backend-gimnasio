package  com.backend_gimnasio.backend_gimnasio.services.interfaces;



import com.backend_gimnasio.backend_gimnasio.model.dtos.WorkoutPlanDTO;

import java.util.List;
import java.util.Optional;

public interface IWorkoutPlanService {

    List<WorkoutPlanDTO> findAll();

    Optional<WorkoutPlanDTO> findById(Long id);

    WorkoutPlanDTO create(WorkoutPlanDTO plan);

    Optional<WorkoutPlanDTO> update(Long id, WorkoutPlanDTO plan);

    boolean delete(Long id);
}
