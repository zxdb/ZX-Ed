package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,name,licensetype.text,licenseLink")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class License {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="License"))
    @ReadOnly
    private Integer id;

    @Column(length=100)
    @Required
    private String name;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Licensetype licensetype;

    @Column(length=200)
    private String licenseLink;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="license", cascade=CascadeType.REMOVE)
    @ListProperties("label.id,label.name")
    private Collection<Licensor> licensors;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
