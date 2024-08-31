package com.mytv.api.livetv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.livetv.model.FavLiveTv;
import com.mytv.api.livetv.model.LiveTv;

@Repository
public interface FavLiveRepository extends JpaRepository<FavLiveTv, Long>{
	
	List<FavLiveTv> findByUser(FirebaseUser u);
	List<FavLiveTv> findByLivetv(LiveTv l);
	Optional<FavLiveTv> findByUserAndLivetv(FirebaseUser u, LiveTv l);
	
}
