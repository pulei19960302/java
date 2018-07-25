package com.qim.loan.util.common;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * (OK)
 * 类名:GsonUtil 
 * 描述:gson工具 
 * 创建者:冯子文 
 * 创建时间: 2017年6月28日 下午3:28:04 
 * 更新者:冯子文 更新时间:
 * 2017年6月28日 下午3:28:04
 */
public class GsonUtil {
	
	//private static Logger logger = Logger.getLogger(GsonUtil.class);
	
	private static Gson gson;

	static {
		gson = new Gson();
	}

	public static <T> T fromJson(String json, Class<T> clz) {
		return gson.fromJson(json, new TypeToken<T>() {}.getType());
	}

	public static <T> List<T> fromJsonList(String json, Class<T> cls) {
		return gson.fromJson(json, new TypeToken<List<T>>() {}.getType());
	}

	public static <T> List<Map<String, T>> fromJsonListMap(String json) {
		return gson.fromJson(json, new TypeToken<List<Map<String, T>>>() {}.getType());
	}

	public static <T> Map<String, T> fromJsonMap(String json) {
		return gson.fromJson(json, new TypeToken<Map<String, T>>() {}.getType());
	}

	public static <T> T fromJson(String json, Type type) {
		return gson.fromJson(json, type);
	}

	public static <T> T fromJson(Reader json, Class<T> clz) {
		return gson.fromJson(json, clz);
	}

	public static <T> T fromJson(Reader json, Type type) {
		return gson.fromJson(json, type);
	}

	public static <T> T fromJson(JsonElement jsonElement, Class<T> clz) {
		return gson.fromJson(jsonElement, clz);
	}

	public static <T> T fromJson(JsonElement jsonElement, Type type) {
		return gson.fromJson(jsonElement, type);
	}

	public static <T> T fromJson(JsonReader reader, Type type) {
		return gson.fromJson(reader, type);
	}

	public static String toJson(Object src) {
		return gson.toJson(src);
	}

	public static String toJson(JsonElement jsonElement) {
		return gson.toJson(jsonElement);
	}

	public static String toJson(Object src, Type typeOfSrc) {
		return gson.toJson(src, typeOfSrc);
	}

	public static void toJson(Object src, Appendable writer) {
		gson.toJson(src, writer);
	}

	public static void toJson(Object src, Type typeOfSrc, Appendable writer) {
		gson.toJson(src, typeOfSrc, writer);
	}

	public static void toJson(Object src, Type typeOfSrc, JsonWriter writer) {
		gson.toJson(src, typeOfSrc, writer);
	}

	public static void toJson(JsonElement jsonElement, JsonWriter writer) {
		gson.toJson(jsonElement, writer);
	}
}
