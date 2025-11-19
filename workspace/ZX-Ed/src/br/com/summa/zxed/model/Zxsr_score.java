package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Zxsr_score {

    @Id
    @Column(length=11)
    private Integer id;

    @ManyToOne(optional=false)
    private Magref magref;

    @Column(length=4)
    private Integer scoreSeq;

    @Column(length=100)
    @Required
    private String category;

    @Column
    @Required
    private Boolean isOverall;

    @Column(length=100)
    private String score;

    @Column(length=1000)
    private String comments;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
