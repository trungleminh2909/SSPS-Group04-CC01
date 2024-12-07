package com.software.ssps.Entity;

import java.sql.Date;

public class paymentHistory {
    public enum paymentStatus {
        CANCELLED,
        COMPLETED,
    }

    Integer paymentID;
    Integer StudentID;
    Date paymentDate;
    Integer pageBought;

    Integer paymentAmount;

    String Note;

}
