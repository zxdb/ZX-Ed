package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(Zxsr_scoreKey.class)
public class Zxsr_score {

    @Id
    @ManyToOne(optional=false)
    private Magref magref;

    @Id
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
