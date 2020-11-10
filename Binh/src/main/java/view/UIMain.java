package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controller.ListCustommerController;
import controller.ListCustommerControllerImpl;

import static utils.CompUtils.*;

@SuppressWarnings("serial")
public class UIMain extends JFrame {

	private JLabel lbTitle;
	private JLabel lbSugges;
	private JTextField tfSearch;
	private JLabel lbNot;
	private JButton btSearch;
	private JScrollPane spResult;
	private JTable tbResult;
	private DefaultTableModel tbmdResult;
	private String[] columnNames;

	private final ListCustommerController controller;

	public UIMain() {
		controller = new ListCustommerControllerImpl();

		initComponents();
		initEvents();
	}

	private void initComponents() {
		setSize(800, 500);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Thu phí dịch vụ");

		lbTitle = new JLabel();
		lbSugges = new JLabel();
		tfSearch = new JTextField();
		lbNot = new JLabel();
		btSearch = new JButton();
		spResult = new JScrollPane();
		tbResult = new JTable();
		tbmdResult = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		columnNames = new String[] { "Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại", "Địa Chỉ", "Trạng Thái" };

		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lbTitle.setText("Thu Phí Dịch Vụ Internet");
		lbTitle.setToolTipText("");
		lbTitle.setBounds(170, 20, getPreWidth(lbTitle), getPreHeight(lbTitle));
		add(lbTitle);

		lbSugges.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbSugges.setText("Nhập mã khách hàng (VD: KH01, ...)");
		lbSugges.setBounds(210, 90, getPreWidth(lbSugges), getPreHeight(lbSugges));
		add(lbSugges);

		tfSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfSearch.setBounds(210, 110, 220, 35);
		add(tfSearch);

		lbNot.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbNot.setForeground(Color.RED);
		lbNot.setText("Không tìm thấy!");
		lbNot.setVisible(false);
		lbNot.setBounds(210, 130 + getPreHeight(tfSearch), getPreWidth(lbNot), getPreHeight(lbNot));
		add(lbNot);

		btSearch.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btSearch.setText("Search");
		btSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btSearch.setFocusable(false);
		btSearch.setBounds(500, 110, 70, 35);
		add(btSearch);

		tbResult.setModel(tbmdResult);
		tbResult.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spResult.setBounds(80, 170, 640, 270);
		setTableColumns();
		setTableRows();
		spResult.setViewportView(tbResult);
		add(spResult);
	}

	private void initEvents() {

		tfSearch.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					convert();
				}
			}
		});

		tbResult.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				String idCostommer = tbResult.getValueAt(tbResult.getSelectedRow(), 0).toString();
				new UIInformation(idCostommer).setVisible(true);
				tbResult.setValueAt(controller.getCustommer(idCostommer), tbResult.getSelectedRow(), 4);
			}
		});

		btSearch.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				convert();
			}
		});
	}

	private void convert() {
		String input = tfSearch.getText();

		if (input.isEmpty()) {
			return;
		} else if (controller.getInfor(input).equals("")) {
			lbNot.setVisible(true);
		} else {
			new UIInformation(input).setVisible(true);
			lbNot.setVisible(false);
		}
	}

	public void setTableColumns() {
		tbmdResult.addColumn(columnNames);

		tbmdResult.setColumnIdentifiers(columnNames);
		tbResult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbResult.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbResult.getColumnModel().getColumn(1).setPreferredWidth(170);
		tbResult.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbResult.getColumnModel().getColumn(3).setPreferredWidth(170);
		tbResult.getColumnModel().getColumn(4).setPreferredWidth(80);
	}

	private void setTableRows() {
		for (Object[] cus : controller.getListCustommer()) {
			tbmdResult.addRow(new Object[] { cus[0], cus[1], cus[2], cus[3], cus[4] });
		}
	}
}