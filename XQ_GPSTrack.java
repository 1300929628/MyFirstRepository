package rubic.test.xq.gps.dbscan;

import java.util.Date;

public class XQ_GPSTrack {

    private Long id;
    private Double speed;
    private Integer angle;
    private Double longitude;
    private Double latitude;
    private String plateNo;
    private String gpsNo;
    private Date gpsUploadTime;
    private Integer companyId;
    private String gpsName;
    private Integer alarm;
    private Integer elevation;
    private Integer statusField;

    private Date searchStartTime;
    private Date searchEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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

    public Date getGpsUploadTime() {
        return gpsUploadTime;
    }

    public void setGpsUploadTime(Date gpsUploadTime) {
        this.gpsUploadTime = gpsUploadTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getGpsName() {
        return gpsName;
    }

    public void setGpsName(String gpsName) {
        this.gpsName = gpsName;
    }

    public Integer getAlarm() {
		return alarm;
	}

	public void setAlarm(Integer alarm) {
		this.alarm = alarm;
	}

	public Integer getElevation() {
		return elevation;
	}

	public void setElevation(Integer elevation) {
		this.elevation = elevation;
	}

	public Integer getStatusField() {
		return statusField;
	}

	public void setStatusField(Integer statusField) {
		this.statusField = statusField;
	}

	public Date getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(Date searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public Date getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(Date searchEndTime) {
        this.searchEndTime = searchEndTime;
    }
}
