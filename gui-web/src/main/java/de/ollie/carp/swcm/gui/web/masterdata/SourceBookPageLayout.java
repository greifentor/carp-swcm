package de.ollie.carp.swcm.gui.web.masterdata;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SelectionEvent;

import de.ollie.carp.corelib.gui.Disposable;
import de.ollie.carp.corelib.gui.vaadin.component.Button;
import de.ollie.carp.corelib.gui.vaadin.component.ParentLayout;
import de.ollie.carp.corelib.localization.LocalizationSO;
import de.ollie.carp.swcm.gui.web.ButtonFactory;
import de.ollie.carp.swcm.gui.web.ServiceAccess;
import de.ollie.carp.swcm.gui.web.go.PageParametersGO;
import de.ollie.carp.swcm.gui.web.go.SourceBookGO;

/**
 * A view for paginated source book lists.
 *
 * @author ollie (16.08.2021)
 */
public class SourceBookPageLayout extends VerticalLayout implements ParentLayout {

	private static final Logger logger = LogManager.getLogger(SourceBookPageLayout.class);

	private Button buttonAdd;
	private Button buttonBack;
	private Button buttonEdit;
	private Grid<SourceBookGO> grid;
	private ServiceAccess serviceAccess;
	private ParentLayout parent;

	public SourceBookPageLayout(ServiceAccess serviceAccess, ParentLayout parent) {
		super();
		this.parent = parent;
		this.serviceAccess = serviceAccess;
		buttonBack = ButtonFactory.createButton("sourcebooks.button.back.text");
		buttonBack.addClickListener(event -> parent.back());
		buttonAdd = ButtonFactory.createButton("sourcebooks.button.add.text");
		buttonAdd.addClickListener(event -> addRecord());
		buttonEdit = ButtonFactory.createButton("sourcebooks.button.edit.text");
		buttonEdit.addClickListener(event -> editRecord());
		grid = new Grid<>();
		grid
				.addColumn(SourceBookGO::getName)
				.setHeader(
						serviceAccess
								.getResourceManager()
								.getLocalizedString("sourcebooks.grid.header.name", LocalizationSO.DE));
		grid
				.addColumn(SourceBookGO::getToken)
				.setHeader(
						serviceAccess
								.getResourceManager()
								.getLocalizedString("sourcebooks.grid.header.token", LocalizationSO.DE));
		grid.setWidthFull();
		grid.addSelectionListener(this::enabledButtons);
		add(buttonBack, grid, buttonAdd, buttonEdit);
		updateGrid(0);
		setMargin(false);
		setWidthFull();
	}

	private void enabledButtons(SelectionEvent<Grid<SourceBookGO>, SourceBookGO> event) {
		if (event.getFirstSelectedItem().isEmpty()) {
			buttonAdd.setEnabled(true);
			buttonEdit.setEnabled(false);
		} else {
			buttonAdd.setEnabled(false);
			buttonEdit.setEnabled(true);
		}
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		logger.info("onAttach");
		super.onAttach(attachEvent);
	}

	@Override
	protected void onDetach(DetachEvent detachEvent) {
		logger.info("onDetach");
		super.onDetach(detachEvent);
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
		Disposable.removeAll(this);
		add(
				new SourceBookDetailLayout(
						this,
						serviceAccess.getResourceManager(),
						serviceAccess.getSourceBookService(),
						new SourceBookGO().setGlobalId("").setName("").setOriginalName("").setToken("")));
	}

	private void editRecord() {
		grid.getSelectedItems().stream().findFirst().ifPresent(go -> {
			Disposable.removeAll(this);
			add(
					new SourceBookDetailLayout(
							this,
							serviceAccess.getResourceManager(),
							serviceAccess.getSourceBookService(),
							go));
		});
	}

	@Override
	public void back() {
		Disposable.removeAll(this);
		add(new SourceBookPageLayout(serviceAccess, parent));
	}

}