package com.xinaml.datasink.mongo.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.xinaml.datasink.mongo.types.FieldType;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data implements Serializable {
    private String name;
    private String val;
    private FieldType type;

    public Data() {

    }

    public Data(String name, String val, FieldType type) {
        this.name = name;
        this.val = val;
        this.type = type;
    }

    public Data(String name, String val) {
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

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

}
