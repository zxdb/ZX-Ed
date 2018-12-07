package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Website {

    @Id
    @Column(length=4)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Website"))
    @ReadOnly
    private Integer id;

    @Column(length=100)
    @Required
    private String name;

    @Column(length=200)
    private String link;

    @Column(length=100)
    private String linkMask;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
