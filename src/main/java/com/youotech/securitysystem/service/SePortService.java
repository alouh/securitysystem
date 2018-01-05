package com.youotech.securitysystem.service;

import com.youotech.securitysystem.bo.SePort;
import com.youotech.securitysystem.datasource.DataSource;

import java.util.List;

/**
 * 异常端口表  暂时不用
 * Created by chenzc  on 2017-11-23.
 */
public interface SePortService extends CommerService<SePort> {

	/**
	 * 批量插入
	 * @param sePortList
	 * @return
	 */
	@DataSource("mysql")
	int batchInsert(List<SePort> sePortList);
}
