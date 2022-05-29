package org.intelciaitsolution.workercompany.infrastructure.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class AppUtils {

    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public AppUtils() {}

    public static String getFormattedDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return date.format(formatter);
    }
}
