package com.souyue.recruit.dao;

import com.souyue.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-09-23 19:18
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

    List<Recruit> findTop6ByStateOrderByCreatetime(String state);

    List<Recruit> findTopByStateAndEducationOrderByCreatetime(String state, String education);


    // 需求：查询状态为2并以创建日期降序排序，查询前6条记录
    public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);

    public List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);
}
