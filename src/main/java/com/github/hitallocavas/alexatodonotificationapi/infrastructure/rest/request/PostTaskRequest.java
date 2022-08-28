package com.github.hitallocavas.alexatodonotificationapi.infrastructure.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostTaskRequest {
    public static final String EMPTY_SPACE = " ";
    private String taskName;
    private String taskMessage;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime taskDateTime;

    private Boolean repeatEveryDay;

    public String getCronExpression() {
        var cronBuilder = new StringBuilder().append(taskDateTime.getSecond())
                .append(EMPTY_SPACE)
                .append(taskDateTime.getMinute())
                .append(EMPTY_SPACE)
                .append(taskDateTime.getHour())
                .append(EMPTY_SPACE);

        return repeatEveryDay ?
                cronBuilder.append("* * *").toString() :
                cronBuilder.append(taskDateTime.getDayOfMonth())
                        .append(EMPTY_SPACE)
                        .append(taskDateTime.getMonthValue())
                        .append(EMPTY_SPACE)
                        .append("*")
                        .toString();
    }
}
