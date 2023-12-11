package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,name,country.text,language.text,linkSite,magtype.text,topic.id,linkMask,archiveMask,englishMask")
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

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Country country;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Language language;

    @Column(length=200)
    private String linkSite;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Magtype magtype;

    @ManyToOne
    @ReferenceView("Compact")
    private Topic topic;

    @Column(length=250)
    private String linkMask;

    @Column(length=250)
    private String archiveMask;

    @Column(length=250)
    private String englishMask;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="magazine", cascade=CascadeType.REMOVE)
    @ListProperties("id,dateYear,dateMonth,dateDay,volume,number,special,supplement,coverPage,label.id,label2.id,linkMask,archiveMask,englishMask")
    @XOrderBy("volume,number,dateYear,dateMonth,dateDay,special,supplement,id")
    private Collection<Issue> issues;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
