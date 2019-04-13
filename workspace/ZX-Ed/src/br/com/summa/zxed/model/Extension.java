package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Extension {

    @Id
    @Column(length=15)
    private String ext;

    @Column(length=50)
    @Required
    private String text;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
