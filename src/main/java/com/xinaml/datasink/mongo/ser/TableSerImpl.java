package com.xinaml.datasink.mongo.ser;

import com.xinaml.datasink.common.exception.SerException;
import com.xinaml.datasink.mongo.entity.Table;
import com.xinaml.datasink.mongo.rep.TableRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TableSerImpl implements TableSer {

    @Autowired
    private TableRep tableRep;

    @Override
    public void save(Table table) throws SerException {
        tableRep.save(table);
    }

    @Override
    public List<Table> list() throws SerException {
        return tableRep.find(null);
    }

    @Override
    public Table findById(String id) throws SerException {
        return tableRep.findById(id);
    }

    @Override
    public void update(Table table) throws SerException {
        Query query = Query.query(Criteria.where("id").is(table.getId()));
        Update update = Update.update("fields",table.getFields());
        tableRep.update(query,update);
    }

    @Override
    public void del() throws SerException {
        tableRep.del();
    }
}
