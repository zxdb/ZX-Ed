package br.com.summa.zxed.model;

import java.io.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
public class PermissionKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Website website;
    private Label label;
}
