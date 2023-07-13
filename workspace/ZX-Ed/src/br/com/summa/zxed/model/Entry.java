package br.com.summa.zxed.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

import br.com.summa.zxed.calc.*;

@Tab(properties="id,title,isXrated,machinetype.text,maxPlayers,genretype.text,spotGenretype.text,availabletype.text,language.text,originalPublisher")
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

    @Column
    @Required
    private Boolean isXrated;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Machinetype machinetype;

    @Column(length=4)
    @Required
    @DefaultValueCalculator(value=IntegerCalculator.class, properties=@PropertyValue(name="value", value="1"))
    private Integer maxPlayers;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Genretype genretype;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Genretype spotGenretype;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Availabletype availabletype;

    @Column
    @Required
    private Boolean withoutLoadScreen;

    @Column
    @Required
    private Boolean withoutInlay;

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList(descriptionProperties="text")
    private Language language;

    @ManyToOne(fetch=FetchType.LAZY)
    private Issue issue;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("id,notetype.text,beginText")
    @XOrderBy("id")
    private Collection<Note> notes;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("authorSeq,label.id,label.name,team.id,team.name,authorRoles")
    @XOrderBy("authorSeq")
    private Collection<Author> authors;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("id,name,label.id,label.name")
    private Collection<SpexAuthor> spexAuthors;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("releaseSeq,releaseYear,releaseMonth,releaseDay,currency.symbol,releasePrice,budgetPrice,microdrivePrice,diskPrice,cartridgePrice,bookIsbn,bookPages")
    @XOrderBy("releaseSeq")
    private Collection<Release> releases;

    // FIXME: There's a bug in OpenXava 6.0.2 that prevents defining publishers within releases
    // See: https://sourceforge.net/p/openxava/discussion/419691/thread/e16eca0cea/
    // See: https://sourceforge.net/p/openxava/discussion/419691/thread/500a67d5ac/
    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("releaseSeq,publisherSeq,label.id,label.name,label.country.text")
    @XOrderBy("releaseSeq,publisherSeq")
    private Collection<Publisher> publishers;

    // FIXME: There's a bug in OpenXava 6.0.2 that prevents defining aliases within releases
    // See: https://sourceforge.net/p/openxava/discussion/419691/thread/e16eca0cea/
    // See: https://sourceforge.net/p/openxava/discussion/419691/thread/500a67d5ac/
    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("releaseSeq,language.text,title")
    @XOrderBy("releaseSeq,title")
    private Collection<Alias> aliases;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("license.id,license.name,license.licensetype.text,isOfficial")
    @XOrderBy("license.licensetype.text,license.name")
    private Collection<Relatedlicense> relatedLicenses;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("relationtype.text,original.id,original.title,original.originalPublisher")
    private Collection<Relation> relatedEntries;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("tag.tagtype.text,tag.id,tag.name,memberSeq,category.text,variant")
    @XOrderBy("tag.tagtype.text,tag.name,category.text")
    private Collection<Member> tagMembers;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="container", cascade=CascadeType.REMOVE)
    @ListProperties("mediaSeq,mediaSide,progSeq,entry.id,entry.title,label.id,label.name,alias,isOriginal,contenttype.text")
    @XOrderBy("mediaSeq,mediaSide,progSeq,entry.id")
    private Collection<Content> contents;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("container.title,container.id,isOriginal,alias,contenttype.text")
    @XOrderBy("container.libraryTitle,container.id")
    private Collection<Content> compilations;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("referencetype.text,label.id,label.name,topic.id,topic.name,issue.magazine.name,issue.id,issue.fancyName,page,isOriginal")
    @XOrderBy("referencetype.text,issue.magazine.name,issue.id,page")
    private Collection<Magref> magazineReferences;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="book", cascade=CascadeType.REMOVE)
    @ListProperties("installment,volume,page,entry.id,entry.title,isOriginal")
    @XOrderBy("installment,volume,page,entry.id")
    private Collection<Booktypein> bookTypeins;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("book.id,book.title,installment,volume,page,isOriginal")
    @XOrderBy("book.id,installment,volume,page,isOriginal")
    private Collection<Booktypein> books;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("website.name,language.text,link")
    @XOrderBy("website.name,link")
    private Collection<Webref> webReferences;

    @lombok.ToString.Exclude
    @OneToMany(mappedBy="entry", cascade=CascadeType.REMOVE)
    @ListProperties("id,title,platform.text,isOfficial,linkSystem")
    @XOrderBy("title,platform.text,id")
    private Collection<Port> ports;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;

    @Transient
    @Depends("publishers,compilations,magazineReferences,books")
    public String getOriginalPublisher() {
        StringJoiner sj = new StringJoiner("; ");
        publishers.stream()
            .filter(p -> p.getReleaseSeq() == 0)
            .sorted(Comparator.comparingInt(Publisher::getPublisherSeq))
            .forEach(p -> sj.add(p.getLabel().getName()));
        if (sj.length() == 0) {
            for (Content content : compilations) {
                if (content.getIsOriginal()) {
                    String originalPublisher = content.getContainer().getOriginalPublisher();
                    return (originalPublisher.isEmpty() ? "?" : originalPublisher)+" - within \""+content.getContainer().getTitle()+"\"";
                }
            }
            for (Booktypein booktypein : books) {
                if (booktypein.getIsOriginal()) {
                    String originalPublisher = booktypein.getBook().getOriginalPublisher();
                    return (originalPublisher.isEmpty() ? "?" : originalPublisher)+" - book type-in from \""+booktypein.getBook().getTitle()+"\"";
                }
            }
            for (Magref magref : magazineReferences) {
                if (magref.getIsOriginal()) {
                    return magref.getIssue().getMagazine().getName()+" - magazine type-in from issue "+magref.getIssue().getFancyName();
                }
            }
            if (issue != null && genretype != null && "Covertape".equals(genretype.getText())) {
                return issue.getMagazine().getName()+" - magazine covertape from issue "+issue.getFancyName();
            }
        }
        return sj.toString();
    }
}
