package com.api.service;

import java.util.HashMap;
import java.util.List;

import com.api.mapper.ApiServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.api.entity.Board;
import com.api.entity.Community;
import com.api.entity.Role;
import com.api.entity.User;
import com.api.repository.BoardCommentRepository;
import com.api.repository.BoardRepository;
import com.api.repository.UserRepository;

@Service
public class BoardService {
	
	
	
	@Autowired
	BoardRepository boardRepo;
	
	@Autowired
	BoardCommentRepository boardCommentRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ApiServiceMapper mapper;
	
	
	
	public Board save(Board board, Authentication auth) {
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		String userid = userDetails.getUsername();
		User user = userRepo.findByEmail(userid);
		
		board.setUserId(user.getId());
		
		//수정
		if(null!=board.getId()) {
			Board updateBoard = boardRepo.findById(board.getId()).get();
			updateBoard.setTitle(board.getTitle());
			updateBoard.setContent(board.getContent());
			return boardRepo.save(board);
			
		}
		//저장
		return boardRepo.save(board);
	}
	
	
	public List<HashMap> list(Community community) {
		
		HashMap param = new HashMap();
		param.put("communityId", community.getId());
		
		return mapper.findByCommunityBoard(param);
	}
	
	public Board delete(Board board, Authentication auth) {
		
	//자신의글 or 관리자일때
		
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		String userid = userDetails.getUsername();
		User user = userRepo.findByEmail(userid);
		board.setUserId(user.getId());
		Board deleteBoard = boardRepo.findById(board.getId()).get();
		
		if(user.getRole() == Role.ROLE_ADMIN || user.getId() == deleteBoard.getUserId()) {
			boardRepo.delete(board);
			return null;
		}else {
			return board; 
		}

	}

}
