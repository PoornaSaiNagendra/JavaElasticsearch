package com.profile.profilematching.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import com.profile.profilematching.helper.Index;

@Component
@Document(indexName=Index.WORKER_INDEX, shards=1)
@Setting(settingPath = "static/es-settings.json")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerProfile {
	
	@Id
	@Field(type = FieldType.Keyword)
	private String id;
	@Field(type = FieldType.Text)
	private String name;
	@Field(type = FieldType.Text)
	private String skills;
	@GeoPointField
    private GeoPoint location;
	@Field( type = FieldType.Nested)
	private List<Map<String, Object>> embds;

}
