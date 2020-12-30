package rubic.test.xq.gps.dbscan;

import java.io.Serializable;
import java.util.Date;

public class XQ_GPSEquipmentVo implements Serializable {
    private Integer id;
    private String plateNo;
    private String gpsNo;
    private String gpsName;
    private Integer status;
    private Integer companyId;
    private Date createTime;
    private Date updateTime;

    private String statusDesc;

    // 平均速度
    private Double aveSpeed;

    // 总里程
    private Double mileage;

    /**
     * 是否掉线报警推送
     */
    private Integer isDetect;

    /**
     * 推送的手机号码
     */
    private String pushPhone;

    private Integer vehicleId;

    private Date startTime;

    private Date endTime;

    /**
     * 设备来源。默认为0，表示客户从讯轻购买的设备。数据存储在讯轻。1表示G7，数据存储在G7'
     */
    private Integer source;

    private Integer vehicleType;

    /**
     * 外部平台公钥
     */
    private String appKey;

    /**
     * 外部平台私钥
     */
    private String appSecret;

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getGpsNo() {
        return gpsNo;
    }

    public void setGpsNo(String gpsNo) {
        this.gpsNo = gpsNo;
    }

    public String getGpsName() {
        return gpsName;
    }

    public void setGpsName(String gpsName) {
        this.gpsName = gpsName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getAveSpeed() {
        return aveSpeed;
    }

    public void setAveSpeed(Double aveSpeed) {
        this.aveSpeed = aveSpeed;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Integer getIsDetect() {
        return isDetect;
    }

    public void setIsDetect(Integer isDetect) {
        this.isDetect = isDetect;
    }

    public String getPushPhone() {
        return pushPhone;
    }

    public void setPushPhone(String pushPhone) {
        this.pushPhone = pushPhone;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
