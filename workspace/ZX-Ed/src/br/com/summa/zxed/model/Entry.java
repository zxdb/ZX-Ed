package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;
import br.com.summa.zxed.sql.*;

@View(name="Compact", members="id,title")
@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Entry {

    @Id
    @Column(length=11)
    @DefaultValueCalculator(value=NextEntryId.class, properties= {@PropertyValue(name="title"),
            @PropertyValue(name="genretypeId", from="genretype.id"),
            @PropertyValue(name="machinetypeId", from="machinetype.id")})
    @ReadOnly
    private Integer id;

    @Column(length=250)
    @Required
    private String title;

    @Column(length=250)
    @Required
    private String libraryTitle;

    @ManyToOne(fetch=FetchType.LAZY)
    @ReferenceView("Compact")
    private Entry original;

    @Column
    @Required
    private Boolean isMod;

    @Column
    @Required
    private Boolean isXrated;

    @Column
    @Required
    private Boolean isCrap;

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 1)
    private NullableBoolean wasInspired;

    @ManyToOne(fetch=FetchType.LAZY)
    private License license;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Machinetype machinetype;

    @Column(length=4)
    private Integer maxPlayers;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Genretype genretype;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Genretype spotGenretype;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Publicationtype publicationtype;

    @Column(length=50)
    private String spanishPrice;

    @Column(length=80)
    private String microdrivePrice;

    @Column(length=80)
    private String diskPrice;

    @Column(length=50)
    private String cartridgePrice;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Availabletype availabletype;

    @Column
    @Required
    private Boolean withoutLoadScreen;

    @Column
    @Required
    private Boolean withoutInlay;

    @Column
    @Required
    private Boolean hideFromStp;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Idiom idiom;

    @Column(length=4)
    private String magRatings;

    @ManyToOne(fetch=FetchType.LAZY)
    private Issue issue;

    @Column(length=50)
    private String bookIsbn;

    @Column(length=50)
    private String bookPages;

    @Column
    @Stereotype("MEMO")
    private String knownErrors;

    @Column
    @Stereotype("MEMO")
    private String comments;

    @Column
    @Stereotype("MEMO")
    private String spotComments;

    @Column
    @Stereotype("MEMO")
    private String hardwareBlurb;

    @Column
    @Stereotype("MEMO")
    private String hardwareFeature;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry")
    @ListProperties("releaseSeq")
    private Collection<Release> releases;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
