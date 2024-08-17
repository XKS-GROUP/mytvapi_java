package com.mytv.api.live.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.livetv.model.FavLiveTv;
import com.mytv.api.livetv.model.LiveTv;
import com.mytv.api.user.model.User;

@Repository
public interface FavLiveRepository extends JpaRepository<FavLiveTv, Long>{
	
	List<FavLiveTv> findByUser(User u);
	List<FavLiveTv> findByLivetv(LiveTv l);
	Optional<FavLiveTv> findByUserAndLivetv(User u, LiveTv l);
	
}
