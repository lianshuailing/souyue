package com.souyue.search.dao;

import com.souyue.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author shkstart
 * @create 2019-09-29 18:25
 */
public interface ArticleDao extends ElasticsearchRepository<Article, String> {

    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
