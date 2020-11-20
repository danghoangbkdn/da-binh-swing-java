package view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import entity.Custommer;
import entity.Request;
import tcp_ip.ClientHandling;

import static utils.CompUtils.*;

@SuppressWarnings("serial")
public class UINotification extends JFrame {

	private JLabel lbNot;
	private JButton btNot;

	private JTable tbResult;
	private Object[][] custommerObj;
	private List<Custommer> listCustommers;

	private final Font font = new Font("Tahoma", Font.PLAIN, 13);

	private Custommer custommer;
	private final ClientHandling client;

	public UINotification(Custommer custommer, ClientHandling client, JTable tbResult, Object[][] custommerObj,
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
		setSize(250, 120);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Thông báo");

		lbNot = new JLabel();
		btNot = new JButton();

		lbNot.setFont(font);
		checkStatus();
		add(lbNot);

		btNot.setFont(font);
		btNot.setText("OK");
		btNot.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btNot.setFocusable(false);
		btNot.setBounds((250 - getPreWidth(btNot)) / 2 - 5, 35, getPreWidth(btNot), getPreHeight(btNot));
		add(btNot);
	}

	private void initEvents() {
		btNot.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!checkStatus()) {
					Request request = new Request("setStatus");
					request.setCustommer(custommer);
					custommer = client.setStatus(request);
					Custommer custommer = client.getCustommer(request);
					custommer.setNgayTP(new Date());
					System.out.println(custommer);
					for (int i = 0; i < custommerObj.length; i++) {
						if (custommerObj[i][0].equals(custommer.getId())) {
							custommerObj[i][4] = custommer.getStatus();
							tbResult.setValueAt(custommerObj[i][4], tbResult.getSelectedRow(), 4);
							listCustommers.set(i, custommer);
							break;
						}
					}
				}
				setVisible(isUndecorated());
			}
		});
	}

	private boolean checkStatus() {
		if (custommer.getStatus().equals("Đã thu")) {
			lbNot.setText("Khánh hàng đã nộp tiền dịch vụ!");
			lbNot.setBounds((250 - getPreWidth(lbNot)) / 2, 15, getPreWidth(lbNot), getPreHeight(lbNot));
			return true;
		} else {
			lbNot.setText("Xác nhận thanh toán!");
			lbNot.setBounds((250 - getPreWidth(lbNot)) / 2, 15, getPreWidth(lbNot), getPreHeight(lbNot));
			return false;
		}
	}
}