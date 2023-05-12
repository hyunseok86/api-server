package com.api.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiServiceMapper {
	public List<HashMap> findByUserList(HashMap Hparam);
	
	public List<HashMap> findByMyCommunity(HashMap map);
	
	public List<HashMap> findByCommunityBoard(HashMap map);
	
	public List<HashMap> findByBoardComment(HashMap map);
	
	
}
