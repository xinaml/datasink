package com.xinaml.datasink.mongo.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.xinaml.datasink.mongo.types.FieldType;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data implements Serializable {
    private String name;
    private Object val;
    private FieldType type;

    public Data() {

    }

    public Data(String name, Object val, FieldType type) {
        this.name = name;
        this.val = val;
        this.type = type;
    }

    public Data(String name, Object val) {
        this.name = name;
        this.val = val;
        this.type = FieldType.STRING;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

}
