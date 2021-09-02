package de.ollie.carp.swcm.gui.vaadin.converter;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.carp.swcm.core.model.SourceBook;
import de.ollie.carp.swcm.gui.vaadin.converter.SourceBookGOConverter;
import de.ollie.carp.swcm.gui.vaadin.go.converter.SourceBookGO;

@ExtendWith(MockitoExtension.class)
public class SourceBookGOConverterTest {

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

	@InjectMocks
	private SourceBookGOConverter unitUnderTest;

	@Nested
	class TestsOfMethod_toGO_SourceBook {

		@Test
		void passNullValue_returnsNullValue() {
			assertNull(unitUnderTest.toGO(null));
		}

		@Test
		void passAModel_returnsAMatchingGO() {
			assertEquals(GO, unitUnderTest.toGO(MODEL));
		}

	}

	@Nested
	class TestsOfMethod_toModel_SourceBookGO {

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