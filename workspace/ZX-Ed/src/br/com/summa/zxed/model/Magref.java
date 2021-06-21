package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,referencetype.text,entry.id,entry.title,label.id,label.name,topic.id,topic.name,issue.id,page,isOriginal")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Magref {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Magref"))
    @ReadOnly
    private Integer id;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Referencetype referencetype;

    @ManyToOne
    @ReferenceView("Compact")
    private Entry entry;

    @ManyToOne
    @ReferenceView("Compact")
    private Label label;

    @ManyToOne
    @ReferenceView("Compact")
    private Topic topic;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    private Issue issue;

    @Column(length=6)
    @Required
    @DefaultValueCalculator(value=ZeroIntegerCalculator.class)
    private Integer page;

    @Column
    @Required
    private Boolean isOriginal;

    @Column(length=100)
    @Required
    @DefaultValueCalculator(value=StringCalculator.class, properties={ @PropertyValue(name="string", value=" ")})
    private String scoreGroup;

    @ManyToOne
    private Zxsr_review review;

    @ManyToOne
    private Zxsr_award award;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="magref", cascade=CascadeType.REMOVE)
    @ListProperties("feature.id,feature.name,feature.version")
    @XOrderBy("feature.id,feature.name,feature.version")
    private Collection<Magreffeat> magreffeats;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="magref", cascade=CascadeType.REMOVE)
    @ListProperties("host.id,host.title,link")
    @XOrderBy("host.id,host.title,link")
    private Collection<Magreflink> magreflinks;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="magref", cascade=CascadeType.REMOVE)
    @ListProperties("scoreSeq,category,isOverall,score")
    @XOrderBy("scoreSeq")
    private Collection<Zxsr_score> scores;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
