package com.youotech.securitysystem.service.impl;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.bo.SePort;
import com.youotech.securitysystem.dao.SePortMapper;
import com.youotech.securitysystem.service.SePortService;
import com.youotech.securitysystem.utils.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 异常端口表  暂时不用
 * Created by chenzc on 2017-11-23.
 */
@Service
public class SePortServiceImpl implements SePortService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SePortServiceImpl.class);

    @Autowired
    private SePortMapper sePortMapper;
    @Override
    public Integer save(SePort entity) {
        return null;
    }

    @Override
    public List<SePort> selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteByIds(List<Integer> ids) {
        return null;
    }

    @Override
    public Boolean update(SePort entity) {
        return null;
    }

    @Override
    public List<SePort> findEntityByParam(SePort entity) {
        return null;
    }

    @Override
    public List<SeDevice> findEntityByParamFuzzy(SeDevice entity) {
        return null;
    }

    @Override
    public Pager<SePort> findByParamPage(SePort entity, Integer start, Integer limit) {
        return null;
    }

    /**
     * 批量插入端口
     * @param sePortList
     * @return
     */
    @Override
    public int batchInsert(List<SePort> sePortList) {
        LOGGER.info("SePortServiceImpl.batchInsert入参:" + sePortList);
        int i = sePortMapper.batchInsert(sePortList);
        if (i == 0){
            LOGGER.info("SePortServiceImpl.batchInsert插入错误");
        }
        return i;
    }
}
