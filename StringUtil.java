package rubic.test.xq.gps.dbscan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public final class StringUtil {

	// 特殊字符数组，可扩容
	public static String[] SpecialCharacterArray = {"+", "%", "#", "&"};
	
	public static String convertNullToString(String input) {
		if (null == input || "null".equals(input)) {
			return "";
		}
		return input;
	}

	public static String join(List<? extends Object> list, String symbol) {

		if (null == list || list.size() == 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {

			sb.append(list.get(i).toString().trim());
			if (i != list.size() - 1) {
				sb.append(symbol);
			}
		}

		return sb.toString();
	}

	public static String buildString(int length, String symbol) {

		if (0 == length) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(symbol);
		}

		return sb.toString();
	}

	public static String writeExcelCheckNull(String value) {
		if (value == null || "null".equals(value)) {
			return "";
		}
		return value;
	}

	public static String writeExcelConvertNull(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	public static BigDecimal calcCheckPriceNull(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			bigDecimal = new BigDecimal("0.00");
		}
		return bigDecimal;
	}

	// 1：现付，2：到付，3：回单付，4：月结，5：混合支付，6：免费
	public static Integer handleOrderPayMethod(String content) {
		if (null != content && !"".equals(content)) {
			Integer pay_method = null;
			switch (content) {
			case "现付":
				pay_method = 1;
				break;
			case "到付":
				pay_method = 2;
				break;
			case "回单付":
				pay_method = 3;
				break;
			case "月结":
				pay_method = 4;
				break;
			case "混合支付":
				pay_method = 5;
				break;
			case "免费":
				pay_method = 6;
				break;
			default:
				break;
			}
			return pay_method;
		}
		return null;
	}

	// 配送方式（1.配送；2.直送；3.自提）
	public static Integer handleOrderDeliveryMethod(String content) {
		if (null != content && !"".equals(content)) {
			Integer delivery_method = null;
			switch (content) {
			case "拼单":
				delivery_method = 1;
				break;
			case "不拼单":
				delivery_method = 2;
				break;
			default:
				break;
			}
			return delivery_method;
		}
		return null;
	}

	// 回单要求（1.无需回单；2.原单返回；3.打收条；4.签信封；5.签回单盖章；6.传真返回；7.签回单）
	public static Integer handleOrderReceiptMethod(String content) {
		if (null != content && !"".equals(content)) {
			Integer receipt_method = null;
			switch (content) {
			case "无需回单":
				receipt_method = 1;
				break;
			case "原单返回":
				receipt_method = 2;
				break;
			case "打收条":
				receipt_method = 3;
				break;
			case "签信封":
				receipt_method = 4;
				break;
			case "签回单盖章":
				receipt_method = 5;
				break;
			case "传真返回":
				receipt_method = 6;
				break;
			case "签回单":
				receipt_method = 7;
				break;
			default:
				break;
			}
			return receipt_method;
		}
		return null;
	}

	// 回单要求（1.无需回单；2.原单返回；3.打收条；4.签信封；5.签回单盖章；6.传真返回；7.签回单）
	public static String handleOrderReceiptMethod(Integer receipt_method) {
		if (null != receipt_method) {
			String content = null;
			switch (receipt_method) {
			case 1:
				content = "无需回单";
				break;
			case 2:
				content = "原单返回";
				break;
			case 3:
				content = "打收条";
				break;
			case 4:
				content = "签信封";
				break;
			case 5:
				content = "签回单盖章";
				break;
			case 6:
				content = "传真返回";
				break;
			case 7:
				content = "签回单";
				break;
			default:
				break;
			}
			return content;
		}
		return null;
	}

	public static String handleOrderPayMethod(Integer content) {
		if (null != content) {
			String pay_method = null;
			switch (content) {
			case 1:
				pay_method = "现付";
				break;
			case 2:
				pay_method = "到付";
				break;
			case 3:
				pay_method = "回单付";
				break;
			case 4:
				pay_method = "月结";
				break;
			case 5:
				pay_method = "混合支付";
				break;
			case 6:
				pay_method = "免费";
				break;
			default:
				break;
			}
			return pay_method;
		}
		return null;
	}

	/**
	 * 通过status对应线路状态
	 * 
	 * @param content
	 * @return
	 */
	public static String handleRouteStatus(Integer routeStatus) {
		if (null != routeStatus) {
			String orderStatusString = null;
			switch (routeStatus) {
			case 1:
				orderStatusString = "未分配";
				break;
			case 2:
				orderStatusString = "未开始";
				break;
			case 3:
				orderStatusString = "进行中";
				break;
			case 4:
				orderStatusString = "已完成";
				break;
			default:
				orderStatusString = "未知状态";
				break;
			}
			return orderStatusString;
		}
		return null;
	}

	/**
	 * 运单的计价单位转换
	 * 
	 * @param content
	 * @return
	 */
	public static Integer handleOrderOrderUnit(String content) {
		if (null != content && !"".equals(content)) {
			Integer unit = null;
			switch (content) {
			case "件":
				unit = 1;
				break;
			case "千克":
				unit = 2;
				break;
			case "立方米":
				unit = 3;
				break;
			default:
				break;
			}
			return unit;
		}
		return null;
	}

	/**
	 * @param phoneNum
	 */
	public static boolean validPhoneNum(String phoneNum) {
		if (null == phoneNum || "".equals(phoneNum)) {
			return false;
		}
		boolean flag = false;
		Pattern p1 = Pattern.compile("(\\+\\d+)?1[3456789]\\d{9}$");
		Pattern p2 = Pattern.compile("((\\d+)\\-\\d+)|((\\d+)\\-\\d+\\-\\d+)$");

		if (p1.matcher(phoneNum).matches() || p2.matcher(phoneNum).matches()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public static boolean validEmail(String email) {
		if (null == email || "".equals(email)) {
			return false;
		}
		boolean flag = false;
		Pattern pattern = Pattern
				.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");

		if (pattern.matcher(email).matches()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * @param input
	 * @return true is not null false is null
	 */
	public static boolean checkNull(String input) {
		if (input == null || "".equals(input.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串有效性校验 @Title: isValid @Description: @param str @return 设定文件 @return
	 * boolean 返回类型 @throws
	 */
	public static boolean isValid(String str) {

		if ("\\\\".equals(str) || "、、".equals(str) || "、".equals(str) || isNumeric(str)
				|| isNumeric(str.replaceAll("、", "")) || "#N/A".equals(str) || "NULL".equals(str)
				|| "null".equals(str)) {
			return false;
		}

		return true;
	}

	// 校验字符串是否是数字
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	// 解析ID字符串
	public static List<Integer> parseIdsToInteger(String ids) {

		if (null == ids || "".equals(ids.trim()) || ",".equals(ids.trim())) {
			return null;
		}

		List<Integer> IDList = new ArrayList<>();
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {

			Integer ID = null;
			try {
				if(null != idArray[i] && !"".equals(idArray[i])){
					ID = Integer.parseInt(idArray[i]);
				}

			} catch (NumberFormatException e) {
				System.err.println(idArray[i] + "转换int类型失败！");
			}
			if (null == ID) {
				continue;
			}

			if (!IDList.contains(ID)) {
				IDList.add(ID);
			}
		}
		return IDList;
	}

	// 解析ID字符串
	public static List<String> parseIdsToString(String ids) {

		if (null == ids || "".equals(ids.trim()) || ",".equals(ids.trim())) {
			return null;
		}

		List<String> IDList = new ArrayList<>();
		String[] idArray = ids.split(",");
		for (int i = 0; i < idArray.length; i++) {

			String ID = idArray[i];

			if (null != ID && !"".equals(ID.trim()) && !IDList.contains(ID.trim())) {
				IDList.add(ID.trim());
			}
		}
		return IDList;
	}
	
	// 解析ID字符串
	public static List<String> parseIdsToStringBySemicolon(String ids) {

		if (null == ids || "".equals(ids.trim())) {
			return null;
		}

		List<String> IDList = new ArrayList<>();
		String[] idArray = ids.split(";");
		for (int i = 0; i < idArray.length; i++) {

			String ID = idArray[i];

			if (null != ID && !"".equals(ID.trim()) && !IDList.contains(ID.trim())) {
				IDList.add(ID.trim());
			}
		}
		return IDList;
	}

	// 处理导入的数字类型的字符串被转换成了小数类型
	public static String dealNumberInExcel(String content) {
		if (null != content && !"".equals(content.trim())) {
			content = content.replace("'", "").replace("\"", "");
		}
		if (content.contains(".")) {
			try {
				content = String.valueOf((long) Double.parseDouble(content));
			} catch (NumberFormatException e) {
			}
		}
		return content;
	}

	// 校验经纬度集合信息
	public static boolean isGpsSetInfoCorrect(String gpsSet) {

		if (null == gpsSet || "".equals(gpsSet)) {
			return false;
		}

		String[] gpsSetArray = gpsSet.split(";");

		if (gpsSetArray.length < 3) {
			return false;
		}

		for (String gpsStr : gpsSetArray) {
			String[] gpsList = gpsStr.split(",");

			if (gpsList.length != 2) {
				return false;
			}

			Double longitude = null;
			try {
				longitude = Double.parseDouble(gpsList[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			}

			Double latitude = null;
			try {
				latitude = Double.parseDouble(gpsList[1]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			}

			if (null == longitude || longitude < 0.00 || longitude > 360.00) {
				return false;
			}

			if (null == latitude || latitude < 0.00 || latitude > 360.00) {
				return false;
			}
		}
		return true;
	}

	public static String toBinary(String str){
		//把字符串转成字符数组
		char[] strChar=str.toCharArray();
		StringBuilder result= new StringBuilder();
		for (char c : strChar) {
			//toBinaryString(int i)返回变量的二进制表示的字符串
			//toHexString(int i) 八进制
			//toOctalString(int i) 十六进制
			result.append(Integer.toBinaryString(c));
		}
		return result.toString();
	}

	// 校验车型信息
	public static String handleYesOrNo(Integer status) {
		if (null != status) {
			String statusString = null;
			switch (status) {
			case 0:
				statusString = "否";
				break;
			case 1:
				statusString = "是";
				break;
			default:
				break;
			}
			return statusString;
		}
		return null;
	}

	// 校验车辆车牌类型
	public static String handleVehiclePlateType(Integer status) {
		if (null != status) {
			String statusString = null;
			switch (status) {
			case 1:
				statusString = "黄底黑字";
				break;
			case 2:
				statusString = "蓝底白字";
				break;
			case 3:
				statusString = "其它号牌";
				break;
			default:
				break;
			}
			return statusString;
		}
		return null;
	}

	// 根据指定长度切分list集合，切分的下一个集合首元素是上一个集合的尾元素
	// isStayTail 是否留取尾部，作为被切分的下一个集合的首部
	public static void split(List<String> list, int length, boolean isStayTail, List<List<String>> result) {

		if (null == list || 0 == list.size()) {
			return;
		}

		if (list.size() > length) {
			List<String> sub = new ArrayList<String>(list.subList(0, length));
			result.add(sub);
			sub = isStayTail ? sub.subList(0, sub.size() - 1) : sub;
			for (int i = 0; i < sub.size(); i++) {
				list.remove(0);
			}
			split(list, length, isStayTail, result);
		} else {
			result.add(list);
		}
	}

	// 根据指定符号来切分字符串
	public static List<String> split(String content, String...symbols) {
		
		content = content.trim();
		if (null == content || "".equals(content)) {
			return null;
		}
		
		StringBuffer regex = new StringBuffer();
		for (int i = 0; i < symbols.length; i++) {
			regex.append("\\" + symbols[i]);
			if (i != symbols.length - 1) {
				regex.append("|");
			}
		}
		
		return new ArrayList<>(Arrays.asList(content.split(regex.toString())));
	}
	
	// 校验字符串中是否含有特殊字符
	public static boolean checkSpecialCharacter(String str) {
		
		if (null == str || "".equals(str.trim())) {
			return true;
		}
		
		for (int i = 0; i < SpecialCharacterArray.length; i++) {
			if (str.contains(SpecialCharacterArray[i])) {
				return false;
			}
		}
		
		return true;
	}

	// 限制经纬度长度为6位
	public static String limitLngLatLength(String lnglat) {

		int index = lnglat.indexOf(".");
		int length = lnglat.length();
		int lenLnglat = length - index;
		lenLnglat = lenLnglat > 7 ? 7 : lenLnglat;
		lnglat = lnglat.substring(0, lnglat.indexOf(".") + lenLnglat);

		double doubleValue = 0;
		try {
			doubleValue = Double.parseDouble(lnglat);
		} catch (NumberFormatException e) {
		}

		// 如果经纬度值为0，或者解析失败，则将经纬度的值设为null
		if (doubleValue <= 0) {
			lnglat = null;
		}
		
		return lnglat;
	}

	public static <T> List<List<T>> splitList(List<T> list, int len) {
		if (list == null || list.size() == 0 || len < 1) {
			return null;
		}
		//返回结果
		List<List<T>> result = new ArrayList<List<T>>();
		//传入集合长度
		int size = list.size();
		//分隔后的集合个数
		int count = (size + len - 1) / len;
		for (int i = 0; i < count; i++) {
			List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
			result.add(subList);
		}
		return result;
	}

	public static String replaceSpecialCharacter(String str) {
    	
    	if (!checkNull(str)) {
    		return str;
    	}

    	// '  "  < > /
		str = str.replace("'", "’").replace("\"", "”").replace("<", "(").replace(">", ")").replace("/", "\\");
    	
    	return str;
    }
}
