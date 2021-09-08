package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,topictype.text,name,label.id,label.name,comments")
@View(name="Compact", members="id,name")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Topic {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Topic"))
    @ReadOnly
    private Integer id;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Topictype topictype;

    @Column(length=150)
    @Required
    private String name;

    @ManyToOne
    @ReferenceView("Compact")
    private Label label;

    @Column(length=150)
    @Stereotype("MEMO")
    private String comments;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="topic", cascade=CascadeType.REMOVE)
    @ListProperties("referencetype.text,entry.id,entry.title,label.id,label.name,issue.magazine.name,issue.id,issue.fancyName,page,isOriginal")
    @XOrderBy("referencetype.text,issue.magazine.name,issue.id,page")
    private Collection<Magref> magazineReferences;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
