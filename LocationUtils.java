package rubic.test.xq.gps.dbscan;

public class LocationUtils {

    private static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static int getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return (int) s;
    }

    public static int getDistance(XQ_GPSTrack gps1, XQ_GPSTrack gps2) {
        return getDistance(gps1.getLatitude(), gps1.getLongitude(), gps2.getLatitude(), gps2.getLongitude());
    }

    public static int getDistance(GpsPoint point1, GpsPoint point2) {
        return getDistance(point1.getLatitude(), point1.getLongitude(), point2.getLatitude(), point2.getLongitude());
    }

	public static int getDistance(XQ_GpsCalcStationInfo stopPoint, XQ_StationAddressRecord station) {
		return getDistance(stopPoint.getStopPointLatitude(), stopPoint.getStopPointLongitude(),
				station.getLatitude().doubleValue(), station.getLongitude().doubleValue());
	}
	
}
