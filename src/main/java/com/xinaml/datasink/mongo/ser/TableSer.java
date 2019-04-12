package com.xinaml.datasink.mongo.ser;

import com.xinaml.datasink.common.exception.SerException;
import com.xinaml.datasink.mongo.entity.Table;

import java.util.List;


public interface TableSer {
    default void save(Table table) throws SerException {

    }

    default List<Table> list() throws SerException {
        return null;
    }

    default Table findById(String id) throws SerException {
        return null;
    }

    default void update(Table table) throws SerException{

    }
    default void del() throws SerException{

    }
}
