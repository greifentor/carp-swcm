package de.ollie.carp.swcm.gui.vaadin.converter;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.gui.vaadin.converter.PageParametersGOConverter;
import de.ollie.carp.swcm.gui.vaadin.go.converter.PageParametersGO;

@ExtendWith(MockitoExtension.class)
public class PageParametersGOConverterTest {

	private static final int ENTRIES_PER_PAGE = 1;
	private static final int PAGE_NUMBER = 2;

	private static final PageParameters MODEL =
			new PageParameters().setEntriesPerPage(ENTRIES_PER_PAGE).setPageNumber(PAGE_NUMBER);
	private static final PageParametersGO GO =
			new PageParametersGO().setEntriesPerPage(ENTRIES_PER_PAGE).setPageNumber(PAGE_NUMBER);

	@InjectMocks
	private PageParametersGOConverter unitUnderTest;

	@Nested
	class TestsOfMethod_toModel_PageParametersGO {

		@Test
		void passNullValue_returnsNullValue() {
			assertNull(unitUnderTest.toModel(null));
		}

		@Test
		void passAGO_returnsAMatchingModel() {
			assertEquals(MODEL, unitUnderTest.toModel(GO));
		}

	}
}
