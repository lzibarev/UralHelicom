package ru.uh.web.main.client;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class CancelSelectHandler implements SelectHandler {
	protected Window window;

	public CancelSelectHandler(Window window) {
		this.window = window;
	}

	@Override
	public void onSelect(SelectEvent event) {
		window.hide();
	}

}
