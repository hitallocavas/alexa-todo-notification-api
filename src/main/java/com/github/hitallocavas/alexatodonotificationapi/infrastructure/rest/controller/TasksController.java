package com.github.hitallocavas.alexatodonotificationapi.infrastructure.rest.controller;

import com.github.hitallocavas.alexatodonotificationapi.Constants;
import com.github.hitallocavas.alexatodonotificationapi.domain.usecases.UseCase;
import com.github.hitallocavas.alexatodonotificationapi.infrastructure.rest.request.PostTaskRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.hitallocavas.alexatodonotificationapi.Constants.Qualifiers.SCHEDULE_TASK_USE_CASE_QUALIFIER;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final UseCase<PostTaskRequest, Void, Void> scheduleTaskUseCase;

    public TasksController(
            @Qualifier(SCHEDULE_TASK_USE_CASE_QUALIFIER) UseCase<PostTaskRequest, Void, Void> scheduleTaskUseCase
    ) {
        this.scheduleTaskUseCase = scheduleTaskUseCase;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarTask(@RequestBody PostTaskRequest postTaskRequest){
        this.scheduleTaskUseCase.execute(postTaskRequest, null);
        return ResponseEntity.noContent().build();
    }

}
