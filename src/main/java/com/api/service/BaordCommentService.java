package com.api.service;

import java.util.HashMap;
import java.util.List;

import com.api.entity.BoardComment;
import com.api.mapper.ApiServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.api.entity.Role;
import com.api.entity.User;
import com.api.repository.BoardCommentRepository;
import com.api.repository.UserRepository;

@Service
public class BaordCommentService {
	
	@Autowired
	BoardCommentRepository boardCommentRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	@Autowired
	ApiServiceMapper mapper;
	
	
	
	public List<HashMap> save(BoardComment param, Authentication auth) {

		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		String userid = userDetails.getUsername();
		User user = userRepo.findByEmail(userid);
		param.setUserId(user.getId());
		BoardComment saveBoard = boardCommentRepo.save(param);
		
		HashMap hParam = new HashMap();
		hParam.put("boardId", saveBoard.getBoardId());
		List<HashMap> result = mapper.findByBoardComment(hParam);
		
		
		return result;
	}
	
	public List<HashMap> delete(BoardComment param, Authentication auth) {

		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		String userid = userDetails.getUsername();
		User user = userRepo.findByEmail(userid);
		param.setUserId(user.getId());
		
		BoardComment board = boardCommentRepo.findById(param.getId()).get();
		HashMap hParam = new HashMap();
		hParam.put("boardId", board.getBoardId());
		if(user.getRole() == Role.ROLE_ADMIN || user.getRole() == Role.ROLE_USER && board.getUserId() == user.getId()){
			boardCommentRepo.delete(param);
			return mapper.findByBoardComment(hParam); 
		}
		
		return null;
	}
	
	

}
