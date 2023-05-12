package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Community;

public interface CommunityRepository extends JpaRepository<Community, Long>{

}
