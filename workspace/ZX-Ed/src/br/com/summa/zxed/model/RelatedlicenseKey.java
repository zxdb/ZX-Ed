package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class RelatedlicenseKey implements Serializable {
	private static final long serialVersionUID = 1L;

	private Entry entry;
	private License license;
}
