package com.qim.loan.core.model.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qim.loan.core.dao.CommonDao;
import com.qim.loan.core.model.CommonModel;
import com.qim.loan.util.common.StringUtil;
import com.qim.loan.util.paramter.RequestPager;

@Component("baseCommonModel")
public class CommonModelImpl implements CommonModel {

	@Autowired
	@Qualifier("commonDao")
	protected CommonDao baseDao;

	public void setPage(RequestPager requestPager) {
		if (requestPager == null)
			requestPager = new RequestPager();
		PageHelper.startPage(requestPager.getCurrentPage(), requestPager.getPageSize());
	}

	/**
	 * 
	 * 方法名: 功能描述: 创建者:冯子文 创建时间: Feb 8, 2018 10:13:05 AM 更新者:冯子文 更新时间: Feb 8, 2018
	 * 10:13:05 AM
	 */
	@Override
	public PageInfo<Map<String, Object>> basePage(RequestPager requestPager, String staticsSql) throws Exception {
		if (StringUtil.isNull(staticsSql))
			return new PageInfo<Map<String, Object>>();
		setPage(requestPager);
		List<Map<String, Object>> list = baseDao.baseList(staticsSql);
		return new PageInfo<Map<String, Object>>(list);
	}

	/**
	 * 
	 * 方法名:operateDDL 功能描述:ddl语句(谨慎使用) 创建者:冯子文 创建时间: Jan 3, 2018 11:41:04 AM 更新者:冯子文
	 * 更新时间: Jan 3, 2018 11:41:04 AM
	 */
	@Override
	public int baseAlter(String ddlSql) throws Exception {
		if (StringUtil.isNull(ddlSql))
			return -10;
		return baseDao.baseAlter(ddlSql);
	}

	// 基础查询
	@Override
	public List<Map<String, Object>> baseList(String sql) throws Exception {
		if (StringUtil.isNull(sql))
			return null;
		return baseDao.baseList(sql);
	}

	// 基础插入
	@Override
	public int baseUpdate(String sql) throws Exception {
		if (StringUtil.isNull(sql))
			return -10;
		return baseDao.baseUpdate(sql);
	}

	// 基础插入
	@Override
	public int baseInsert(String sql) throws Exception {
		if (StringUtil.isNull(sql))
			return -10;
		return baseDao.baseInsert(sql);
	}

	// 基础删除
	@Override
	public int baseDelete(String sql) throws Exception {
		if (StringUtil.isNull(sql))
			return -10;
		return baseDao.baseDelete(sql);
	}

	@Override
	public int baseCount(String sql) throws Exception {
		if (StringUtil.isNull(sql))
			return -10;
		return baseDao.baseCount(sql);
	}

	@Override
	public String baseQuery(String sql) throws Exception {
		if (StringUtil.isNull(sql))
			return null;
		return baseDao.baseQuery(sql);
	}

	@Override
	public Map<String, Object> baseOne(String sql) throws Exception {
		if (StringUtil.isNull(sql))
			return null;
		return baseDao.baseOne(sql);
	}

}
