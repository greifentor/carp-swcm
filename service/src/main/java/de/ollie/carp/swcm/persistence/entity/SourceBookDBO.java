package de.ollie.carp.swcm.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A DBO for source_books.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "SourceBook")
@Table(name = "SOURCE_BOOK")
public class SourceBookDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;
	@Column(name = "GLOBAL_ID")
	private String globalId;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "ORIGINAL_NAME")
	private String originalName;
	@Column(name = "TOKEN", nullable = false)
	private String token;

}