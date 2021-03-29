package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,name,country.text,country2.text,wasRenamed,from.id,from.name,owner.id,owner.name,deceased,linkWikipedia,linkSite,labeltype.text",
     defaultOrder="${name}")
@View(name="Compact", members="id,name")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Label {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextId.class, properties=@PropertyValue(name="modelName", value="Label"))
    @ReadOnly
    private Integer id;

    @Column(length=100)
    @Required
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Country country;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Country country2;

    @Column
    @Required
    private Boolean wasRenamed;

    @ManyToOne(fetch=FetchType.LAZY)
    @ReferenceView("Compact")
    private Label from;

    @ManyToOne(fetch=FetchType.LAZY)
    @ReferenceView("Compact")
    private Label owner;

    @Column(length=200)
    private String deceased;

    @Column(length=200)
    private String linkWikipedia;

    @Column(length=200)
    private String linkSite;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Labeltype labeltype;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="label", cascade=CascadeType.REMOVE)
    @ListProperties("id,notetype.text,section,beginText")
    @XOrderBy("id")
    private Collection<Note> notes;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="label", cascade=CascadeType.REMOVE)
    @ListProperties("website.name,permissiontype.text,text")
    @XOrderBy("website.id")
    private Collection<Permission> permissions;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="label", cascade=CascadeType.REMOVE)
    @ListProperties("link,title,language.text,interviewer,interviewYear")
    @XOrderBy("interviewYear,link")
    private Collection<Interview> interviews;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
