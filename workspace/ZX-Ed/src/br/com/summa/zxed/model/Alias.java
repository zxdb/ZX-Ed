package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,entry.id,entry.title,releaseSeq,idiom.text,title,libraryTitle",
     defaultOrder="${entry.id}, ${releaseSeq}")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Alias {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Alias"))
    @ReadOnly
    private Integer id;

    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Column(length=6)
    @Required
    @DefaultValueCalculator(value=ZeroIntegerCalculator.class)
    private Integer releaseSeq;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Idiom idiom;

    @Column(length=250)
    @Required
    private String title;

    @Column(length=250)
    @Required
    @DefaultValueCalculator(value=LibTitle.class, properties=@PropertyValue(name="title"))
    private String libraryTitle;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
