package com.misabelleeli.indytrip;

/**
 * Created by Lee on 2014-07-27.
 */
public class Result {
    private String resullt_image;
    private String result_name;
    private String result_address;
    private String reseult_description;
    private int longtitude;
    private int latitude;

    public String getResullt_image() {
        return resullt_image;
    }

    public void setResullt_image(String resullt_image) {
        this.resullt_image = resullt_image;
    }

    public String getResult_name() {
        return result_name;
    }

    public void setResult_name(String result_name) {
        this.result_name = result_name;
    }

    public String getReseult_description() {
        return reseult_description;
    }

    public void setReseult_description(String reseult_description) {
        this.reseult_description = reseult_description;
    }

    public int getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(int longtitude) {
        this.longtitude = longtitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public String getResult_address(){
        return result_address;
    }

    public void setResult_address(String address) {
        this.result_address = address;
    }

    public Result(String resullt_image, String result_name, String result_address, String reseult_description, int longtitude, int latitude) {
        this.result_address = result_address;
        this.resullt_image = resullt_image;
        this.result_name = result_name;
        this.reseult_description = reseult_description;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }
}
