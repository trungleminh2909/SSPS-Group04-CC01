package com.software.ssps.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {
    String adminName;
    @Id
    String adminID;
    String username;
    String password;
    String adminEmail;
    String adminAddress;
    String avatar;
    Integer pageNumber;
    List<String> uploadedFiles;

    public Admin(String adminName, String adminID, String username, String password, String adminEmail,
            String adminAddress, String avatar, Integer pageNumber, List<String> uploadedFiles) {
        this.adminName = adminName;
        this.adminID = adminID;
        this.username = username;
        this.password = password;
        this.adminEmail = adminEmail;
        this.adminAddress = adminAddress;
        this.avatar = avatar;
        this.pageNumber = pageNumber;
        this.uploadedFiles = uploadedFiles;
    }

    // #region setter getter
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<String> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<String> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    // #endregion

    @Override
    public String toString() {
        return "Admin [adminName=" + adminName + ", adminID=" + adminID + ", username=" + username
                + ", password=" + password + ", adminEmail=" + adminEmail + ", adminAddress=" + adminAddress
                + ", avatar=" + avatar + ", pageNumber=" + pageNumber + ", uploadedFiles=" + uploadedFiles + "]";
    }

}
