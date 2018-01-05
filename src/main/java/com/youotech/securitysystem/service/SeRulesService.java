package com.youotech.securitysystem.service;

import com.youotech.securitysystem.bo.SeRules;
import com.youotech.securitysystem.datasource.DataSource;

import java.util.List;

/**
 * 安全审计规则
 * Created by chenzc on 2017-11-23.
 */
public interface SeRulesService extends CommerService<SeRules> {
	//根据设备信息查找规则
	@DataSource("mysql")
	List<SeRules> findByDevice();
}
