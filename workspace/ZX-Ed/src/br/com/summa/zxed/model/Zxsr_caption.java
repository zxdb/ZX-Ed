package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(Zxsr_captionKey.class)
public class Zxsr_caption {

    @Id
    @ManyToOne(optional=false)
    private Magref magref;

    @Id
    @Column(length=6)
    private Integer captionSeq;

    @Column
    @Stereotype("MEMO")
    private String text;

    @Column
    @Required
    private Boolean isBanner;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
