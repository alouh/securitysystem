package com.youotech.securitysystem.service;

import com.youotech.securitysystem.bo.SeServer;
import com.youotech.securitysystem.datasource.DataSource;
import com.youotech.securitysystem.utils.Pager;

/**
 * 网络设备服务开放详情
 * Created by chenzc on 2017-11-23.
 */
public interface SeServerService extends CommerService<SeServer> {
    Pager<SeServer> findByParamPagePlus(SeServer entity, Integer start, Integer limit,String svDate);

	/**
	 * 插入扫描到的服务
	 * @param record
	 * @return
	 */
	@DataSource("mysql")
	int insert(SeServer record);
}
