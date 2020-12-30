package rubic.test.xq.gps.dbscan;

public class GpsTrackConstant {

    /**
     * 车辆的最大行驶速度。单位为km/h
     */
    public static final int MAX_MOVE_SPEED = 120;

    /**
     * 密度。单位为秒
     */
    public static final int MAX_WAIT_TIME_TRAFFIC_LIGHT = 180;

    /**
     * 聚类时的最大速度。单位为km/h
     */
    public static final short MAX_CLUSTER_SPEED = 10;

    /**
     * 时间系数
     * 聚类时的最大时间间隔数，意思是超过一定时间间隔的点不能聚类
     */
    public static final int TIME_FACTOR = /*5*/ 10;

    /**
     * 最小交接时间为5分钟，即300秒
     */
    public static final int MIN_HANDOVER_TIME = /*300*/ 60;

    /**
     * 推测的停留点和和站点之间的距离。单位为米
     */
    public static final int MAX_DISTANCE_BETWEEN_STOP_POINT_AND_STATION = /*100*/ 200;

    /**
     * 聚类点距离站点的距离
     */
    public static final int MAX_DISTANCE_STATION = 70;

    /**
     * 点太少的最小时间间隔
     */
    public static final int MIN_TIME_POINT_TOO_LITTLE = 3;

    /**
     * 点太少的最大时间间隔
     */
    public static final int MAX_TIME_POINT_TOO_LITTLE = 30;

    /**
     * 由于没聚类到被拆分为多个聚类的站点的可以合并的最大时间间隔
     */
    public static final int MULTI_CLUSTER_TIME_INTERVAL = 30;

    /**
     * 在停靠时间内的轨迹点中，与停靠点最远的距离
     */
    public static final int MAX_DISTANCE_IN_STOP_TIME = 500;

    /**
     * 距离间隔时间
     */
    public static final int INSERT_TIME_INTERVAL = 60;
    
    /**
     * GPS历史轨迹点间隔距离
     */
    public static final int GPS_HISTORY_DISTANCE = 200;
}
