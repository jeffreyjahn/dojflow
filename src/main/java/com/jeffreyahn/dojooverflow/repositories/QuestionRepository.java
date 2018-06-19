package com.jeffreyahn.dojooverflow.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeffreyahn.dojooverflow.models.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long>{
	List<Question> findAll();
	
	Optional<Question> findById(Long id);
//	@Query("SELECT q.question, GROUP_CONCAT(t.subject) Question q JOIN q.tags t GROUP BY q.question")
//	List<Object[]> allQuestionsAndTags();
//	
	
	@Query("SELECT q.question, COUNT(t.id) From Question q JOIN q.tags t WHERE q.id=?1")
	List<Object[]> getOneQuestion(Long id);
	
}
