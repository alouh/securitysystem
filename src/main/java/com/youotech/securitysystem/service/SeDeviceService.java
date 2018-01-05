package com.youotech.securitysystem.service;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.datasource.DataSource;

import java.util.List;

/**
 * 台账管理
 * Created by chenzc on 2017-11-23.
 */
public interface SeDeviceService extends CommerService<SeDevice> {
    /**
     * 根据主键ID集合，更新实体
     *
     * @param ids
     * @return
     */
    @DataSource("mysql")
    Boolean updateByIds(List<Integer> ids);

	/**
	 * 初始化手动维护设备列表(终端设备,网络设备)
	 */
	@DataSource("mysql")
	List<SeDevice> initDeviceList();
}
