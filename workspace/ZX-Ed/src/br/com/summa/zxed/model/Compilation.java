package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(CompilationKey.class)
public class Compilation {
    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry compilation;

    @Id
    private Integer tapeSeq;

    @Id
    @Column(length=1)
    private String tapeSide;

    @Id
    private Integer progSeq;

    @ManyToOne
    @ReferenceView("Compact")
    private Entry entry;

    @ManyToOne
    @ReferenceView("Compact")
    private Label label;

    @Column(length=250)
    private String alias;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Variationtype variationtype;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
