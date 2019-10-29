package com.souyue.qa.dao;

import com.souyue.qa.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author shkstart
 * @create 2019-09-29 18:14
 */
public interface ReplyDao extends JpaRepository<Reply,String>, JpaSpecificationExecutor<Reply> {

}
