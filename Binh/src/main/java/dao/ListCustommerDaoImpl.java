package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionManager;
import connection.ConnectionManagerImpl;
import entity.Custommer;
import entity.ServiceCharge;
import utils.SQLUtils;

public class ListCustommerDaoImpl implements ListCustommerDao {

	private final ConnectionManager connection;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;

	public ListCustommerDaoImpl() {
		connection = new ConnectionManagerImpl();
	}

	@Override
	public List<Custommer> getListCustommer() {
		List<Custommer> list = new ArrayList<>();
		Connection con = connection.getConnection();
		String query = "SELECT * FROM khachhang";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				Custommer custommer = new Custommer(rs.getString("MaKH"), rs.getString("TenKH"), rs.getString("SDT"),
						rs.getString("DiaChi"), rs.getString("NgaySD"), rs.getString("NgayHetHan"),
						rs.getBoolean("TrangThai"), rs.getString("NgayThuPhi"), rs.getString("MaPDV"));
				list.add(custommer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closed(rs, st, con);
		}

		return list;
	}

	@Override
	public List<ServiceCharge> getListServiceCharge() {
		List<ServiceCharge> list = new ArrayList<>();
		Connection con = connection.getConnection();
		String query = "SELECT * FROM phidichvu pdv JOIN khachhang kh ON pdv.MaPDV = kh.MaPDV";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				ServiceCharge custommer = new ServiceCharge(rs.getString("MaPDV"), rs.getString("TenLoaiDV"),
						rs.getFloat("TienDV"), rs.getString("MaKH"));
				list.add(custommer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closed(rs, st, con);
		}

		return list;
	}

	@Override
	public void setStatusAndDate(String id, String status, String date) {
		Connection con = connection.getConnection();
		boolean stt = status.equals("Đã Thu") ? true : false;
		String query = "UPDATE `khachhang` SET TrangThai = ?, NgayThuPhi = ? WHERE MaKH = ?";

		try {
			pst = con.prepareStatement(query);
			pst.setBoolean(1, stt);
			pst.setString(2, date);
			pst.setString(3, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closed(pst, con);
		}
	}
}