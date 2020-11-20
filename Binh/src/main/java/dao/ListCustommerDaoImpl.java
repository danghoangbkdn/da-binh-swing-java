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
	public List<Custommer> getListCustommers() {
		List<Custommer> list = new ArrayList<>();
		Connection con = connection.getConnection();
		String query = "SELECT * FROM khachhang kh JOIN phidichvu pdv ON pdv.MaPDV = kh.MaPDV";

		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				Custommer custommer = new Custommer(rs.getString("MaKH"), rs.getString("TenKH"), rs.getString("SDT"),
						rs.getString("DiaChi"), rs.getString("NgaySD"), rs.getString("NgayHetHan"),
						rs.getBoolean("TrangThai"), rs.getString("NgayThuPhi"), rs.getString("MaPDV"),
						rs.getString("TenLoaiDV"), rs.getFloat("TienDV"));
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

	@Override
	public Custommer getCustommer(String id) {
		Custommer custommer = null;
		Connection con = connection.getConnection();
		String query = "SELECT * FROM khachhang kh JOIN phidichvu pdv ON pdv.MaPDV = kh.MaPDV WHERE kh.MaKH = '" + id
				+ "'";
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				custommer = new Custommer(rs.getString("MaKH"), rs.getString("TenKH"), rs.getString("SDT"),
						rs.getString("DiaChi"), rs.getString("NgaySD"), rs.getString("NgayHetHan"),
						rs.getBoolean("TrangThai"), rs.getString("NgayThuPhi"), rs.getString("MaPDV"),
						rs.getString("TenLoaiDV"), rs.getFloat("TienDV"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closed(rs, st, con);
		}

		return custommer;
	}
}