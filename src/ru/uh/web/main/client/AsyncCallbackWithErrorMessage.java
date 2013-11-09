package ru.uh.web.main.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.info.Info;

public abstract class AsyncCallbackWithErrorMessage<T> implements AsyncCallback<T> {

	public AsyncCallbackWithErrorMessage() {
	}

	public AsyncCallbackWithErrorMessage(Window window) {
		this.window = window;
	}

	protected Window window;

	@Override
	public void onFailure(Throwable caught) {
		if (window != null)
			window.hide();
		Info.display("Ошибка", "При обработке запроса возникла ошибка <br/>" + caught);
	}
}
