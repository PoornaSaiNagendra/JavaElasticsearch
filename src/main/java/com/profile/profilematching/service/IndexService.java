package com.profile.profilematching.service;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profile.profilematching.helper.Index;
import com.profile.profilematching.helper.Util;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class IndexService {
    private static final List<String> INDICES = List.of(Index.WORKER_INDEX);
    private final RestHighLevelClient client;

    @Autowired
    public IndexService(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void tryToCreateIndices() {
        recreateIndices(false);
    }

    public void recreateIndices(final boolean deleteExisting) {
        final String settings = Util.loadAsString("static/es-settings.json");

        if (settings == null) {
        	System.out.println("Failed to load index settings");
            return;
        }

        for (final String indexName : INDICES) {
            try {
                final boolean indexExists = client
                        .indices()
                        .exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
                if (indexExists) {
                    if (!deleteExisting) {
                        continue;
                    }

                    client.indices().delete(
                            new DeleteIndexRequest(indexName),
                            RequestOptions.DEFAULT
                    );
                }

                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
                createIndexRequest.settings(settings, XContentType.JSON);

                final String mappings = loadMappings(indexName);
                if (mappings != null) {
                    createIndexRequest.mapping(mappings, XContentType.JSON);
                }

                client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            } catch (final Exception e) {
            	System.out.println(e.getMessage());
            }
        }
    }

    private String loadMappings(String indexName) {
        final String mappings = Util.loadAsString("static/mappings/" + indexName + ".json");
        if (mappings == null) {
        	System.out.println("Failed to load mappings for index with name " + indexName);            return null;
        }

        return mappings;
    }
    
    public void deleteIndex(String indexName) {
    	
    	try {
        
	    	final boolean indexExists = client
	                .indices()
	                .exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
	        if (indexExists) {
	
	            client.indices().delete(
	                    new DeleteIndexRequest(indexName),
	                    RequestOptions.DEFAULT
	            );
	        }
    	} catch (final Exception e) {
        	System.out.println(e.getMessage());
        }
    	System.out.println(indexName);
    }
    	
}