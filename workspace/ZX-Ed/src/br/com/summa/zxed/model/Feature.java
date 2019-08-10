package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Feature {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Feature"))
    @ReadOnly
    private Integer id;

    @Column(length=150)
    @Required
    private String name;

    @Column(length=4)
    @Required
    private Integer version;

    @ManyToOne
    @ReferenceView("Compact")
    private Label label;

    @ManyToOne
    @ReferenceView("Compact")
    private Label label2;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
