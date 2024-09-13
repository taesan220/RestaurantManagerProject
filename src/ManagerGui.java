import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import DAODTO.AnorderDAO;
import DAODTO.AnorderDTO;
import DAODTO.EvaluationDAO;
import DAODTO.EvaluationDTO;
import DAODTO.ManagerDAO;

public class ManagerGui extends JPanel implements ActionListener {

	CardLayout cardmanager;
	
	JPanel panelmanagermainall,panellogout, panelmanagermain,panelmanager, panelmanagerall,
			panelchartall, paneltodayprice, panelweekprice;

	JLabel lbshopmanager, lbtodaysales, lbweeksales, lbshopevaluation,
			lbshopservice, lbshopclean, lbhighproduct, lblowproduct;

	JLabel lbtodayprice, lbweekprice, lbservicepoint, lbcleanpoint,
			lbhighproductpoint, lblowproductpoint;

	JButton btnmenu, btngamemanager, btnlogout;
	static JButton btnsaleschart,btnevaluation;
	
	int todayprices, weekprices;
	int todayprice, weekprice;

	String[] names;
	int[] everages, totalpoints, totalnums;

	String query;
	String querychart;

	Calendar calender = Calendar.getInstance();
	int point, n, i;
	String grade;
	ManagermenuGui managermenugui;
	Evaluation evaluation;
	Saleschart saleschart;
	Gamemanager gamemanager;
	
	ImageIcon imagex;
	
	public ManagerGui() {

		managermenugui = new ManagermenuGui();
		gamemanager = new Gamemanager();
		
		cardmanager = new CardLayout();
		
		
		
		panelmanagermainall = new JPanel(null);
		panellogout = new JPanel(null);
		panellogout.setBackground(Color.BLUE);
		panelmanagermain = new JPanel(cardmanager);
		panelmanagermain.setBackground(Color.ORANGE);
		panelmanager = new JPanel(null);
		
		
		panelchartall = new JPanel(null);
		panelmanagerall = new JPanel(null);
		paneltodayprice = new JPanel(null);
		paneltodayprice.setBackground(Color.YELLOW);
		panelweekprice = new JPanel(null);
		panelweekprice.setBackground(Color.YELLOW);

		lbshopmanager = new JLabel("매장 매니저");
		lbshopmanager.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbtodaysales = new JLabel("오늘의 매출");
		lbtodaysales.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lbweeksales = new JLabel("이번 주 매출");
		lbweeksales.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lbshopevaluation = new JLabel("매장 평가");
		lbshopevaluation.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lbshopservice = new JLabel("매장 서비스");
		lbshopclean = new JLabel("청소 상태");
		lbhighproduct = new JLabel("판매 고점 상품");
		lblowproduct = new JLabel("판매 저점 상품");

		lbtodayprice = new JLabel(Integer.toString(todayprice) + "원",
				SwingConstants.RIGHT);
		lbtodayprice.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		lbweekprice = new JLabel(Integer.toString(weekprice) + "원",
				SwingConstants.RIGHT);
		lbweekprice.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		lbservicepoint = new JLabel();
		lbcleanpoint = new JLabel();
		lbhighproductpoint = new JLabel();
		lblowproductpoint = new JLabel();

		btnmenu = new JButton("메뉴 보기");
		btnsaleschart = new JButton("매출 차트");
		btnevaluation = new JButton("평가 보기");
		btngamemanager = new JButton("게임 관리");
// btnlogout = new JButton("로그아웃");

		panelmanagermainall.add(panellogout);
		panellogout.setBounds(0, 0, 376, 50);
		panelmanagermainall.add(panelmanagermain);
		panelmanagermain.setBounds(0, 50, 376, 700);
		
		
		

		panellogout.add(lbshopmanager);
		lbshopmanager.setBounds(30, 15, 100, 20);
	//	panellogout.add(btnlogout);
	//	btnlogout.setBounds(265, 10, 100, 30);
		
		
	//===================================	
		

		panelmanagermain.add(panelmanager,"1");
		panelmanagermain.add(managermenugui.panelstoremenu,"2");
		
		
		
	//===================================	
		
		panelmanager.add(panelchartall);
		panelchartall.setBounds(0, 0, 376, 400);
		panelmanager.add(panelmanagerall);
		panelmanagerall.setBounds(0, 400, 376, 300);

		panelchartall.add(paneltodayprice);
		paneltodayprice.setBounds(200, 100, 120, 30);
		panelchartall.add(panelweekprice);
		panelweekprice.setBounds(200, 150, 120, 30);

		paneltodayprice.add(lbtodayprice);
		lbtodayprice.setBounds(0, 0, 120, 30);
		panelweekprice.add(lbweekprice);
		lbweekprice.setBounds(0, 0, 120, 30);

		panelchartall.add(lbtodaysales);
		lbtodaysales.setBounds(50, 100, 150, 30);
		panelchartall.add(lbweeksales);
		lbweeksales.setBounds(50, 150, 150, 30);
		panelchartall.add(lbshopevaluation);
		lbshopevaluation.setBounds(140, 230, 150, 20);
		panelchartall.add(lbshopservice);
		lbshopservice.setBounds(50, 270, 150, 20);
		panelchartall.add(lbshopclean);
		lbshopclean.setBounds(50, 295, 150, 20);
		panelchartall.add(lbhighproduct);
		lbhighproduct.setBounds(50, 320, 150, 20);
		panelchartall.add(lblowproduct);
		lblowproduct.setBounds(50, 345, 150, 20);

		panelchartall.add(lbservicepoint);
		lbservicepoint.setBounds(200, 270, 50, 20);
		panelchartall.add(lbcleanpoint);
		lbcleanpoint.setBounds(200, 295, 50, 20);
		panelchartall.add(lbhighproductpoint);
		lbhighproductpoint.setBounds(200, 320, 120, 20);
		panelchartall.add(lblowproductpoint);
		lblowproductpoint.setBounds(200, 345, 120, 20);

		panelmanagerall.add(btnmenu);
		btnmenu.setBounds(30, 40, 312, 45);
		panelmanagerall.add(btnsaleschart);
		btnsaleschart.setBounds(30, 100, 312, 45);
		panelmanagerall.add(btnevaluation);
		btnevaluation.setBounds(30, 160, 312, 45);
		panelmanagerall.add(btngamemanager);
		btngamemanager.setBounds(30, 220, 312, 45);

	//	panellogout.add(btnlogout);
	//	btnlogout.setBounds(250, 10, 120, 30);
		
	//	btnlogout.addActionListener(this);
		
		btnmenu.addActionListener(this);
		btnsaleschart.addActionListener(this);
		btnevaluation.addActionListener(this);
		btngamemanager.addActionListener(this);
		gamemanager.btnbackmain.addActionListener(this);
		
		timesales();
		
		cardmanager.show(panelmanagermain, "1");
		
		imagex = new ImageIcon();
		
		managermenugui.btnbackmain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				imagex = new ImageIcon();
				managermenugui.txtmenu.setText("");
				managermenugui.txtmeterial.setText("");
				managermenugui.txtexplain.setText("");
				managermenugui.txtcalories.setText("");
				managermenugui.txtprice.setText("");
				managermenugui.choicekind.select(0);
				managermenugui.choicemenu.removeAll();
				managermenugui.choicemenu.add("메뉴 항목을 선택하세요");
				managermenugui.btnunlock.setText("메뉴 수정");
				managermenugui.lbname.setVisible(true);
				managermenugui.choicemenu.setVisible(true);
				managermenugui.lbimage.setIcon(imagex);
				cardmanager.show(panelmanagermain, "1");
			}
		});
		
		evaluation = new Evaluation();
		
		evaluation.btnbackmain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				cardmanager.show(panelmanagermain, "1");
				
			}
		});
		
		querychart = "SELECT * FROM anorder ORDER BY time DESC";
		saleschart = new Saleschart(querychart);
		saleschart.btnbackmain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e1) {
				
				
				cardmanager.show(panelmanagermain, "1");
				
			}
		});
		gamemanager.btnbackmain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardmanager.show(panelmanagermain, "1");
				
			}
		});
		
		
	}

	public void timesales() {

		/*
		 * todayyear = Integer.toString(calender.get(Calendar.YEAR));
		 * 
		 * 
		 * month = (calender.get(Calendar.MONTH))+1; if(month <10){ todaymonth =
		 * "0"+Integer.toString(month); }else if(month>=10){ todaymonth =
		 * Integer.toString(month); }
		 * 
		 * System.out.println(month+"��");
		 * 
		 * date = calender.get(Calendar.DATE); if(date <10){ todaydate =
		 * "0"+(Integer.toString(date)); }else if(date>=10){ todaydate =
		 * Integer.toString(date); } System.out.println(todaydate+"��");
		 * 
		 * 
		 * System.out.println(todayyear+todaymonth+todaydate+"�Դϴ�");
		 */

		final ManagerDAO managerdao = new ManagerDAO();

		query = "SELECT * FROM evaluation ORDER BY checkscore/checknum";

		ArrayList<EvaluationDTO> list2 = new ArrayList<EvaluationDTO>();
		list2 = managerdao.selectevaluation(query);
		n = managerdao.n;

		System.out.println(n + "����");

		names = new String[n];
		totalpoints = new int[n];
		totalnums = new int[n];
		everages = new int[n];

		for (EvaluationDTO dto2 : list2) {

			names[i] = new String(dto2.getName());
			totalpoints[i] = dto2.getCheckscore();
			totalnums[i] = dto2.getChecknum();
			everages[i] = dto2.getCheckscore() / dto2.getChecknum();

			i++;
		}

		
		
		
		Thread 		threadshoworder = new Thread(new Runnable() {
			// ===========================
			// =============================================������ ����

			@Override
			public void run() {
				while (true) {
					try {
						
						weekprice =0;
						todayprice=0;
						ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();	
							
							
						//query = "SELECT * FROM anorder WHERE time between to_char(sysdate,'yyyymmdd') and sysdate";
						query = "SELECT * FROM anorder";

						list = managerdao.selectime(query);

						for (AnorderDTO dto : list) {

							todayprices = dto.getAnorder();
							todayprice = todayprice + todayprices;
						}
						lbtodayprice.setText(Integer.toString(todayprice) + "원");

						//query = "SELECT * FROM anorder WHERE time between to_char(trunc(sysdate,'iw'),'yyyymmdd')and sysdate order by time";
						query = "SELECT * FROM anorder ORDER BY time;";
						list = managerdao.selectime(query);

						for (AnorderDTO dto : list) {
							weekprices = dto.getAnorder();
							weekprice = weekprice + weekprices;
						}
						lbweekprice.setText(Integer.toString(weekprice) + "원");

						
						
						checkpoint(0);
						lblowproductpoint.setText(grade+"("+names[0]+")");
						
						checkpoint(n-1);
						lbhighproductpoint.setText(grade+"("+names[n-1]+")");
						
						
						for (int i = 0; i < n; i++) {
							System.out.println(names[i]);

							
							if (names[i].equalsIgnoreCase("service")) {

								checkpoint(i);
								lbservicepoint.setText(grade);
								

							} else if (names[i].equalsIgnoreCase("clean")) {

								checkpoint(i);
								lbcleanpoint.setText(grade);
								

							}

						
						}

					System.out.println("������ �۵����Դϴ�~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~``");

					

						Thread.sleep(10000);
	


					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
			
		});
		
		threadshoworder.start();
		// =============================================������ ��	
		
		
		
		
	}

	public void checkpoint(int x) {
		
			point = totalpoints[x] / totalnums[x];
			
			switch (point) {
			
			case 5:
				grade = "A";
				break;
			case 4:
				grade = "B";
				break;
			case 3:
				grade = "C";
				break;
			case 2:
				grade = "D";
				break;
			case 1:
				grade = "E";
				break;
			case 0:
				grade = "F";
				break;
			}
		}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	/*	if(e.getSource()==btnlogout){
			
			
		}else */if(e.getSource()==btnmenu){
			
			cardmanager.show(panelmanagermain, "2");
			
			
		}else if(e.getSource()==btnsaleschart){
			panelmanagermain.add(saleschart.panelsaleschartall,"3");
			cardmanager.show(panelmanagermain, "3");
			
		}else if(e.getSource()==btnevaluation){
			panelmanagermain.add(evaluation.panelevaluationall,"4");
			cardmanager.show(panelmanagermain, "4");
			
		}else if(e.getSource()==btngamemanager){
			panelmanagermain.add(gamemanager.panelgamemanagerall,"5");
			cardmanager.show(panelmanagermain, "5");
			
		}
	}
}
