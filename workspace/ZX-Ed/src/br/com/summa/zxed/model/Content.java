package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Tab(properties="container.id,container.title,mediaSeq,mediaSide,progSeq,entry.id,entry.title,label.id,label.name,alias,isOriginal,contenttype.text",
     defaultOrder="${container.id}, ${mediaSeq}, ${mediaSide}, ${progSeq}")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(ContentKey.class)
public class Content {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry container;

    @Id
    @DefaultValueCalculator(value=ZeroIntegerCalculator.class)
    private Integer mediaSeq;

    @Id
    @Column(length=1)
    @DefaultValueCalculator(value=StringCalculator.class, properties=@PropertyValue(name="string", value="0"))
    private String mediaSide;

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
    private Contenttype contenttype;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
