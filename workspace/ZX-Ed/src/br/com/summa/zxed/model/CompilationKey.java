package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class CompilationKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Entry compilation;
    private Integer tapeSeq;
    private String tapeSide;
    private Integer progSeq;
}
