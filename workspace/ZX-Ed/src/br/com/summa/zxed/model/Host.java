package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Host {

    @Id
    @Column(length=4)
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

    @ManyToOne(optional=false)
    @DescriptionsList
    private Magazine magazine;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
