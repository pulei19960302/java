package com.qim.loan.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qim.loan.core.model.BaseModel;
import com.qim.loan.core.model.BaseTreeModel;
import com.qim.loan.core.service.BaseTreeService;
import com.qim.loan.util.paramter.BaseResponse;
import com.qim.loan.util.paramter.RequestPager;

@Service
public abstract class BaseTreeServiceImpl<T> extends BaseServiceImpl<T> implements BaseTreeService<T> {

	@Autowired
	private BaseTreeModel<T> baseTreeModel;
	@Override
	public BaseModel<T> getModel(){
		return getTreeModel();
	}
	public abstract BaseTreeModel<T> getTreeModel();
	
	@Override
	public BaseResponse getSubClassPageById(RequestPager requestPager, String id) {
		return BaseResponse.ObjectResponse(baseTreeModel.getSubClassPageById(requestPager, id));
	}

	@Override
	public BaseResponse getSubClassListById(String id) {
		return BaseResponse.ObjectResponse(baseTreeModel.getSubClassListById(id));
	}

	@Override
	public BaseResponse getAllSubClassPageById(RequestPager requestPager, String id) {
		return BaseResponse.ObjectResponse(baseTreeModel.getAllSubClassPageById(requestPager,id));
	}

	@Override
	public BaseResponse getAllSubClassListById(String id) {
		return BaseResponse.ObjectResponse(baseTreeModel.getAllSubClassListById(id));
	}

	@Override
	public BaseResponse getAllParClassListById(String id) {
		return BaseResponse.ObjectResponse(baseTreeModel.getAllParClassListById(id));
	}

	@Override
	public BaseResponse getParClassListById(String id) {
		return BaseResponse.ObjectResponse(baseTreeModel.getParClassListById(id));
	}

	@Override
	public BaseResponse update(T record, String pid) throws Exception {
		return BaseResponse.countResponse("更新树形结构", baseTreeModel.update(record, pid));
	}

	@Override
	public BaseResponse insert(T record, String pid) throws Exception {
		return BaseResponse.countResponse("增加树形结构", baseTreeModel.insert(record, pid));
	}

	@Override
	public BaseResponse alter(T record, String pid) throws Exception {
		return BaseResponse.countResponse("修改树形结构", baseTreeModel.alter(record, pid));
	}

	@Override
	public BaseResponse getTree() {
		return BaseResponse.ObjectResponse(baseTreeModel.getListTree());
	}





}
