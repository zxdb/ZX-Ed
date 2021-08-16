package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class SpexAuthor {

    @Id
    @Column(length=11)
    private Integer id;

    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Column(length=150)
    @Required
    private String name;

    @ManyToOne
    @ReferenceView("Compact")
    private Label label;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
