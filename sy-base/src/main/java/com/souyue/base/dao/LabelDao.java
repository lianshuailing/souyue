package com.souyue.base.dao;

import com.souyue.base.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author shkstart
 * @create 2019-09-21 10:58
 */
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {
//    // 直接用Specification<?>  内部类就行
//    // new Specification<Label>(){} 然后重新Specification的实现 alt+entry
//    return labelDao.findAll(new Specification<Label>(){
//        /**
//         *
//         * @param root 根对象，也就是要把条件封装到哪个对象中，相当于  where 类名=Label.getId()
//         * @param query 封装的都是查询关键字，如group by，Oder By。。。等等
//         * @param cb 用来封装条件对象的，如果直接返回null，表示没有传 查询条件(当然前提是query也没传条件)
//         * @return
//         */
//        @Override
//        public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//            // 放多个 查询条件
//            List<Predicate> list = new ArrayList<>();
//            if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
//                Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
//                list.add(predicate);
//            }
//            if(label.getState()!=null && !"".equals(label.getState())){
//                Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
//                list.add(predicate);
//            }
//            Predicate[] parr = new Predicate[list.size()];
//            //把集合中的属性复制到数组中
//            parr = list.toArray(parr);
//
//            return cb.and(parr);
//            //return null; 返回null 就表示没有传 查询条件(当然)
//        }
//    });

    // public List<Label> queryLabelsByCountIsLikeAndLabelname 上面那么大段就相当于 该操作，jpa有提供很多这样的实现。
    // jpa可用查询方式： 根据命名规则查询 、
    //                 jpql语句(类似hql，不支持直接用表名像sql那样写、因为它们都是面向对象查询的方式，所以复杂查询不建议用它们，因都是一般先sql测试再转为jpql或hql繁琐) 、
    //                 原生sql查询
}
