package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;
import br.com.summa.zxed.sql.*;

@Tab(properties="id,name,isElectronic,idiom.text,label.id,label.name,label2.id,label2.name,originalPrice,republishPrice,topic.id,linkMask,archiveMask")
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

    @ManyToOne
    @ReferenceView("Compact")
    private Label label;

    @ManyToOne
    @ReferenceView("Compact")
    private Label label2;

    @Column(length=80)
    private String originalPrice;

    @Column(length=80)
    private String republishPrice;

    @ManyToOne
    @ReferenceView("Compact")
    private Topic topic;

    @Column(length=250)
    private String linkMask;

    @Column(length=250)
    private String archiveMask;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="magazine", cascade=CascadeType.REMOVE)
    @ListProperties("id,dateYear,dateMonth,dateDay,volume,number,special")
    private Collection<Issue> issues;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
