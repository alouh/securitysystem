package com.youotech.usbmonitor.service;

import com.youotech.usbmonitor.bo.TypeList;
import com.youotech.usbmonitor.datasource.DataSource;

import java.util.List;

/**
 * 台账管理
 * Created by chenzc on 2017-11-23.
 */
public interface TypeListService extends CommerService<TypeList> {
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
	List<TypeList> initDeviceList();
}
