package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@Tab(properties="entry.id,entry.libraryTitle,idiom.text,link,website.name")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
@IdClass(WebrefKey.class)
public class Webref {

    @Id
    @ManyToOne(optional=false)
    @ReferenceView("Compact")
    private Entry entry;

	@Id
    @Column(length=200)
    private String link;

    @ManyToOne(optional=false)
    @DescriptionsList
    private Website website;

    @ManyToOne(optional=false)
    @DescriptionsList(descriptionProperties="text")
    private Idiom idiom;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
