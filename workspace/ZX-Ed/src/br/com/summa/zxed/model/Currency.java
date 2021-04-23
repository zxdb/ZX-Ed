package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Currency {

    @Id
    @Column(length=3)
    private String id;

    @Column(length=50)
    @Required
    private String name;

    @Column(length=10)
    @Required
    private String symbol;

    @Column
    @Required
    private Boolean prefix;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
