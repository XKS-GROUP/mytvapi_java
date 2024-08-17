package com.mytv.api.payment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mytv.api.payment.repository.TransactionRepository;
import com.mytv.api.user.model.Transaction;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TransactionService {
	@Autowired
	private TransactionRepository rep;


	public Transaction create(Transaction g) {

		return rep.save(g);

	}

	public List<Transaction> show() {

		return rep.findAll();
	}
	
	public Page<Transaction> showPage(Pageable p) {

		return rep.findAll(p);
	}
	
	
	
	public Transaction upadte(final Long id, Transaction u) {

		u.setId(id);

		return rep.save(u);
	}

	public Boolean delete(Long id) {

		rep.deleteById(id);

		return null;
	}

	public Optional<Transaction> showById(Long id) {

		return rep.findById(id);

	}


}
