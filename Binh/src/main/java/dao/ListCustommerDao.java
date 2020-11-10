package dao;

import java.util.List;

import entity.Custommer;
import entity.ServiceCharge;

public interface ListCustommerDao {
	List<Custommer> getListCustommer();

	List<ServiceCharge> getListServiceCharge();

	void setStatusAndDate(String id, String status, String date);
}