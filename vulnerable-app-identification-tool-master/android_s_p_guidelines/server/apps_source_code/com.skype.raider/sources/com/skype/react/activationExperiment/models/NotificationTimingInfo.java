package com.skype.react.activationExperiment.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationTimingInfo {
    private String endDate;
    private int scheduleHourOfDay;
    private String startDate;

    public final int a() {
        return this.scheduleHourOfDay;
    }

    public final Date b() {
        return a(this.startDate);
    }

    public final Date c() {
        return a(this.endDate);
    }

    private static Date a(String date) {
        Date date2 = null;
        if (date == null) {
            return date2;
        }
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            return date2;
        }
    }
}
