package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class LicensorKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private License license;
	private Label label;
}
