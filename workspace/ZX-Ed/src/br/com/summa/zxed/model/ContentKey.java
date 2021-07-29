package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class ContentKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Entry container;
    private Integer mediaSeq;
    private String mediaSide;
    private Integer progSeq;
}
