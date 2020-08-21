package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

@Tab(properties="entry.id,entry.title,entry.firstPublisher,book.id,book.title,book.firstPublisher,installment,volume,page")
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

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
