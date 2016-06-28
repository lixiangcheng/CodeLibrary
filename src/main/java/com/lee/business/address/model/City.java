package com.lee.business.address.model;

import java.io.Serializable;

public class City implements Serializable {
    private Long id;

    private String cityName;

    private String cityCode;

    private String cityPy;

    private String cityPyCap;

    private String areaCode;

    private String zip;

    private Integer provinceId;

    private String hotCity;

    private String isvalid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCityPy() {
        return cityPy;
    }

    public void setCityPy(String cityPy) {
        this.cityPy = cityPy == null ? null : cityPy.trim();
    }

    public String getCityPyCap() {
        return cityPyCap;
    }

    public void setCityPyCap(String cityPyCap) {
        this.cityPyCap = cityPyCap == null ? null : cityPyCap.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getHotCity() {
        return hotCity;
    }

    public void setHotCity(String hotCity) {
        this.hotCity = hotCity == null ? null : hotCity.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }
}