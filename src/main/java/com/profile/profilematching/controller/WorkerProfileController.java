package com.profile.profilematching.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.profile.profilematching.model.Worker;
import com.profile.profilematching.model.WorkerProfile;
import com.profile.profilematching.service.WorkerProfileServiceImpl;

@RestController
@RequestMapping("api/worker")
public class WorkerProfileController {
	
	private WorkerProfileServiceImpl service;
	
	private WorkerProfile workerProfileObj;
	
	@Autowired
	public WorkerProfileController(WorkerProfileServiceImpl service, WorkerProfile workerProfileObj) {
		this.service = service;
		this.workerProfileObj = workerProfileObj;
	}
	
	
	@PostMapping("/add")
	public String saveWorker(@RequestBody Worker worker) {
		
		service.save(externalAPIRequest(worker)); 
		
		return "Profile added successfully";
	}
	
	public WorkerProfile externalAPIRequest(Worker worker) {
		
		workerProfileObj.setId(worker.getId());
		
		workerProfileObj.setName(worker.getName());
		
		workerProfileObj.setSkills(worker.getSkills());
		
		workerProfileObj.setLocation(worker.getLocation());
		

				
		String url = "http://127.0.0.1:8000/predict?data={queryParameter}";
		
		RestTemplate template = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
		
		Map<String, String> uriVariables = new HashMap<>();
		
		System.out.println(worker.getSkills());
		
		String skill = worker.getSkills();
		
		uriVariables.put("queryParameter", skill);
		
		ResponseEntity<Map> response = template.exchange(url, HttpMethod.POST, requestEntity, Map.class, uriVariables);
		
		
		System.out.println(response.getBody().getClass().getName());
		
		Map<String, List> hm = response.getBody();	
		
		
		for (Map.Entry<String, List> mapElement : hm.entrySet()) {
			
			List value = mapElement.getValue();
			
			System.out.println(value.getClass().getName());
			
			List listOfVectors = new ArrayList();
			
			for (Object mapVal : value) {
				System.out.println(mapVal.getClass().getName());
				
				Map<String, Object> val = new HashMap<String, Object>();
				val.put("vector", mapVal);
				
				listOfVectors.add(val);
				
			}
			
			workerProfileObj.setEmbds(listOfVectors);
			
			System.out.println(listOfVectors.size());
		}
		
		return workerProfileObj;
		
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
