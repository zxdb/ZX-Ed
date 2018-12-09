package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class RoleKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Author author;
	private Roletype roletype;
}
