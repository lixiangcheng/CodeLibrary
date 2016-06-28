package com.lee.business.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lixiangcheng on 16/6/24.
 */
public class User implements Serializable {
    private Long id;

    private String loginname;

    private String password;

    private String openid;

    private Date registerDate;

    private String status;

    private String userType;

    private String normalFlag;

    private String consultFlag;

    private String invitationCode;

    private String qrCode;

    private Integer providerId;

    private Integer superiorUserId;

    private String name;

    private String job;

    private String mobile;

    private Integer provinceId;

    private Integer cityId;

    private Integer districtId;

    private String linkAddr;

    private String zip;

    private Integer referUser;

    private String referPerson;

    private Integer referProviderId;

    private Long createUser;

    private Date createDate;

    private Integer modifyUser;

    private Date modifyDate;

    private String memo;

    private String alipayAccount;

    private String alipayAccountName;

    private String headimgurl;//头像

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getNormalFlag() {
        return normalFlag;
    }

    public void setNormalFlag(String normalFlag) {
        this.normalFlag = normalFlag == null ? null : normalFlag.trim();
    }

    public String getConsultFlag() {
        return consultFlag;
    }

    public void setConsultFlag(String consultFlag) {
        this.consultFlag = consultFlag == null ? null : consultFlag.trim();
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getSuperiorUserId() {
        return superiorUserId;
    }

    public void setSuperiorUserId(Integer superiorUserId) {
        this.superiorUserId = superiorUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getLinkAddr() {
        return linkAddr;
    }

    public void setLinkAddr(String linkAddr) {
        this.linkAddr = linkAddr == null ? null : linkAddr.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public Integer getReferUser() {
        return referUser;
    }

    public void setReferUser(Integer referUser) {
        this.referUser = referUser;
    }

    public String getReferPerson() {
        return referPerson;
    }

    public void setReferPerson(String referPerson) {
        this.referPerson = referPerson == null ? null : referPerson.trim();
    }

    public Integer getReferProviderId() {
        return referProviderId;
    }

    public void setReferProviderId(Integer referProviderId) {
        this.referProviderId = referProviderId;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Integer modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount == null ? null : alipayAccount.trim();
    }

    public String getAlipayAccountName() {
        return alipayAccountName;
    }

    public void setAlipayAccountName(String alipayAccountName) {
        this.alipayAccountName = alipayAccountName == null ? null : alipayAccountName.trim();
    }


}
