package com.software.ssps.Entity;

import java.sql.Date;

public class printHistory {
    public enum PrintStatus {
        CANCELLED,
        COMPLETED,
        PRINTING;
    }

    Integer printID;
    Integer StudentID;
    String fileName;
    Date printDate;
    String Note;

}
