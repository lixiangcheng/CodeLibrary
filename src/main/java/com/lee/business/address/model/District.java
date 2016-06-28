package com.lee.business.address.model;

import java.io.Serializable;

public class District implements Serializable {
    private Long id;

    private String districtName;

    private String districtCode;

    private String districtPy;

    private String districtPyCap;

    private String areaCode;

    private String zip;

    private Integer provinceId;

    private Integer cityId;

    private String hotCity;

    private String isvalid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    public String getDistrictPy() {
        return districtPy;
    }

    public void setDistrictPy(String districtPy) {
        this.districtPy = districtPy == null ? null : districtPy.trim();
    }

    public String getDistrictPyCap() {
        return districtPyCap;
    }

    public void setDistrictPyCap(String districtPyCap) {
        this.districtPyCap = districtPyCap == null ? null : districtPyCap.trim();
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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