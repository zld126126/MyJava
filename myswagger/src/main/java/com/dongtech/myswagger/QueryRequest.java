package com.dongtech.myswagger;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
public class QueryRequest implements Serializable {
    public String param1;
    public int page;
    public int pageSize;
}
