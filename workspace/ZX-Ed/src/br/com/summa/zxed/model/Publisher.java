package br.com.summa.zxed.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(defaultOrder="${entry.id} asc, ${releaseSeq} asc, ${publisherSeq} asc")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(PublisherKey.class)
public class Publisher {
    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Id
    @Column(length=6)
    private Integer releaseSeq;

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Label label;

    @Column(length=6)
    @Required
    @DefaultValueCalculator(value=NextPublisherSeq.class, properties={@PropertyValue(name="entryId", from="entry.id"),@PropertyValue(name="releaseSeq")})
    @Min(1)
    private Integer publisherSeq;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
