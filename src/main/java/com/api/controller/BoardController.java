package com.api.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.api.entity.Board;
import com.api.entity.BoardComment;
import com.api.service.BaordCommentService;
import com.api.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.Message;
import com.api.dto.StatusEnum;
import com.api.entity.Community;

@RestController
@RequestMapping("/board") //localhost:8080/board
public class BoardController {

	@Autowired
    BoardService boardService;
	
	@Autowired
    BaordCommentService boardCommentService;
	
	
	
	@PostMapping("/save")
	public ResponseEntity<Message> save(@RequestBody Board board, Authentication auth){
		
		Board result = boardService.save(board, auth);
		
		Message message = new Message();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		message.setStatus(StatusEnum.OK);
		
		message.setMessage(null == result ? "error" : "ok");
		message.setData(null == result ? new ArrayList():result);
		
		
		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
	@PostMapping("/list")
	public ResponseEntity<Message> list(@RequestBody Community community){
		
		List<HashMap> result = boardService.list(community);
		
		Message message = new Message();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		message.setStatus(StatusEnum.OK);
		
		message.setMessage(null == result ? "error" : "ok");
		message.setData(null == result ? new ArrayList():result);
		
		
		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<Message> delete(@RequestBody Board param, Authentication auth){
		
		Board result = boardService.delete(param, auth);
		
		Message message = new Message();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		message.setStatus(StatusEnum.OK);
		
		message.setMessage(null == result ? "ok" : "error");
		message.setData(null == result ? new ArrayList():result);
		
		
		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
	
	@PostMapping("/comment/save")
	public ResponseEntity<Message> saveComment(@RequestBody BoardComment param, Authentication auth){
		
		List<HashMap> result = boardCommentService.save(param, auth);
		
		Message message = new Message();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		message.setStatus(StatusEnum.OK);
		
		message.setMessage(null == result ? "error" : "ok");
		message.setData(null == result ? new ArrayList():result);
		
		
		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
	@PostMapping("/comment/delete")
	public ResponseEntity<Message> deleteComment(@RequestBody BoardComment param, Authentication auth){
		
		List<HashMap> result = boardCommentService.delete(param, auth);
		
		Message message = new Message();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		message.setStatus(StatusEnum.OK);
		
		message.setMessage(null == result ? "error" : "ok");
		message.setData(null == result ? new ArrayList():result);
		
		
		return new ResponseEntity<>(message, headers, HttpStatus.OK);
	}
	
	
	
}
