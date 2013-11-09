package ru.uh.web.main.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class UHMainModule implements IsWidget, EntryPoint {

	private TabPanel center;

	@Override
	public void onModuleLoad() {
		Widget con = asWidget();
		Viewport viewport = new Viewport();
		viewport.add(con);
		RootPanel.get().add(viewport);
	}

	@Override
	public Widget asWidget() {
		final BorderLayoutContainer con = new BorderLayoutContainer();
		con.setBorders(true);

		ContentPanel west = new ContentPanel();
		west.add(new UHNavigation());
		center = new TabPanel();
		center.setAnimScroll(true);
		center.setTabScroll(true);
		center.setCloseContextMenu(true);

		TaskListController hello = new TaskListController();
		center.add(hello, new TabItemConfig("Расписание работ", false));

		BorderLayoutData westData = new BorderLayoutData(150);
		westData.setCollapsible(true);
		westData.setSplit(true);
		westData.setCollapseMini(true);
		westData.setMargins(new Margins(0, 5, 0, 5));

		MarginData centerData = new MarginData();

		con.setWestWidget(west, westData);
		con.setCenterWidget(center, centerData);

		SimpleContainer simple = new SimpleContainer();
		simple.add(con, new MarginData(10));

		return simple;
	}

	private final static UHServiceAsync service = GWT.create(UHService.class);

	public static UHServiceAsync getService() {
		return service;
	}

}
