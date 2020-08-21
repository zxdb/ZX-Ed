package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Zxsr_review {

	@Id
	@Column(length=11)
	private Integer id;

    @Column
    @Stereotype("MEMO")
	private String reviewText;

    @Column
    @Stereotype("MEMO")
    private String reviewComments;

    @Column
    private String reviewRating;

    @Column
    @Stereotype("MEMO")
    private String reviewers;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
