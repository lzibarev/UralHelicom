package ru.uh.web.main.client;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;

public class UHNavigation implements IsWidget {

	@Override
	public Widget asWidget() {
		VBoxLayoutContainer c = new VBoxLayoutContainer();
		c.setPadding(new Padding(5));
		c.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);

		TextButton button = new TextButton("Распиварие работ");
		button.setEnabled(false);
		c.add(button, new BoxLayoutData(new Margins(0, 0, 5, 0)));

		TextButton button2 = new TextButton("Управление авиапарком");
		button2.setEnabled(false);
		c.add(button2, new BoxLayoutData(new Margins(0, 0, 5, 0)));

		TextButton button3 = new TextButton("Комплектация");
		button3.setEnabled(false);
		c.add(button3, new BoxLayoutData(new Margins(0, 0, 5, 0)));

		TextButton button4 = new TextButton("История");
		button4.setEnabled(false);
		c.add(button4, new BoxLayoutData(new Margins(0, 0, 5, 0)));

		TextButton button5 = new TextButton("Администрирование");
		button5.setEnabled(false);
		c.add(button5, new BoxLayoutData(new Margins(0, 0, 5, 0)));

		return c;
	}
}
