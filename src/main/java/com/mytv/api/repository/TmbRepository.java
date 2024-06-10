package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.util.TmdbSetting;

@Repository
public interface TmbRepository extends JpaRepository<TmdbSetting, Long> {

}
