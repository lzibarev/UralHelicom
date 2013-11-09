package ru.uh.web.main.client;

import java.util.List;

import ru.uh.web.main.shared.TaskInfoProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UHServiceAsync {

	void getTasks(AsyncCallback<List<TaskInfoProxy>> callback);

	void editTask(TaskInfoProxy proxy, AsyncCallback<TaskInfoProxy> callback);

}
