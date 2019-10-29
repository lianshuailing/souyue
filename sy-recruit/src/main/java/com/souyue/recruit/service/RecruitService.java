package com.souyue.recruit.service;

import com.souyue.common.pojo.PageResult;
import com.souyue.common.utils.IdWorker;
import com.souyue.recruit.dao.RecruitDao;
import com.souyue.recruit.entity.Recruit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author shkstart
 * @create 2019-09-23 19:18
 */
@Service
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao;
    @Autowired
    private IdWorker idWorker;


    public List<Recruit> findAll() {
        List<Recruit> recruitList = recruitDao.findAll();
        return recruitList;
    }

    public void save(Recruit recruit) {
        recruit.setId(idWorker.nextId() + "");
        recruitDao.save(recruit);
    }

    public Recruit findById(String recruitId) {
        Optional<Recruit> recruit = recruitDao.findById(recruitId);
        return recruit.get();
//        return recruitDao.findById(recruitId).get();
    }


    public Recruit update(Recruit recruit) {
        Recruit save = recruitDao.save(recruit);
        return save;
    }

    public void delete(String recruitId) {
        recruitDao.deleteById(recruitId);
    }

    /**
     *  条件查询----->Specification
     * @param searchMap
     * @return
     */
    public List<Recruit> search(Map searchMap) {
//        Example<Recruit> example = new Example<Recruit>() {
//            @Override
//            public Recruit getProbe() {
//                return null;
//            }
//
//            @Override
//            public ExampleMatcher getMatcher() {
//                return null;
//            }
//        };

        Specification<Recruit> specification = createSpecification(searchMap);
        List<Recruit> recruits = recruitDao.findAll(specification);
        return recruits;
    }

    /**
     * 抽取查询条件
     * @param searchMap
     * @return
     */
    private Specification<Recruit> createSpecification(Map searchMap) {

        return new Specification<Recruit>() {
            List<Predicate> predicateList = new ArrayList<>();

            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // id
                if(StringUtils.isNotBlank(searchMap.get("id").toString())) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%"+searchMap.get("id").toString()+"%"));
                }
                // jobname
                if(StringUtils.isNotBlank(searchMap.get("jobname").toString())){
                    predicateList.add(cb.like(root.get("jobname").as(String.class), "%"+searchMap.get("jobname").toString()+"%"));
                }
                // salary
                if(StringUtils.isNotBlank(searchMap.get("salary").toString())) {
                    predicateList.add(cb.like(root.get("salary").as(String.class), "%"+searchMap.get("salary").toString()+"%"));
                }
                // experience
                if (StringUtils.isNotBlank(searchMap.get("experience").toString())) {
                    predicateList.add(cb.like(root.get("experience").as(String.class), "%"+searchMap.get("experience").toString()+"%"));
                }
                // education
                if (StringUtils.isNotBlank(searchMap.get("education").toString())) {
                    predicateList.add(cb.like(root.get("education").as(String.class), "%"+searchMap.get("education").toString()+"%"));
                }
                // type
                if (StringUtils.isNotBlank(searchMap.get("type").toString())) {
                    predicateList.add(cb.like(root.get("type").as(String.class), "%"+searchMap.get("type").toString()+"%"));
                }
                // address
                if (StringUtils.isNotBlank(searchMap.get("address").toString())) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%"+searchMap.get("address").toString()+"%"));
                }
                // eid
                if (StringUtils.isNotBlank(searchMap.get("eid").toString())) {
                    predicateList.add(cb.like(root.get("eid").as(String.class), "%"+searchMap.get("eid").toString()+"%"));
                }
                // state
                if (StringUtils.isNotBlank(searchMap.get("state").toString())) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%"+searchMap.get("state").toString()+"%"));
                }
                // url
                if (StringUtils.isNotBlank(searchMap.get("url").toString())) {
                    predicateList.add(cb.like(root.get("url").as(String.class), "%"+searchMap.get("url").toString()+"%"));
                }
                // label
                if (StringUtils.isNotBlank(searchMap.get("label").toString())) {
                    predicateList.add(cb.like(root.get("label").as(String.class), "%"+searchMap.get("label").toString()+"%"));
                }
                // content1
                if (StringUtils.isNotBlank(searchMap.get("content1").toString())) {
                    predicateList.add(cb.like(root.get("content1").as(String.class), "%"+searchMap.get("content1").toString()+"%"));
                }
                // content2
                if (StringUtils.isNotBlank(searchMap.get("content2").toString())) {
                    predicateList.add(cb.like(root.get("content2").as(String.class), "%"+searchMap.get("content2").toString()+"%"));
                }
//                Predicate[] arr = new Predicate[predicateList.size()];
////                return cb.and(predicateList.toArray(arr));
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    /**
     *  条件查询--带分页
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Recruit> searchByPage(Map searchMap, int page, int size) {
        // 查询条件
        Specification<Recruit> specification = createSpecification(searchMap);
        // 分页
        Pageable pageable = PageRequest.of(page - 1, size);
        // 分页结果
        Page<Recruit> recruits = recruitDao.findAll(specification, pageable);

//        recruits.getTotalElements();// 元素总数
//        recruits.getTotalPages();// 总页数
        return new PageResult<Recruit>(recruits.getTotalElements(), recruits.getContent());
    }

    /**
     *  条件查询---根据命名规范查询,例如：findTopBy...  findAllBy...
     * @return
     */
    public List<Recruit> findTop6ByStateOrderByCreatetime() {
        // 假数据
        String state = "2";
        String education = "本科";
//        return recruitDao.findTop6ByStateOrderByCreatetime(state);
        return recruitDao.findTopByStateAndEducationOrderByCreatetime(state, education);
    }
}
