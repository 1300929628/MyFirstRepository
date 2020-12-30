package rubic.test.xq.gps.dbscan;

import java.util.Date;

/**
 * @ClassName: XQ_GpsCalcStationStatisticsInfo
 * @Description: 通过gps计算站点的统计信息
 * @author rubic
 * @date 2020年4月27日 上午11:49:03
 */
public class XQ_GpsCalcStationStatisticsInfo {

	private Integer id;
	private Integer companyId;
	private Integer stationId;

	/**
	 * 统计次数
	 */
	private Integer statisticsTimes;

	/**
	 * 最短停留时长
	 */
	private Long minStopTime;

	/**
	 * 最长停留时长
	 */
	private Long maxStopTime;

	/**
	 * 平均停留时长
	 */
	private Long avgStopTime;

	private Date createTime;
	private Date updateTime;
	private Integer status;

	public XQ_GpsCalcStationStatisticsInfo() {
	}
	
	public XQ_GpsCalcStationStatisticsInfo(int companyId, int stationId, long arrivalTime, long leaveTime) {
		this.companyId = companyId;
		this.stationId = stationId;
		this.statisticsTimes = 1;
		long stopTime = leaveTime - arrivalTime;
		this.minStopTime = stopTime;
		this.maxStopTime = stopTime;
		this.avgStopTime = stopTime;
	}

	public void updateByGpsStationInfo(XQ_GpsCalcStationInfo stationInfo) {

		long stopTime = stationInfo.getLeaveTime() - stationInfo.getArrivalTime();

		this.setMinStopTime(this.minStopTime < stopTime ? this.minStopTime : stopTime);
		this.setMaxStopTime(this.maxStopTime > stopTime ? this.maxStopTime : stopTime);

		long avgStopTime = (this.avgStopTime * this.statisticsTimes + stopTime) / (this.statisticsTimes + 1);
		this.setAvgStopTime(avgStopTime);

		this.setStatisticsTimes(this.statisticsTimes + 1);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public Integer getStatisticsTimes() {
		return statisticsTimes;
	}

	public void setStatisticsTimes(Integer statisticsTimes) {
		this.statisticsTimes = statisticsTimes;
	}

	public Long getMinStopTime() {
		return minStopTime;
	}

	public void setMinStopTime(Long minStopTime) {
		this.minStopTime = minStopTime;
	}

	public Long getMaxStopTime() {
		return maxStopTime;
	}

	public void setMaxStopTime(Long maxStopTime) {
		this.maxStopTime = maxStopTime;
	}

	public Long getAvgStopTime() {
		return avgStopTime;
	}

	public void setAvgStopTime(Long avgStopTime) {
		this.avgStopTime = avgStopTime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
