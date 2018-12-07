package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,name,grouptype.text,link,comments")
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

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Grouptype grouptype;

    @Column(length=200)
    private String link;

    @Column
    @Stereotype("MEMO")
    private String comments;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
