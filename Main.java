package rubic.test.xq.gps.dbscan;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.Now;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private final static String HISTORY_TRACK_URL = "http://gps.java.56etms.com/gpsData/getTrackByNoAndTime2";

    private final static String HEADER_NAME = "gps-data-server-header";

    private final static String SECRET_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4vi8i564xPvj4ld4jkbXEMQg2cF02skFRHXY2iP76z56sOIP4CYqdAu1JJEUX9ghlOdt4wS6x5f3P4skvWWUdU24D+ySXXoPyAQk1v2uOfDbeUSvGnkzCir7dvXL3rlLjPbl8vQ1tuZR/Tzj7p6RymTMujRc2tOkCSwbDMNRyLzjaBlczHNUTWQT/H1gwuLogjVX6ouzQkUelM96xgrXYCFqh/78GQntKQ0b1mSsyejVonI91pSIC67DXhaPVwXPgQTZlM9fNPIZO9fL2H341XrrVjWV11ld7787Bf2b+Jk+65701+Ha0j6gssYCBQrfTvHk1DzbucCKBiJzezAO0QIDAQAB";

    private static Log log = LogFactory.getLog(Main.class);
    private static String s1 = "";//记录卸货时间+卸货起始时间
    private static int n;

    private static RestTemplate geTemplate() {
        return new RestTemplate();

    }

//    private static Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        //经度
        //
//        Double longitude = 121.455621;
//        // 纬度
//        Double latitude = 31.197069;
//
//        // 时间戳格式
//        Long gpsUploadDay = 1596530208000l;
//        //GPS号
//        String gpsNo = "19171403564";
//        //车牌号
//        String platNo = "沪AYL388";
//        String searchDate="2020-12-09";
//        String searchStartTime = searchDate + " 00:00:00";
//        String searchEndTime = searchDate + " 23:59:59";
        try {
            InputStream fileInput = new FileInputStream("C:/Users/dell/Desktop/新建Microsoft Excel 工作表.xlsx");//一天内所有站点情况.xlsx");//创建文件输入流 ");//导出站点交接时长-2020-12-24-94159//新建Microsoft Excel 工作表
            XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
            XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int lastRowNum = sheet.getLastRowNum(); //获取表格内容的最后一行的行数
String e="变化";
            //rowBegin代表要开始读取的行号，下面这个循环的作用是读取每一行内容
            for (int p = 1; p <= lastRowNum; p++) {
                n = p;
                XSSFRow row = sheet.getRow(p);//获取每一行

                Double longitude = Double.valueOf(row.getCell(12).toString());
                // 纬度
                Double latitude = Double.valueOf(row.getCell(13).toString());

                // 时间戳格式
                Long gpsUploadDay = 1596530208000l;
                //GPS号
                String gpsNo = row.getCell(1).toString();
                //车牌号
                String platNo = row.getCell(0).toString();
                String searchDate = row.getCell(4).toString();

                String searchStartTime = searchDate + " 00:00:00";
                String searchEndTime = searchDate + " 23:59:59";


                XQ_GPSEquipmentVo gpsEquipmentVo = new XQ_GPSEquipmentVo();
                gpsEquipmentVo.setGpsNo(gpsNo);//19171403585"); // 填写gpsno
                gpsEquipmentVo.setSource(0);

                // 查询的时间范围，格式：yyyy-MM-dd HH:mm:ss

                // gps历史轨迹
                List<XQ_GPSTrack> gpsTracks = GpsTrackUtil.getGpsTrack(HISTORY_TRACK_URL, geTemplate(), HEADER_NAME, SECRET_KEY,
                        gpsEquipmentVo, searchStartTime, searchEndTime);
                //将GPS原始数据导出
                String s = "X/Y/u" + "\n";
                for (int i = 0; i < gpsTracks.size(); i++) {

                    double[] doubles = CoordinateTool.transformWGS84ToGCJ02(gpsTracks.get(i).getLongitude(), gpsTracks.get(i).getLatitude());
                    double a = doubles[0];
                    double b = doubles[1];
                    Date c = gpsTracks.get(i).getGpsUploadTime();
                    double d = gpsTracks.get(i).getSpeed();

                    s = s + String.valueOf(a) + "/" + String.valueOf(b) + "/" + String.valueOf(c) + "/" + String.valueOf(d) + "\n";
                }
                try {

                    //写入指定文件中
                    BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/dell/Desktop/新建文本文档 (1).txt"));
                    bw.write(s);
                    bw.close();
                } catch (IOException ex) {
                    //Logger.getLogger(EncryptTest.class.getName()).log(Level.SEVERE, null, ex);
                }


                XQ_StationAddressRecord station = new XQ_StationAddressRecord(1, longitude, latitude);

                Map<Integer, List<XQ_GpsCalcStationInfo>> station2GpsInfoMap = new HashMap<>();

                //经纬度转换(高德->WGS84)
                double[] doubles = CoordinateTool.transformGCJ02ToWGS84(station.getLongitude(), station.getLatitude());
                double[] minAndMaxLatAndLng = getNearbyByLongitudeAndLatitudeAndDistance(doubles[0],
                        doubles[1], GpsTrackConstant.MAX_DISTANCE_STATION);

                //过滤出正方形区域内的轨迹点
                List<XQ_GPSTrack> squareGpsTrackList = gpsTracks.stream().filter(gpsTrack -> {
                    Double gpsTrackLatitude = gpsTrack.getLatitude();
                    Double gpsTrackLongitude = gpsTrack.getLongitude();
                    return gpsTrackLatitude >= minAndMaxLatAndLng[0] && gpsTrackLatitude <= minAndMaxLatAndLng[1]
                            && gpsTrackLongitude >= minAndMaxLatAndLng[2] && gpsTrackLongitude <= minAndMaxLatAndLng[3];
                }).collect(Collectors.toList());

                if (CollectionUtils.isEmpty(squareGpsTrackList) || squareGpsTrackList.size() == 1) {
                    System.out.println("获取具类失败");
                }


                calculateTransferTime(gpsNo, platNo, gpsUploadDay, gpsTracks, squareGpsTrackList, station, station2GpsInfoMap);
            }
            wb.close();
            fileInput.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }


    public static void calculateTransferTime(String gpsNo, String platNo, Long gpsUploadDay, List<XQ_GPSTrack> gpsTracks,
                                             List<XQ_GPSTrack> gpsList, XQ_StationAddressRecord station,
                                             Map<Integer, List<XQ_GpsCalcStationInfo>> station2GpsInfoMap) {


        //1. 计算上传gps轨迹数据的频率
        int gpsListSize = gpsList.size();
        //最后一个点和第一个点上传时间的差值除以总个数减去1得到频率。单位为秒/次
        int frequency = (int) ((gpsList.get(gpsListSize - 1).getGpsUploadTime().getTime()
                - gpsList.get(0).getGpsUploadTime().getTime()) / (gpsListSize - 1)) / 1000;
        log.info("[计算交接时间] gps轨迹数据的上传频率为: {}秒/次" + frequency);


        //2. 计算上传间隔时间内的行驶的最大距离
        int maxSeparationDistance = (int) (frequency * GpsTrackConstant.MAX_MOVE_SPEED / 3.6);
        log.info("[计算交接时间] 上传间隔时间内的行驶的最大距离为: {}m" + maxSeparationDistance);
        //去除噪点(距离相差过大的点)
        //gpsList = removeErrorPoint(gpsList, maxSeparationDistance);


        //3. 计算收敛半径、最小聚类数、聚类时的最大时间间隔
//        int sumDistance = 0;
//        //由于gpsList经过清洗，list要重新赋值
//        gpsListSize = gpsList.size();
//        //for循环，计算两点之间的距离
//        for (int i = 0; i < gpsListSize - 1; i++) {
//        	XQ_GPSTrack current = gpsList.get(i);
//        	XQ_GPSTrack next = gpsList.get(i + 1);
//            //计算相邻两个点之间的距离
//            sumDistance += LocationUtils.getDistance(current, next);
//        }

        // 【将搜索半径写死为50米】
//        int convergenceRadius = sumDistance / (gpsListSize - 1);
        int convergenceRadius = GpsTrackConstant.MAX_DISTANCE_STATION;

//        log.info("[计算交接时间] 收敛半径为: {}m", convergenceRadius);
//        int minNumber = GpsTrackConstant.MAX_WAIT_TIME_TRAFFIC_LIGHT / frequency + 1;
        int minNumber = 15;
//        log.info("[计算交接时间] 最小聚类数为: {}", minNumber);

//        int maximumTimeInterval = GpsTrackConstant.TIME_FACTOR * frequency;

        // 【将聚类时的最大时间间隔设置为10分钟】
//        int maximumTimeInterval = 10 * 60;
        int maximumTimeInterval = 2 * 60 * 60;

        log.info("[计算交接时间] 聚类时的最大时间间隔: {}秒" + maximumTimeInterval);

        //4. DBSCAN密度聚类算法进行聚类
        List<List<GpsPoint>> resultList = new ArrayList<>();

        //4.1 生成密度聚类
        List<GpsPoint> pointList = gpsList.stream().map(GpsPoint::transform).collect(Collectors.toList());
        for (GpsPoint GpsPoint : pointList) {

            List<GpsPoint> tmpPointList = Utility.isKeyPoint(pointList, GpsPoint, convergenceRadius, minNumber,
                    maximumTimeInterval);
            if (!CollectionUtils.isEmpty(tmpPointList)) {
                resultList.add(tmpPointList);
            }
        }

        //4.2 合并两个密度聚类
        int length = resultList.size();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                Utility.mergeList(resultList.get(i), resultList.get(j));
            }
        }

        resultList = resultList.stream().filter(tempPointList -> !tempPointList.isEmpty()).collect(Collectors.toList());


        //4.3 生成停留点，也就是聚类中心(计算经纬度、停留时间)
        List<XQ_GpsCalcStationInfo> stopPointList = new ArrayList<>();
        for (List<GpsPoint> points : resultList) {

            if (CollectionUtils.isEmpty(points)) {
                continue;
            }
            // 对聚类中的点按上传时间进行排序
            points.sort(Comparator.comparing(GpsPoint::getGpsUploadTime));


            if (resultList.size() > 1) {
                s1 = s1 + "\n" + "DD";
            }
            //将站点聚类点转换为适应高德地图的坐标，并导出信息
            for (int i = 0; i < resultList.size(); i++) {
                String s = "X/Y/speed\t" + "/time\n";
                for (int j = 0; j < resultList.get(i).size(); j++) {
                    double[] doubles = CoordinateTool.transformWGS84ToGCJ02(resultList.get(i).get(j).getLongitude(), resultList.get(i).get(j).getLatitude());
                    double a = doubles[0];
                    double b = doubles[1];

                    Date c = resultList.get(i).get(j).getGpsUploadTime();
                    Double d = resultList.get(i).get(j).getSpeed();
                    s = s + String.valueOf(a) + "/" + String.valueOf(b) + "/" + String.valueOf(d) + "/" + String.valueOf(c) + "\n";
                }
                try {

                    //写入指定文件中
                    BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/dell/Desktop/新建文本文档 " + i + ".txt"));
                    bw.write(s);
                    bw.close();
                } catch (IOException ex) {
                    //Logger.getLogger(EncryptTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //生成聚类中心(聚类点集的中心)
            XQ_GpsCalcStationInfo stopPoint = Utility.centerPoint(points);
            if (stopPoint == null) {
                log.info("[计算交接时间] 未能生成聚类中心, 原有点集的个数: {}" + points.size());
                continue;
            }

            long arrivalTime = points.get(0).getGpsUploadTime().getTime();
            long leaveTime = points.get(points.size() - 1).getGpsUploadTime().getTime();

            //计算停留时间。这里的单位是秒
            long stopTime = new BigDecimal(leaveTime).subtract(new BigDecimal(arrivalTime)).divide(new BigDecimal(1000))
                    .longValue();


            //分割站点停留时间
            if (stopTime < 300) {

                log.info("货车到达站点立即开始装卸货，未发生排队现象！");
                s1 = s1 + "\n" + n + "/" + "货车到达站点立即开始装卸货，未发生排队现象！" + "/" + points.get(0).getGpsUploadTime().toString() + "/" + points.get(points.size() - 1).getGpsUploadTime().toString();
            } else {
                long intervalUnloadTime = 0;//卸货总时间
                Date uET = points.get(0).getGpsUploadTime();
                Date uST = points.get(0).getGpsUploadTime();

                List<Integer> internalID = new ArrayList<>();//用于记录存在间隔5分钟的点
                for (int i = 0; i < points.size() - 1; i++) {
                    long firstTime = points.get(i).getGpsUploadTime().getTime();
                    long secondTime = points.get(i + 1).getGpsUploadTime().getTime();
                    //计算间隔时间。
                    long intervalTime = new BigDecimal(secondTime).subtract(new BigDecimal(firstTime)).divide(new BigDecimal(1000)).longValue();
                    if (intervalTime >= 180) {
                        internalID.add(i);
                    }
                }
                if (internalID.size() < 1) {
                    log.info("点集中不存在大于3分钟的时间间隔");
                    s1 = s1 + "\n" + n + "/" + "点集中不存在大于3分钟的时间间隔" + "/" + points.get(0).getGpsUploadTime().toString() + "/" + points.get(points.size() - 1).getGpsUploadTime().toString();
                }
                //点集中存在一个5分钟的时间间隔
                //将此间隔时间点为标准点，向上查询经纬度相同的点，以结果点集中第一个点为装卸货起始时间，以站点点集最后一个点为装卸货结束时间。
                else if (internalID.size() == 1) {
                    log.info("点集中存在" + internalID.size() + "个大于5分钟的时间间隔");
                    double exampleLatitude = points.get(internalID.get(0).intValue()).getLatitude();
                    double exampleLongtitude = points.get(internalID.get(0).intValue()).getLongitude();
                    for (int i = internalID.get(0).intValue() - 1; i > 0; i--) {
                        if (exampleLatitude == points.get(i).getLatitude() && exampleLongtitude == points.get(i).getLongitude()) {
                            continue;
                        } else {
                            long unloadStartTime = points.get(i + 1).getGpsUploadTime().getTime();
                            uST = points.get(i + 1).getGpsUploadTime();
                            long unloadEndTime = points.get(points.size() - 1).getGpsUploadTime().getTime();
                            uET = points.get(points.size() - 1).getGpsUploadTime();
                            intervalUnloadTime = new BigDecimal(unloadEndTime).subtract(new BigDecimal(unloadStartTime)).divide(new BigDecimal(1000)).longValue();
                            log.info("卸货时间：" + intervalUnloadTime);
                            s1 = s1 + "\n" + n + "/" + intervalUnloadTime + "/" + uST.toString() + "/" + uET.toString();
                            break;
                        }
                    }
                }
                //点集中存在多个5分钟的时间间隔
                //首先查询连续间隔5分钟的点，计算间隔时间；若存在多个连续间隔5分钟的点集，以时间最长者为卸货时间；
                // 若不存在连续间隔5分钟的点集,则以最后一次5分钟间隔的点为标准点，向上向下寻找同经纬度的点，其结果点集
                // 就是装卸货时间
                else {
                    log.info("点集中存在" + internalID.size() + "个大于5分钟的时间间隔");
                    long unloadStartTime = 0;//开始卸货时间
                    long unloadEndTime = 0;//卸货结束时间

                    double stratExampleLatitude;//开始卸货点经纬度
                    double startExampleLongtitude;
                    double endExampleLatitude;//卸货结束点经纬度
                    double endExampleLongtitude;
                    if (internalID.get(internalID.size() - 1).intValue() < points.size() - 1) {
                        endExampleLatitude = points.get(internalID.get(internalID.size() - 1).intValue() + 1).getLatitude();
                        endExampleLongtitude = points.get(internalID.get(internalID.size() - 1).intValue() + 1).getLongitude();
                    } else {
                        endExampleLatitude = points.get(internalID.get(internalID.size() - 1).intValue()).getLatitude();
                        endExampleLongtitude = points.get(internalID.get(internalID.size() - 1).intValue()).getLongitude();
                    }

                    //向下寻找与标记点相同经纬度的点，将最后一个具有相同经纬度的点设为开始卸货点，其时间为开始时间
                    for (int h = internalID.get(internalID.size() - 1).intValue() + 2; h < points.size(); h++) {
                        if (endExampleLatitude == points.get(h).getLatitude() && endExampleLongtitude == points.get(h).getLongitude()) {
                            unloadEndTime = points.get(h).getGpsUploadTime().getTime();
                            uET = points.get(h).getGpsUploadTime();
                        } else {
                            unloadEndTime = points.get(h - 1).getGpsUploadTime().getTime();
                            uET = points.get(h - 1).getGpsUploadTime();
                            break;
                        }
                    }
                    if (unloadEndTime == 0) {
                        unloadEndTime = points.get(internalID.get(internalID.size() - 1).intValue() + 1).getGpsUploadTime().getTime();
                        uET = points.get(internalID.get(internalID.size() - 1).intValue() + 1).getGpsUploadTime();
                    }
                    //向上查询，找出第一个不连续的点，以此为分割线向上为排队时间
                    for (int i = internalID.size(); i - 2 > 0; i--) {
                        if (internalID.get(i - 1).intValue() - internalID.get(i - 2).intValue() != 1) {
                            stratExampleLatitude = points.get(internalID.get(i - 1).intValue()).getLatitude();
                            startExampleLongtitude = points.get(internalID.get(i - 1).intValue()).getLongitude();
                            //向上寻找与标记点相同经纬度的点，将第一个具有相同经纬度的点设为开始卸货点，其时间为开始时间
                            for (int j = internalID.get(i - 1).intValue() - 1; j > 0; j--) {
                                if (stratExampleLatitude != points.get(j).getLatitude() || startExampleLongtitude != points.get(j).getLongitude()) {
                                    unloadStartTime = points.get(j + 1).getGpsUploadTime().getTime();
                                    uST = points.get(j + 1).getGpsUploadTime();
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if (unloadStartTime == 0) {
                        stratExampleLatitude = points.get(internalID.get(0).intValue()).getLatitude();
                        startExampleLongtitude = points.get(internalID.get(0).intValue()).getLongitude();
                        //向上寻找与标记点相同经纬度的点，将第一个具有相同经纬度的点设为开始卸货点，其时间为开始时间
                        for (int j = internalID.get(0).intValue() - 1; j > 0; j--) {
                            if (stratExampleLatitude != points.get(j).getLatitude() || startExampleLongtitude != points.get(j).getLongitude()) {
                                unloadStartTime = points.get(j + 1).getGpsUploadTime().getTime();
                                uST = points.get(j + 1).getGpsUploadTime();
                                break;
                            }
                        }
                        if (unloadStartTime == 0) {
                            unloadStartTime = points.get(internalID.get(0).intValue()).getGpsUploadTime().getTime();
                            uST = points.get(internalID.get(0).intValue()).getGpsUploadTime();
                        }
                    }
                    log.info(uST.toString() + "////" + uET.toString());
                    intervalUnloadTime = new BigDecimal(unloadEndTime).subtract(new BigDecimal(unloadStartTime)).divide(new BigDecimal(1000)).longValue();

                    log.info("卸货时间：" + intervalUnloadTime);
                    s1 = s1 + "\n" + n + "/" + intervalUnloadTime + "/" + uST.toString() + "/" + uET.toString();
                }
                try {

                    //写入指定文件中
                    BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/dell/Desktop/新建文本文档 " + ".txt"));
                    bw.write(s1);
                    bw.close();
                } catch (IOException ex) {
                    //Logger.getLogger(EncryptTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


            //如果停留时间小于1分钟，认为没有计算的意义。过滤掉
            if (stopTime < GpsTrackConstant.MIN_HANDOVER_TIME) {
//				log.info("[计算交接时间] 停留时间过小，为{}分钟, 聚类中点的总数: {}", stopTime, points.size());
                continue;
            }

            stopPoint.setArrivalTime(arrivalTime);
            stopPoint.setLeaveTime(leaveTime);

            // 判断这段时间内GPS轨迹中最远点与停靠点的距离是否超过一定距离，如果超过，则不是停靠点
            boolean checkMaxDistance = checkMaxDistanceToStopPointInTime(stopPoint, gpsTracks);
            if (!checkMaxDistance) {
                log.info("[计算停靠时间内最远距离] 停靠点与停靠时间内轨迹点最远距离过大");
                continue;
            }

//			log.info("[计算交接时间] 预测的停留点: 经度: {}, 纬度: {}, 停留时间: {}分钟, 聚类中点的总数: {}", stopPoint.getStopPointLongitude(),
//					stopPoint.getStopPointLatitude(), stopTime, points.size());
            stopPointList.add(stopPoint);
        }

        //5. 生成的聚类中心通过距离和站点进行匹配
        if (CollectionUtils.isEmpty(stopPointList)) {
            log.info("[计算交接时间] 未生成任何的停留点");
            return;
        }

        for (XQ_GpsCalcStationInfo stopPoint : stopPointList) {

            //5.1 在站点的List中找到与计算得到的停留点距离最近的点
            XQ_StationAddressRecord minDistanceStation = null;
            int minDistance = Integer.MAX_VALUE;

            // 此处要判断站点入库的时间，如果站点入库的时间比gps数据上传的时间还晚，则不进行计算
//    		long createTime = station.getCreateTime().getTime();
//    		if (createTime >= gpsUploadDay) {
//    			continue;
//    		}

            int distance = LocationUtils.getDistance(stopPoint, station);
            if (distance < minDistance) {
                minDistance = distance;
                minDistanceStation = station;
            }

            Long arrivalTime = stopPoint.getArrivalTime();
            Long leaveTime = stopPoint.getLeaveTime();
            long stopTime = new BigDecimal(leaveTime).subtract(new BigDecimal(arrivalTime)).divide(new BigDecimal(1000))
                    .longValue();

            if (minDistance > GpsTrackConstant.MAX_DISTANCE_BETWEEN_STOP_POINT_AND_STATION) {
//				log.info("[计算交接时间] 未成功匹配站点。预测的停留点的经度: {}, 纬度: {}, 进站时间: {}, 出站时间: {},  停留时间: {}秒, 最小距离: {}m",
//						stopPoint.getStopPointLongitude(), stopPoint.getStopPointLatitude(),
//						DateUtil.parseToString(new Date(arrivalTime), null),
//						DateUtil.parseToString(new Date(leaveTime), null), stopTime, minDistance);
                continue;
            }

            //5.2 如果最小距离小于设定的值。则认为是同一个地点
            Integer stationId = minDistanceStation.getId();

//			log.info(
//					"[计算交接时间] 成功匹配站点。站点的id: {}, 站点名称: {}, 停留点的经度: {}, 纬度: {}, 进站时间: {}, 出站时间: {}, 原有的交接时间: {}分钟, "
//							+ "计算生成的交接时间: {}分钟, 预测的停留点和站点之间的距离: {}m",
//					stationId, minDistanceStation.getStationName(), stopPoint.getStopPointLongitude(),
//					stopPoint.getStopPointLatitude(), DateUtil.parseToString(new Date(arrivalTime), null),
//					DateUtil.parseToString(new Date(leaveTime), null), minDistanceStation.getHandoverTime(), stopTime,
//					minDistance);

            stopPoint.setStationId(stationId);
            stopPoint.setStationName(minDistanceStation.getStationName());
            stopPoint.setCompanyId(minDistanceStation.getCompanyId());
            stopPoint.setGpsNo(gpsNo);
            stopPoint.setPlatNo(platNo);
            stopPoint.setGpsUploadDay(gpsUploadDay);
            stopPoint.setMatchDistance(minDistance);

//        	gpsCalcStationInfos.add(stopPoint);

            // 用来去除无效信息
            List<XQ_GpsCalcStationInfo> stationInfos = station2GpsInfoMap.get(stationId);
            if (null == stationInfos) {
                stationInfos = new ArrayList<>();
                station2GpsInfoMap.put(stationId, stationInfos);
            }
            station2GpsInfoMap.get(stationId).add(stopPoint);

        }

    }


    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     *
     * @param @param  lng1
     * @param @param  lat1
     * @param @param  lng2
     * @param @param  lat2
     * @param @return
     * @return int    返回类型
     * @throws
     * @Title: getDistance
     * @Description:
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin(a / 2), 2)
                                + Math.cos(radLat1)
                                * Math.cos(radLat2)
                                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;

        return (Math.round(s * 10000) / 10000);
    }

    private static double[] getNearbyByLongitudeAndLatitudeAndDistance(double lng, double lat, Integer distance) {
        // 地球半径千米
        double r = EARTH_RADIUS;
        double dlng = 2 * Math.asin(Math.sin(distance / (2 * r)) / Math.cos(lat * Math.PI / 180));
        // 角度转为弧度
        dlng = dlng * 180 / Math.PI;
        double dlat = distance / r;
        dlat = dlat * 180 / Math.PI;
        double minlat = lat - dlat;
        double maxlat = lat + dlat;
        double minlng = lng - dlng;
        double maxlng = lng + dlng;

        return new double[]{minlat, maxlat, minlng, maxlng};
    }

    /**
     * 判断这段时间内GPS轨迹中最远点与停靠点的距离是否超过一定距离，如果超过，则不是停靠点
     *
     * @param stopPoint 停靠点
     * @param gpsTracks GPS历史轨迹
     * @return
     */
    private static boolean checkMaxDistanceToStopPointInTime(XQ_GpsCalcStationInfo
                                                                     stopPoint, List<XQ_GPSTrack> gpsTracks) {

        gpsTracks.sort(Comparator.comparing(XQ_GPSTrack::getGpsUploadTime));

        Long arrivalTime = stopPoint.getArrivalTime();
        Long leaveTime = stopPoint.getLeaveTime();

        Double longitude = stopPoint.getStopPointLongitude();
        Double latitude = stopPoint.getStopPointLatitude();
        //停留点的经纬度就是WGS84坐标系, 不用进行转换
        //double[] location = CoordinateTool.transformGCJ02ToWGS84(longitude, latitude);

        for (XQ_GPSTrack gpsTrack : gpsTracks) {
            Date gpsUploadTime = gpsTrack.getGpsUploadTime();
            Double gpsLng = gpsTrack.getLongitude();
            Double gpsLat = gpsTrack.getLatitude();
            if (null == gpsUploadTime || null == gpsLng || null == gpsLat) {
                continue;
            }

            long gpsTime = gpsUploadTime.getTime();
            if (gpsTime < arrivalTime || gpsTime > leaveTime) {
                continue;
            }

            double distance = getDistance(longitude, latitude, gpsLng, gpsLat);
            if (distance >= GpsTrackConstant.MAX_DISTANCE_IN_STOP_TIME) {
                return false;
            }
        }

        return true;
    }
}