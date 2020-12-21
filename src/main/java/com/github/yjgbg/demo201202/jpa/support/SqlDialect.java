package com.github.yjgbg.demo201202.jpa.support;

import org.hibernate.dialect.MariaDBDialect;

public class SqlDialect extends MariaDBDialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
    }
}
