package com.zdf.taskmanager.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public final class CommonUtil {

    private CommonUtil() {
        throw new AssertionError("No CommonUtil instances allowed!");
    }

    public static <T> boolean isNotEmpty(List<T> list) {
        return Objects.nonNull(list) && !list.isEmpty();
    }

    public static String generateTaskId() {
        Long timeStr = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()).toInstant().toEpochMilli();
        return "TA_" + String.valueOf(timeStr);
    }

}
