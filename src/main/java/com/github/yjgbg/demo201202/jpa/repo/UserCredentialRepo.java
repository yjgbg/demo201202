package com.github.yjgbg.demo201202.jpa.repo;

import com.github.yjgbg.demo201202.jpa.entity.UserCredential;
import com.github.yjgbg.jpa.plus.specificationSupport.StdRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepo extends StdRepository<UserCredential> {
}
