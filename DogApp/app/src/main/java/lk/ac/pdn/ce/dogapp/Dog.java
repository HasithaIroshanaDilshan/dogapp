package lk.ac.pdn.ce.dogapp;

import java.util.Date;

/**
 * Created by Hishan Indrajith on 12/4/2017.
 */
public class Dog {
    private int id;
    private double locationLatitude;
    private double locationLongitude;
    private String mainLocalPhotoAddress;
    private String colorCode;
    private String size;
    private String type;
    private String dateTime;

    public Dog() {
    }



    public Dog(int id, double locationLatitude, double locationLongitude, String colorCode, String size, String type, String dateTime,String mainLocalPhotoAddress) {
        this.id = id;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.colorCode = colorCode;
        this.size = size;
        this.type = type;
        this.dateTime = dateTime;
        this.mainLocalPhotoAddress=mainLocalPhotoAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMainLocalPhotoAddress() {
        return mainLocalPhotoAddress;
    }

    public void setMainLocalPhotoAddress(String mainLocalPhotoAddress) {
        this.mainLocalPhotoAddress = mainLocalPhotoAddress;
    }
}
