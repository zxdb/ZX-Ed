package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="id,text")
@View(name="Compact", members="id,text")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Category {

    @Id
    @Column(length=6)
    private Integer id;

    @Column(length=200)
    @Required
    private String text;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
