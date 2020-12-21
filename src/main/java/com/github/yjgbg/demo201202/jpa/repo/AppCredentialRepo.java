package com.github.yjgbg.demo201202.jpa.repo;

import com.github.yjgbg.demo201202.jpa.entity.AppCredential;
import com.github.yjgbg.jpa.plus.specificationSupport.StdRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCredentialRepo extends StdRepository<AppCredential> {
}
