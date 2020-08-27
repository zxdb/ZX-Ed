package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

import org.openxava.calculators.IntegerCalculator;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Issue {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Issue"))
    @ReadOnly
    private Integer id;

    @ManyToOne(optional=false)
    @DescriptionsList
    private Magazine magazine;

    @Column(length=4)
    private Integer dateYear;

    @Column(length=2)
    private Integer dateMonth;

    @Column(length=2)
    private Integer dateDay;

    @Column(length=6)
    private Integer volume;

    @Column(length=6)
    private Integer number;

    @Column(length=100)
    private String special;

    @Column(length=100)
    private String supplement;

    @Column(length=6)
    @Required
	@DefaultValueCalculator(value=IntegerCalculator.class, properties={ @PropertyValue(name="value", value="1")})
    private Integer coverPage;

    @ManyToOne
    private Issue parent;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
