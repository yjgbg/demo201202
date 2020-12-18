package com.github.yjgbg.demo201202.jpa.entity;

import com.github.yjgbg.jpa.plus.entitySupport.ActiveEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class DataInsertionConfig implements ActiveEntity<DataInsertionConfig> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String key; // 一般使用tableName
    private String sqlFormat;
}
