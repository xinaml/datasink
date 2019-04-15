package com.xinaml.datasink.mongo.act;

import com.xinaml.datasink.base.BaseAct;
import com.xinaml.datasink.common.exception.ActException;
import com.xinaml.datasink.common.exception.SerException;
import com.xinaml.datasink.common.result.ActResult;
import com.xinaml.datasink.mongo.entity.Table;
import com.xinaml.datasink.mongo.ser.TableSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("table")
public class TableAct extends BaseAct {
    @Autowired
    private TableSer tableSer;


    @GetMapping("save")
    public ActResult saveTable() throws ActException {
        try {
            Table table = new Table();
            table.setId(UUID.randomUUID().toString());
            table.setName("monitor_data");
            table.setCreateTime(LocalDateTime.now().plusHours(8));
            tableSer.save(table);
            return new ActResult((Object) table.getId());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    @GetMapping("list")
    public ActResult listTable() throws ActException {
        try {
            List<Table> tables = tableSer.list();
            return new ActResult(tables);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @GetMapping("del")
    public ActResult del() throws ActException {
        try {
             tableSer.del();
            return new ActResult(SUCCESS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
