package com.github.yjgbg.demo201202.jpa.repo;

import com.github.yjgbg.demo201202.jpa.entity.AppInfo;
import com.github.yjgbg.jpa.plus.specificationSupport.StdRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppInfoRepo extends StdRepository<AppInfo> {
}
