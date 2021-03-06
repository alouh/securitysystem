package com.youotech.usbmonitor.service;

import com.youotech.usbmonitor.bo.SMPlatform;
import com.youotech.usbmonitor.datasource.DataSource;

import java.util.List;

/**
 * 补丁、软件、漏洞安装详情
 * Created by chenzc on 2017-11-23.
 */
public interface SMPlatformService extends CommerService<SMPlatform> {
    List<SMPlatform> findByParamFuzzy(SMPlatform entity);

    /**
     * 获取所有VRV表中的软件安装信息
     *
     * @return
     */
    List<SMPlatform> selectAllVRV(int start, int increase);

    /**
     * 自动获取SQLServer中VRV软件数据,插入MySQL中
     *
     * @param sMPlatforms
     * @return
     */
    int autoInsert(List<SMPlatform> sMPlatforms);

    /**
     * 使用一条记录,用其中的mac地址查询设备id,然后用查询的设备id替换Mac地址返回完整的记录
     *
     * @param sMPlatform
     * @return
     */
    SMPlatform insteadDeviceID(SMPlatform sMPlatform);

    /**
     * 查询总记录数
     *
     * @return
     */
    int selectCount();

    /**
     * 根据设备ID查询记录
     *
     * @param sdId
     * @return
     */
    /*List<String> selectBySdId(int sdId);*/
	/**
	 * 删除所有SE_INSTALLED中的数据
	 */
	int deleteInstalled();
}
