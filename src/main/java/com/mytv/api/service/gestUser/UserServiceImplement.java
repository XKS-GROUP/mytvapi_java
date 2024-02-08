package com.mytv.api.service.gestUser;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytv.api.model.gestUser.User;
import com.mytv.api.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImplement implements UserService {
	
	
	@Autowired
	private UserRepository userRep;

	@Override
	public User create(User u) {
		
		return userRep.save(u);
	}

	@Override
	public List<User> show() {
		
		return userRep.findAll();
	}

	@Override
	public User upadte(final Long id, User u) {
		
		User old = userRep.findById(id).get();
		
		old = u;
		//userRep.findById(id).map(p->)
		old.setIdUser(id);
		
		return userRep.save(old);
	}

	@Override
	public Boolean delete(Long id) {
		
		userRep.deleteById(id);
		
		return null;
	}


	@Override
	public Optional<User> showById(final Long id) {
		
		return userRep.findById(id);
	}

}
