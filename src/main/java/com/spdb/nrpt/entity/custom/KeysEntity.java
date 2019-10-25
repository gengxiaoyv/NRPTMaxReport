package com.spdb.nrpt.entity.custom;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeysEntity {
	private String id;
	private String divid;
	private String keyid;
	private String orgid;
	private Timestamp update_time;
}


