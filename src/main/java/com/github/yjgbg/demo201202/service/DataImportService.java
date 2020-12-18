package com.github.yjgbg.demo201202.service;

public interface DataImportService {
    void importCsvFile(String sqlFormat,String csvContent);
    String findSqlFormat(String key);
    void removeDataInsertionConfigByKey(String key);
    default void importCsvFileByKey(String key,String csvContent) {
        importCsvFile(findSqlFormat(key),csvContent);
    }
}
