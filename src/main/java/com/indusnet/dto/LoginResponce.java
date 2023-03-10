package com.indusnet.dto;

import com.indusnet.entity.MyUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponce {

	private String token;
	private MyUser user;
}
