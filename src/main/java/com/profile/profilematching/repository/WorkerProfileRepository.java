package com.profile.profilematching.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.profile.profilematching.model.WorkerProfile;


public interface WorkerProfileRepository extends ElasticsearchRepository<WorkerProfile, String> {

}
