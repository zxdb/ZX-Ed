package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class RelationKey implements Serializable {
	private static final long serialVersionUID = 1L;

	private Entry entry;
    private Relationtype relationtype;
	private Entry original;
}
