package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(ReleaseKey.class)
public class Release {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Id
    @Column(length=6)
    @DefaultValueCalculator(value=NextReleaseSeq.class, properties=@PropertyValue(name="entryId", from="entry.id"))
    private Integer releaseSeq;

    @Column(length=4)
    private Integer releaseYear;

    @Column(length=2)
    private Integer releaseMonth;

    @Column(length=2)
    private Integer releaseDay;

    @ManyToOne(optional=true)
    private Currency currency;

    @Column(length=150)
    private String releasePrice;

    @Column(length=150)
    private String budgetPrice;

    @Column(length=80)
    private String microdrivePrice;

    @Column(length=80)
    private String diskPrice;

    @Column(length=80)
    private String cartridgePrice;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
