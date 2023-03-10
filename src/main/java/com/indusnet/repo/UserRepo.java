package com.indusnet.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indusnet.entity.MyUser;

@Repository
public interface UserRepo extends JpaRepository<MyUser, String> {

	MyUser findByUsername(String username);
}
