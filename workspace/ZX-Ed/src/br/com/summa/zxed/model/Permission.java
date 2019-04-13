package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="website.name,label.id,label.name,permissiontype.text,text",
     defaultOrder="${website.id}, ${label.name}")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(PermissionKey.class)
public class Permission {
    @Id
    @ManyToOne(optional=false)
    @DescriptionsList
    private Website website;

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Label label;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Permissiontype permissiontype;

    @Column(length=300)
    @Stereotype("MEMO")
    private String text;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
