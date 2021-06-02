package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,title,entry.id,entry.title,platform.text,isOfficial,linkSystem")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Port {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Port"))
    @ReadOnly
    private Integer id;

    @Column(length=250)
    private String title;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Platform platform;

    @Column
    @Required
    private Boolean isOfficial;

    @Column(length=200)
    private String linkSystem;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
