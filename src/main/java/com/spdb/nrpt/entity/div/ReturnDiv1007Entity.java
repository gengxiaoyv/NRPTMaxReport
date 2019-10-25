package com.spdb.nrpt.entity.div;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDiv1007Entity {
    private List<Div100107Entity> dataList;
    private String storeMax;
    private String loanMax;
}
