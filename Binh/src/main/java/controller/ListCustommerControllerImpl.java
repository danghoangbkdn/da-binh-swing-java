package controller;

import service.ListCustommerService;
import service.ListCustommerServiceImpl;

public class ListCustommerControllerImpl implements ListCustommerController {

	private final ListCustommerService service;

	public ListCustommerControllerImpl() {
		service = new ListCustommerServiceImpl();
	}

	@Override
	public Object[][] getListCustommer() {
		return service.getListCustommer();
	}

	@Override
	public String getInfor(String idKH) {
		return service.getInfor(idKH);
	}

	@Override
	public void setStatus(String idKH) {
		service.setStatus(idKH);
	}

	@Override
	public String getCustommer(String id) {
		return service.getCustommer(id);
	}

	@Override
	public boolean checkStatusCustommer(String id) {
		return service.checkStatusCustommer(id);
	}
}