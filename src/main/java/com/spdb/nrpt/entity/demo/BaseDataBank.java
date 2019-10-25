package com.spdb.nrpt.entity.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDataBank {
	String id;
	String data_type;
	String data_value;
}
