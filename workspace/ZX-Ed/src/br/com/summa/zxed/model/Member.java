package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="tag.tagtype.text,tag.id,tag.name,memberSeq,category.text,variant,entry.id,entry.title,entry.originalPublisher")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(MemberKey.class)
public class Member {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Tag tag;

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Id
    @Column(length=100)
    @Required
    // FIXME: There's a bug in OpenXava 6.6.3 that will go crazy if an empty string is part of composite primary key
    @DefaultValueCalculator(value=StringCalculator.class, properties={ @PropertyValue(name="string", value="*")})
    private String variant;

    @ManyToOne
    @ReferenceView("Compact")
    private Category category;

    @Column(length=6)
    @DefaultValueCalculator(value=NextMemberSeq.class, properties={@PropertyValue(name="tagId", from="tag.id"),@PropertyValue(name="tagtypeId", from="tag.tagtype.id")})
    private Integer memberSeq;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
