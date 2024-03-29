package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Tab(properties="entry.id,entry.title,entry.originalPublisher,book.id,book.title,book.originalPublisher,installment,volume,page,isOriginal")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(BooktypeinKey.class)
public class Booktypein {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry book;

    @Id
    @Column(length=6)
    @DefaultValueCalculator(value=ZeroIntegerCalculator.class)
    private Integer installment;

    @Id
    @Column(length=6)
    @DefaultValueCalculator(value=ZeroIntegerCalculator.class)
    private Integer volume;

    @Id
    @Column(length=6)
    private Integer page;

    @Column
    @Required
    private Boolean isOriginal;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
