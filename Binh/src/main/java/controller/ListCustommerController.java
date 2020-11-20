package controller;

import java.util.List;

import entity.Custommer;

public interface ListCustommerController {
	Object[][] getCustommerObj();

	List<Custommer> getListCustommers();

	Custommer setStatus(Custommer custommer);

	Custommer getCustommer(String id);
}