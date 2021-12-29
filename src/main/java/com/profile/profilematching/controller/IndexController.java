package com.profile.profilematching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.profile.profilematching.service.IndexService;

@RestController
@RequestMapping("/api/index")
public class IndexController {
    private final IndexService service;

    @Autowired
    public IndexController(IndexService service) {
        this.service = service;
    }


    @PostMapping("/recreate")
    public String recreateAllIndices() {
        service.recreateIndices(true);
        return "Index recreated successfully";
    }
    
    @PostMapping("/delete")
    public String deleteIndexName(@RequestParam(name = "indexName") String indexName) {
    	service.deleteIndex(indexName);
    	return "Successfully Deleted index with name " + indexName;
    }
}