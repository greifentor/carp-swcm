package de.ollie.carp.swcm.core.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.ollie.carp.swcm.core.model.Page;
import de.ollie.carp.swcm.core.model.PageParameters;
import de.ollie.carp.swcm.core.model.SourceBook;

@SpringBootTest
public class SourceBookServiceTest {

	private static final String GLOBAL_ID = "global id";
	private static final String NAME = "name";
	private static final String ORIGINAL_NAME = "original name";
	private static final String TOKEN = "token";
	private static final PageParameters PAGE_PARAMETERS = new PageParameters().setEntriesPerPage(10).setPageNumber(0);

	@Autowired
	private SourceBookService sourceBookService;

	@AfterEach
	void tearDown() {
		sourceBookService
				.findAll(PAGE_PARAMETERS)
				.getEntries()
				.forEach(sourceBook -> sourceBookService.delete(sourceBook));
		assertEquals(0, sourceBookService.findAll(PAGE_PARAMETERS).getEntriesTotal());
	}

	@Test
	void createNewSourcebookAndSaveToTheDatabase() {
		// Prepare
		SourceBook passed =
				new SourceBook().setGlobalId(GLOBAL_ID).setName(NAME).setOriginalName(ORIGINAL_NAME).setToken(TOKEN);
		// Run
		SourceBook returned = sourceBookService.create(passed);
		// Check
		passed.setId(returned.getId());
		assertEquals(passed, returned);
	}

	@Test
	void readASourcebookFromDatabase() {
		// Prepare
		SourceBook passed =
				new SourceBook().setGlobalId(GLOBAL_ID).setName(NAME).setOriginalName(ORIGINAL_NAME).setToken(TOKEN);
		passed = sourceBookService.create(passed);
		// Run
		SourceBook returned = sourceBookService.findById(passed.getId()).get();
		// Check
		assertEquals(passed, returned);
	}

	@Test
	void readAPageOfSourcebookFromDatabase() {
		// Prepare
		sourceBookService
				.create(
						new SourceBook()
								.setGlobalId(GLOBAL_ID + 1)
								.setName(NAME + 1)
								.setOriginalName(ORIGINAL_NAME + 1)
								.setToken(TOKEN + 1));
		sourceBookService
				.create(
						new SourceBook()
								.setGlobalId(GLOBAL_ID + 2)
								.setName(NAME + 2)
								.setOriginalName(ORIGINAL_NAME + 2)
								.setToken(TOKEN + 2));
		// Run
		Page<SourceBook> returned = sourceBookService.findAll(PAGE_PARAMETERS);
		// Check
		assertEquals(2L, returned.getEntriesTotal());
		assertContainsGlobalId(returned, GLOBAL_ID + 1);
		assertContainsGlobalId(returned, GLOBAL_ID + 2);
	}

	private static void assertContainsGlobalId(Page<SourceBook> returned, String globalId) {
		assertTrue(
				"'" + globalId + "' not contained in page!",
				returned.getEntries().stream().anyMatch(sourceBook -> globalId.equals(sourceBook.getGlobalId())));
	}

	@Test
	void updatesASourcebookInTheDatabase() {
		// Prepare
		SourceBook passed =
				new SourceBook().setGlobalId(GLOBAL_ID).setName(NAME).setOriginalName(ORIGINAL_NAME).setToken(TOKEN);
		passed = sourceBookService.create(passed);
		// Run
		SourceBook returned = sourceBookService.update(passed.setName(NAME + 1));
		// Check
		passed.setName(NAME + 1);
		assertEquals(passed, returned);
	}

}