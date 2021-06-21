package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class MemberKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Tag tag;
    private Entry entry;
}
