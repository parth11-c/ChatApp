package com.chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chat.entity.Message;
import com.chat.entity.Room;
import com.chat.repo.Repository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/rooms")
public class Controller {
	
	
	private Repository repo; 
	
	@PostMapping("/create/")
	public ResponseEntity<?> createroom(@RequestBody String roomId) {
		
		if(repo.findbyroomId(roomId)!= null) {
			
			return ResponseEntity.badRequest().body("Room already Exists");
			
		}
		
	      Room room =	new Room();
	      room.setRoomId(roomId);
	      Room savedRoom = repo.save(room);
	      
	      
	      return ResponseEntity.status(HttpStatus.CREATED).body(room);
 		 
 	}
	
	
	
	
	
	@GetMapping("/{roomId}")
	public ResponseEntity<?> JoinRoom(@PathVariable String roomId) {
		
		Room room = repo.findbyroomId(roomId); 
		
		if(room == null) {
			
			return ResponseEntity.badRequest().body("Room Not Found");
		}
		
		return ResponseEntity.ok(room);
		
		
	}
	
	
	
	
	@GetMapping("/{roomId}/messages")
	public ResponseEntity<List<Message>> getMessages( @PathVariable String roomId ,
			@RequestParam(value = "page" , defaultValue = "0" , required  = false ) int page ,
			@RequestParam(value = "size" , defaultValue = "20" , required  = false ) int size)
	{
		
		Room room = repo.findbyroomId(roomId);
		
		if(room == null) {
			return ResponseEntity.badRequest().build();
		}
		
		List<Message > message = room.getMessage();
		int start = Math.max(0 , message.size()-(page+1)*size);
		int  end  = Math.min(message.size(),start+size);
		
		
		List<Message> 
		
		return ResponseEntity.ok(message);
		
	}
	
	
	
	
	
	
	
	
	

}
