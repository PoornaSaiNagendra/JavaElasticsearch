package com.profile.profilematching.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profile.profilematching.model.WorkerProfile;
import com.profile.profilematching.repository.WorkerProfileRepository;


@Service
public class WorkerProfileServiceImpl implements WorkerProfileService {
	
	private WorkerProfileRepository repo;
	
	@Autowired
	public WorkerProfileServiceImpl(WorkerProfileRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void save(WorkerProfile worker) {
		repo.save(worker);
	}
	
	@Override
	public Optional<WorkerProfile> findById(final String Id) {
		return repo.findById(Id);
	}

	@Override
	public Iterable<WorkerProfile> findAll() {
		return repo.findAll();
	}
}
