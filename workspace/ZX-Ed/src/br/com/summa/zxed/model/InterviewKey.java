package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class InterviewKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Label label;
    private String link;
}
