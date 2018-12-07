package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@View(name="Compact", members="id,name")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Label {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Label"))
    @ReadOnly
    private Integer id;

    @Column(length=100)
    @Required
    private String name;

    @Column(length=100)
    private String spotName;

    @Column(length=150)
    private String spotComment;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Country country;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Country country2;

    @ManyToOne(fetch=FetchType.LAZY)
    @ReferenceView("Compact")
    private Label from;

    @ManyToOne(fetch=FetchType.LAZY)
    @ReferenceView("Compact")
    private Label owner;

    @Column
    @Required
    private Boolean wasRenamed;

    @Column(length=200)
    private String deceased;

    @Column(length=200)
    private String linkWikipedia;

    @Column(length=200)
    private String linkSite;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Labeltype labeltype;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
