package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.db1.model.User;
import com.example.demo.db1.repository.UserRepository;
import com.example.demo.db2.model.Project;
import com.example.demo.db2.repository.ProjectRepository;

@RestController
public class MainController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProjectRepository projectRepo;
	
	@PostConstruct
	public void addData() {
		// add user data to DB1
		List<User> users = new ArrayList<User>();
		users.add(new User(10, "Jon Snow"));
		users.add(new User(20, "Ghost"));
		userRepo.saveAll(users);
		
		// add project data to DB2
		List<Project> projects = new ArrayList<Project>();
		projects.add(new Project(1, "Beyond The Wall"));
		projectRepo.saveAll(projects);
	}
	
	// read data from DB1 - http://localhost:8080/users
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	// read data from DB2 - http://localhost:8080/projects
	@GetMapping("/projects")
	public List<Project> getProjects() {
		return projectRepo.findAll();
	}
}
