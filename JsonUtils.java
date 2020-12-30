package rubic.test.xq.gps.dbscan;

import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: 杨顾
 * json和java对象之间的转换
 **/

public class JsonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * 将json字符串，转成对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T toBean(String json, Class<T> tClass) {
		return JSONObject.parseObject(json, tClass);
    }

    /**
     * 将数组类型的json字符串，转成List集合
     * @param json
     * @param eClass
     * @param <E>
     * @return
     */
    public static <E> List<E> toList(String json, Class<E> eClass) {
        try {
//			return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, eClass));
			return JSONObject.parseArray(json, eClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("json序列化出错");
		}
    }


    public static  boolean validJsonArr(String text) {
        if (!StringUtils.isEmpty(text)) {
            try {
                JSONArray arr = JSON.parseArray(text);
                if (!CollectionUtils.isEmpty(arr)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
