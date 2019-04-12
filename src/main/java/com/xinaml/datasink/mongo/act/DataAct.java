package com.xinaml.datasink.mongo.act;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xinaml.datasink.base.BaseAct;
import com.xinaml.datasink.common.exception.ActException;
import com.xinaml.datasink.common.exception.SerException;
import com.xinaml.datasink.common.result.ActResult;
import com.xinaml.datasink.common.utils.BeanUtil;
import com.xinaml.datasink.mongo.entity.Data;
import com.xinaml.datasink.mongo.entity.FieldConf;
import com.xinaml.datasink.mongo.entity.Table;
import com.xinaml.datasink.mongo.ser.TableSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [19-4-12 上午10:51]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
@RestController
@RequestMapping("data")
public class DataAct extends BaseAct {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TableSer tableSer;

    @GetMapping("save")
    public ActResult saveData(@RequestParam String tableId) throws ActException {
        try {
            Table table = tableSer.findById(tableId);
            List<Data> datas = new ArrayList<>();
            for (FieldConf conf : table.getFields()) {
                Data data = new Data(conf.getName(), "7");
                datas.add(data);
            }
            Object o = BeanUtil.createObj(datas);
            mongoTemplate.save(JSON.toJSON(o), "data_" + table.getName());
            return new ActResult(SUCCESS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @GetMapping("list")
    public ActResult list(@RequestParam String tableId) throws ActException {
        try {
            Table table = tableSer.findById(tableId);
            Query query = new Query(Criteria.where("氧气").is("7"));
            List<JSONObject> objects = mongoTemplate.find(query, JSONObject.class, "data_" + table.getName());
            for (Iterator<JSONObject> it = objects.iterator(); it.hasNext(); ) {
                JSONObject obj = it.next();
                obj.remove("_id");
            }
            return new ActResult(objects);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
