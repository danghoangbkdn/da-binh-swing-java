package controller;

import java.util.List;

import entity.Custommer;
import service.ListCustommerService;
import service.ListCustommerServiceImpl;

public class ListCustommerControllerImpl implements ListCustommerController {

	private final ListCustommerService service;
	private final List<Custommer> custommers;

	public ListCustommerControllerImpl() {
		service = new ListCustommerServiceImpl();
		custommers = service.getListCustommers();
		custommers.sort((c1, c2) -> c1.getId().compareTo(c2.getId()));
	}

	@Override
	public Object[][] getCustommerObj() {
		return getListCustommer();
	}

	@Override
	public List<Custommer> getListCustommers() {
		return custommers;
	}

	@Override
	public Custommer setStatus(Custommer custommer) {
		return service.setStatus(custommer);
	}

	private Object[][] getListCustommer() {
		Object[][] custommer = new Object[custommers.size()][5];

		for (int i = 0; i < custommers.size(); i++) {
			custommer[i][0] = custommers.get(i).getId();
			custommer[i][1] = custommers.get(i).getFullname();
			custommer[i][2] = custommers.get(i).getNumberPhone();
			custommer[i][3] = custommers.get(i).getAddress();
			custommer[i][4] = custommers.get(i).getStatus();
		}
		return custommer;
	}

	@Override
	public Custommer getCustommer(String id) {
		return service.getCustommer(id);
	}
}