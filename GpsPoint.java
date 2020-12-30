package rubic.test.xq.gps.dbscan;

import java.util.Date;

public class GpsPoint {

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 速度
     */
    private Double speed;

    /**
     * 角度
     */
    private Integer angle;

    /**
     * gps记录的上传时间
     */
    private Date gpsUploadTime;

    /**
     * 是否为核心点
     */
    private Boolean isKey = false;
    
    public GpsPoint() {
    	
    }

    public GpsPoint(Double latitude, Double longitude, Double speed, Integer angle, Date gpsUploadTime) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.speed = speed;
		this.angle = angle;
		this.gpsUploadTime = gpsUploadTime;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
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

	public Date getGpsUploadTime() {
		return gpsUploadTime;
	}

	public void setGpsUploadTime(Date gpsUploadTime) {
		this.gpsUploadTime = gpsUploadTime;
	}

	public Boolean getIsKey() {
		return isKey;
	}

	public void setIsKey(Boolean isKey) {
		this.isKey = isKey;
	}

    @Override
    public String toString() {
        return this.latitude + ", " + this.longitude + ", " + this.speed + ", " + DateUtil.parseToString(this.gpsUploadTime, null);
    }

    public static GpsPoint transform(XQ_GPSTrack gps) {
		return new GpsPoint(gps.getLatitude(), gps.getLongitude(), gps.getSpeed(), gps.getAngle(),
				gps.getGpsUploadTime());
    }
}
