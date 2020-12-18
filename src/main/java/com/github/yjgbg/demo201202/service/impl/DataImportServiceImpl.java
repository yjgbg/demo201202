package com.github.yjgbg.demo201202.service.impl;

import com.github.yjgbg.demo201202.exceptions.BizException;
import com.github.yjgbg.demo201202.jpa.entity.DataInsertionConfig;
import com.github.yjgbg.demo201202.jpa.repo.DataInsertionConfigRepo;
import com.github.yjgbg.demo201202.service.DataImportService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class DataImportServiceImpl implements DataImportService {
    private static final Integer BATCH_SIZE = 1000;
    private static final String SEPARATOR = ",";
    private final JdbcTemplate jdbcTemplate;
    private final DataInsertionConfigRepo dataInsertionConfigRepo;

    @Override
    public void importCsvFile(String sqlFormat, String csvContent) {
        val batchSqlBuilder = new StringBuilder();
        val atomInteger = new AtomicInteger(0);
        csvContent.lines().map(DataImportServiceImpl::splitBySeparator)
                .map(array -> String.format(sqlFormat, (Object[]) array))
                .forEach(sql -> {
                    batchSqlBuilder.append(sql);
                    val batchSize = atomInteger.addAndGet(1);
                    if (batchSize < BATCH_SIZE) return;
                    jdbcTemplate.execute(batchSqlBuilder.toString());
                    atomInteger.set(0);
                    batchSqlBuilder.replace(0, batchSqlBuilder.length(), "");
                });
    }

    private Optional<DataInsertionConfig> findByKey(String key) {
        return dataInsertionConfigRepo.spec()
                .eq("key", key)
                .findOne();
    }

    @Override
    public String findSqlFormat(String key) {
        return findByKey(key).map(DataInsertionConfig::getSqlFormat)
                .orElseThrow(new BizException("没有找到key为%s的sqlFormat", key));
    }

    @Override
    public void removeDataInsertionConfigByKey(String key) {
        findByKey(key).orElseThrow(new BizException("没有找到key为%s的sqlFormat", key))
                .remove();
    }


    private static String[] splitBySeparator(String line) {
        if (line == null) return new String[0];
        val words = line.split(SEPARATOR);
        return Arrays.stream(words)
                .map(x -> (x.startsWith("\"") && x.endsWith("\""))
                        ? x.substring(1, x.length() - 1)
                        : x)
                .toArray(String[]::new);
    }

    @PostConstruct
    public void postConstructor() {
        val key = "dataInsertionConfig";
        val exist = dataInsertionConfigRepo.spec()
                .eq("key", key)
                .exist();
        if (exist) return;
        new DataInsertionConfig()
                .setKey(key)
                .setSqlFormat("insert into data_insertion_config(`key`,`sql_format`) values ('%s','%s')");
    }

}
