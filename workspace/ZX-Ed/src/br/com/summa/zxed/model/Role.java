package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="entry.id,entry.title,label.id,label.name,roletype.text",defaultOrder="${entry.title} asc, ${label.name} asc, ${roletype.id} asc")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(RoleKey.class)
public class Role {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Label label;

    @Id
    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Roletype roletype;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
