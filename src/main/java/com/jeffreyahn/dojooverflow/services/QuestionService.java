package com.jeffreyahn.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jeffreyahn.dojooverflow.models.Answer;
import com.jeffreyahn.dojooverflow.models.Question;
import com.jeffreyahn.dojooverflow.models.Tag;
import com.jeffreyahn.dojooverflow.repositories.AnswerRepository;
import com.jeffreyahn.dojooverflow.repositories.QuestionRepository;
import com.jeffreyahn.dojooverflow.repositories.TagRepository;

@Service
public class QuestionService {
	private final QuestionRepository quesoRepo;
	private final TagRepository tagRepo;
	private final AnswerRepository ansRepo;
	public QuestionService(QuestionRepository quesoRepo, TagRepository tagRepo,AnswerRepository ansRepo) {
		this.quesoRepo = quesoRepo;
		this.tagRepo = tagRepo;
		this.ansRepo = ansRepo;
	}
	public List<Question> allQuestions(){
		return quesoRepo.findAll();
	}
	public Question newQuestion(Question question) {
		return quesoRepo.save(question);
	}
	public List<Tag> allTags(){
		return tagRepo.findAll();
	}
	public Tag newTag(Tag tag) {
		return tagRepo.save(tag);
	}
	public List<Tag> findTagBySubject(String subject) {
		return tagRepo.findBySubject(subject);
	}
	public Question findQuestion(Long id) {
		Optional<Question> optQuest = quesoRepo.findById(id);
		if(optQuest.isPresent()) {
			return optQuest.get();
		}else {
			return null;
		}
	}
	public Answer newAnswer(Answer answer) {
		return ansRepo.save(answer);
	}
	
}
