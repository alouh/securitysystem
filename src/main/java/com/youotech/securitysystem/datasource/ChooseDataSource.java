package com.youotech.securitysystem.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author sundongyang
 * @date 2017/11/23 0023
 * @time 15:45
 * @desc 类继承AbstractRoutingDataSource，并实现determineCurrentLookupKey方法
 * @see
 */
public class ChooseDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return HandleDataSource.getDataSource();
    }
}
