package com.indusnet.exception;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyError {

	private HttpStatus status;
	private String discription;
	private String message;
	private LocalDate date;
}
