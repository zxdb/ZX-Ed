package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="entry.id,entry.title,releaseSeq,idiom.text,title,libraryTitle",
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
    private Idiom idiom;

    @Id
    @Column(length=250)
    private String title;

    @Column(length=250)
    @Required
    @DefaultValueCalculator(value=LibTitle.class, properties=@PropertyValue(name="title"))
    private String libraryTitle;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
