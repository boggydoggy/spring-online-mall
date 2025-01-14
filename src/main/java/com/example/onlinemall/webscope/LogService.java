package com.example.onlinemall.webscope;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogService {
	private final MyLogger myLogger;
	
	public void logic(String id) {
		myLogger.log("Service Id = " + id);
	}
}
