package com.example.onlinemall.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
	private String url;
	
	public NetworkClient() {
		System.out.println("생성자 호출, url = " + url);
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	private void connect() {
		System.out.println("connect: " + url);
	}
	
	public void call(String message) {
		System.out.println("call: " + url + " message = " + message);
	}
	
	public void disconnect() {
		System.out.println("close: " + url);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("==Open NetworkClient==");
		connect();
		call("초기화 연결 메시지");
		System.out.println("======================");
	}
	
	@PreDestroy
	public void close() {
		System.out.println("==Close NetworkClient==");
		disconnect();
		System.out.println("=======================");
	}
}
