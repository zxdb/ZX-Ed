package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(MemberKey.class)
public class Member {

    @Id
    @ManyToOne(optional=false)
    private Group group;

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Column(length=6)
    @DefaultValueCalculator(value=NextMemberSeq.class, properties={@PropertyValue(name="groupId", from="group.id"),@PropertyValue(name="grouptypeId", from="group.grouptype.id")})
    private Integer seriesSeq;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
