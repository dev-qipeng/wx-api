package site.qipeng.wxapi.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


public class JSONUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> String toString(T obj){
		try {
			if(obj==null){
				return null;
			}
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T toObj(String str, Class<T> t){
		try {
			if(str==null){
				return null;
			}
			return mapper.readValue(str,t);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
}
