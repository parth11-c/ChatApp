package com.chat.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chat.entity.Room;

public interface Repository extends MongoRepository<Room, String>{
	
	Room findbyroomId(String roomId);
	
	
	

}
