package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.sql.*;

@Tab(properties="id,entry.id,entry.title,platform.text,isOfficial,linkSystem")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Port {

    @Id
    @Column(length=11)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Platform platform;

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 1)
    private NullableBoolean isOfficial;

    @Column(length=200)
    private String linkSystem;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
