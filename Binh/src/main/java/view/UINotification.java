package view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import controller.ListCustommerController;
import controller.ListCustommerControllerImpl;

import static utils.CompUtils.*;

@SuppressWarnings("serial")
public class UINotification extends JFrame {

	private JLabel lbNot;
	private JButton btNot;

	private final Font font = new Font("Tahoma", Font.PLAIN, 13);

	private final String idCustommer;
	private final ListCustommerController controller;

	public UINotification(String idCustommer) {
		this.idCustommer = idCustommer;
		controller = new ListCustommerControllerImpl();

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
		check();
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
				if (!check()) {
					controller.setStatus(idCustommer);
				}

				setVisible(isUndecorated());
			}
		});
	}

	private boolean check() {
		if (controller.checkStatusCustommer(idCustommer)) {
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