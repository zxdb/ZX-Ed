package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,referencetype.text,entry.id,entry.title,label.id,label.name,topic.id,topic.name,issue.id,page,isSupplement,score,feature.id,feature.name")
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
    private Boolean isSupplement;

    @Column(length=20)
    private String score;

    // FIXME: There's a bug in OpenXava 6.2 that does not work with id=0 unless @DescriptionsList is used here
    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="id,name")
    private Feature feature;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
