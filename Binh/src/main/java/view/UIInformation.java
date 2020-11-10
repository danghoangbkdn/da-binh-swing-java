package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import controller.ListCustommerController;
import controller.ListCustommerControllerImpl;

@SuppressWarnings("serial")
public class UIInformation extends JFrame {

	private JScrollPane spResult;
	private JTextArea taResult;
	private JButton btRecorded;
	private JButton btCancel;

	private final String idCustommer;
	private final ListCustommerController controller;

	public UIInformation(String idCustommer) {
		this.idCustommer = idCustommer;
		controller = new ListCustommerControllerImpl();

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
		taResult.setText(controller.getInfor(idCustommer));

		btRecorded.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				new UINotification(idCustommer).setVisible(true);
			}
		});

		btCancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO
				setVisible(isUndecorated());
			}
		});
	}
}