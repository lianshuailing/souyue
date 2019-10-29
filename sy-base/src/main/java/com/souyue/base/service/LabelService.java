package com.souyue.base.service;

import com.souyue.base.dao.LabelDao;
import com.souyue.base.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.souyue.common.utils.IdWorker;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @create 2019-09-21 11:02
 */
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public void save(Label label) {
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void delete(String labelId) {
        labelDao.deleteById(labelId);
    }

    /**
     *
     * @param label
     * @return
     */
    public List<Label> findSearch(Label label){
        // 直接用Specification<?>  内部类就行
        // new Specification<Label>(){} 然后重新Specification的实现 alt+entry
        return labelDao.findAll(new Specification<Label>(){
            /**
             *
             * @param root 根对象，也就是要把条件封装到哪个对象中，相当于  where 类名=Label.getId()
             * @param query 封装的都是查询关键字，如group by，Oder By。。。等等
             * @param cb 用来封装条件对象的，如果直接返回null，表示没有传 查询条件(当然前提是query也没传条件)
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // 放多个 查询条件
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                //把集合中的属性复制到数组中
                parr = list.toArray(parr);

                return cb.and(parr);
                //return null; 返回null 就表示没有传 查询条件(当然)
            }
        });
    }


    public Page<Label> pageQuery(Label label, int page, int size){

        //封装了一个分页对象，在spring-data-jpa中想要实现分页，直接传一个分页对象即可
        Pageable pageable = PageRequest.of(page-1, size);

        return labelDao.findAll(new Specification<Label>(){
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                //把集合中的属性复制到数组中
                parr = list.toArray(parr);
                return cb.and(parr);
            }

        }, pageable);
    }


}
