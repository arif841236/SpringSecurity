package com.indusnet.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class MyUser {

	@Id
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="user_role",
	joinColumns = { @JoinColumn(name = "user_id")
	},
	inverseJoinColumns = {
			@JoinColumn(name = "role_id")
	}
			)
	private List<Role> roles;

	public MyUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
}
