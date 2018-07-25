package com.qim.loan.util.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qim.loan.util.author.OauthUtil;
import com.qim.loan.util.cache.classify.string.IStringCache;

/**
 * 
 * 类名:MapUtil 描述:map工具 创建者:冯子文 创建时间: Dec 8, 2017 10:11:55 AM 更新者:冯子文 更新时间: Dec
 * 8, 2017 10:11:55 AM
 */
public class MapUtil {

	private static Logger logger = LoggerFactory.getLogger(MapUtil.class);
	
	private Map<String, Object> map;

	public MapUtil() {
		this.map = new HashMap<String, Object>();
	}

	public static Map<String, Object> set(String key, String value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(key, value);
		return map;
	}

	public static Map<String, Object> setAuthority(IStringCache userCache,String userId, String userName,String suffix) {
		if (StringUtil.isNull(userId))
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(userCache.exists(userId)) {
			map.put("userKey",OauthUtil.encrypt(userId));
			map.put("userToken",OauthUtil.encrypt(userCache.get(userId).split("@")[0])+"@"+suffix);				
		}else {
			String userToken=PrimaryKeyUtil.getToken();
			userCache.set(userId, userToken+"@"+suffix);
			map.put("userKey",OauthUtil.encrypt(userId));
			map.put("userToken",OauthUtil.encrypt(userToken)+"@"+suffix);			
		}
		return map;
	}
	
	public static void setAuthorityOut(IStringCache userCache,String userId) {
		userCache.remove(userId);
	}
	
	
	
	
	public static Map<String, Object> getJsonByParam(List<Map<String, Object>> list, String column, String alias) {
		StringBuilder sBuilder = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (Map<String, Object> tmp : list)
				if (tmp.get(column) != null)
					sBuilder.append(tmp.get(column) + ",");
			if (sBuilder.length() > 0)
				sBuilder.deleteCharAt(sBuilder.length() - 1);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(alias, sBuilder.toString());
		return map;
	}

	public MapUtil(Map<String, Object> map) {
		this.map = map;
	}

	public MapUtil put(String key, Object value) {
		this.map.put(key, value);
		return this;
	}

	public static Map<String, List<?>> transMapLs(Map<String, ?> map) {
		if (isNull(map))
			return null;
		Map<String, List<?>> resultMap = new HashMap<String, List<?>>();
		for (String key : map.keySet()) {
			Object tmpObject = map.get(key);
			List<Object> tmpLs = new ArrayList<Object>();
			if (tmpObject == null) {
				resultMap.put(key, null);
			} else {
				tmpLs.add(tmpObject);
				resultMap.put(key, tmpLs);
			}
		}
		return resultMap;
	}

	public MapUtil putAll(Map<? extends String, ? extends Object> map) {
		this.map.putAll(map);
		return this;
	}

	public MapUtil remove(String key) {
		this.map.remove(key);
		return this;
	}

	public MapUtil replace(String key, Object value) {
		this.map.replace(key, value);
		return this;
	}

	public Map<String, Object> getMap() {
		return this.map;
	}

	@SuppressWarnings("rawtypes")
	public static Boolean isNull(Map map) {
		if (map == null)
			return true;
		return false;
	}

	public static Boolean isNotNull(Map<String, Object> map) {
		return !isNull(map);
	}

	// 转换位驼峰
	public static Map<String, Object> toCamel(Map<String, Object> map) {
		if (BeanUtil.isNotNull(map)) {
			Map<String, Object> result = new HashMap<String, Object>();
			for (String key : map.keySet())
				result.put(StringUtil.toCamel((String) key), map.get(key));
			return result;
		} else {
			return null;
		}
	}

	// 转换为_s
	public static <E> Map<String, E> toStrip(Map<String, E> map) {
		if (BeanUtil.isNotNull(map)) {
			Map<String, E> result = new HashMap<String, E>();
			for (String key : map.keySet())
				result.put(StringUtil.toStrip((String) key), map.get(key));
			result.remove("user_id");
			result.remove("user_token");
			result.remove("sys_user_id");
			result.remove("os_version");
			result.remove("ip_address");
			result.remove("browse_version");
			result.remove("page_size");
			result.remove("current_page");
			return result;
		} else {
			return null;
		}
	}

	public static <T> T toBean(Map<String, Object> map, T bean) {
		map = toCamel(map);
		if (map != null)
			for (String key : map.keySet())
				ReflectUtil.setSingleField(bean, key, map.get(key));
		return bean;
	}

	/**
	 * 
	 * 方法名:toListMap 功能描述:整体排列组合 创建者:冯子文 创建时间: Feb 6, 2018 11:15:19 AM 更新者:冯子文 更新时间:
	 * Feb 6, 2018 11:15:19 AM
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Map<String, ?>> toListMap(Map<String, List<?>> map) {
		if (map == null || map.size() == 0)
			return null;
		// 排序
		List<Map.Entry<String, List<?>>> list = new ArrayList<Map.Entry<String, List<?>>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, List<?>>>() {
			public int compare(Entry<String, List<?>> o1, Entry<String, List<?>> o2) {
				return (o2.getValue().size()) - (o1.getValue().size());// 降序
			}
		});
		Entry<String, List<?>> tmpEntry = null;
		Map[] tmpMap = new HashMap[list.get(0).getValue().size()];
		for (int i = 0; i < list.size(); i++) {
			tmpEntry = list.get(i);
			if (i == 0) {//
				for (int j = 0; j < tmpEntry.getValue().size(); j++) {// 收个
					tmpMap[j] = new HashMap();
					tmpMap[j].put(tmpEntry.getKey(), tmpEntry.getValue().get(j));
				}
			} else {
				if (tmpEntry.getValue().size() == list.get(0).getValue().size())
					for (int j = 0; j < tmpEntry.getValue().size(); j++)
						tmpMap[j].put(tmpEntry.getKey(), tmpEntry.getValue().get(j));
				else
					for (int j = 0; j < list.get(0).getValue().size(); j++)
						tmpMap[j].put(tmpEntry.getKey(), tmpEntry.getValue().get(0));
			}
		}
		return Arrays.asList(tmpMap);
	}

	public static <T> List<T> toBeans(List<Map<String, Object>> mapList, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		try {
			if (mapList != null && mapList.size() > 0) {
				Map<String, Object> map = null;
				T bean = null;
				for (int i = 0, size = mapList.size(); i < size; i++) {
					map = mapList.get(i);
					bean = clazz.newInstance();
					toBean(map, bean);
					list.add(bean);
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			MessageUtil.error(logger, "toBeans", e);
			e.printStackTrace();
		}
		return list;
	}

	public static <K, V> void removeValue(Map<K, V> record, V value) {
		Iterator<Map.Entry<K, V>> it = record.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<K, V> entry = it.next();
			if (entry.getValue().equals(value))
				it.remove();
		}
	}

	public static <K, V> void removeKey(Map<K, V> record, K key) {
		Iterator<Map.Entry<K, V>> it = record.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<K, V> entry = it.next();
			if (entry.getKey().equals(key))
				it.remove();
		}
	}

	public static void main(String[] args) {

	}

}
