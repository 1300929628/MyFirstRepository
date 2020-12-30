package rubic.test.xq.gps.dbscan;

import java.util.Date;
import java.util.List;

public class XQ_StationAddressRecord {

	private Integer id;

	private Integer companyId;
	// 站点所在片区ID
	private Integer stationAreaId;
	// 站点所在省份
	private String provinceName;
	// 站点所在城市
	private String cityName;
	// 站点所在区县
	private String districtName;
	// 站点编号
	private String stationNo;
	// 站点名称
	private String stationName;
	// 站点对应的详细地址
	private String stationDetailAddress;
	// 高德解析出的实际详细地址
	private String amapParseAddress;
	// 高德地图区域编码
	private String amapAdcode;

	private Double longitude;

	private Double latitude;
	// 允许停留时间（分钟）
	private Integer stayTime;
	// 交接货时间方式，0：无；1：平均时间；2：阶梯时间
	private Integer handoverTimeType;
	// 计时方式（1、重量（kg）；2、数量（件）；3、体积（m³））
	private Integer handoverTimeMethod;
	// 交接货时长（分钟）
	private Integer handoverTime;
	// 排队时长（分钟）
	private Integer queueTime;

	private Date createTime;

	private Date updateTime;

	private Integer status;
	// 优先级（0代表没有优先级，大于0时值越小优先级越高）
	private Integer priority;

	// 扩展字段
	// 站点所在片区名称
	private String stationAreaName;
	// 公司名称
	private String companyName;
	// 车型数量
	private Integer vehicleTypeNumber;
	// 模糊搜索字段
	private String fuzzy;
	// 排序字段名
	private String sortName;
	private String sortName2;
	// 升降序
	private String sortType;

	private Integer isNewStation;

	private List<String> addresses;

	// 允许进入的最大车型
	private String maxVehicleType;

	// 所属行
	private Integer row;

	// 原始经度
	private Double originLongitude;
	// 原始维度
	private Double originLatitude;
	// 定位来源，内容可参考枚举类LocationSourceType
	private Integer locationType;

	// 回写错误的列数
	private int writeCol;

	// 禁用车型
	private String vehicleTypeName;

	// 是否可以判定为特殊站点
	private boolean canJudgedAsSpecial;

	// 是否是重要客户
	private Integer isKeyAccount;

	// 校准后的经度
	private Double correctLongitude;
	
	// 校准后的纬度
	private Double correctLatitude;

	public XQ_StationAddressRecord() {

	}
	
	public XQ_StationAddressRecord(Integer companyId) {
		this.companyId = companyId;
	}

	public XQ_StationAddressRecord(Integer companyId, List<String> addresses) {
		this.companyId = companyId;
		this.addresses = addresses;
	}

	public XQ_StationAddressRecord(String cityName, String stationName, String stationDetailAddress) {
		this.cityName = cityName;
		this.stationName = stationName;
		this.stationDetailAddress = stationDetailAddress;
	}

	public XQ_StationAddressRecord(String province, String city, String district, String name, String detailAddress,
			Double longitude, Double latitude) {
		this(city, name, detailAddress);
		this.provinceName = province;
		this.districtName = district;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public XQ_StationAddressRecord(Integer companyId, String province, String city, String district, String name,
			String detailAddress, Double longitude, Double latitude, String amapParseAddress, String amapAdcode) {
		this(province, city, district, name, detailAddress, longitude, latitude);
		this.companyId = companyId;
		this.amapParseAddress = amapParseAddress;
		this.amapAdcode = amapAdcode;
	}

	public XQ_StationAddressRecord(Integer id, Double longitude, Double latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public XQ_StationAddressRecord(Integer id, Integer handoverTimeType, Integer handoverTime) {
		this.id = id;
		this.handoverTimeType = handoverTimeType;
		this.handoverTime = handoverTime;
	}

	// 用已存在的站点更新当前站点
	public void updateByExist(XQ_StationAddressRecord exist) {
		if (this.id == null) {
			this.setId(exist.getId());
		}
		
		// 站点名称为空，则替换
		if (!StringUtil.checkNull(this.stationName)) {
			this.setStationName(exist.getStationName());
		}

		// 站点编号为空，则替换
		if (!StringUtil.checkNull(this.stationNo)) {
			this.setStationNo(exist.getStationNo());
		}
	}

	/**
	 * 构建以站点关键字构建的Map键
	 * 组合：站点编号+站点名称+站点地址
	 * @return
	 */
	public String buildMapKey() {
		return stationNo + stationName + stationDetailAddress;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Integer getStayTime() {
		return stayTime;
	}

	public void setStayTime(Integer stayTime) {
		this.stayTime = stayTime;
	}

	public Integer getHandoverTimeType() {
		return handoverTimeType;
	}

	public void setHandoverTimeType(Integer handoverTimeType) {
		this.handoverTimeType = handoverTimeType;
	}

	public Integer getHandoverTimeMethod() {
		return handoverTimeMethod;
	}

	public void setHandoverTimeMethod(Integer handoverTimeMethod) {
		this.handoverTimeMethod = handoverTimeMethod;
	}

	public Integer getHandoverTime() {
		return handoverTime;
	}

	public void setHandoverTime(Integer handoverTime) {
		this.handoverTime = handoverTime;
		if (null != handoverTime && handoverTime > 0) {
			this.setCanJudgedAsSpecial(true);
		}
	}

	public String getFuzzy() {
		return fuzzy;
	}

	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}

	public Integer getVehicleTypeNumber() {
		return vehicleTypeNumber;
	}

	public void setVehicleTypeNumber(Integer vehicleTypeNumber) {
		this.vehicleTypeNumber = vehicleTypeNumber;
	}

	public Integer getStationAreaId() {
		return stationAreaId;
	}

	public void setStationAreaId(Integer stationAreaId) {
		this.stationAreaId = stationAreaId;
	}

	public String getAmapParseAddress() {
		return amapParseAddress;
	}

	public void setAmapParseAddress(String amapParseAddress) {
		this.amapParseAddress = amapParseAddress;
	}

	public String getStationAreaName() {
		return stationAreaName;
	}

	public void setStationAreaName(String stationAreaName) {
		this.stationAreaName = stationAreaName;
	}

	public String getAmapAdcode() {
		return amapAdcode;
	}

	public void setAmapAdcode(String amapAdcode) {
		this.amapAdcode = amapAdcode;
	}

	public Integer getIsNewStation() {
		return isNewStation;
	}

	public void setIsNewStation(Integer isNewStation) {
		this.isNewStation = isNewStation;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
		if (null != priority && priority > 0) {
			this.setCanJudgedAsSpecial(true);
		}
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {

		if (null == sortName) {
			return;
		}

		String name = "";
		switch (sortName) {
		case "station_no":				// 站点编号
			name = "xtsar.station_no";
			break;
		case "station_name":			// 站点名称
			name = "xtsar.station_name";
			break;
		case "priority_index":			// 优先级
			name = "priority_index";
			break;
		case "station_detail_address":	// 站点详细地址
			name = "xtsar.station_detail_address";
			break;
		case "vehicle_type_number":		// 禁用车型
			name = "vehicle_type_number";
			break;
		case "max_vehicle_type":		// 最大可进车型
			name = "xtsar.max_vehicle_type";
			break;
		case "station_area_id":			// 所属片区
			name = "xtsar.station_area_id";
			break;
		case "handover_time":			// 交接时长
			name = "handover_time_type";
			setSortName2("xtsar.handover_time");
			break;
		case "queue_time":				// 排队时长
			name = "xtsar.queue_time";
			break;
		default:
			break;
		}

		this.sortName = name;
	}

	public String getSortName2() {
		return sortName2;
	}

	public void setSortName2(String sortName2) {
		this.sortName2 = sortName2;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getMaxVehicleType() {
		return maxVehicleType;
	}

	public void setMaxVehicleType(String maxVehicleType) {
		this.maxVehicleType = maxVehicleType;
		if (StringUtil.checkNull(maxVehicleType)) {
			this.setCanJudgedAsSpecial(true);
		}
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getQueueTime() {
		return queueTime;
	}

	public void setQueueTime(Integer queueTime) {
		this.queueTime = queueTime;
		if (null != queueTime && queueTime > 0) {
			this.setCanJudgedAsSpecial(true);
		}
	}

	public Double getOriginLongitude() {
		return originLongitude;
	}

	public void setOriginLongitude(Double originLongitude) {
		this.originLongitude = originLongitude;
	}

	public Double getOriginLatitude() {
		return originLatitude;
	}

	public void setOriginLatitude(Double originLatitude) {
		this.originLatitude = originLatitude;
	}

	public Integer getLocationType() {
		return locationType;
	}

	public void setLocationType(Integer locationType) {
		this.locationType = locationType;
	}

	public int getWriteCol() {
		return writeCol;
	}

	public void setWriteCol(int writeCol) {
		this.writeCol = writeCol;
	}

	public String getVehicleTypeName() {
		return vehicleTypeName;
	}

	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}

	public boolean isCanJudgedAsSpecial() {
		return canJudgedAsSpecial;
	}

	public void setCanJudgedAsSpecial(boolean canJudgedAsSpecial) {
		this.canJudgedAsSpecial = canJudgedAsSpecial;
	}

	public Integer getIsKeyAccount() {
		return isKeyAccount;
	}

	public void setIsKeyAccount(Integer isKeyAccount) {
		this.isKeyAccount = isKeyAccount;
		if (null != isKeyAccount && isKeyAccount == 1) {
			this.setCanJudgedAsSpecial(true);
		}
	}

	public Double getCorrectLongitude() {
		return correctLongitude;
	}

	public void setCorrectLongitude(Double correctLongitude) {
		this.correctLongitude = correctLongitude;
	}

	public Double getCorrectLatitude() {
		return correctLatitude;
	}

	public void setCorrectLatitude(Double correctLatitude) {
		this.correctLatitude = correctLatitude;
	}

}
