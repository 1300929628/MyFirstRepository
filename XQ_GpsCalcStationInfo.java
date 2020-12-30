package rubic.test.xq.gps.dbscan;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: XQ_GpsCalcStationInfo
 * @Description: gps计算站点信息实体类
 * @author rubic
 * @date 2020年4月15日 下午6:57:46
 */
public class XQ_GpsCalcStationInfo {

	// 主键ID
	private Integer id;

	// 公司ID
	private Integer companyId;

	// 用户ID
	private Integer adminId;

	// 站点ID
	private Integer stationId;

	// 车牌号
	private String platNo;

	// gps设备号
	private String gpsNo;

	// 预测的停留点经度
	private Double stopPointLongitude;

	// 预测的停留点纬度
	private Double stopPointLatitude;

	// 预测的停留点匹配站点的距离
	private Integer matchDistance;

	// 到站时间
	private Long arrivalTime;

	// 离开时间
	private Long leaveTime;

	// gps数据上传日期，具体到某一天的时间戳
	private Long gpsUploadDay;

	private Date createTime;
	private Date updateTime;
	private Integer status;

	// 关联表数据
	private String stationNo;
	private String stationName;
	private String stationDetailAddress;
	private Double stationLongitude;
	private Double stationLatitude;

	// 关联的站点交接时长统计信息
	private XQ_GpsCalcStationStatisticsInfo statisticsInfo;

	// 查询条件
	private Long gpsUploadStartTime;
	private Long gpsUploadEndTime;

	private String stationInfoIdsString;
	private List<Integer> stationInfoIds;

	public XQ_GpsCalcStationInfo() {
	}

	public XQ_GpsCalcStationInfo(Integer companyId) {
		this.companyId = companyId;
	}
	
	public XQ_GpsCalcStationInfo(Integer companyId, List<Integer> stationInfoIds) {
		this.companyId = companyId;
		this.stationInfoIds = stationInfoIds;
	}

	public XQ_GpsCalcStationInfo(String gpsNo) {
		this.gpsNo = gpsNo;
	}

	public XQ_GpsCalcStationInfo(Double stopPointLongitude, Double stopPointLatitude) {
		this.stopPointLongitude = stopPointLongitude;
		this.stopPointLatitude = stopPointLatitude;
	}

	public XQ_GpsCalcStationStatisticsInfo toStatisticsInfo() {
		return new XQ_GpsCalcStationStatisticsInfo(companyId, stationId, arrivalTime, leaveTime);
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

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getPlatNo() {
		return platNo;
	}

	public void setPlatNo(String platNo) {
		this.platNo = platNo;
	}

	public String getGpsNo() {
		return gpsNo;
	}

	public void setGpsNo(String gpsNo) {
		this.gpsNo = gpsNo;
	}

	public Double getStopPointLongitude() {
		return stopPointLongitude;
	}

	public void setStopPointLongitude(Double stopPointLongitude) {
		this.stopPointLongitude = stopPointLongitude;
	}

	public Double getStopPointLatitude() {
		return stopPointLatitude;
	}

	public void setStopPointLatitude(Double stopPointLatitude) {
		this.stopPointLatitude = stopPointLatitude;
	}

	public Integer getMatchDistance() {
		return matchDistance;
	}

	public void setMatchDistance(Integer matchDistance) {
		this.matchDistance = matchDistance;
	}

	public Long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Long getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Long leaveTime) {
		this.leaveTime = leaveTime;
	}

	public Long getGpsUploadDay() {
		return gpsUploadDay;
	}

	public void setGpsUploadDay(Long gpsUploadDay) {
		this.gpsUploadDay = gpsUploadDay;
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

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationDetailAddress() {
		return stationDetailAddress;
	}

	public void setStationDetailAddress(String stationDetailAddress) {
		this.stationDetailAddress = stationDetailAddress;
	}

	public Double getStationLongitude() {
		return stationLongitude;
	}

	public void setStationLongitude(Double stationLongitude) {
		this.stationLongitude = stationLongitude;
	}

	public Double getStationLatitude() {
		return stationLatitude;
	}

	public void setStationLatitude(Double stationLatitude) {
		this.stationLatitude = stationLatitude;
	}

	public XQ_GpsCalcStationStatisticsInfo getStatisticsInfo() {
		return statisticsInfo;
	}

	public void setStatisticsInfo(XQ_GpsCalcStationStatisticsInfo statisticsInfo) {
		this.statisticsInfo = statisticsInfo;
	}

	public Long getGpsUploadStartTime() {
		return gpsUploadStartTime;
	}

	public void setGpsUploadStartTime(Long gpsUploadStartTime) {
		this.gpsUploadStartTime = gpsUploadStartTime;
	}

	public Long getGpsUploadEndTime() {
		return gpsUploadEndTime;
	}

	public void setGpsUploadEndTime(Long gpsUploadEndTime) {
		this.gpsUploadEndTime = gpsUploadEndTime;
	}

	public String getStationInfoIdsString() {
		return stationInfoIdsString;
	}

	public void setStationInfoIdsString(String stationInfoIdsString) {
		this.stationInfoIdsString = stationInfoIdsString;
		if (!StringUtil.checkNull(stationInfoIdsString)) {
			return;
		}
		List<Integer> stationInfoIds = StringUtil.parseIdsToInteger(stationInfoIdsString);
		this.setStationInfoIds(stationInfoIds);
	}

	public List<Integer> getStationInfoIds() {
		return stationInfoIds;
	}

	public void setStationInfoIds(List<Integer> stationInfoIds) {
		this.stationInfoIds = stationInfoIds;
	}

}
