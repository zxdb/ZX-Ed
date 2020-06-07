package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class MagreffeatKey implements Serializable {
	private static final long serialVersionUID = 1L;

	private Magref magref;
	private Feature feature;
}
