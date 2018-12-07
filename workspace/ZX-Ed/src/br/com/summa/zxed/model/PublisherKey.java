package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class PublisherKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Entry entry;
	private Integer releaseSeq;
	private Label label;
}
