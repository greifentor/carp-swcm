package de.ollie.carp.swcm.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A DBO for source_book_changess.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "SourceBookChanges")
@Table(name = "SOURCE_BOOK_CHANGES")
public class SourceBookChangesDBO {

	@Id
	@Column(name = "ID", nullable = false)
	private long id;
	@Column(name = "RECORD_ID", nullable = false)
	private long recordId;
	@Column(name = "ATTRIBUTE_NAME", nullable = false)
	private String attributeName;
	@Column(name = "CHANGE_DATE", nullable = false)
	private LocalDateTime changeDate;
	@Column(name = "NEW_VALUE")
	private String newValue;

}