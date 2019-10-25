package com.spdb.nrpt.entity.procedure;

import lombok.Data;

import java.io.Serializable;

@Data
public class SoapInParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schemaName;
    private String procedureName;
    private ProcedureInParams values;
}
