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
 * A DBO for sourcebook_changess.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "SourcebookChanges")
@Table(name = "Sourcebook_Changes")
public class SourcebookChangesDBO {

	@Id
	@Column(name = "Id", nullable = false)
	private long id;
	@Column(name = "RecordId", nullable = false)
	private long recordId;
	@Column(name = "AttributeName", nullable = false)
	private String attributeName;
	@Column(name = "ChangeDate", nullable = false)
	private LocalDateTime changeDate;
	@Column(name = "NewValue")
	private String newValue;

}