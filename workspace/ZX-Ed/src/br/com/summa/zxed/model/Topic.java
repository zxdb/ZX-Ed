package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,topictype.text,label.id,label.name,name,comments")
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

    @ManyToOne
    @ReferenceView("Compact")
    private Label label;

    @Column(length=150)
    @Required
    private String name;

    @Column(length=150)
    @Stereotype("MEMO")
    private String comments;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
