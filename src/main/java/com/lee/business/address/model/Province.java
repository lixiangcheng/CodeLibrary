package com.lee.business.address.model;

import java.io.Serializable;

public class Province implements Serializable {
    private Long id;

    private String provinceName;

    private String provinceCode;

    private String provincePy;

    private String provincePyCap;

    private String directCity;

    private String hotCity;

    private String isvalid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getProvincePy() {
        return provincePy;
    }

    public void setProvincePy(String provincePy) {
        this.provincePy = provincePy == null ? null : provincePy.trim();
    }

    public String getProvincePyCap() {
        return provincePyCap;
    }

    public void setProvincePyCap(String provincePyCap) {
        this.provincePyCap = provincePyCap == null ? null : provincePyCap.trim();
    }

    public String getDirectCity() {
        return directCity;
    }

    public void setDirectCity(String directCity) {
        this.directCity = directCity == null ? null : directCity.trim();
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