package com.qim.loan.util.dynamic;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qim.loan.util.dynamic.DataSourceSwitcher.DbType;

@Aspect
@Component
public class DataSourceAop{

	private static final Logger logger=LoggerFactory.getLogger(DataSourceAop.class); 	

    @Before("execution(* com..*Dao.get*(..)) || execution(* com..*Dao.select*(..))")
    public void setReadDataSourceType() {
    	DataSourceSwitcher.setDbType(DbType.SLAVE);
        logger.info("dataSource切换到：Read");
    }
   
    @Before("execution(* com..*Dao.alter*(..)) || execution(* com..*Dao.create*(..)) || execution(* com..*Dao.save*(..)) || execution(* com..*Dao.insert*(..)) || execution(* com..*Dao.add*(..)) || execution(* com..*Dao.update*(..)) || execution(* com..*Dao.remove*(..)) || execution(* com..*Dao.delete*(..)) || execution(* com..*Dao.edit*(..))")
    public void setWriteDataSourceType() {
    	DataSourceSwitcher.setDbType(DbType.MASTER);
        logger.info("dataSource切换到：write");
    }
}
