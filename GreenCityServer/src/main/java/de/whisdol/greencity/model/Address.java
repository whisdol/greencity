package de.whisdol.greencity.model;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.AddressDAO;
import de.whisdol.greencity.dao.CityDAO;

/**
 * Created by cedric on 22.04.17.
 */
public class Address {
    private long id;
    private String roadName;
    private String houseNumber;
    private String postCode;
    private float latitude;
    private float longitude;
    private City city;

    public Address() {
        city = new City();
    }

    public Address(String roadName, String houseNumber, String postCode, float latitude, float longitude, City city) {
        this.roadName = roadName;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public Address(String roadName, String houseNumber, String postCode, float latitude, float longitude, long cityId) {
        this.id = id;
        this.roadName = roadName;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
        setCity(cityId);
    }

    public Address(long id, String roadName, String houseNumber, String postCode, float latitude, float longitude, City city) {
        this.id = id;
        this.roadName = roadName;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public Address(long id, String roadName, String houseNumber, String postCode, float latitude, float longitude, long cityId) {
        this.id = id;
        this.roadName = roadName;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.latitude = latitude;
        this.longitude = longitude;
        setCity(cityId);
    }

    public Address(Address address) {
        this.id = address.id;
        this.roadName = address.roadName;
        this.houseNumber = address.houseNumber;
        this.postCode = address.postCode;
        this.latitude = address.latitude;
        this.longitude = address.longitude;
        this.city = address.city;
    }

    public Address(long id) {
        this.id = id;
        AddressDAO addressDao = (AddressDAO) GreencityserverApplication.context.getBean("AddressDAO");
        Address dbAddress = addressDao.selectAddressById(id);
        this.roadName = dbAddress.roadName;
        this.houseNumber = dbAddress.houseNumber;
        this.postCode = dbAddress.postCode;
        this.latitude = dbAddress.latitude;
        this.longitude = dbAddress.longitude;
        this.city = dbAddress.city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setCity(long cityId) {
        city = new City(cityId);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", roadName='" + roadName + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city=" + city +
                '}';
    }
}
