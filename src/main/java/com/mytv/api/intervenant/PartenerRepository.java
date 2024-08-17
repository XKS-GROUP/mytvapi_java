package com.mytv.api.intervenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartenerRepository extends  JpaRepository<Partener, Long>{

}
