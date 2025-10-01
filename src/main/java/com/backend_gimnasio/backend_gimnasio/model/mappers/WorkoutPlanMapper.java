package com.backend_gimnasio.backend_gimnasio.model.mappers;

import com.backend_gimnasio.backend_gimnasio.model.dtos.WorkoutPlanDTO;
import com.backend_gimnasio.backend_gimnasio.model.entities.WorkoutPlan;
import org.springframework.stereotype.Component;

@Component
public class WorkoutPlanMapper {

    public static WorkoutPlanDTO toDTO(WorkoutPlan entity) {
        if (entity == null) return null;

        return new WorkoutPlanDTO(
                entity.getId(),
                entity.getClient() != null ? entity.getClient().getId() : null,
                entity.getClient() != null ? entity.getClient().getFirstName() + " " + entity.getClient().getLastName() : null,
                entity.getTitle(),
                entity.getLink(),
                entity.getDescription(),
                entity.getCreationDate(),
                entity.getMembershipId(),
                entity.getRegisteredBy() != null ? entity.getRegisteredBy().getId() : null,
                entity.getRegisteredBy() != null ? entity.getRegisteredBy().getUserName() : null,
                entity.getExpirationDate()
        );
    }
}
