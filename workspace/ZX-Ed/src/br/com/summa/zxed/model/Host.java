package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Host {

    @Id
    @Column(length=4)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Host"))
    @ReadOnly
    private Integer id;

    @Column(length=150)
    @Required
    private String title;

    @Column(length=150)
    @Required
    private String link;

    @Column(length=150)
    @Required
    private String admin;

    @ManyToOne
    @DescriptionsList
    private Magazine magazine;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
