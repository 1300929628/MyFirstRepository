package rubic.test.xq.gps.dbscan;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class Utility {

    /**
     * 检测p点是不是核心点，tmpLst存储核心点的直达点
     *
     * @param originalPointList   原始数据集
     * @param keyPoint            核心点
     * @param convergenceRadius   收敛半径
     * @param minNumber           最小数据数
     * @param maximumTimeInterval 聚类时的最大时间间隔
     * @return
     */
	public static List<GpsPoint> isKeyPoint(List<GpsPoint> originalPointList, GpsPoint keyPoint, int convergenceRadius,
			int minNumber, int maximumTimeInterval) {

		List<GpsPoint> tempList = new ArrayList<>();
		maximumTimeInterval = 1000 * maximumTimeInterval;

		for (GpsPoint GpsPoint : originalPointList) {
			// 如果点的速度小于设定的值且两点之间的距离小于等于设定的收敛距离
			if (GpsPoint.getSpeed() <= GpsTrackConstant.MAX_CLUSTER_SPEED
					&& LocationUtils.getDistance(keyPoint, GpsPoint) <= convergenceRadius) {
				long pointTime = GpsPoint.getGpsUploadTime().getTime();
				if (tempList.isEmpty()) {
					// 如果list为空,计算两点之间的时间差
					if (Math.abs(keyPoint.getGpsUploadTime().getTime() - pointTime) <= maximumTimeInterval) {
						tempList.add(GpsPoint);
					}
					// 如果tempList不包含point
				} else if (!tempList.contains(GpsPoint)) {
					// 得到tempList中时间的最大值
					long maxTime = tempList.stream().max((point1, point2) -> (int) (point1.getGpsUploadTime().getTime()
							- point2.getGpsUploadTime().getTime())).get().getGpsUploadTime().getTime();
					// 如果二者的时间差小于设置的值
					if (Math.abs((pointTime - maxTime)) <= maximumTimeInterval) {
						tempList.add(GpsPoint);
					}
				}
			}
		}
		// 如果聚类中的点数，大于最小聚类数，认为是合理的聚类
		if (tempList.size() >= minNumber) {
			keyPoint.setIsKey(true);
			return tempList;
//		}
		} else {
			// 可能存在进站后熄火后点数过少，因此使用另一种规则来判断聚类是否合理（GPS最大上传时间减去最小上传时间大于某个常量）
			if(tempList.size()>=2) {
				tempList.sort(Comparator.comparing(GpsPoint::getGpsUploadTime));

				int interval = new BigDecimal(tempList.get(tempList.size()-1).getGpsUploadTime().getTime() - tempList.get(0).getGpsUploadTime().getTime())
	        			.divide(new BigDecimal(1000 * 60), 1, BigDecimal.ROUND_UP).intValue();

				if(interval >= GpsTrackConstant.MIN_TIME_POINT_TOO_LITTLE && interval <= GpsTrackConstant.MAX_TIME_POINT_TOO_LITTLE) {
					keyPoint.setIsKey(true);
					return tempList;
				}
			}

		}
		return null;
	}

    /**
     * 合并两个聚类，前提是b中的核心点包含在a中
     *
     * @param pointList1
     * @param pointList2
     */
    public static void mergeList(List<GpsPoint> pointList1, List<GpsPoint> pointList2) {
        if (CollectionUtils.isEmpty(pointList1) || CollectionUtils.isEmpty(pointList2)) {
            return;
        }
        boolean merge = false;
        for (GpsPoint point2 : pointList2) {
            if (point2.getIsKey() && pointList1.contains(point2)) {
                merge = true;
                break;
            }
        }
        if (merge) {
            for (GpsPoint point2 : pointList2) {
                if (!pointList1.contains(point2)) {
                    pointList1.add(point2);
                }
            }
            pointList2.clear();
        }
    }

    /**
     * 得到中心点
     *
     * @param pointList
     * @return
     */
	public static XQ_GpsCalcStationInfo centerPoint(List<GpsPoint> pointList) {
		if (CollectionUtils.isEmpty(pointList)) {
			return null;
		}

		if (pointList.size() == 1) {
			return null;
		}

		double sumLatitude = 0.0;
		double sumLongitude = 0.0;
		for (GpsPoint gpsPoint : pointList) {
			sumLatitude += gpsPoint.getLatitude();
			sumLongitude += gpsPoint.getLongitude();
		}
		
		double longitude = sumLongitude / pointList.size();
		double latitude = sumLatitude / pointList.size();
		
		double[] gcj02Gps = CoordinateTool.transformWGS84ToGCJ02(longitude, latitude);
		
		DecimalFormat df = new DecimalFormat("#.000000");
		
		return new XQ_GpsCalcStationInfo(Double.valueOf(df.format(gcj02Gps[0])),
				Double.valueOf(df.format(gcj02Gps[1])));
	}
	
}