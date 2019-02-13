package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,title,original.id,original.title,isMod,isXrated,isCrap,machinetype.text,maxPlayers,genretype.text,spotGenretype.text,publicationtype.text,availabletype.text,idiom.text,firstPublisher")
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
    @DefaultValueCalculator(value=LibTitle.class, properties=@PropertyValue(name="title"))
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

    @Column(length=1000)
    @Stereotype("MEMO")
    private String spotComments;

    @Column(length=1000)
    @Stereotype("MEMO")
    private String hardwareBlurb;

    @Column(length=300)
    @Stereotype("MEMO")
    private String hardwareFeature;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("authorSeq,label.id,label.name,team.id,team.name,authorRoles")
    @XOrderBy("authorSeq")
    private Collection<Author> authors;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("releaseSeq,releaseYear,releaseMonth,releaseDay,releasePrice,budgetPrice,microdrivePrice,diskPrice,cartridgePrice")
    @XOrderBy("releaseSeq")
    private Collection<Release> releases;

    // FIXME: There's a bug in OpenXava 6.0.2 that prevents defining publishers within releases
    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("releaseSeq,publisherSeq,label.id,label.name")
    @XOrderBy("releaseSeq,publisherSeq")
    private Collection<Publisher> publishers;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("license.id,license.name,license.licensetype.text,isOfficial")
    @XOrderBy("license.licensetype.text,license.name")
    private Collection<Relatedlicense> relatedLicenses;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("util.id,util.title")
    private Collection<Framework> frameworks;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("group.grouptype.text,group.id,group.name,seriesSeq")
    @XOrderBy("group.grouptype.text,group.id")
    private Collection<Member> groupMembers;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="compilation", cascade=CascadeType.REMOVE)
    @ListProperties("tapeSeq,tapeSide,progSeq,entry.id,entry.title,label.id,label.name,alias,variationtype.text")
    @XOrderBy("tapeSeq,tapeSide,progSeq,entry.id")
    private Collection<Compilation> compilationContents;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("website.name,idiom.text,link")
    @XOrderBy("website.name,link")
    private Collection<Relatedlink> relatedLinks;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;

    @Transient
    @Depends("publishers")
    public String getFirstPublisher() {
        StringJoiner sj = new StringJoiner("; ");
        for (Publisher publisher : publishers) {
        	if (publisher.getReleaseSeq() == 0) {
        		sj.add(publisher.getLabel().getName());
        	}
        }
        return sj.toString();
    }
}
