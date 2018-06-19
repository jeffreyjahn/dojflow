package com.jeffreyahn.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jeffreyahn.dojooverflow.models.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long>{
	List<Answer> findAll();
	
}
