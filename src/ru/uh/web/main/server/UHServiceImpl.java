package ru.uh.web.main.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ru.uh.server.model.PMF;
import ru.uh.server.model.TaskInfo;
import ru.uh.web.main.client.UHService;
import ru.uh.web.main.shared.TaskInfoProxy;

@SuppressWarnings("serial")
public class UHServiceImpl extends RemoteServiceServlet implements UHService {

	@SuppressWarnings("unchecked")
	public List<TaskInfoProxy> getTasks() {
		List<TaskInfoProxy> list = new ArrayList<TaskInfoProxy>();
		List<TaskInfo> tasks = null;
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			Query query = em.newQuery(TaskInfo.class);
			tasks = (List<TaskInfo>) query.execute();
		} finally {
			em.close();
		}

		for (TaskInfo taskInfo : tasks) {
			list.add(taskInfo.asProxy());
		}
		System.out.println(list.size());
		return list;
	}

	@Override
	public TaskInfoProxy editTask(TaskInfoProxy proxy) {
		TaskInfo task = new TaskInfo(proxy);
		PersistenceManager em = null;
		try {
			em = PMF.get().getPersistenceManager();
			em.makePersistent(task);
		} finally {
			em.close();
		}
		System.out.println(task.getKey().toString());
		return task.asProxy();
	}
}
