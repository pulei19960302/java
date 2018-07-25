package com.qim.loan.core.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.util.common.BeanUtil;
import com.qim.loan.util.common.DateUtil;
import com.qim.loan.util.common.GsonUtil;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.common.MapUtil;
import com.qim.loan.util.common.ReflectUtil;
import com.qim.loan.util.common.SetUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.RequestPager;



public abstract class BaseModelImpl<T> implements BaseModel<T> {

    protected List<String> physicsKeyLs;
    //-10:鏉′欢閿欒 -1:閲嶅 鍏朵粬鍊兼牴鎹垽鏂潵

    public abstract BaseDao<T> getDao();

    public void setPage(RequestPager requestPager) {
        if (requestPager == null)
            requestPager = new RequestPager();
        PageHelper.startPage(requestPager.getCurrentPage(), requestPager.getPageSize());
    }

    /**
     * 鏂规硶鍚�:setPrimary 鍔熻兘鎻忚堪:璁剧疆鐗╃悊涓婚敭 鍒涘缓鑰�:鍐瓙鏂� 鍒涘缓鏃堕棿: Jan 3, 2018 4:20:29 PM 鏇存柊鑰�:鍐瓙鏂�
     * 鏇存柊鏃堕棿: Jan 3, 2018 4:20:29 PM
     */
    protected void setPhysicsKeyName(String physicsKeyName, String primarykey) {
        physicsKeyLs = new LinkedList<String>();
        physicsKeyLs.add(primarykey);
        if (StringUtil.isNotNull(physicsKeyName))
            physicsKeyLs.addAll(Arrays.asList(physicsKeyName.split(",")));
    }


    /**************缁煎悎**************/
    //鏌ラ噸
    @Override
    public int queryRepeat(T record) throws Exception {
        @SuppressWarnings("unchecked")
        T temp = ReflectUtil.getInstance((Class<T>) record.getClass());
        Object idObject = getSingleField(record, "id");
        if (idObject != null)
            setSingleField(temp, "id", idObject);
        if (physicsKeyLs.size() > 1) {
            List<String> tempPhysicsKeyLs = physicsKeyLs.subList(1, physicsKeyLs.size());
            for (String key : tempPhysicsKeyLs) {
                Object tmpValue = getSingleField(record, key);
                if (tmpValue != null)
                    setSingleField(temp, key, tmpValue);
                if (tmpValue == null && StringUtil.equalsIgnoreCase(key, "createDate"))
                    setSingleField(temp, "createDate", DateUtil.formatCurrentDate());
            }
            return getDao().getCount(temp);
        }
        return 0;
    }

    @Override
    public int queryRepeatByMap(Map<String, List<?>> map) throws Exception {
        Map<String, List<?>> temp = new HashMap<String, List<?>>();
        if (physicsKeyLs.size() > 1) {
            List<String> tempPhysicsKeyLs = physicsKeyLs.subList(1, physicsKeyLs.size());
            for (String key : tempPhysicsKeyLs) {
                List<?> tmpValue = map.get(key);
                if (ListUtil.isNotNull(tmpValue))
                    temp.put(key, tmpValue);
                if (tmpValue == null && StringUtil.equalsIgnoreCase(key, "createDate")) {
                    ArrayList<Integer> createDateList = new ArrayList<Integer>();
                    createDateList.add(DateUtil.formatCurrentDate());
                    temp.put("createDate", createDateList);
                }
            }
            return getDao().getCountByParams(temp);
        }
        return 0;
    }

    @Override
    public int alter(T record) throws Exception {
        Object idObject = getSingleField(record, "id");
        if (idObject == null)
            return insertRepeat(record);
        return updateRepeatById(record);
    }


    /****鑾峰彇鍒嗛〉*****/
    @Override
    public PageInfo<Map<String, Object>> getPage(RequestPager requestPager, T record) throws Exception {
        return getPage(requestPager, null, record, null, null);
    }

    @Override
    public PageInfo<Map<String, Object>> getPageByParams(RequestPager requestPager, Map<String, List<?>> params) throws Exception {
        return getPageByParams(requestPager, null, params, null, null);
    }

    @Override
    public PageInfo<Map<String, Object>> getPage(RequestPager requestPager, T record, String orderBys) throws Exception {
        return getPage(requestPager, null, record, null, orderBys);
    }

    @Override
    public PageInfo<Map<String, Object>> getPageByParams(RequestPager requestPager, Map<String, List<?>> params, String orderBys) throws Exception {
        params.remove("referer");
        return getPageByParams(requestPager, null, params, null, orderBys);
    }

    @Override
    public PageInfo<Map<String, Object>> getPage(RequestPager requestPager, T record, String groupBys, String orderBys) throws Exception {
        return getPage(requestPager, null, record, groupBys, orderBys);
    }

    @Override
    public PageInfo<Map<String, Object>> getPageByParams(RequestPager requestPager, Map<String, List<?>> params, String groupBys, String orderBys) throws Exception {
        return getPageByParams(requestPager, null, params, groupBys, orderBys);
    }

    @Override
    public PageInfo<Map<String, Object>> getPage(RequestPager requestPager, String fields, T record) throws Exception {
        return getPage(requestPager, fields, record, null, null);
    }

    @Override
    public PageInfo<Map<String, Object>> getPage(RequestPager requestPager, String fields, T record, String orderBys) throws Exception {
        return getPage(requestPager, fields, record, null, orderBys);
    }

    @Override
    public PageInfo<Map<String, Object>> getPageByParams(RequestPager requestPager, String fields, Map<String, List<?>> params, String orderBys) throws Exception {
        return getPageByParams(requestPager, fields, params, null, orderBys);
    }

    @Override
    public PageInfo<Map<String, Object>> getPage(RequestPager requestPager, String fields, T record, String groupBys, String orderBys) throws Exception {
        setPage(requestPager);
        List<Map<String, Object>> list = getDao().getListRelative(toBySelfLs(fields), toByRelativeLs(fields), record, toByLs(groupBys), toByLs(orderBys));
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        pageInfo.setList(ListUtil.toCamel(list));
        return pageInfo;
    }

    @Override
    public PageInfo<Map<String, Object>> getPageByParams(RequestPager requestPager, String fields, Map<String, List<?>> params, String groupBys, String orderBys) throws Exception {
        setPage(requestPager);
        List<Map<String, Object>> list = getDao().getListRelativeByParams(toBySelfLs(fields), toByRelativeLs(fields), MapUtil.toStrip(params), toByLs(groupBys), toByLs(orderBys));
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        pageInfo.setList(ListUtil.toCamel(list));
        return pageInfo;
    }

    protected List<String> toByLs(String orderBys) {
        return StringUtil.toList(toBy(orderBys));
    }


    protected List<String> toBySelfLs(String orderBys) {
        List<String> result = StringUtil.toList(orderBys);
        if (result == null || result.size() == 0)
            return null;
        List<String> resultS = toByRelativeLs(orderBys);
        if (resultS == null || resultS.size() == 0)
            return result;
        return ListUtil.toStripStr(ListUtil.subtract(result, resultS));
    }

    protected List<String> toByRelativeLs(String orderBys) {
        List<String> result = StringUtil.toList(orderBys);
        if (result == null || result.size() == 0)
            return null;
        return ListUtil.toStripSplit(StringUtil.toList(result, "."));
    }

    protected String toBy(String orderBys) {
        return StringUtil.toStrip(StringUtil.replaceSort(orderBys));
    }

    /*****鑾峰彇鍒楄〃****/
    @Override
    public List<Map<String, Object>> getListRelative(T record) throws Exception {
        return getListRelative(null, record, null, null);
    }

    @Override
    public List<Map<String, Object>> getListRelativeByParams(Map<String, List<?>> params) throws Exception {
        return getListRelativeByParams(null, params, null, null);
    }


    @Override
    public List<Map<String, Object>> getListRelative(T record, String orderBys) throws Exception {
        return getListRelative(null, record, null, orderBys);
    }

    @Override
    public List<Map<String, Object>> getListRelativeByParams(Map<String, List<?>> params, String orderBys) throws Exception {
        return getListRelativeByParams(null, params, null, orderBys);
    }

    @Override
    public List<Map<String, Object>> getListRelative(T record, String groupBys, String orderBys) throws Exception {
        return getListRelative(null, record, groupBys, orderBys);
    }

    @Override
    public List<Map<String, Object>> getListRelativeByParams(Map<String, List<?>> params, String groupBys, String orderBys) throws Exception {
        return getListRelativeByParams(null, params, groupBys, orderBys);
    }

    @Override
    public List<Map<String, Object>> getListRelative(String fields, T record, String orderBys) throws Exception {
        return getListRelative(fields, record, null, orderBys);
    }

    @Override
    public List<Map<String, Object>> getListRelativeByParams(String fields, Map<String, List<?>> params, String orderBys) throws Exception {
        return getListRelativeByParams(fields, params, null, orderBys);
    }

    @Override
    public List<Map<String, Object>> getListRelative(String fields, T record, String groupBys, String orderBys) throws Exception {
        return getDao().getListRelative(toBySelfLs(fields), toByRelativeLs(fields), record, toByLs(groupBys), toByLs(orderBys));
    }

    @Override
    public List<Map<String, Object>> getListRelativeByParams(String fields, Map<String, List<?>> params, String groupBys, String orderBys) throws Exception {
        return getDao().getListRelativeByParams(toBySelfLs(fields), toByRelativeLs(fields), MapUtil.toStrip(params), toByLs(groupBys), toByLs(orderBys));
    }

    @Override
    public List<T> getList(T record) throws Exception {
        return getList(null, record, null, null);
    }

    @Override
    public List<T> getList(T record, String orderBys) throws Exception {
        return getList(null, record, null, orderBys);
    }

    @Override
    public List<T> getListByParams(Map<String, List<?>> params, String orderBys) throws Exception {
        return getListByParams(null, params, null, orderBys);
    }

    @Override
    public List<T> getListByParams(Map<String, List<?>> params) throws Exception {
        return getListByParams(null, params, null, null);
    }


    @Override
    public List<T> getList(T record, String groupBys, String orderBys) throws Exception {
        return getList(null, record, groupBys, orderBys);
    }

    @Override
    public List<T> getListByParams(Map<String, List<?>> params, String groupBys, String orderBys) throws Exception {
        return getListByParams(null, params, groupBys, orderBys);
    }

    @Override
    public List<T> getList(String fields) throws Exception {
        return getList(fields, null, null, null);
    }

    @Override
    public List<T> getList(String fields, T record) throws Exception {
        return getList(fields, record, null, null);
    }

    @Override
    public List<T> getList(String fields, T record, String orderBys) throws Exception {
        return getList(fields, record, null, orderBys);
    }

    @Override
    public List<T> getListByParams(String fields, Map<String, List<?>> params, String orderBys) throws Exception {
        return getListByParams(fields, params, null, orderBys);
    }

    @Override
    public List<T> getList(String fields, T record, String groupBys, String orderBys) throws Exception {
        return getDao().getList(toBy(fields), record, toBy(groupBys), toBy(orderBys));
    }

    //閫氳繃鏈韩鑾峰彇鑷繁
    @Override
    public List<T> getListByParams(String fields, Map<String, List<?>> params, String groupBys, String orderBys) throws Exception {
        return getDao().getListByParams(toBy(fields), MapUtil.toStrip(params), toBy(groupBys), toBy(orderBys));
    }

    /********鑾峰彇璁板綍鏁�**********/
    @Override
    public int getCount(T record) throws Exception {
        if (record == null)
            return -10;
        return getDao().getCount(record);
    }

    @Override
    public int getCountByParams(Map<String, List<?>> whParams) throws Exception {
        if (MapUtil.isNull(whParams))
            return -10;
        //return baseDao.getCountByParams(whParams);
        return 0;
    }

    /********鑾峰彇鍗曟寫璁板綍**********/
    @Override
    public T getOneById(String id) throws Exception {
        return getOneById(null, id);
    }

    @Override
    public T getOneById(String fields, String id) throws Exception {
        if (StringUtil.isNotNull(fields))
            fields = StringUtil.toStrip(fields);
        if (StringUtil.isNull(id))
            return null;
        return getDao().getOneById(fields, id);
    }

    @Override
    public T getOne(T record) throws Exception {
        return getOne(null, record);
    }

    @Override
    public T getOne(String fields, T record) throws Exception {
        if (record == null)
            return null;
        return getDao().getOne(toBy(fields), record);
    }

    @Override
    public T getOneByParams(Map<String, List<?>> whParams) throws Exception {
        return getOneByParams(null, whParams);
    }

    @Override
    public T getOneByParams(String fields, Map<String, List<?>> whParams) throws Exception {
        fields = StringUtil.toStrip(fields);
        if (MapUtil.isNull(whParams))
            return null;
        return getDao().getOneByParams(fields, MapUtil.toStrip(whParams));
    }

    /********鑾峰彇鍗曟寫璁板綍鐩稿叧*****/
    @Override
    public Map<String, Object> getOneRelativeById(String id) throws Exception {
        return getDao().getOneRelativeById(null, null, id);
    }

    @Override
    public Map<String, Object> getOneRelativeById(String fields, String id) throws Exception {
        fields = StringUtil.toStrip(fields);
        if (StringUtil.isNull(id))
            return null;
        return getDao().getOneRelativeById(toBySelfLs(fields), toByRelativeLs(fields), id);
    }

    @Override
    public Map<String, Object> getOneRelative(T record) throws Exception {
        return getOneRelative(null, record);
    }

    @Override
    public Map<String, Object> getOneRelative(String fields, T record) throws Exception {
        if (StringUtil.isNull(fields))
            fields = "";
        if (record == null)
            return null;
        return getDao().getOneRelative(toBySelfLs(fields), toByRelativeLs(fields), record);
    }

    @Override
    public Map<String, Object> getOneRelativeByParam(Map<String, List<?>> whParams) throws Exception {
        return getOneRelativeByParam(null, whParams);
    }

    //鏍规嵁鏉′欢鑾峰彇鐩稿簲鍏宠仈瀛楁
    @Override
    public Map<String, Object> getOneRelativeByParam(String fields, Map<String, List<?>> whParams) throws Exception {
        fields = StringUtil.toStrip(fields);
        if (MapUtil.isNull(whParams))
            return null;
        return getDao().getOneRelativeByParams(toBySelfLs(fields), toByRelativeLs(fields), MapUtil.toStrip(whParams));
    }

    /********浠ヤ笅涓烘彃鍏�********/
    @Override
    public int insert(T record) throws Exception {
        if (record == null)
            return -10;
        setSingleField(record, "createDate", DateUtil.formatCurrentDate());
        setSingleField(record, "createTime", DateUtil.formatCurrentTime());
        return getDao().insert(record);
    }

    @Override
    public int insertRepeat(T record) throws Exception {
        if (record == null)
            return -10;
        setSingleField(record, "createDate", DateUtil.formatCurrentDate());
        setSingleField(record, "createTime", DateUtil.formatCurrentTime());
        System.err.println(GsonUtil.toJson(record));
        if (queryRepeat(record) == 0)
            return getDao().insert(record);
        return -1;
    }

    @Override
    public int insertBatch(List<T> recordList) throws Exception {
        if (ListUtil.isNull(recordList))
            return -10;
        return getDao().insertBatch(recordList);
    }

    @Override
    public int insertByParams(Map<String, List<?>> params) throws Exception {
        if (MapUtil.isNull(params))
            return -10;
        List<Map<String, ?>> tmpParam = MapUtil.toListMap(MapUtil.toStrip(params));
        return getDao().insertByParams(SetUtil.toString(tmpParam.get(0).keySet()), tmpParam);
    }

    /*****************浠ヤ笅涓烘洿鏂�*****************/
    @Override
    public int updateById(T record) throws Exception {
        if (record == null)
            return -10;
        return getDao().updateById(record);
    }

    @Override
    public int updateRepeatById(T record) throws Exception {
        if (record == null)
            return -10;
        if (queryRepeat(record) == 0)
            return getDao().updateById(record);
        return -1;
    }

    @Override
    public int updateByParams(T record, Map<String, List<?>> params) throws Exception {
        if (record == null)
            return -10;
        if (params != null)
            return getDao().updateByParams(record, MapUtil.toListMap(params));
        return getDao().updateByParams(record, null);
    }

    /*************浠ヤ笅涓哄垹闄�*****************/
    @Override
    public int deleteAll() throws Exception {
        return getDao().deleteAll();
    }

    @Override
    public int delete(T record) throws Exception {
        if (BeanUtil.isNull(record))
            return -10;
        return getDao().delete(record);
    }

    @Override
    public int deleteByParams(Map<String, List<?>> params) {
        if (MapUtil.isNull(params))
            return -10;
        return getDao().deleteByParams(MapUtil.toStrip(params));
    }

    @Override
    public int deleteById(String id) throws Exception {
        if (StringUtil.isNull(id))
            return -10;
        return getDao().deleteById(id);
    }

    @Override
    public int deleteByIds(String ids) throws Exception {
        if (StringUtil.isNull(ids))
            return -10;
        return getDao().deleteByIds(Arrays.asList(ids.split(",")));
    }


    /*********鍩虹鏂规硶*********/
    protected T getT(Class<T> cls) {
        return ReflectUtil.getInstance(cls);
    }

    protected String getFieldType(T t, String primaryKeyName) {
        return ReflectUtil.getFieldType(t, primaryKeyName);
    }

    // 鑾峰彇T涓媝arimaryKey鐨勫��
    protected Object getSingleField(T t, String primaryKeyName) {
        return ReflectUtil.getMethodValue(t, primaryKeyName);
    }

    protected Boolean setSingleField(T t, String primaryKeyName, Object physicsKeyValue) {
        return ReflectUtil.setMethodValue(t, primaryKeyName, physicsKeyValue);
    }


}
