package ru.uh.web.main.client;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public abstract class CommonDirectoryWindow<T> extends Window {
	protected T object;

	protected IUpdatable updatableForm;

	public CommonDirectoryWindow() {
		super();

		add(getInfoContainer());

		setPixelSize(500, 100);
		setModal(true);
		setBlinkModal(true);
		TextButton cancelButton = new TextButton("Отмена");
		cancelButton.addSelectHandler(new CancelSelectHandler(this));
		TextButton saveButton = new TextButton("Сохранить");
		saveButton.addSelectHandler(new SaveSelectHandler());
		addButton(saveButton);
		addButton(cancelButton);
	}

	protected abstract Container getInfoContainer();

	public abstract void setData(T object);

	protected abstract void editProcess();

	public void setUpdatable(IUpdatable form) {
		this.updatableForm = form;
	}

	private class SaveSelectHandler extends CancelSelectHandler {

		public SaveSelectHandler() {
			super(CommonDirectoryWindow.this);
		}

		@Override
		public void onSelect(SelectEvent event) {
			editProcess();
			super.onSelect(event);
		}
	}

}