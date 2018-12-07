package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Topictype {

    @Id
    @Column(length=1)
    private String id;

    @Column(length=50)
    @Required
    private String text;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
