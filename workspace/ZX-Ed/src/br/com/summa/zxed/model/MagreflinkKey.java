package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class MagreflinkKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Magref magref;
    private String link;
}
