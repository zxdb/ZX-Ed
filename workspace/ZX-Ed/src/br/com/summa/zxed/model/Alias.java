package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Tab(properties="entry.id,entry.title,releaseSeq,language.text,title",
     defaultOrder="${entry.id}, ${releaseSeq}")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(AliasKey.class)
public class Alias {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Id
    @Column(length=6)
    @DefaultValueCalculator(value=ZeroIntegerCalculator.class)
    private Integer releaseSeq;

    @Id
    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Language language;

    @Id
    @Column(length=250)
    private String title;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
