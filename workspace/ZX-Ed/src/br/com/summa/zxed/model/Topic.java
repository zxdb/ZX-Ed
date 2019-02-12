package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,topictype.text,label.id,label.name,name,comment")
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

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Topictype topictype;

    @ManyToOne
    @ReferenceView("Compact")
    private Label label;

    @Column(length=150)
    private String name;

    @Column(length=150)
    private String comment;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
