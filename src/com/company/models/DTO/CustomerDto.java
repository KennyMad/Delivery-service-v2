package com.company.models.DTO;

public class CustomerDto {

    private int id;

    private String name;

    private String additionalInformation;

    public CustomerDto() {}

    public CustomerDto(String name, String additionalInformation){
        this.name = name;
        this.additionalInformation = additionalInformation;
    }

    public CustomerDto(String name, String additionalInformation, int id){
        this.name = name;
        this.additionalInformation = additionalInformation;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                '}';
    }
}
