package ru.uh.web.main.client;

import java.util.List;

import ru.uh.web.main.shared.TaskInfoProxy;
import ru.uh.web.main.shared.TaskShowParams;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("uhservice")
public interface UHService extends RemoteService {

	List<TaskInfoProxy> getTasks(TaskShowParams params);

	TaskInfoProxy editTask(TaskInfoProxy proxy);

}
