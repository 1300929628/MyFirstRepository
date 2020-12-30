package rubic.test.xq.gps.dbscan;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class GpsTrackUtil {

	public static List<XQ_GPSTrack> getGpsTrack(String requestUri, RestTemplate restTemplate, String headerName,
			String secretKey, XQ_GPSEquipmentVo gpsEquipmentVo, String searchStartTime, String searchEndTime) {

		// 2.1 设置json请求头
		HttpHeaders headers = new HttpHeaders();

		// 2.2 添加请求头和秘钥
		headers.set(headerName, secretKey);

		// 添加json请求头(并且是UTF-8编码)
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		Map<String, Object> params = new HashMap<>();
		params.put("gpsEquipment", gpsEquipmentVo);
		params.put("searchStartTime", searchStartTime);
		params.put("searchEndTime", searchEndTime);

		// 封装请求体
		HttpEntity<Object> request = new HttpEntity<>(params, headers);

		// 设置restemplate编码为utf-8
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		// 访问gps数据服务器
		String result = restTemplate.postForObject(requestUri, request, String.class);

		// 查询一段时间内的gps历史轨迹
		List<XQ_GPSTrack> gpsTracks = JsonUtils.toList(result, XQ_GPSTrack.class);

		return gpsTracks;
	}
	
}
