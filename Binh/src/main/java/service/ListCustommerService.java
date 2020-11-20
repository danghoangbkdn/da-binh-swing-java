package service;

import java.util.List;

import entity.Custommer;

public interface ListCustommerService {
	List<Custommer> getListCustommers();

	Custommer setStatus(Custommer custommer);

	Custommer getCustommer(String id);
}