package com.mytv.api.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.user.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
