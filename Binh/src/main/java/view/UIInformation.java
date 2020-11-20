package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import entity.Custommer;
import tcp_ip.ClientHandling;

@SuppressWarnings("serial")
public class UIInformation extends JFrame {

	private JScrollPane spResult;
	private JTextArea taResult;
	private JButton btRecorded;
	private JButton btCancel;

	private JTable tbResult;
	private Object[][] custommerObj;
	private List<Custommer> listCustommers;

	private final Custommer custommer;
	private final ClientHandling client;

	public UIInformation(Custommer custommer, ClientHandling client, JTable tbResult, Object[][] custommerObj,
			List<Custommer> listCustommers) {
		this.custommer = custommer;
		this.client = client;
		this.tbResult = tbResult;
		this.custommerObj = custommerObj;
		this.listCustommers = listCustommers;

		initComponents();
		initEvents();
	}

	private void initComponents() {
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		setTitle("Thông tin chi tiết");

		taResult = new JTextArea();
		spResult = new JScrollPane();
		btRecorded = new JButton();
		btCancel = new JButton();

		spResult.setViewportView(taResult);
		spResult.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spResult.setBackground(Color.WHITE);
		spResult.setForeground(Color.BLACK);
		spResult.setBounds(30, 15, 320, 180);
		spResult.getViewport().setEnabled(false);
		add(spResult);

		btRecorded.setFont(new Font("Tahoma", Font.BOLD, 13));
		btRecorded.setText("Thu Phí");
		btRecorded.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btRecorded.setFocusable(false);
		btRecorded.setBounds(70, 210, 80, 35);
		add(btRecorded);

		btCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btCancel.setText("Hủy");
		btCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btCancel.setFocusable(false);
		btCancel.setBounds(240, 210, 80, 35);
		add(btCancel);
	}

	private void initEvents() {
		taResult.setText(showInfor());

		btRecorded.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new UINotification(custommer, client, tbResult, custommerObj, listCustommers).setVisible(true);
			}
		});

		btCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setVisible(isUndecorated());
			}
		});
	}

	private String showInfor() {
		String result = "";
		int index = 1;
		result = "       THÔNG TIN KHÁCH HÀNG:\n\t" + index++ + ". Mã KH: " + custommer.getId() + "\n\t" + index++
				+ ". Tên KH: " + custommer.getFullname() + "\n\t" + index++ + ". Ngày SD: " + custommer.getNgaySD()
				+ "\n\t" + index++ + ". Ngày HH: " + custommer.getNgayHH() + "\n       THÔNG TIN THANH TOÁN:\n\t"
				+ index++ + ". Trạng Thái: " + custommer.getStatus()
				+ ((custommer.getNgayTP() != null) ? ("\n\t" + index++ + ". Ngày Thu Phí: " + custommer.getNgayTP())
						: "")
				+ "\n       THÔNG TIN DỊCH VỤ:\n\t" + index++ + ". Mã PDV: " + custommer.getId() + "\n\t" + index++
				+ ". Tên Loại DV: " + custommer.getFullname() + "\n\t" + index++ + ". Tiền Phí DV: "
				+ custommer.getPrice();
		return result;
	}
}