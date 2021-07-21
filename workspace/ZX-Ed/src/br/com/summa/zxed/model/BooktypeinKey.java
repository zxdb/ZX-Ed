package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class BooktypeinKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Entry entry;
    private Entry book;
    private Integer installment;
    private Integer volume;
    private Integer page;
}
