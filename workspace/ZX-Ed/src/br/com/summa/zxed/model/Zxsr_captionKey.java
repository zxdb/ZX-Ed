package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class Zxsr_captionKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Magref magref;
    private Integer captionSeq;
}
