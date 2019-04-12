package com.xinaml.datasink.mongo.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.xinaml.datasink.mongo.types.FieldType;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldConf implements Serializable {
    private String id;
    private String name;
    private String code;
    private FieldType type;
    private Integer seq;

    public FieldConf(){
        this.id = UUID.randomUUID().toString();
    }
    public FieldConf(String name,String code, FieldType type) {
        this.name = name;
        this.code = code;
        this.type = type;
        this.id = UUID.randomUUID().toString();
    }
    public FieldConf(String name,String code) {
        this.name = name;
        this.code = code;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
