import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.EventHandler;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import DAODTO.AnorderDAO;
import DAODTO.AnorderDTO;

public class Billorder extends JPanel implements ActionListener, ItemListener {

	JPanel panelbillorderall, panelbillall, panelorderall, panelbill,
			panelbillcontent, paneltotalprice;

	JPanel[] panelordercontents;

	ScrollPane scrollbill;

	JButton btnpay, btndelete, btnallcomplete;

	static JButton btnfirstmain;

	JLabel lbname, lbtotal, lbprice, lbwon;

	JLabel[] lbnames, lbamounts, lbtotals,lbwons;

	JRadioButton[] radio;
	Checkbox[] checkbox;

	int[] nums;
	int tablecodes, totalprice, num, n, i, c,x;

	String query;

	AnorderDAO anorderdao = new AnorderDAO();

	public Billorder(int tablecode) {
		
		tablecodes= tablecode;

		System.out.println(tablecode + "�̴�!");

		panelbillorderall = new JPanel(null);
		panelbillall = new JPanel(null);
		panelbillall.setBackground(Color.blue);
		panelorderall = new JPanel(null){
			public void paint(Graphics g) {

				Image imgposter = Toolkit.getDefaultToolkit().getImage(
						"image/eventposter/"
								+ "ordermain" + ".jpg");

				g.drawImage(imgposter, 0, 0, 376, 250, panelorderall);

			}
		};
		panelbill = new JPanel(null);

		scrollbill = new ScrollPane(Scrollbar.VERTICAL);

		panelbillcontent = new JPanel(null);

		
		panelbillorderall.add(panelorderall);
		panelorderall.setBounds(0, 0, 376, 250);
		panelbillorderall.add(panelbillall);
		panelbillall.setBounds(0, 250, 376, 500);


		panelbillall.add(panelbill);
		panelbill.setBounds(16, 20, 346, 30);

		panelbillall.add(scrollbill);
		scrollbill.setBounds(16, 50, 346, 360);

		scrollbill.add(panelbillcontent);
		panelbillcontent.setBounds(0, 0, 360, 900);

		paneltotalprice = new JPanel(null);
		btnfirstmain = new JButton("첫 페이지");

		lbname = new JLabel("Table " + tablecode + "  전체 주문");
		lbtotal = new JLabel("합계");
		lbprice = new JLabel(Integer.toString(totalprice), SwingConstants.RIGHT); // 가격, 오른쪽 정렬
		lbwon = new JLabel("원");

		panelbillall.add(lbtotal);
		lbtotal.setBounds(250, 420, 50, 20);
		panelbillall.add(paneltotalprice);
		paneltotalprice.setBounds(290, 415, 55, 20);
		paneltotalprice.add(lbprice);
		lbprice.setBounds(0, 0, 50, 30);
		panelbillall.add(lbwon);
		lbwon.setBounds(350, 420, 50, 20);

		btnpay = new JButton("결제");
		btndelete = new JButton("삭제");
		btnallcomplete = new JButton("전체 완료");
		
		
		panelbillall.add(btnpay);
		btnpay.setBounds(15, 450, 114, 40);
		panelbillall.add(btndelete);
		btndelete.setBounds(132, 450, 114, 40);
		panelbillall.add(btnallcomplete);
		btnallcomplete.setBounds(249, 450, 114, 40);

		
		panelbill.add(btnfirstmain);
		btnfirstmain.setBounds(244, 2, 100, 26);
		panelbill.add(lbname);
		lbname.setBounds(120, 6, 150, 20);

		query = "SELECT * FROM anorder WHERE `condition` >=2 AND tablecode=? ORDER BY name";

		// anorderdao.showbill(query,tablecode);
		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
		list = anorderdao.showbill(query, tablecode);

		n = anorderdao.n;

		if (n != 0 && n > 9) {
			panelbillcontent.setLayout(new GridLayout(n, 0));
		}
		if (n != 0 && n < 10) {
			panelbillcontent.setLayout(new GridLayout(10, 0));
		}
		panelordercontents = new JPanel[n];
		lbnames = new JLabel[n];
		lbamounts = new JLabel[n];
		lbtotals = new JLabel[n];
		radio = new JRadioButton[n];
		checkbox = new Checkbox[n];
		nums = new int[n];
		lbwons = new JLabel[n];
		for (AnorderDTO dto : list) {

			panelordercontents[i] = new JPanel();
			lbnames[i] = new JLabel(dto.getName());
			lbamounts[i] = new JLabel("  x  "
					+ Integer.toString(dto.getAmount()) + " 개    ");
			lbtotals[i] = new JLabel(Integer.toString(dto.getAnorder()));
			lbwons[i] = new JLabel("원");
			radio[i] = new JRadioButton();
			checkbox[i] = new Checkbox();
			checkbox[i].setState(true);
			nums[i] = dto.getNum();
			if (dto.getCondition() == 3) {
				lbnames[i].setForeground(Color.RED);
				lbamounts[i].setForeground(Color.RED);
				lbtotals[i].setForeground(Color.RED);
				checkbox[i].setState(false);
				checkbox[i].addItemListener(this);
			}
			panelordercontents[i].add(radio[i]);
			radio[i].setBounds(0, 0, 50, 30);
			panelordercontents[i].add(lbnames[i]);
			lbnames[i].setBounds(50, 0, 150, 30);
			panelordercontents[i].add(lbamounts[i]);
			lbamounts[i].setBounds(2200, 0, 100, 30);
			panelordercontents[i].add(lbtotals[i]);
			lbtotals[i].setBounds(270, 0, 80, 30);
			panelordercontents[i].add(lbwons[i]);
			lbwons[i].setBounds(330, 0, 50, 30);
			panelordercontents[i].add(checkbox[i]);
			checkbox[i].setBounds(360, 0, 50, 30);


			totalprice = totalprice + dto.getAnorder();
			lbprice.setText(Integer.toString(totalprice));

			panelbillcontent.add(panelordercontents[i]);

			System.out.println(dto.getName());

			i++;
		}
		btnfirstmain.addActionListener(this);
		btnpay.addActionListener(this);
		btndelete.addActionListener(this);
		btnallcomplete.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnallcomplete) {
			for (int i = 0; i < n; i++) {
				if (!checkbox[i].getState()) { // checkbox가 선택되지 않은 경우
					checkbox[i].setState(true); // 체크박스 선택 상태로 설정
					query = "UPDATE anorder SET `condition` = ? WHERE num = ?";
					int condition = 2;
					num = nums[i];
					anorderdao.updateshowbill(query, num, condition);

					lbnames[i].setForeground(Color.BLACK);
					lbamounts[i].setForeground(Color.BLACK);
					lbtotals[i].setForeground(Color.BLACK);
				}
			}
		} else if (e.getSource() == btndelete) {
			for (int i = 0; i < n; i++) {
				if (radio[i].isSelected()) { // 라디오 버튼이 선택된 경우
					num = nums[i];
					query = "DELETE FROM anorder WHERE num=?";
					anorderdao.deletecontent(query, num);
					totalprice = totalprice - Integer.parseInt(lbtotals[i].getText());
					lbprice.setText(Integer.toString(totalprice));

					panelordercontents[i].setVisible(false);
					x++;

					if (x == n) {
						MainGui.showorder[tablecodes].setBackground(Color.LIGHT_GRAY);
						MainGui.lbcondition[tablecodes].setText("");
					}
				} else {
					c++;

					if (c == n) {
						JOptionPane.showMessageDialog(this, "삭제할 항목을 선택하세요.");
					}
				}
			}
		} else if (e.getSource() == btnpay) {
			if (totalprice != 0) {
				JOptionPane optionpane = new JOptionPane("주문을 결제하시겠습니까?",
						JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				int identifyorder = optionpane.showConfirmDialog(null,
						totalprice + " 원 입니다. 결제하시겠습니까?", "결제 확인창",
						JOptionPane.YES_NO_OPTION);
				if (identifyorder == optionpane.YES_OPTION) {
					for (int i = 0; i < n; i++) {
						query = "UPDATE anorder SET `condition` = ? WHERE num = ?";
						int condition = 1;
						num = nums[i];
						anorderdao.updateshowbill(query, num, condition);
						panelordercontents[i].setVisible(false);
					}

					MainGui.showorder[tablecodes].setBackground(Color.LIGHT_GRAY);
					MainGui.lbcondition[tablecodes].setText("");
				}
			}
		}

		if (e.getSource() == btnfirstmain) {
			MainGui.cardleft.show(MainGui.panelmainshow, "1");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		for (int i = 0; i < n; i++) {
			if (checkbox[i].getState() == true) {
				query = "UPDATE anorder SET `condition` = ? WHERE num = ?";
				int condition = 2;
				num = nums[i];
				anorderdao.updateshowbill(query, num, condition);

				lbnames[i].setForeground(Color.BLACK);
				lbamounts[i].setForeground(Color.BLACK);
				lbtotals[i].setForeground(Color.BLACK);
			}
		}

	}

}
