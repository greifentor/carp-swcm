package de.ollie.carp.swcm.gui.web.masterdata;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SelectionEvent;

import de.ollie.carp.swcm.gui.vaadin.component.Button;
import de.ollie.carp.swcm.gui.vaadin.component.ButtonFactory;
import de.ollie.carp.swcm.gui.vaadin.component.MasterDataButtonLayout;
import de.ollie.carp.swcm.gui.vaadin.component.ParentLayout;
import de.ollie.carp.swcm.gui.vaadin.go.SourceBookGO;
import de.ollie.carp.swcm.gui.vaadin.go.converter.PageParametersGO;
import de.ollie.carp.swcm.gui.web.ServiceAccess;
import de.ollie.carp.swcm.gui.web.go.LocalizationGO;

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
	private Button buttonRemove;
	private Grid<SourceBookGO> grid;
	private ServiceAccess serviceAccess;
	private ParentLayout parent;

	public SourceBookPageLayout(ServiceAccess serviceAccess, ParentLayout parent) {
		super();
		this.parent = parent;
		this.serviceAccess = serviceAccess;
		buttonBack = ButtonFactory
				.createButton(serviceAccess.getResourceManager().getLocalizedString("sourcebooks.button.back.text"));
		buttonBack.addClickListener(event -> parent.back());
		buttonAdd = ButtonFactory
				.createButton(serviceAccess.getResourceManager().getLocalizedString("sourcebooks.button.add.text"));
		buttonAdd.addClickListener(event -> addRecord());
		buttonEdit = ButtonFactory
				.createButton(serviceAccess.getResourceManager().getLocalizedString("sourcebooks.button.edit.text"));
		buttonEdit.addClickListener(event -> editRecord());
		buttonRemove = ButtonFactory
				.createButton(serviceAccess.getResourceManager().getLocalizedString("sourcebooks.button.remove.text"));
		buttonRemove.addClickListener(event -> removeRecord());
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
		add(buttonBack, grid, buttonLayout);
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
		logger.info("onAttach");
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
		SourceBookDetailLayout layout = new SourceBookDetailLayout(
				this,
				serviceAccess.getResourceManager(),
				serviceAccess.getSourceBookService(),
				new SourceBookGO().setId(-1).setGlobalId("").setName("").setOriginalName("").setToken(""));
		layout.setMargin(false);
		layout.setWidthFull();
		parent.updateViewWith(layout);
	}

	private void editRecord() {
		grid.getSelectedItems().stream().findFirst().ifPresent(go -> {
			parent
					.updateViewWith(
							new SourceBookDetailLayout(
									this,
									serviceAccess.getResourceManager(),
									serviceAccess.getSourceBookService(),
									go));
		});
	}

	private void removeRecord() {
		grid.getSelectedItems().stream().findFirst().ifPresent(go -> {
			serviceAccess.getSourceBookService().delete(go);
			updateGrid(0);
		});
	}

	@Override
	public void back() {
		parent.updateViewWith(this);
	}

	@Override
	public void updateViewWith(Component component) {
		parent.updateViewWith(component);
	}

}