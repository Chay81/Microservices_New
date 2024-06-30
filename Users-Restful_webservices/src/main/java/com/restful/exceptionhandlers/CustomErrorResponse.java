package com.restful.exceptionhandlers;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

	private Date timeStamp;
	private String message;
}
