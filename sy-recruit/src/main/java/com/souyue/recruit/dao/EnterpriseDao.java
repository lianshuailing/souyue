package com.souyue.recruit.dao;

import com.souyue.recruit.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-09-23 19:32
 */
public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {

    //面向对象的查询语句jpql。jpql类似于hql。hql是hibernate内部面向对象的查询语句
    public List<Enterprise> findByIshot(String ishot);
}
