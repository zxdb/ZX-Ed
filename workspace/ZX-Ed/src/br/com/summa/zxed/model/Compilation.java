package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Tab(properties="compilation.id,compilation.title,tapeSeq,tapeSide,progSeq,entry.id,entry.title,label.id,label.name,alias,isOriginal,variationtype.text",
     defaultOrder="${compilation.id}, ${tapeSeq}, ${tapeSide}, ${progSeq}")
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
    @DefaultValueCalculator(value=ZeroIntegerCalculator.class)
    private Integer tapeSeq;

    @Id
    @Column(length=1)
    @DefaultValueCalculator(value=StringCalculator.class, properties=@PropertyValue(name="string", value="0"))
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

    @Column
    @Required
    private Boolean isOriginal;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Variationtype variationtype;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
