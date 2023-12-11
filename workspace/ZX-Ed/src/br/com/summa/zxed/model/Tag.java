package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="tagtype.text,id,name,link,comments")
@View(name="Compact", members="tagtype,id,name")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Tag {

    @Id
    @Column(length=11)
    @ReadOnly
    @DefaultValueCalculator(value=NextTagId.class, properties=@PropertyValue(name="tagtypeId", from="tagtype.id"))
    private Integer id;

    @Column(length=100)
    @Required
    private String name;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Tagtype tagtype;

    @Column(length=200)
    private String link;

    @Column(length=1500)
    @Stereotype("MEMO")
    private String comments;

    @ManyToOne
    @ReferenceView("Compact")
    private Tool tool;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="tag", cascade=CascadeType.REMOVE)
    @ListProperties("entry.id,entry.title,entry.originalPublisher,memberSeq,category.text,variant")
    @XOrderBy("category.text,memberSeq,variant,entry.title,entry.id")
    private Collection<Member> members;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
