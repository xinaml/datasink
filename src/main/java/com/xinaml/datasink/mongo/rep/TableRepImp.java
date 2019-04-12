package com.xinaml.datasink.mongo.rep;

import com.xinaml.datasink.mongo.base.rep.MongoRepImp;
import com.xinaml.datasink.mongo.entity.Table;
import org.springframework.stereotype.Repository;

@Repository
public class TableRepImp extends MongoRepImp<Table> implements TableRep {
}
