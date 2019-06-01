package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import br.com.summa.zxed.calc.*;

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

    @Column(length=250)
    private String link;

    @Column(length=250)
    private String link2;

    @Column(length=20)
    private String score;

    @ManyToOne(optional=false)
    private Feature feature;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
