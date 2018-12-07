package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class ReleaseKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Entry entry;
	private Integer releaseSeq;
}
