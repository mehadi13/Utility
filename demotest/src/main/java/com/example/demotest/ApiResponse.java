package com.example.demotest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {

    private HttpStatus status;
    private Integer code;
    private Object result;
    private String message;

    public ApiResponse() {
        this(null);
    }

    public ApiResponse(Object result) {
        this.result = result;
        this.message = null;
    }

    public ResponseEntity<ApiResponse> send(HttpStatus status) {
        this.status = status;
        return new ResponseEntity<ApiResponse>(this, status);
    }

    public ResponseEntity<ApiResponse> send(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        return new ResponseEntity<ApiResponse>(this, status);
    }
    
    
    public ResponseEntity<ApiResponse> send(HttpStatus status, Integer code,String message) {
		this.status = status;
		this.code = code;
		this.message = message;
		return new ResponseEntity<ApiResponse>(this, status);
	}

	public Object getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

	public Integer getCode() {
		return code;
	}
    
    
}


