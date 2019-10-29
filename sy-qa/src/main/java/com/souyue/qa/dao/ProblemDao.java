package com.souyue.qa.dao;

import com.souyue.qa.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author shkstart
 * @create 2019-09-25 11:37
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
    @Query(value = "SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid=?) ORDER BY replytime DESC", nativeQuery = true)
    public Page<Problem> newList(String labelid, Pageable pageable);

    @Query(value = "SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid=?) ORDER BY reply DESC ", nativeQuery = true)
    public Page<Problem> hotList(String labelid, Pageable pageable);

    @Query(value = "SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid=?) AND reply=0 ORDER BY createtime DESC ", nativeQuery = true)
    public Page<Problem> waitList(String labelid, Pageable pageable);
}
