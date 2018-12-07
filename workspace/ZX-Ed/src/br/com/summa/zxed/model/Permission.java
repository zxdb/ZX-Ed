package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

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

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Permissiontype permissiontype;

    @Column(length=300)
    private String text;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
