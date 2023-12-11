package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="author.entry.id,author.entry.title,author.authorSeq,author.label.id,author.label.name,roletype.text",
     defaultOrder="${author.entry.title}, ${author.authorSeq}, ${roletype.id}")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(RoleKey.class)
public class Role {

    @Id
    @ManyToOne(optional=false)
    private Author author;

    @Id
    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Roletype roletype;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
