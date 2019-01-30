package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(RelatedlicenseKey.class)
public class Relatedlicense {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

    @Id
    @ManyToOne(optional=false)
    private License license;

    @Column
    @Required
    private Boolean isOfficial;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
