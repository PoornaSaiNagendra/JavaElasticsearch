package com.profile.profilematching.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profile.profilematching.model.WorkerProfile;
import com.profile.profilematching.service.WorkerProfileServiceImpl;

@RestController
@RequestMapping("/worker")
public class WorkerProfileController {
	
	private WorkerProfileServiceImpl service;
	
	@Autowired
	public WorkerProfileController(WorkerProfileServiceImpl service) {
		this.service = service;
	}
	
	@PostMapping("/add")
	public String saveCustomer(@RequestBody WorkerProfile worker) {
		service.save(worker); 
		return "Profile added successfully";
	}
	
	@GetMapping("/findAll")
	public Iterable<WorkerProfile> findAllWorkerProfiles() {
		return service.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Optional<WorkerProfile> findById(@PathVariable String id) {
		return service.findById(id);
	}
	
	@GetMapping("/info")
	public String info() {
		System.out.println("this is a get request to info end point");
		return "This is an index which store the information regarding workers!!!";
	}

}
