package com.youotech.securitysystem.dao;

import com.youotech.securitysystem.bo.SeInstalled;

import java.util.List;
import java.util.Map;

public interface SeInstalledMapper {
    int deleteByPrimaryKey(Integer siId);

    int insert(SeInstalled record);

    int insertSelective(SeInstalled record);

    SeInstalled selectByPrimaryKey(Integer siId);

    int updateByPrimaryKeySelective(SeInstalled record);

    int updateByPrimaryKey(SeInstalled record);

    List<SeInstalled> findByParam(SeInstalled entity);

    List<SeInstalled> findByParamFuzzy(SeInstalled entity);

    List<SeInstalled> findByParamPage(Map<String, Object> map);

    int deleteByIds(List<Integer> userIds);

    List<SeInstalled> selectAllVRV(int start, int increase);

    int autoInsert(List<SeInstalled> seInstalleds);

    SeInstalled insteadDeviceID(SeInstalled seInstalled);

    int selectCount();

    List<String> selectBySdId(int sdId);

    int deleteInstalled();
}