package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,name,language.text,linkSite,label.id,label.name,label2.id,label2.name,originalPrice,republishPrice,topic.id,linkMask,archiveMask")
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

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Language language;

    @Column(length=200)
    private String linkSite;

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
    @ListProperties("id,dateYear,dateMonth,dateDay,volume,number,special,supplement,coverPage,linkMask,archiveMask")
    private Collection<Issue> issues;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
