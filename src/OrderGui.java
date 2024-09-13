import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import DAODTO.AnorderDAO;
import DAODTO.AnorderDTO;
import DAODTO.MenuDAO;
import DAODTO.MenuDTO;

public class OrderGui extends JPanel implements ActionListener {

	JPanel panelorderall, panelbill, panelmenuall, panelmenu, panelcontentall,
			panelcontent, panelbackkind;
	JPanel panelselectall, panelselect;
	JButton btndelete, btncancle, btnconpletion;
	JTable tableorder;
	DefaultTableModel ordermodel;
	DefaultTableColumnModel ordercolumnmodel;

	TableColumn column1;
	TableColumn column2;
	TableColumn column3;

	CardLayout cardorder;
	JScrollPane scrolltable;
	ScrollPane scrollmenu;
	JButton btnhamburger, btnchicken, btndrink, btndessert, btnhamburgerset,
			btnbackorder;
	JButton[] btnfood;

	String[] ordertitle = { "상품명", "주문수량", "금액" };

	JLabel lbtotal, lbtotalprice, lbtototalwon;
	int totalprice;

	Vector vectordata = new Vector();

	String kind;
	JButton[] btnkind;
	String selectname; //
	int n, i;
	int number, price;
	String name;
	// ==========

	Frame frametablenum;
	JButton selectbtnup, selectbtndown, selectbtncancle, selectbtnorder;
	int amount = 1;
	TextField txtprice, txtamount;

	// ================버튼
	JButton btnnumber1, btnnumber2, btnnumber3, btnnumber4, btnnumber5,
			btnnumber6, btnnumber7, btnnumber8, btnnumber9, btnnumber0;
	JButton btntablenumok, btntablenumcancel;
	JLabel lbtablenum;
	int[] inttablenum = new int[10];
	int tablenum, x;

	OrderGui() {

		cardorder = new CardLayout();

		panelorderall = new JPanel(null);
		panelorderall.setBackground(Color.CYAN);
		panelbill = new JPanel(null);
		panelbill.setBackground(Color.BLUE);
		panelmenuall = new JPanel(cardorder);
		panelmenuall.setBackground(Color.MAGENTA);

		// ------------------
		panelorderall.add(panelbill);
		panelbill.setBounds(0, 0, 376, 400);
		panelorderall.add(panelmenuall);
		panelmenuall.setBounds(0, 400, 376, 350);

		// ================================ 결제영역

		lbtotal = new JLabel("합계");
		lbtotalprice = new JLabel("          0");
		lbtototalwon = new JLabel("원");

		btndelete = new JButton("주문 삭제");
		btncancle = new JButton("주문 취소");
		btnconpletion = new JButton("주문 완료");

		ordermodel = new DefaultTableModel(ordertitle, 30) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			};
		};
		ordercolumnmodel = new DefaultTableColumnModel();
		column1 = new TableColumn(0, 110); // 첫 번째 열 크기 설정
		column1.setHeaderValue("상품명"); // 열 제목 설정
		column2 = new TableColumn(1, 2);
		column2.setHeaderValue("수량");
		column3 = new TableColumn(2, 10);
		column3.setHeaderValue("합계");
		ordercolumnmodel.addColumn(column1);
		ordercolumnmodel.addColumn(column2);
		ordercolumnmodel.addColumn(column3);

		tableorder = new JTable(ordermodel, ordercolumnmodel);

		scrolltable = new JScrollPane(tableorder);

		panelbill.add(scrolltable);
		scrolltable.setBounds(0, 0, 376, 300);

		tableorder.setBounds(0, 0, 300, 300);

		panelbill.add(lbtotal);
		lbtotal.setBounds(230, 320, 80, 20);
		panelbill.add(lbtotalprice);
		lbtotalprice.setBounds(270, 320, 120, 20);
		panelbill.add(lbtototalwon);
		lbtototalwon.setBounds(330, 320, 80, 20);

		panelbill.add(btncancle);
		btncancle.setBounds(10, 360, 115, 30);
		panelbill.add(btndelete);
		btndelete.setBounds(130, 360, 115, 30);
		panelbill.add(btnconpletion);
		btnconpletion.setBounds(250, 360, 115, 30);

		// ===================================���� �г�

		panelmenu = new JPanel(null);

		scrollmenu = new ScrollPane();
		panelcontentall = new JPanel(new BorderLayout());

		panelbackkind = new JPanel();

		btnhamburger = new JButton("햄버거");
		btnchicken = new JButton("치킨");
		btndrink = new JButton("음료");
		btndessert = new JButton("디저트");
		btnhamburgerset = new JButton("세트메뉴");
		btnbackorder = new JButton("이전화면");
		panelselectall = new JPanel(null);
		panelselectall.setBackground(Color.WHITE);

		panelmenuall.add(panelmenu, "1");
		panelmenuall.add(panelcontentall, "2");
		panelmenuall.add(panelselectall, "3");

		// MainGui maingui = new MainGui();
		// panelmenuall.add(maingui.Panelposter,"4");

		panelmenu.add(btnhamburgerset);
		btnhamburgerset.setBounds(75, 60, 100, 100);
		panelmenu.add(btnhamburger);
		btnhamburger.setBounds(195, 60, 100, 100);

		panelmenu.add(btndrink);
		btndrink.setBounds(35, 200, 80, 80);
		panelmenu.add(btndessert);
		btndessert.setBounds(145, 200, 80, 80);
		panelmenu.add(btnchicken);
		btnchicken.setBounds(255, 200, 80, 80);

		panelcontentall.add(scrollmenu, "Center");
		panelcontentall.add(panelbackkind, "South");

		panelbackkind.add(btnbackorder);
		btncancle.addActionListener(this);
		btndelete.addActionListener(this);

		btnhamburger.addActionListener(this);
		btnchicken.addActionListener(this);
		btndrink.addActionListener(this);
		btndessert.addActionListener(this);
		btnhamburgerset.addActionListener(this);
		btnbackorder.addActionListener(this);

		// ---------------------------------------------------------------------------------

		selectbtnup = new JButton("+");
		selectbtndown = new JButton("-");
		selectbtncancle = new JButton("취소");
		selectbtnorder = new JButton("주문");

		selectbtnup.addActionListener(this);
		selectbtndown.addActionListener(this);
		selectbtncancle.addActionListener(this);
		selectbtnorder.addActionListener(this);
		btnconpletion.addActionListener(this);
		// ========================================================================
	}

	public void menuselect(String kind) {

		panelcontent = new JPanel(new GridLayout(0, 2));
		scrollmenu.add(panelcontent);

		name = null;
		MenuDAO orderdao = new MenuDAO();

		String query = "SELECT * FROM menu WHERE kind=?";
		orderdao.select(query, kind, name);
		n = orderdao.n;
		btnkind = new JButton[n];

		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
		list = orderdao.select(query, kind, name);

		for (MenuDTO dto : list) {

			btnkind[i] = new JButton(dto.getName());
			btnkind[i].addActionListener(this);
			panelcontent.add(btnkind[i]);
			i++;

		}

		cardorder.show(panelmenuall, "2");

	}

	public void choiceselect(String kind, String name) {

		JTextArea txtexplain;
		JLabel selectlbimg, selectlbname, selectlbcalorie, selectlbmeterial, selectlbprice, selectlbx, selectlbwon, selectlbamount;
		String selectkind, slectmeterial, selectexplain, selectprice, selectamount;
		ImageIcon selectimg = null;

		panelselect = new JPanel(null);
		panelselectall.add(panelselect);
		panelselect.setBounds(0, 0, 376, 350);

		txtprice = new TextField();
		txtprice.setEditable(false);
		txtexplain = new JTextArea(185, 100);
		txtexplain.setEditable(false);

		txtamount = new TextField(Integer.toString(amount));
		txtamount.setEditable(false);
		selectlbname = new JLabel();
		selectlbcalorie = new JLabel();
		selectlbmeterial = new JLabel();
		selectlbprice = new JLabel();
		selectlbx = new JLabel("x");
		selectlbwon = new JLabel("원");
		selectlbamount = new JLabel("개");

		String query = "SELECT * FROM menu WHERE kind = ? and name = ?";
		MenuDAO orderdao = new MenuDAO();
		orderdao.select(query, kind, name);
		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
		list = orderdao.select(query, kind, name);

		for (MenuDTO dto : list) {
			selectimg = new ImageIcon(
					"image/"
							+ dto.getKind() + "/" + dto.getName() + ".jpg");
			selectimg = new ImageIcon(selectimg.getImage().getScaledInstance(
					100, 100, Image.SCALE_REPLICATE));

			price = dto.getPrice();
			selectname = dto.getName();
			selectlbname.setText(selectname);
			selectlbcalorie
					.setText(Integer.toString(dto.getCalorie()) + "Kcal");
			selectlbmeterial.setText(dto.getMeterial());
			selectlbprice.setText(Integer.toString(price) + " 원");
			txtexplain.setText(dto.getExplain());
			txtprice.setText(Integer.toString(price));

		}

		selectlbimg = new JLabel(selectimg);
		panelselect.add(selectlbimg);
		selectlbimg.setBounds(30, 65, 100, 100);
		panelselect.add(selectlbname);
		selectlbname.setBounds(30, 15, 180, 30);
		panelselect.add(selectlbcalorie);
		selectlbcalorie.setBounds(295, 15, 180, 30);
		panelselect.add(selectlbmeterial);
		selectlbmeterial.setBounds(30, 180, 150, 30);
		panelselect.add(selectlbprice);
		selectlbprice.setBounds(300, 180, 100, 30);
		txtexplain.setLineWrap(true); // ---- 입력된 텍스트 내용이 길면 자동으로 줄바꿈을 처리한다.
		panelselect.add(txtexplain);
		txtexplain.setBounds(160, 65, 185, 100);
		panelselect.add(txtprice);
		txtprice.setBounds(50, 250, 50, 20);
		panelselect.add(selectlbwon);
		selectlbwon.setBounds(110, 250, 40, 20);
		panelselect.add(selectlbx);
		selectlbx.setBounds(150, 250, 30, 20);
		panelselect.add(txtamount);
		txtamount.setBounds(190, 250, 30, 20);
		panelselect.add(selectlbamount);
		selectlbamount.setBounds(230, 250, 30, 20);
		panelselect.add(selectbtnup);
		selectbtnup.setBounds(280, 237, 45, 20);
		panelselect.add(selectbtndown);
		selectbtndown.setBounds(280, 262, 45, 20);
		panelselect.add(selectbtncancle);
		selectbtncancle.setBounds(10, 310, 178, 30);
		panelselect.add(selectbtnorder);
		selectbtnorder.setBounds(188, 310, 178, 30);

	}

	public void tablenum() {

		frametablenum = new Frame("테이블 번호를 입력해주세요");
		frametablenum.setLayout(null);

		lbtablenum = new JLabel(Integer.toString(tablenum));
		lbtablenum.setFont(new Font("돋움체", Font.BOLD, 30));

		btnnumber1 = new JButton("1");
		btnnumber2 = new JButton("2");
		btnnumber3 = new JButton("3");
		btnnumber4 = new JButton("4");
		btnnumber5 = new JButton("5");
		btnnumber6 = new JButton("6");
		btnnumber7 = new JButton("7");
		btnnumber8 = new JButton("8");
		btnnumber9 = new JButton("9");
		btnnumber0 = new JButton("0");
		btntablenumok = new JButton("확인");
		btntablenumcancel = new JButton("취소");


		frametablenum.add(lbtablenum);
		lbtablenum.setBounds(124, 25, 50, 50);

		frametablenum.add(btnnumber1);
		btnnumber1.setBounds(13, 90, 50, 50);
		frametablenum.add(btnnumber2);
		btnnumber2.setBounds(65, 90, 50, 50);
		frametablenum.add(btnnumber3);
		btnnumber3.setBounds(117, 90, 50, 50);
		frametablenum.add(btnnumber4);
		btnnumber4.setBounds(169, 90, 50, 50);
		frametablenum.add(btnnumber5);
		btnnumber5.setBounds(221, 90, 50, 50);
		frametablenum.add(btnnumber6);
		btnnumber6.setBounds(13, 142, 50, 50);
		frametablenum.add(btnnumber7);
		btnnumber7.setBounds(65, 142, 50, 50);
		frametablenum.add(btnnumber8);
		btnnumber8.setBounds(117, 142, 50, 50);
		frametablenum.add(btnnumber9);
		btnnumber9.setBounds(169, 142, 50, 50);
		frametablenum.add(btnnumber0);
		btnnumber0.setBounds(221, 142, 50, 50);

		frametablenum.add(btntablenumok);
		btntablenumok.setBounds(13, 195, 127, 40);
		frametablenum.add(btntablenumcancel);
		btntablenumcancel.setBounds(143, 195, 127, 40);

		frametablenum.setSize(300, 275);
		frametablenum.setLocation(50, 500);
		frametablenum.setVisible(true);

		btnnumber1.addActionListener(this);
		btnnumber2.addActionListener(this);
		btnnumber3.addActionListener(this);
		btnnumber4.addActionListener(this);
		btnnumber5.addActionListener(this);
		btnnumber6.addActionListener(this);
		btnnumber7.addActionListener(this);
		btnnumber8.addActionListener(this);
		btnnumber9.addActionListener(this);
		btnnumber0.addActionListener(this);
		btntablenumok.addActionListener(this);
		btntablenumcancel.addActionListener(this);
		frametablenum.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {

				tablenum = 0;
				x = 0;
				// number = 0;
				// frametablenum.remove(0);
				frametablenum.dispose();

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});
		{

		}

	}

	public void regist(int tablecode) {
		System.out.println(tablecode + "번 테이블 등록");
		String sql = "INSERT INTO anorder(tablecode,kind,name,amount,anorder,time,`condition`) VALUES(?,?,?,?,?,NOW(),?)";
		String[] kindof = new String[number];
		String[] bname = new String[number];
		int[] bamount = new int[number];
		int[] banorder = new int[number];
		int condition = 3; // 주문 등록 상태
		String kind;

		AnorderDAO anorderdao = new AnorderDAO();
		for (int y = 0; y < number; y++) {

			bname[y] = (String) tableorder.getValueAt(y, 0);
			bamount[y] = (int) tableorder.getValueAt(y, 1);
			banorder[y] = (int) tableorder.getValueAt(y, 2);

			// -------------메뉴 정보에서 종류(kind)를 가져옴
			name = bname[y];

			MenuDAO menudao = new MenuDAO();
			menudao.fororder(name); // ----------------------------한번 실행1
			ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
			list = menudao.fororder(name); // ---------------------한번 실행2
			for (MenuDTO dto : list) {
				kindof[y] = dto.getKind();
			}
			name = null;
			// ------------------------
		}


		for (int i = 0; i < number; i++) {

			String name = bname[i];
			int amount = bamount[i];
			int anorder = banorder[i];
			kind = kindof[i];

			anorderdao.insertorder(sql, tablecode, kind, name, amount, anorder,
					condition);

		}

		JOptionPane.showMessageDialog(this, "주문이 완료되었습니다.");
		for (int i = 0; i < number; i++) {
			ordermodel.setValueAt("", i, 0);
			ordermodel.setValueAt("", i, 1);
			ordermodel.setValueAt("", i, 2);

		}
		lbtotalprice.setText(("        0"));
		MainGui.cardleft.show(MainGui.panelmainshow, "1");
		cardorder.show(panelmenuall, "1");

		number = 0;
		i=0;
		// showorder=0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnhamburger) {
			kind = "hamburger";
			menuselect(kind);

		}

		else if (e.getSource() == btnchicken) {
			kind = "chicken";
			menuselect(kind);

		} else if (e.getSource() == btndrink) {
			kind = "drink";
			menuselect(kind);

		} else if (e.getSource() == btndessert) {
			kind = "dessert";
			menuselect(kind);

		} else if (e.getSource() == btnhamburgerset) {
			kind = "hamburgerset";
			menuselect(kind);

		}

		if (e.getSource() == btnbackorder) {
			cardorder.show(panelmenuall, "1");

			panelcontent.remove(0);
			i = 0;

		}

		for (int i = 0; i < n; i++) {
			if (e.getSource() == btnkind[i]) {
				String name = btnkind[i].getText().toString();

				cardorder.show(panelmenuall, "3");
				choiceselect(kind, name);
			}

		}

		if (e.getSource() == selectbtnup) {

			amount = amount + 1;
			txtamount.setText(Integer.toString(amount));

		} else if (e.getSource() == selectbtndown) {
			if (amount > 1) {
				amount = amount - 1;
				txtamount.setText(Integer.toString(amount));

			}
		} else if (e.getSource() == selectbtncancle) {
			// panelmenuall.add(panelcontentall,);
			cardorder.show(panelmenuall, "2");
			amount = 1;
			txtamount.setText(Integer.toString(amount));
			panelselectall.remove(0);

		} else if (e.getSource() == selectbtnorder) {

			int order = price * amount;
			ordermodel.setValueAt(selectname, number, 0);
			ordermodel.setValueAt(amount, number, 1);
			ordermodel.setValueAt(order, number, 2);

			cardorder.show(panelmenuall, "2");
			amount = 1;
			txtamount.setText(Integer.toString(amount));
			panelselectall.remove(0);
			number++;
			totalprice = totalprice + order;
			lbtotalprice.setText(Integer.toString(totalprice));
		}

		if (e.getSource() == btndelete) {

			int selectline = tableorder.getSelectedRow();
			if (selectline == -1) {
				JOptionPane.showMessageDialog(this, "삭제할 항목을 선택해주세요");
				return;
			}

			int y = tableorder.getSelectedRow();
			int x = tableorder.getSelectedColumn();
			System.out.println(y + "행 " + x + "열");
			// System.out.println(tablebill.getValueAt(y, x));
			Object a = tableorder.getValueAt(y, x);
			// System.out.println(a+"값");

			if (a == null) {
				System.out.println("빈 셀");
			} else {
				number = number - 1;
				System.out.println("선택된 항목이 삭제되었습니다.");
			}

			// -----------행 추가
			vectordata.addElement("");
			vectordata.addElement("");
			vectordata.addElement("");
			ordermodel.removeRow(selectline);
			ordermodel.addRow(vectordata);
			totalprice = 0;
			for (int i = 0; i < number; i++) {
				Object totalprices = ordermodel.getValueAt(i, 2);
				totalprice = totalprice + (int) totalprices;
				System.out.println("전체 금액 = " + totalprice);
			}
			lbtotalprice.setText(Integer.toString(totalprice));
		}else if (e.getSource() == btnconpletion) {

			if (number != 0) {
				tablenum();

				// String kindof[y] = new String[number];

			}

			System.out.println("�ֹ���ư ����");
		}

		if (e.getSource() == btnnumber1) {
			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 1;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 1;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));

			}
		} else if (e.getSource() == btnnumber2) {
			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 2;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 2;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));
			}
		} else if (e.getSource() == btnnumber3) {
			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 3;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 3;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));
			}
		} else if (e.getSource() == btnnumber4) {

			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 4;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 4;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));
			}

		} else if (e.getSource() == btnnumber5) {
			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 5;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 5;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));
			}

		} else if (e.getSource() == btnnumber6) {
			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 6;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 6;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));
			}
		} else if (e.getSource() == btnnumber7) {
			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 7;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 7;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));
			}

		} else if (e.getSource() == btnnumber8) {
			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 8;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 8;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));
			}

		} else if (e.getSource() == btnnumber9) {
			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 9;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 9;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));
			}

		} else if (e.getSource() == btnnumber0) {
			x = x + 1;
			if (x == 1) {
				inttablenum[0] = 0;
				tablenum = inttablenum[0];
				lbtablenum.setText(Integer.toString(tablenum));
			} else if (x == 2) {
				inttablenum[1] = 0;
				tablenum = ((inttablenum[0] * 10) + inttablenum[1]);
				lbtablenum.setText(Integer.toString(tablenum));
				System.out.println(tablenum + "테이블 번호");
			}
		}
		if (e.getSource() == btntablenumok) {
			String query = "SELECT * FROM anorder WHERE `condition` >=2 AND tablecode=?";
			AnorderDAO anorderdao = new AnorderDAO();
			ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
			list = anorderdao.selectevaluation(query, tablenum);
			int n = anorderdao.n;

			if (n == 0) {
				System.out.println(tablenum + " 테이블 번호로 주문을 등록합니다.");
				regist(tablenum);
				tablenum = 0;
				x = 0;
				frametablenum.dispose();
			} else if (n != 0) {
				JOptionPane optionpane = new JOptionPane("주문을 등록하시겠습니까?",
						JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				int identifyorder = optionpane.showConfirmDialog(null,
						"이미 테이블 번호로 주문이 등록되어 있습니다. 추가로 주문을 등록하시겠습니까?", "주문 확인",
						JOptionPane.YES_NO_OPTION);
				if (identifyorder == optionpane.YES_OPTION) {
					System.out.println(tablenum + " 테이블 번호로 주문을 등록합니다.");
					regist(tablenum);
					tablenum = 0;
					x = 0;
					frametablenum.dispose();
				} else if (identifyorder == optionpane.NO_OPTION) {
					tablenum = 0;
					x = 0;
					lbtablenum.setText(Integer.toString(tablenum));
				}
			}
		} else if (e.getSource() == btntablenumcancel) {
			tablenum = 0;
			x = 0;
			frametablenum.dispose();

		}
		if (e.getSource() == btncancle) {

			if (number != 0) {
				System.out.println(number);
				JOptionPane optionpane = new JOptionPane("주문을 취소하시겠습니까?",
						JOptionPane.QUESTION_MESSAGE,
						JOptionPane.YES_NO_CANCEL_OPTION);
				int identifyorder = optionpane.showConfirmDialog(null,
						"주문을 취소하시겠습니까?", "주문 취소",
						JOptionPane.YES_NO_CANCEL_OPTION);

				if (identifyorder == optionpane.YES_OPTION) {

					System.out.println(number + "개 항목을 삭제합니다.");

					for (int i = 0; i <= number; i++) {
						System.out.println(i + "번째 항목 삭제 중");
						ordermodel.removeRow(0);

						vectordata.addElement("");
						vectordata.addElement("");
						vectordata.addElement("");
						ordermodel.addRow(vectordata);
						System.out.println(i + "번째 항목 삭제 완료");
					}

					number = 0;

					lbtotalprice.setText("     0");

					MainGui.cardleft.show(MainGui.panelmainshow, "1");

				} else if (identifyorder == optionpane.NO_OPTION) {

					System.out.println("No button clicked");

					//MainGui.cardleft.show(MainGui.panelmainshow, "1");

				} else if (identifyorder == optionpane.CANCEL_OPTION) {
					System.out.println("Cancel button clicked");
				}

			}
			if (number == 0) {
				MainGui.cardleft.show(MainGui.panelmainshow, "1");
				
			}

			//MainGui.cardleft.show(MainGui.panelmainshow, "1");
		}
	}
}
