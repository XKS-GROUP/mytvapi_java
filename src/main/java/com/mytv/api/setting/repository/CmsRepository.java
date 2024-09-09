package com.mytv.api.setting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.setting.model.Cms;

public interface CmsRepository extends JpaRepository<Cms, Long> {

}
