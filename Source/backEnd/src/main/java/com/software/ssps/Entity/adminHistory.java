package com.software.ssps.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class adminHistory {
    @Id
    String timeStamp;
    String adminID; // the person make the change
    String action; // the change
    String detail; // detail of the change

    public adminHistory(String adminID, String action, String detail) {
        this.timeStamp = LocalDateTime.now().toString();
        this.adminID = adminID;
        this.action = action;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return timeStamp + "_Admin: " + adminID + "_" + action + "_" + detail;
    }

}
