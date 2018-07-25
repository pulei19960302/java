package com.qim.loan.bo.client;  

import com.qim.loan.entity.client.ClientData;

  
public class ClientDataBo{  

	private ClientData clientData;
	
	public ClientData getClientData() {
		return clientData;
	}

	public void setClientData(ClientData clientData) {
		this.clientData = clientData;
	}

	public ClientDataBo(){
		clientData=new ClientData();
	}
	/**
	 * 
	     * 方法名:setTelphoneNumber
	     * 功能描述:未填写	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月26日 上午11:55:48 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月26日 上午11:55:48
	 */
	public static ClientData setTelphoneNumber(String telphoneNumber) {
		ClientData clientData=new ClientData();
		clientData.setTelphoneNumber(telphoneNumber);
		clientData.setIsFill(0);//非填充
		return clientData;
	}
	
	/**
	 * 
	     * 方法名:setUnhandleTelphoneNumber
	     * 功能描述:已提交，未处理	 
	     * 创建者:冯子文
	     * 创建时间: 2018年6月26日 上午11:56:13 
		 * 更新者:冯子文  
		 * 更新时间: 2018年6月26日 上午11:56:13
	 */
	public static ClientData setUnhandleTelphoneNumber(String telphoneNumber) {
		ClientData clientData=new ClientData();
		clientData.setTelphoneNumber(telphoneNumber);
		clientData.setIsFill(1);//已提交资料
		clientData.setIsHanle(0);//未处理
		return clientData;
	}	
	

} 