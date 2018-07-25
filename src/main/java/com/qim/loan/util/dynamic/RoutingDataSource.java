package com.qim.loan.util.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.qim.loan.util.dynamic.DataSourceSwitcher.DbType;

public class RoutingDataSource extends AbstractRoutingDataSource {

	public RoutingDataSource() {
	}

	@Override
	protected Object determineCurrentLookupKey() {
		DbType key = DataSourceSwitcher.getDbType();
		if (key==null)
			return DbType.MASTER;
		return key;
	}
}
