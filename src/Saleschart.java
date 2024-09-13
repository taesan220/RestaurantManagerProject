import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import DAODTO.AnorderDAO;
import DAODTO.AnorderDTO;

public class Saleschart extends JPanel implements ActionListener, ItemListener {

	JPanel panelsaleschartall, panelchart,panelprice;

	JScrollPane jscrolltimechart, jscrollorderchart;

	CardLayout cardchart;

	Calendar calendar;

	JLabel lbdate,lbyear,lbmonth,lbday;

	JLabel lbtotalname,lbtotalprice,lbtotalwon;

	Choice choiceyear, choicemonth, choicedate;

	JLabel lbdatetotal, lbdateprice;

	JTable tabletimechart, tableorderchart;

	DefaultTableColumnModel timecolumnmodel, ordercolumnmodel;
	DefaultTableModel timemodel, ordermodel;

	TableColumn columntime1, columntime2, columntime3, columntime4,
			columntime5, columnorder1, columnorder2;

	JButton btntimedown, btntimeup, btnorderup, btnorderdown, btnback, btnnext,
			btnbackmain;

	String query = "SELECT * FROM anorder ORDER BY time DESC";
	String condition;
	int n, i, x = 1, v, s;

	String[] years, dates;
	String[] months = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12" };
	int year,lastday,totalprice;

	String yyyy,yyyy2, mm, dd;
	String mmquery,mmquery2, ddquery, ddquery2;

	public Saleschart(String query) {

		cardchart = new CardLayout();

		panelsaleschartall = new JPanel(null);
		panelchart = new JPanel(cardchart);
		panelprice = new JPanel(null);
		calendar = Calendar.getInstance();

		lbdate = new JLabel("날짜 검색");
		lbyear = new JLabel("년");
		lbmonth = new JLabel("월");
		lbday = new JLabel("일");

		choiceyear = new Choice();
		choicemonth = new Choice();
		choicedate = new Choice();

		lbtotalname = new JLabel("총 합계");
		lbtotalprice = new JLabel("");
		lbtotalwon = new JLabel("원");

		choiceyear.add("  ------");
		int year = calendar.get(calendar.YEAR);
		year = year - 2013;
		years = new String[year + 1];
		int year2 = 2013;

		for (int i = 0; i <= year; i++) {
			years[i] = Integer.toString(year2);
			choiceyear.addItem(years[i]);
			year2++; // ================================================연도 증가
		}

		choicemonth.add("  ----");
		for (int i = 1; i <= 12; i++) {
			choicemonth.add(String.valueOf(i));
		}

		choicedate.add("  ----");
		for (int i = 1; i <= 31; i++) {
			choicedate.add(String.valueOf(i));
		}

		lbdatetotal = new JLabel();
		lbdateprice = new JLabel();

		// ------------------------------timechart

		String[] tabletitle = { ".No", "상품명", "수량", "합계 금액", "주문 시간" };
		timemodel = new DefaultTableModel(tabletitle, 30) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		timecolumnmodel = new DefaultTableColumnModel();
		tabletimechart = new JTable(timemodel, timecolumnmodel);

		columntime1 = new TableColumn(0, 50);
		columntime1.setHeaderValue(tabletitle[0]);
		columntime2 = new TableColumn(1, 180);
		columntime2.setHeaderValue(tabletitle[1]);
		columntime3 = new TableColumn(2, 30);
		columntime3.setHeaderValue(tabletitle[2]);
		columntime4 = new TableColumn(3, 50);
		columntime4.setHeaderValue(tabletitle[3]);
		columntime5 = new TableColumn(4, 80);
		columntime5.setHeaderValue(tabletitle[4]);

		timecolumnmodel.addColumn(columntime1);
		timecolumnmodel.addColumn(columntime2);
		timecolumnmodel.addColumn(columntime3);
		timecolumnmodel.addColumn(columntime4);
		timecolumnmodel.addColumn(columntime5);

		// --------------------------------

		// ================================orderchart

		String[] ordertitle = { "상품명", "주문 수량" };

		ordermodel = new DefaultTableModel(ordertitle, 30) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		ordercolumnmodel = new DefaultTableColumnModel();
		tableorderchart = new JTable(ordermodel, ordercolumnmodel);

		columnorder1 = new TableColumn(0, 200);
		columnorder1.setHeaderValue("상품명");
		columnorder2 = new TableColumn(1, 80);
		columnorder2.setHeaderValue("주문 수량");

		ordercolumnmodel.addColumn(columnorder1);
		ordercolumnmodel.addColumn(columnorder2);

		jscrolltimechart = new JScrollPane(tabletimechart);
		jscrollorderchart = new JScrollPane(tableorderchart);

		// ================================

		btntimeup = new JButton("날짜+");
		btntimedown = new JButton("날짜-");
		btnorderup = new JButton("주문수량+");
		btnorderdown = new JButton("주문수량-");
		btnback = new JButton("이전");
		btnnext = new JButton("다음");
		btnbackmain = new JButton("메인으로");

		lbtotalname = new JLabel("총 합계");
		lbtotalprice = new JLabel(Integer.toString(totalprice), SwingConstants.RIGHT);
		lbtotalwon = new JLabel("원");



		panelsaleschartall.add(panelchart);
		panelchart.setBounds(10, 80, 358, 505);



		panelchart.add(jscrolltimechart, "1");
		// jscrolltimechart.setBounds(0, 0, 360, 505);

		panelchart.add(jscrollorderchart, "2");

		panelsaleschartall.add(panelprice);
		panelprice.setBounds(210, 630, 130, 20);


		panelsaleschartall.add(btntimeup);
		btntimeup.setBounds(19, 13, 80, 25);
		panelsaleschartall.add(btntimedown);
		btntimedown.setBounds(104, 13, 80, 25);
		panelsaleschartall.add(btnorderup);
		btnorderup.setBounds(189, 13, 80, 25);
		panelsaleschartall.add(btnorderdown);
		btnorderdown.setBounds(274, 13, 80, 25);


		panelsaleschartall.add(lbdate);
		lbdate.setBounds(39, 50, 70, 20);
		panelsaleschartall.add(lbyear);
		lbyear.setBounds(180, 50, 30, 20);
		panelsaleschartall.add(lbmonth);
		lbmonth.setBounds(250, 50, 30, 20);
		panelsaleschartall.add(lbday);
		lbday.setBounds(320, 50, 30, 20);


		panelsaleschartall.add(choiceyear);
		choiceyear.setBounds(115, 47, 60, 20);
		panelsaleschartall.add(choicemonth);
		choicemonth.setBounds(205, 47, 40, 20);
		panelsaleschartall.add(choicedate);
		choicedate.setBounds(275, 47, 40, 20);



		panelsaleschartall.add(btnback);
		btnback.setBounds(95, 595, 80, 20);
		panelsaleschartall.add(btnnext);
		btnnext.setBounds(195, 595, 80, 20);
		panelsaleschartall.add(btnbackmain);
		btnbackmain.setBounds(265, 660, 100, 30);
		chartcontent(query, condition, v, x);

		panelsaleschartall.add(lbtotalname);
		lbtotalname.setBounds(145, 630, 80, 20);
		panelprice.add(lbtotalprice);
		lbtotalprice.setBounds(0, 0, 130, 20);
		panelsaleschartall.add(lbtotalwon);
		lbtotalwon.setBounds(345, 630, 50, 20);






		btnback.addActionListener(this);
		btnnext.addActionListener(this);
		btntimeup.addActionListener(this);
		btntimedown.addActionListener(this);
		btnorderup.addActionListener(this);
		btnorderdown.addActionListener(this);
		btnbackmain.addActionListener(this);

		choiceyear.addItemListener(this);
		choicemonth.addItemListener(this);
		choicedate.addItemListener(this);


		cardchart.show(panelchart, "1");




		ManagerGui.btnsaleschart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String querychart = "SELECT * FROM anorder ORDER BY time DESC";
				condition=null;
				v=0;
				x=1;
				i=0;
				s=0;

				chartcontent(querychart,condition, v, x);
			}
		});
	}

	public void chartcontent(String query, String condition, int v, int x) {
		totalprice = 0;
		lbtotalname.setVisible(true);
		lbtotalprice.setVisible(true);
		lbtotalwon.setVisible(true);

		System.out.println(query);
		AnorderDAO anorderdao = new AnorderDAO();
		anorderdao.n = 0;
		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
		list = anorderdao.selectchart(query, condition);
		n = anorderdao.n;
		for (AnorderDTO dto : list) {

			totalprice = totalprice + dto.getAnorder();

			if (30 * v <= i && i < 30 * x) {

				if (condition == null) {
					timemodel.setValueAt(dto.getTablecode(), s, 0);
					timemodel.setValueAt(dto.getTime(), s, 4);
					timemodel.setValueAt(dto.getAnorder(), s, 3);
					timemodel.setValueAt(dto.getName(), s, 1);
					timemodel.setValueAt(dto.getAmount(), s, 2);

					System.out.println(dto.getName());

				} else if (condition != null) {

					ordermodel.setValueAt(dto.getName(), s, 0);
					ordermodel.setValueAt(dto.getAmount(), s, 1);

					lbtotalname.setVisible(false);
					lbtotalprice.setVisible(false);
					lbtotalwon.setVisible(false);

				}


				s++;
			}
			i++;

		}lbtotalprice.setText(Integer.toString(totalprice));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnback) {

			if (v > 0) {

				v--;
				x--;
				i = 0;
				s = 0;
				chartcontent(query, condition, v, x);
			} else {

				JOptionPane.showMessageDialog(this, "더 이상 이전 페이지가 없습니다.");
			}

		} else if (e.getSource() == btnnext) {
			if (30 * x < i) {
				for (int i = 0; i < 30; i++) {
					timemodel.setValueAt("", i, 0);
					timemodel.setValueAt("", i, 1);
					timemodel.setValueAt("", i, 2);
					timemodel.setValueAt("", i, 3);
					timemodel.setValueAt("", i, 4);
					ordermodel.setValueAt("", i, 0);
					ordermodel.setValueAt("", i, 1);
				}

				v++;
				x++;
				i = 0;
				s = 0;

				chartcontent(query, condition, v, x);
			} else {
				JOptionPane.showMessageDialog(this, "���̻��� ����� �����ϴ�.");
			}
		} else if (e.getSource() == btntimeup) {
			cardchart.show(panelchart, "1");
			query = "SELECT * FROM anorder ORDER BY time DESC";
			v = 0;
			x = 1;
			i = 0;
			s = 0;
			condition = null;
			chartcontent(query, condition, v, x);

		} else if (e.getSource() == btntimedown) {
			cardchart.show(panelchart, "1");
			query = "SELECT * FROM anorder ORDER BY time";
			v = 0;
			x = 1;
			i = 0;
			s = 0;
			condition = null;
			chartcontent(query, condition, v, x);
		} else if (e.getSource() == btnorderup) {
			cardchart.show(panelchart, "2");

			query = "SELECT name, SUM(amount) AS amount, time, anorder FROM anorder GROUP BY name, time, anorder ORDER BY amount DESC;";
			v = 0;
			x = 1;
			i = 0;
			s = 0;
			condition = "ss";
			chartcontent(query, condition, v, x);

		} else if (e.getSource() == btnorderdown) {

			cardchart.show(panelchart, "2");

			query = "SELECT name, SUM(amount) AS amount, time, anorder FROM anorder GROUP BY name, time, anorder ORDER BY amount;";
			v = 0;
			x = 1;
			i = 0;
			s = 0;
			condition = "ss";
			chartcontent(query, condition, v, x);

		}else if(e.getSource()==btnbackmain){

	/*		choicemonth.removeAll();
			choicedate.removeAll();
			
			choiceyear.select(0);
			choicemonth.addItem("  ----");
			choicedate.addItem("  ----");
			

			
			query="SELECT * FROM anorder ORDER BY time DESC";
			
			
			condition = null;
			v=0;
			x=1;
			i = 0;
			s=0;
			
			for (int i = 0; i < 30; i++) {
				timemodel.setValueAt("", i, 0);
				timemodel.setValueAt("", i, 1);
				timemodel.setValueAt("", i, 2);
				timemodel.setValueAt("", i, 3);
				timemodel.setValueAt("", i, 4);
			}
			
			chartcontent(query, condition, v, x);*/
			cardchart.show(panelchart, "1");
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		for (int i = 0; i < year + 1; i++) {

			if (e.getItem() == years[i]) {
				yyyy = years[i];

				choicemonth.removeAll();
				choicemonth.add("--");
				for (int x = 0; x < 12; x++) {
					choicemonth.addItem(months[x]);

				}

			}
		}

		for (int i = 0; i < 12; i++) {

			if (e.getItem() == months[i]) {
				choicedate.removeAll();
				choicedate.add("--");
				if (yyyy != null) {
					mm = months[i];

					calendar.set(Integer.parseInt(yyyy),
							(Integer.parseInt(months[i]) - 1), 1);

					lastday = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

					dates = new String[lastday + 1];

					for (int s = 1; s <= lastday; s++) {

						dates[s] = Integer.toString(s);

						choicedate.addItem(dates[s]);

					}

				}

			}
		}

		for (int z = 1; z < lastday + 1; z++) {

			if (e.getItem() == dates[z]) {
				cardchart.show(panelchart, "1");
				dd = dates[z];
				if (Integer.parseInt(dd) != lastday) {
					yyyy2 = yyyy;
					if (Integer.parseInt(mm) < 10) {
						mmquery = 0 + mm;
						mmquery2 = 0 + mm;
					}else if(Integer.parseInt(mm)>=10){
						mmquery = mm;
						mmquery2 = mm;
					}

					if (Integer.parseInt(dd) < 10) {
						ddquery = 0 + dd;

					} else {
						ddquery = dd;
					}

					if (Integer.parseInt(dd) < 9) {

						ddquery2 = "0"+ Integer.toString(Integer.parseInt(dd) + 1);
					} else {
						ddquery2 = Integer.toString(Integer.parseInt(dd) + 1);
					}
				}else if(Integer.parseInt(dd)==lastday){
					ddquery = dd;
					ddquery2 = 0 + Integer.toString(1);
					if(Integer.parseInt(mm)==12){

						yyyy2 = Integer.toString(Integer.parseInt(yyyy)+1);
						mmquery2="01";
						ddquery2 = "01";

					}else if (Integer.parseInt(mm) < 9) {
						yyyy2 = yyyy;
						mmquery = 0 + mm;
						mmquery2 = 0 + Integer.toString(Integer.parseInt(mm)+1);

					}
					else if(Integer.parseInt(mm) == 9){
						yyyy2 = yyyy;
						mmquery = 0 + mm;
						mmquery2 = Integer.toString(Integer.parseInt(mm)+1);
					}else{
						yyyy2 = yyyy;
						mmquery = mm;

						mmquery2 = Integer.toString(Integer.parseInt(mm)+1);
					}

				}


				query = "SELECT * FROM anorder WHERE time between to_char('"
						+ yyyy + mmquery + ddquery + "') AND to_char('" + yyyy2
						+ mmquery2 + ddquery2 + "')";
				System.out.println(query);
				v = 0;
				x = 1;
				i = 0;
				s = 0;
				condition = null;

				for (int i = 0; i < 30; i++) {
					timemodel.setValueAt("", i, 0);
					timemodel.setValueAt("", i, 1);
					timemodel.setValueAt("", i, 2);
					timemodel.setValueAt("", i, 3);
					timemodel.setValueAt("", i, 4);
				}

				chartcontent(query, condition, v, x);
				System.out.println(query);
			}
		}

	}
}
