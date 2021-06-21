package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="tag.tagtype.text,tag.id,tag.name,seriesSeq,entry.id,entry.title,entry.firstPublisher")
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

    @Column(length=6)
    @DefaultValueCalculator(value=NextMemberSeq.class, properties={@PropertyValue(name="tagId", from="tag.id"),@PropertyValue(name="tagtypeId", from="tag.tagtype.id")})
    private Integer seriesSeq;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
