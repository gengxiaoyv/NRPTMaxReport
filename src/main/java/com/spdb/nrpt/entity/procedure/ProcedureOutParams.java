package com.spdb.nrpt.entity.procedure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureOutParams implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<OutData> O_REF;
    private String O_FLAG;
}
