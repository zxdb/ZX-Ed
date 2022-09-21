package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,tooltype.text,platform.text,title,authors,link,latestDate,comments")
@View(name="Compact", members="id,title")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Tool {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Tool"))
    @ReadOnly
    private Integer id;

    @Column(length=250)
    @Required
    private String title;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Tooltype tooltype;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Platform platform;

    @Column(length=250)
    private String authors;

    @Column(length=200)
    private String link;

    @Column(length=100)
    private String latestDate;

    @Column(length=500)
    @Stereotype("MEMO")
    private String comments;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="tool", cascade=CascadeType.REMOVE)
    @ListProperties("tagtype.text,id,name")
    @XOrderBy("name")
    private Collection<Tag> tags;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
