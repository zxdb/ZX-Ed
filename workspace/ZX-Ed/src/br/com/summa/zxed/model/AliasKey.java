package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class AliasKey implements Serializable {
	private static final long serialVersionUID = 1L;

	private Entry entry;
    private Integer releaseSeq;
    private Idiom idiom;
    private String title;
}
