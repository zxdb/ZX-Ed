package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="entry.id,entry.title,entry.originalPublisher,relationtype.text,original.id,original.title,original.originalPublisher")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(RelationKey.class)
public class Relation {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Id
    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Relationtype relationtype;

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry original;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
