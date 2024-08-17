package com.mytv.api.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PubliciteCatRepository extends  JpaRepository<PubliciteCat, Long>{


}
