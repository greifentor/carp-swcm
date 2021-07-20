package de.ollie.carp.swcm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A DBO for sourcebooks.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "Sourcebook")
@Table(name = "Sourcebook")
public class SourcebookDBO {

	@Id
	@SequenceGenerator(allocationSize = 1, name = "SourcebookSequence", sequenceName = "sourcebook_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SourcebookSequence")
	@Column(name = "Id", nullable = false)
	private long id;
	@Column(name = "GlobalId")
	private String globalId;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "OriginalName")
	private String originalName;
	@Column(name = "Token", nullable = false)
	private String token;

}