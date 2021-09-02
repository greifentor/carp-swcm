package de.ollie.carp.swcm.gui.vaadin.converter;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.SourceBook;
import de.ollie.carp.swcm.gui.vaadin.converter.SourceBookGOConverter;
import de.ollie.carp.swcm.gui.vaadin.go.converter.PageGO;
import de.ollie.carp.swcm.gui.vaadin.go.converter.SourceBookGO;

@ExtendWith(MockitoExtension.class)
public class PageGOConverterTest {

	private static final int ENTRIES_PER_PAGE = 2;
	private static final int ENTRIES_TOTAL = 1;
	private static final String GLOBAL_ID = "globalId";
	private static final Long ID = 42L;
	private static final String NAME = "name";
	private static final String ORIGINAL_NAME = "original name";
	private static final String TOKEN = "token";

	private static final SourceBookGO GO = new SourceBookGO()
			.setGlobalId(GLOBAL_ID)
			.setId(ID)
			.setOriginalName(ORIGINAL_NAME)
			.setName(NAME)
			.setToken(TOKEN);
	private static final SourceBook MODEL = new SourceBook()
			.setGlobalId(GLOBAL_ID)
			.setId(ID)
			.setOriginalName(ORIGINAL_NAME)
			.setName(NAME)
			.setToken(TOKEN);

	private PageGOConverter<SourceBookGO, SourceBook> unitUnderTest;

	@BeforeEach
	void setUp() {
		unitUnderTest = new PageGOConverter<>(new SourceBookGOConverter());
	}

	@Nested
	class TestsOfMethod_toGO_SourceBook {

		@Test
		void passNullValue_returnsNullValue() {
			assertNull(unitUnderTest.toGO(null));
		}

		@Test
		void passAModel_returnsAMatchingGO() {
			// Prepare
			Page<SourceBook> passed = new Page<SourceBook>()
					.setEntries(Arrays.asList(MODEL))
					.setEntriesPerPage(ENTRIES_PER_PAGE)
					.setEntriesTotal(ENTRIES_TOTAL);
			PageGO<SourceBookGO> expected = new PageGO<SourceBookGO>()
					.setEntries(Arrays.asList(GO))
					.setEntriesPerPage(ENTRIES_PER_PAGE)
					.setEntriesTotal(ENTRIES_TOTAL);
			// Run & Check
			assertEquals(expected, unitUnderTest.toGO(passed));
		}

	}

}
