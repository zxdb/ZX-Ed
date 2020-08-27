package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="magref.id,magref.referencetype.text,magref.entry.id,magref.entry.title,magref.label.id,magref.label.name,magref.topic.id,magref.topic.name,magref.issue.id,magref.page,feature.id,feature.name")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(MagreffeatKey.class)
public class Magreffeat {

    @Id
    @ManyToOne(optional=false)
    private Magref magref;

    @Id
    @ManyToOne(optional=false)
    private Feature feature;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
