package entities;

import java.io.Serializable;

public class Service implements Serializable {

    private String id;
    private String serviceName;
    private String servicePrice;

    public Service() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id='" + id + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", servicePrice='" + servicePrice + '\'' +
                '}';
    }
}
