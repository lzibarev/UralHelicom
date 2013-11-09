package ru.uh.web.main.client;

import ru.uh.web.main.shared.TaskInfoProxy;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface TaskInfoProxyProperties extends PropertyAccess<TaskInfoProxy> {
	@Path("id")
	ModelKeyProvider<TaskInfoProxy> key();

	@Path("taskNumber")
	ValueProvider<TaskInfoProxy, String> taskNumber();

	ValueProvider<TaskInfoProxy, String> taskTitle();

	ValueProvider<TaskInfoProxy, String> limitHFGrid();

}