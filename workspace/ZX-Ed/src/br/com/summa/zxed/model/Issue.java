package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import br.com.summa.zxed.calc.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Issue {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Issue"))
    @ReadOnly
    private Integer id;

    @ManyToOne(optional=false)
    @DescriptionsList
    private Magazine magazine;

    @Column(length=4)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.dateYear"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer dateYear;

    @Column(length=2)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.dateMonth"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer dateMonth;

    @Column(length=2)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.dateDay"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer dateDay;

    @Column(length=6)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.volume"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer volume;

    @Column(length=6)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.number"),@PropertyValue(name="parentId", from="parent.id")})
    private Integer number;

    @Column(length=100)
    @DefaultValueCalculator(value=ParentByPassCalculator.class, properties={@PropertyValue(name="source", from="parent.special"),@PropertyValue(name="parentId", from="parent.id")})
    private String special;

    @Column(length=100)
    private String supplement;

    @Column(length=6)
    @Required
    @DefaultValueCalculator(value=IntegerCalculator.class, properties={ @PropertyValue(name="value", value="1")})
    private Integer coverPage;

    @ManyToOne
    private Issue parent;

    @Column(length=250)
    private String linkMask;

    @Column(length=250)
    private String archiveMask;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
