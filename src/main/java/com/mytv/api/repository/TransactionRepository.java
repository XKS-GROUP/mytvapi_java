package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestUser.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
