package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;
import br.com.summa.zxed.sql.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Magazine {

    @Id
    @Column(length=6)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Magazine"))
    @ReadOnly
    private Integer id;

    @Column(length=100)
    @Required
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 1)
    private NullableBoolean isElectronic;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Idiom idiom;

    @Column(length=300)
    private String linkMask;

    @Column(length=300)
    private String archiveMask;

    @ListProperties("id")
    @lombok.ToString.Exclude
    @OneToMany(mappedBy="magazine")
    private Collection<Issue> issues;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
