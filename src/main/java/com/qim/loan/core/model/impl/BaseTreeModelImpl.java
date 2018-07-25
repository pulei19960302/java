package com.qim.loan.core.model.impl;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.qim.loan.core.dao.BaseDao;
import com.qim.loan.core.dao.BaseTreeDao;
import com.qim.loan.core.dao.CommonDao;
import com.qim.loan.core.model.BaseTreeModel;
import com.qim.loan.util.common.ListUtil;
import com.qim.loan.util.common.PrimaryKeyUtil;
import com.qim.loan.util.common.ReflectUtil;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.RequestPager;


public abstract class BaseTreeModelImpl<T> extends BaseModelImpl<T> implements BaseTreeModel<T> {

    protected String[] tableName;

    @Override
    public BaseDao<T> getDao() {
        return getTreeDao();
    }

    public abstract BaseTreeDao<T> getTreeDao();

    public abstract CommonDao getCommonDao();

    // 设置(主键)
    protected void setPhysicsKeyName(String physicsKeyName, String primarykey, String hierarchyName) {
        setPhysicsKeyName(physicsKeyName, primarykey);
        if (StringUtil.isNotNull(hierarchyName))
            tableName = hierarchyName.split(",");
    }

    @Override
    public PageInfo<Map<String, Object>> getSubClassPageById(RequestPager requestPager, String id) {
        if (StringUtil.isNull(id))
            return null;
        setPage(requestPager);
        List<Map<String, Object>> list = getTreeDao().getSubClassListById(id);
        return new PageInfo<Map<String, Object>>(ListUtil.toCamel(list));
    }

    @Override
    public PageInfo<Map<String, Object>> getAllSubClassPageById(RequestPager requestPager, String id) {
        if (StringUtil.isNull(id))
            return null;
        setPage(requestPager);
        List<Map<String, Object>> list = getTreeDao().getAllSubClassListById(id);
        return new PageInfo<Map<String, Object>>(list);
    }

    @Override
    public List<Map<String, Object>> getAllSubClassListById(String id) {
        if (StringUtil.isNull(id))
            return null;
        return getTreeDao().getAllSubClassListById(id);
    }

    @Override
    public List<Map<String, Object>> getListTree() {
        return getTreeDao().getListTree();
    }

    @Override
    public List<Map<String, Object>> getSubClassListById(String id) {
        if (StringUtil.isNull(id))
            return null;
        return getTreeDao().getSubClassListById(id);
    }

    @Override
    public List<Map<String, Object>> getParClassByHierarchy(List<String> hierarchyList) {
        if (ListUtil.isNull(hierarchyList))
            return null;
        return getTreeDao().getParClassByHierarchy(hierarchyList);
    }

    @Override
    public List<Map<String, Object>> getAllParClassListById(String id) {
        if (StringUtil.isNull(id))
            return null;
        return getTreeDao().getAllParClassListById(id);
    }

    @Override
    public List<Map<String, Object>> getParClassListById(String id) {
        if (StringUtil.isNull(id))
            return null;
        return getTreeDao().getParClassListById(id);
    }

    @Override
    public int updateHierarchy(String oldHierarchy, String newHierarchy) {
        if (StringUtil.isNull(newHierarchy) || StringUtil.isNull(newHierarchy))
            return -10;
        return getTreeDao().updateHierarchy(oldHierarchy, newHierarchy);
    }

    @Override
    public int alter(T record, String pid) throws Exception {
        if (record == null)
            return -10;
        Object idObject = getSingleField(record, "id");
        if (idObject != null)
            return update(record, pid);
        return insert(record, pid);
    }

    @Override
    public int update(T record, String pid) throws Exception {

        if (StringUtil.isNotNull(pid)) {
            String table = StringUtil.firstLowCharStrip(record.getClass().getSimpleName());
            //原数据
            String oldId = String.valueOf(getSingleField(record, "id"));
            T oldTmp = super.getOneById(oldId);
            String oldHierarchy = String.valueOf(getSingleField(oldTmp, "hierarchy"));
            //new
            T tmp = super.getOneById(pid);
            Object hierarchyObj = getSingleField(tmp, "hierarchy");
            String hierarchyTmp = (hierarchyObj == null ? "" : String.valueOf(hierarchyObj));

            String level = PrimaryKeyUtil.getHierarchy();
            String newHierarchy = "";
            if (StringUtil.isNull(hierarchyTmp))
                newHierarchy = "/" + level + "/";
            else
                newHierarchy = hierarchyTmp + level + "/";
            String sql = "update "
                    + table.replaceAll("\\u007F", "") +
                    " set hierarchy=REPLACE(hierarchy,'" + oldHierarchy + "','" + newHierarchy + "') where LEFT(hierarchy,LENGTH('" + oldHierarchy + "'))='" + oldHierarchy + "'";
            setSingleField(record, "hierarchy", newHierarchy);
            //修改
            getCommonDao().baseUpdate(sql);
        }
        return super.updateRepeatById(record);
    }

    @Override
    public int insert(T record, String pid) throws Exception {
        int count = super.getCount(record);
        if (count > 0) {
            return -1;
        }
        String level = PrimaryKeyUtil.getHierarchy();
        if (StringUtil.isNull(pid)) {
            Object hierarchyObj = getSingleField(record, "hierarchy");
            String hierarchy = (hierarchyObj == null ? "" : String.valueOf(hierarchyObj));
            if (StringUtil.isNull(hierarchy)) {// 失败
                // 判断是否有根节点,无则添加根节点,有则在根节点下添加
                @SuppressWarnings("unchecked")
                T paramRecord = ReflectUtil.getInstance((Class<T>) record.getClass());
                setSingleField(paramRecord, "hierarchy", "/");
                List<T> list = super.getList(paramRecord);
                if (list == null || list.size() == 0) {
                    // 添加根节点
                    @SuppressWarnings("unchecked")
                    T topRecord = ReflectUtil.getInstance((Class<T>) record.getClass());
                    setSingleField(topRecord, "hierarchy", "/");
                    if (this.tableName != null && this.tableName.length == 2 && StringUtil.isNotNull(this.tableName[0]) && StringUtil.isNotNull(this.tableName[1]))
                        setSingleField(topRecord, StringUtil.firstLowCharCamel(this.tableName[0]), String.valueOf(this.tableName[1]));
                    int insertCount = super.insertRepeat(topRecord);
                    if (insertCount < 0)
                        return insertCount;
                    setSingleField(record, "hierarchy", "/" + level + "/");
                } else {
                    setSingleField(record, "hierarchy", "/" + level + "/");
                }
            } else {
                setSingleField(record, "hierarchy", hierarchy + level + "/");
            }
        } else {
            if (pid.equals("0")) {
                return -10;
            } else {
                T bankType = super.getOneById(pid);
                setSingleField(record, "hierarchy", String.valueOf(getSingleField(bankType, "hierarchy")) + level + "/");
            }
        }
        return super.insertRepeat(record);
    }

}
