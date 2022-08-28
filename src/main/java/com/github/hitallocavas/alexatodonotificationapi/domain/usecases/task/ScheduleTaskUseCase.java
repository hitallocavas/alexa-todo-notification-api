package com.github.hitallocavas.alexatodonotificationapi.domain.usecases.task;

import com.github.hitallocavas.alexatodonotificationapi.domain.usecases.UseCase;
import com.github.hitallocavas.alexatodonotificationapi.infrastructure.rest.request.PostTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import static com.github.hitallocavas.alexatodonotificationapi.Constants.Qualifiers;

@Service
@Qualifier(Qualifiers.SCHEDULE_TASK_USE_CASE_QUALIFIER)
@RequiredArgsConstructor
public class ScheduleTaskUseCase implements UseCase<PostTaskRequest, Void, Void> {
    private final TaskScheduler taskScheduler;

    @Override
    public Void execute(PostTaskRequest input, Void context) {
        taskScheduler.schedule(() -> sendNotification(input), new CronTrigger(input.getCronExpression()));
        return null;
    }

    public void sendNotification(PostTaskRequest postTaskRequest){
        System.out.println(postTaskRequest.getTaskName() + ":" + postTaskRequest.getTaskMessage());
    }
}
