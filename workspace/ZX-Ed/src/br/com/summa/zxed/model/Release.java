package br.com.summa.zxed.model;

import java.math.*;

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

    @Column
    private BigDecimal releasePrice;

    @Column
    private BigDecimal budgetPrice;

    @Column
    private BigDecimal microdrivePrice;

    @Column
    private BigDecimal diskPrice;

    @Column
    private BigDecimal cartridgePrice;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
