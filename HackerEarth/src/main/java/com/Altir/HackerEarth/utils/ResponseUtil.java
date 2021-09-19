package com.Altir.HackerEarth.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static ResponseEntity<Object> errorResponse(Object obj, String message,HttpStatus statusCode)
    {
        Response response=new Response();
        response.setData(obj);
        response.setIsOk(false);
        response.setMessage(message);
        return new ResponseEntity<Object>(response, statusCode);
    }
    public static ResponseEntity<Object> successResponse(Object obj, String message,HttpStatus statusCode)
    {
        Response response=new Response();
        response.setData(obj);
        response.setIsOk(true);
        response.setMessage(message);
        return new ResponseEntity<Object>(response, statusCode);
        
    }
}
