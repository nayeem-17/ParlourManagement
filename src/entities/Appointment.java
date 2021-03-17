package entities;

import java.io.Serializable;
import java.util.UUID;

public class Appointment implements Serializable {
    private Integer appointmentId;
    private String appointmentNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private String appointMakingDate; //time when the appointment is made
    private String appointmentTime;
    private String selectedService;
    private String appointmentDate; //the appointment date
    private String remark;
    private String status;
    private String remarkDate;

    public Appointment() {
        status = "pending";
    }

    public Appointment(Integer appointmentId, String appointmentNumber, String name, String email, String phoneNumber, String appointMakingDate, String appointmentTime, String selectedService, String appointmentDate, String remark, String status, String remarkDate) {
        this.appointmentId = appointmentId;
        this.appointmentNumber = appointmentNumber;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.appointMakingDate = appointMakingDate;
        this.appointmentTime = appointmentTime;
        this.selectedService = selectedService;
        this.appointmentDate = appointmentDate;
        this.remark = remark;
        this.status = status;
        this.remarkDate = remarkDate;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(String appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAppointMakingDate() {
        return appointMakingDate;
    }

    public void setAppointMakingDate(String appointMakingDate) {
        this.appointMakingDate = appointMakingDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(String selectedService) {
        this.selectedService = selectedService;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarkDate() {
        return remarkDate;
    }

    public void setRemarkDate(String remarkDate) {
        this.remarkDate = remarkDate;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", appointmentNumber=" + appointmentNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", appointMakingDate='" + appointMakingDate + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", selectedService='" + selectedService + '\'' +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", remarkDate='" + remarkDate + '\'' +
                '}';
    }
}