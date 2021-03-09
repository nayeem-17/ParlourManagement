package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private Integer customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private String details;
    private String gender;
    private String address;

    private List<CustomerInvoice> customerInvoiceList = new ArrayList<>();

    public Customer() {
    }

    public Customer(Integer customerId, String name, String email, String mobileNumber, String details, String gender, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.details = details;
        this.gender = gender;
        this.address = address;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CustomerInvoice> getCustomerInvoiceList() {
        return customerInvoiceList;
    }

    public void setCustomerInvoiceList(List<CustomerInvoice> customerInvoiceList) {
        this.customerInvoiceList = customerInvoiceList;
    }
}
