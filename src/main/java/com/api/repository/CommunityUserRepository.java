package com.api.repository;

import com.api.entity.CommunityUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityUserRepository extends JpaRepository<CommunityUser, Long>{

	boolean existsByCommunityIdAndUserId(Long cid, Long uid);
}
