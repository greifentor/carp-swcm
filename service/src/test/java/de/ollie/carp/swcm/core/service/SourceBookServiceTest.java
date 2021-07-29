package de.ollie.carp.swcm.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.ollie.carp.swcm.core.model.SourceBook;

@SpringBootTest
public class SourceBookServiceTest {

	private static final String GLOBAL_ID = "global id";
	private static final String NAME = "name";
	private static final String ORIGINAL_NAME = "original name";
	private static final String TOKEN = "token";

	@Autowired
	private SourceBookService sourceBookService;

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

}