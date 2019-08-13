package br.com.summa.zxed.model;

import javax.persistence.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(MagreflinkKey.class)
public class Magreflink {

    @Id
    @ManyToOne(optional=false)
    private Magref magref;

    @Id
    @Column(length=250)
    private String link;

    @ManyToOne(optional=false)
    private Host host;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
