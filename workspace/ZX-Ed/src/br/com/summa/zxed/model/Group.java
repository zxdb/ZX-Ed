package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="grouptype.text,id,name,link,comments")
@View(name="Compact", members="grouptype,id,name")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Group {

    @Id
    @Column(length=11)
    @ReadOnly
    @DefaultValueCalculator(value=NextGroupId.class, properties=@PropertyValue(name="grouptypeId", from="grouptype.id"))
    private Integer id;

    @Column(length=100)
    @Required
    private String name;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Grouptype grouptype;

    @Column(length=200)
    private String link;

    @Column
    @Stereotype("MEMO")
    private String comments;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="group", cascade=CascadeType.REMOVE)
    @ListProperties("entry.id,entry.title,entry.firstPublisher,seriesSeq")
    @XOrderBy("seriesSeq,entry.libraryTitle,entry.id")
    private Collection<Member> members;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
