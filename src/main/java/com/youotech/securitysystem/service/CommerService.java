package com.youotech.securitysystem.service;

import com.youotech.securitysystem.bo.SeDevice;
import com.youotech.securitysystem.utils.Pager;

import java.util.List;

/**
 * 公用Service
 * Created by chenzc on 2017-11-16.
 */
public interface CommerService<T> {
    /**
     * 实体新增，返回主键ID
     *
     * @param entity
     * @return
     */
    Integer save(T entity);

    /**
     * 根据主键查询实体
     *
     * @param id
     * @return
     */
    List<T> selectByPrimaryKey(Integer id);


    /**
     * 根据主键ID，删除实体
     *
     * @param id
     * @return
     */
    Boolean deleteByPrimaryKey(Integer id);

    /**
     * 根据主键ID集合，删除实体
     *
     * @param ids
     * @return
     */
    Boolean deleteByIds(List<Integer> ids);

    /**
     * 根据主键ID修改实体
     *
     * @param entity
     * @return
     */
    Boolean update(T entity);


    /**
     * 根据条件查询实体
     *
     * @param entity
     * @return
     */
    List<T> findEntityByParam(T entity);

    /**
     * 根据条件模糊查询实体
     *
     * @param entity
     * @return
     */
    List<SeDevice> findEntityByParamFuzzy(SeDevice entity);

    /**
     * 分页、模糊、排序查询实体
     *
     * @param entity 查询条件
     * @param start
     * @param limit  一页数据量
     * @return
     */
    Pager<T> findByParamPage(T entity, Integer start, Integer limit);
}
