package com.jeffreyahn.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeffreyahn.dojooverflow.models.Answer;
import com.jeffreyahn.dojooverflow.models.Question;
import com.jeffreyahn.dojooverflow.models.Tag;
import com.jeffreyahn.dojooverflow.services.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	private final QuestionService questServe;
	public QuestionController(QuestionService questServe) {
		this.questServe = questServe;
	}
	@RequestMapping("")
	public String index(Model model) {
		List<Question> questions = questServe.allQuestions();
		List<Tag> tags = questServe.allTags();
		model.addAttribute("questions", questions);
		model.addAttribute("tags", tags);
		return "index.jsp";
	}
	@RequestMapping("/new")
	public String addQuestion(@ModelAttribute(value="newQuestion") Question question) {
		return "new.jsp";
	}
	
//	@PostMapping("/new") does the same as request mapping post
//	can do PutMapping and DeleteMapping
	
	@PostMapping("/new")
	public String questionProcess(@Valid @ModelAttribute(value="newQuestion") Question question, BindingResult result, @RequestParam("addTag") String addTags, Model model) {
		if(result.hasErrors()) {
			return "new.jsp";
		}
		List<Tag> tags = new ArrayList<Tag>();
		if(addTags == null) {
			model.addAttribute("errors", "Cannot be empty!");
			return "new.jsp";
		}
		String[] newTags = addTags.split(",");
		if(newTags.length > 3) {
			model.addAttribute("errors", "Cannot have more than 3 tags!");
			System.out.println("Too many tags!");
			return "new.jsp";
		}else {
			for(String item: newTags) {
				String temp = item.toLowerCase().trim();
				List<Tag> checkTag = questServe.findTagBySubject(temp);
				System.out.println(checkTag);
				if(checkTag.isEmpty() == true) {
					Tag newTag = new Tag(temp);
					questServe.newTag(newTag);
					tags.add(newTag);
				} else {
					System.out.println(checkTag.get(0));
					tags.add(checkTag.get(0));
				}
			}
			question.setTags(tags);
			questServe.newQuestion(question);
			return "redirect:/questions";
		}
	}
	
	@RequestMapping("/{id}")
	public String show(@PathVariable(value="id") Long id, @ModelAttribute("addAnswer") Answer answer, Model model) {
		Question question = questServe.findQuestion(id);
		model.addAttribute("question", question);
		return "show.jsp";
	}
	@PostMapping("/{qid}")
	public String addAnswer(@PathVariable(value="qid") Long qid, @Valid @ModelAttribute("addAnswer") Answer answer, BindingResult result, Model model) {
		System.out.println(answer);
		if(result.hasErrors()) {
			Question question = questServe.findQuestion(qid);
			model.addAttribute("question", question);
			return "show.jsp";
		}
		Question question = questServe.findQuestion(qid);
		answer.setQuestion(question);
		questServe.newAnswer(answer);
		return "redirect:/questions/"+qid;
	}
}
