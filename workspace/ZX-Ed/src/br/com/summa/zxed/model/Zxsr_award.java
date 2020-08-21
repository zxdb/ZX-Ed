package br.com.summa.zxed.model;

import javax.persistence.*;

import org.openxava.annotations.*;

@lombok.Data
@lombok.ToString(includeFieldNames=true)
@Entity
public class Zxsr_award {

	@Id
	@Column(length=4)
	private Integer id;

	@Column(length=50)
	@Required
	private String text;

    @lombok.ToString.Exclude
    @Version
    private Integer zxed;
}
