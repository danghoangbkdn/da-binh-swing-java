package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import tcp_ip.ClientHandling;

import static utils.CompUtils.*;

@SuppressWarnings("serial")
public class UIStart extends JFrame {

	private JLabel lbTitle;
	private JLabel lbNot;
	private JButton btNot;

	private Container con = getContentPane();
	private final String status;
	private final ClientHandling client;

	public UIStart(String status, Socket socket) {
		this.status = status;
		client = new ClientHandling(socket);
		client.run();

		initComponent();
		initEvents();
	}

	private void initComponent() {
		setSize(400, 300);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setTitle("Thu Tiền Internet");
		con.setBackground(Color.BLACK);

		lbTitle = new JLabel();
		lbNot = new JLabel();
		btNot = new JButton();

		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbTitle.setForeground(Color.WHITE);
		lbTitle.setText("Thu Phí Dịch Vụ Iternet");
		lbTitle.setBounds((400 - getPreWidth(lbTitle)) / 2 - 10, 30, getPreWidth(lbTitle), getPreHeight(lbTitle));
		add(lbTitle);

		lbNot.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lbNot);

		btNot.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btNot.setText("OK");
		btNot.setVisible(false);
		btNot.setFocusable(false);
		btNot.setBounds((395 - getPreWidth(btNot)) / 2, 210 - getPreHeight(lbTitle), getPreWidth(btNot),
				getPreHeight(btNot));
		add(btNot);
	}

	private void initEvents() {
		lbNot.setForeground(Color.WHITE);
		lbNot.setText(status);
		lbNot.setBounds((400 - getPreWidth(lbNot)) / 2, 170 - getPreHeight(lbTitle), getPreWidth(lbNot),
				getPreHeight(lbNot));

		btNot.setVisible(true);
		btNot.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (status.equals("Kết nối thành công!")) {
					new UIMain(client).setVisible(true);
					setVisible(isUndecorated());
				} else {
					System.exit(0);
				}
			}
		});

	}
}