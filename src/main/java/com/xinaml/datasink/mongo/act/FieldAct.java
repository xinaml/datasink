package com.xinaml.datasink.mongo.act;

import com.xinaml.datasink.base.BaseAct;
import com.xinaml.datasink.common.exception.ActException;
import com.xinaml.datasink.common.exception.SerException;
import com.xinaml.datasink.common.result.ActResult;
import com.xinaml.datasink.mongo.entity.FieldConf;
import com.xinaml.datasink.mongo.entity.Table;
import com.xinaml.datasink.mongo.ser.TableSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
public class FieldAct extends BaseAct {
    @Autowired
    private TableSer tableSer;

    @GetMapping("field/save")
    public ActResult saveField( String tableId) throws ActException {
        try {
            Table table = tableSer.findById(tableId);
            FieldConf f1 = new FieldConf("氧气", "o2");
            FieldConf f2 = new FieldConf("二氧化碳", "co2");
            FieldConf f3 = new FieldConf("苯", "bn");
            FieldConf f4 = new FieldConf("苯金", "bnj");
            table.setFields(Arrays.asList(f1, f2, f3,f4));
            tableSer.update(table);
            return new ActResult((Object) table.getId());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    @GetMapping("field/list")
    public ActResult findFieldByTable( String tableId) throws ActException {
        try {
            Table table = tableSer.findById(tableId);
            return new ActResult(table.getFields());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
