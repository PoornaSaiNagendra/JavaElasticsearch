package com.profile.profilematching.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import com.profile.profilematching.helper.Index;


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

}
