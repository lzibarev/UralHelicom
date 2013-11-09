package ru.uh.web.main.client;

import ru.uh.web.main.shared.TaskInfoProxy;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

public class TaskInfoProperties extends CommonDirectoryWindow<TaskInfoProxy> {

	private TextField orderName;
	private TextField customerName;
	private TextField legalNumber;

	public TaskInfoProperties() {
		super();
		setPixelSize(500, 150);
		setHeadingText("Заказ");
	}

	@Override
	protected Container getInfoContainer() {
		VerticalLayoutContainer p = new VerticalLayoutContainer();

		orderName = new TextField();
		orderName.setAllowBlank(false);
		orderName.setEmptyText("Введите название...");
		p.add(new FieldLabel(orderName, "Название"), new VerticalLayoutData(1, -1));

		customerName = new TextField();
		customerName.setAllowBlank(false);
		customerName.setEmptyText("Введите название...");
		p.add(new FieldLabel(customerName, "Заказчик"), new VerticalLayoutData(1, -1));

		legalNumber = new TextField();
		legalNumber.setAllowBlank(false);
		legalNumber.setEmptyText("Введите номер...");
		p.add(new FieldLabel(legalNumber, "Договор"), new VerticalLayoutData(1, -1));

		return p;
	}

	@Override
	public void setData(TaskInfoProxy object) {
		this.object = object;
		// orderName.setValue(object.getName());
		// customerName.setValue(object.getCustomer());
		// legalNumber.setValue(object.getLegalNumber());
	}

	@Override
	protected void editProcess() {
		if (object == null)
			object = new TaskInfoProxy();
		// object.setName(orderName.getValue());
		// object.setCustomer(customerName.getValue());
		// object.setLegalNumber(legalNumber.getValue());
		UHMainModule.getService().editTask(object, new AsyncCallbackWithErrorMessage<TaskInfoProxy>() {
			@Override
			public void onSuccess(TaskInfoProxy result) {
				Info.display("Данные сохранены", result.getTaskNumber());
				if (updatableForm != null)
					updatableForm.update();
			}
		});
	}

}
