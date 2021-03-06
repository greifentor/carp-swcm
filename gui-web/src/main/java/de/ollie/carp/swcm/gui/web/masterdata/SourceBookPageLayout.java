package de.ollie.carp.swcm.gui.web.masterdata;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;

import de.ollie.carp.swcm.gui.vaadin.component.Button;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonFactory;
import de.ollie.carp.swcm.gui.vaadin.component.MasterDataButtonLayout;
import de.ollie.carp.swcm.gui.vaadin.go.SourceBookGO;
import de.ollie.carp.swcm.gui.vaadin.go.converter.PageParametersGO;
import de.ollie.carp.swcm.gui.web.HeaderLayout;
import de.ollie.carp.swcm.gui.web.HeaderLayout.HeaderLayoutMode;
import de.ollie.carp.swcm.gui.web.ServiceAccess;
import de.ollie.carp.swcm.gui.web.SessionData;
import de.ollie.carp.swcm.gui.web.UserAuthorizationChecker;
import de.ollie.carp.swcm.gui.web.go.LocalizationGO;
import de.ollie.carp.swcm.gui.web.port.ResourceManager;
import lombok.RequiredArgsConstructor;

/**
 * A view for paginated source book lists.
 *
 * @author ollie (16.08.2021)
 */
@Route(SourceBookPageLayout.URL)
@RequiredArgsConstructor
public class SourceBookPageLayout extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {

	public static final String URL = "carp-swcm/masterdata/sourcebooks";

	private static final Logger logger = LogManager.getLogger(SourceBookPageLayout.class);

	private final ResourceManager resourceManager;
	private final ServiceAccess serviceAccess;
	private final SessionData sessionData;

	private Button buttonAdd;
	private Button buttonBack;
	private Button buttonEdit;
	private Button buttonRemove;
	private Grid<SourceBookGO> grid;

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		logger.debug("setParameter");
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		UserAuthorizationChecker.forwardToLoginOnNoUserSetForSession(sessionData, beforeEnterEvent);
		buttonAdd = ButtonFactory.createAddButton(resourceManager, event -> addRecord(), sessionData);
		buttonEdit = ButtonFactory.createEditButton(resourceManager, event -> editRecord(), sessionData);
		buttonRemove = ButtonFactory.createRemoveButton(resourceManager, event -> removeRecord(), sessionData);
		grid = new Grid<>();
		grid
				.addColumn(SourceBookGO::getName)
				.setHeader(
						serviceAccess
								.getResourceManager()
								.getLocalizedString("sourcebooks.grid.header.name", LocalizationGO.DE));
		grid
				.addColumn(SourceBookGO::getToken)
				.setHeader(
						serviceAccess
								.getResourceManager()
								.getLocalizedString("sourcebooks.grid.header.token", LocalizationGO.DE));
		grid.setWidthFull();
		grid.addSelectionListener(this::enabledButtons);
		MasterDataButtonLayout buttonLayout = new MasterDataButtonLayout(buttonAdd, buttonEdit, buttonRemove);
		buttonLayout.setMargin(false);
		buttonLayout.setWidthFull();
		setMargin(false);
		setWidthFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(false);
		layout.setWidthFull();
		layout
				.add(
						new HeaderLayout(
								ButtonFactory
										.createBackButton(
												resourceManager,
												this::getUI,
												MasterDataLayout.URL,
												sessionData),
								ButtonFactory.createLogoutButton(resourceManager, this::getUI, sessionData, logger),
								"Sourcebooks",
								HeaderLayoutMode.PLAIN),
						grid,
						buttonLayout);
		add(layout);
		updateGrid(0);
		setButtonEnabled(buttonEdit, false);
		setButtonEnabled(buttonRemove, false);
	}

	private void enabledButtons(SelectionEvent<Grid<SourceBookGO>, SourceBookGO> event) {
		if (event.getFirstSelectedItem().isEmpty()) {
			setButtonEnabled(buttonAdd, true);
			setButtonEnabled(buttonEdit, false);
			setButtonEnabled(buttonRemove, false);
		} else {
			setButtonEnabled(buttonAdd, false);
			setButtonEnabled(buttonEdit, true);
			setButtonEnabled(buttonRemove, true);
		}
	}

	private void setButtonEnabled(Button button, boolean enabled) {
		button.setEnabled(enabled);
		if (enabled) {
			button.setBackgroundImage("gate.png");
			button.setBorderColor("yellow");
		} else {
			button.setBackgroundImage("gate-disabled.png");
			button.setBorderColor("gray");
		}
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		logger.info("source book page layout opened for user '{}'.", sessionData.getUserName());
		super.onAttach(attachEvent);
	}

	@Override
	protected void onDetach(DetachEvent detachEvent) {
		logger.info("onDetach");
		super.onDetach(detachEvent);
		getElement().removeFromTree();
	}

	private void updateGrid(int pageNumber) {
		grid
				.setItems(
						serviceAccess
								.getSourceBookService()
								.findAll(new PageParametersGO().setEntriesPerPage(10).setPageNumber(pageNumber))
								.getEntries());
	}

	private void addRecord() {
		getUI().ifPresent(ui -> ui.navigate(SourceBookDetailLayout.URL));
	}

	private void editRecord() {
		grid.getSelectedItems().stream().findFirst().ifPresent(go -> {
			QueryParameters parameters = new QueryParameters(Map.of("id", List.of("" + go.getId())));
			getUI().ifPresent(ui -> ui.navigate(SourceBookDetailLayout.URL, parameters));
		});
	}

	private void removeRecord() {
		grid.getSelectedItems().stream().findFirst().ifPresent(go -> {
			serviceAccess.getSourceBookService().delete(go);
			updateGrid(0);
		});
	}

}