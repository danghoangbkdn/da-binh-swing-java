package dao;

import java.util.List;

import entity.Custommer;

public interface ListCustommerDao {
	List<Custommer> getListCustommers();

	void setStatusAndDate(String id, String status, String date);

	Custommer getCustommer(String id);
}