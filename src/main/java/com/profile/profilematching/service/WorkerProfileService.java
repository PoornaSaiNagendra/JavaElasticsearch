package com.profile.profilematching.service;

import java.util.Optional;

import com.profile.profilematching.model.WorkerProfile;

public interface WorkerProfileService {
	
	public Optional<WorkerProfile> findById(final String id);
	public Iterable<WorkerProfile> findAll();
	public void save(WorkerProfile worker);
	

}
