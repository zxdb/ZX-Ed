package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(LicensorKey.class)
public class Licensor {

    @Id
    @ManyToOne(optional=false)
    private License license;

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Label label;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
