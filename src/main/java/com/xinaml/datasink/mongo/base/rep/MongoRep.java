package com.xinaml.datasink.mongo.base.rep;

import com.xinaml.datasink.mongo.base.entity.Page;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface MongoRep<T> {

     List<T> find(Query query);


     T findOne(Query query);

     void update(Query query, Update update);

     void update(Query query, Update update,Class c);

     T save(T entity);


     T findById(String id);


     T findById(String id, String collectionName);


    Page<T> findPage(Page<T> page, Query query);

    Long count(Query query);

    void del();
}
