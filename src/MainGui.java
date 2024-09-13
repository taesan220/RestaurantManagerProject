import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAODTO.AnorderDAO;
import DAODTO.AnorderDTO;

public class MainGui extends JPanel implements ActionListener {

	JPanel panelmanagerall, panelmanagermain, panelordershow, panelmaintime;
	static JPanel panelmainshow;
	JPanel panelmainbutton;
	JPanel panelorder;
	JPanel panelscreen;
	JPanel Panelposter;
	JButton btnorder, btnmanager;

	ScrollPane scrollorder, scrollscreen;

	JLabel lbtime;
	Calendar timeminute;

	int year, month, date, hour, minute, second;
	String ampm;

	Thread threadtime;

	static CardLayout cardleft;
	// ------------------------------------------���̽���

	Vector vectordata = new Vector();

	// ------------------------------------------------���� �г� ����

	static JPanel[] showorder;
	JLabel[] lbtablename;
	static JLabel[] lbcondition;
	JButton[] btnshoworder;
	int tablecodecheck, i, n, x;
	int[] tablecodechecks;

	Billorder billorder;
	
	// =================================================================================================�޼���
	// ���� �гν���
	Thread threadshoworder;

	//������ �α���=====================
	JFrame framelogin;
	JLabel lbid,lbpasswd,lblogin;
	TextField txtid,txtpasswd;
	JButton btnlogin,btncancle;
	
	String id,passwd;
	
	public MainGui(String id,String passwd) {

		this.id= id;
		this.passwd = passwd;
		panelmanagerall = new JPanel(null);

		cardleft = new CardLayout();
		panelmanagermain = new JPanel(null);
		panelmanagermain.setBackground(Color.GREEN);
		panelordershow = new JPanel(null);
		panelordershow.setBackground(Color.YELLOW);

		panelmanagerall.add(panelmanagermain);
		panelmanagermain.setBounds(0, 0, 400, 1000);
		panelmanagerall.add(panelordershow);
		panelordershow.setBounds(400, 0, 1100, 1000);

		panelmainshow = new JPanel(cardleft);
		panelmainshow.setBackground(Color.CYAN);

		Panelposter = new JPanel() {
			public void paint(Graphics g) {

				Image imgposter = Toolkit.getDefaultToolkit().getImage(
						"image/eventposter/"
								+ "managermain" + ".jpg");

				g.drawImage(imgposter, 0, 0, 376, 750, Panelposter);

			}

		};

		lbtime = new JLabel();
		lbtime.setFont(new Font("����ü", Font.BOLD, 20));

		btnorder = new JButton("주문하기");
		btnmanager = new JButton("매장 관리");

		panelmanagermain.add(lbtime);
		lbtime.setBounds(45, 14, 400, 20);

		panelmanagermain.add(panelmainshow);
		panelmainshow.setBounds(10, 50, 376, 750);

		panelmanagermain.add(btnorder);
		btnorder.setBounds(10, 830, 376, 50);
		panelmanagermain.add(btnmanager);
		btnmanager.setBounds(10, 890, 376, 50);

		final OrderGui ordergui = new OrderGui();
		ManagerGui managergui = new ManagerGui();
		
		panelmanagermain.add(ordergui.panelorderall, "1");

		threadtime = new Thread(new Runnable() {
			// ===========================
			// =============================================������ ����

			@Override
			public void run() {
				while (true) {
					try {
						timeminute = Calendar.getInstance(); // ============================�ð���
																// ��� ���� �޼���
						year = timeminute.get(Calendar.YEAR);
						month = timeminute.get(Calendar.MONTH);
						month = month + 1;
						date = timeminute.get(Calendar.DATE);
						hour = timeminute.get(Calendar.HOUR_OF_DAY); // ���� �������
																		// �ҷ�����
																		// minute
																		// =
						minute = timeminute.get(Calendar.MINUTE); // ���� �������
																	// �ҷ�����
																	// second
						// =
						second = timeminute.get(Calendar.SECOND);
						if (hour >= 12) {
							hour = hour - 12;
							ampm = "오후";
						} else {
							ampm = "오전";
						}
						lbtime.setText(" " + Integer.toString(year) + "."
								+ Integer.toString(month) + "."
								+ Integer.toString(date) + "        " + ampm
								+ Integer.toString(hour) + ":"
								+ Integer.toString(minute) + " "
								+ Integer.toString(second));

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		});
		threadtime.start();
		// ============================================================������ ��

		
		btnorder.addActionListener(this);
		btnmanager.addActionListener(this);

		
		panelmainshow.add(Panelposter, "1");
		panelmainshow.add(ordergui.panelorderall, "2");
		panelmainshow.add(managergui.panelmanagermainall, "4");
		
		

		cardleft.show(panelmainshow, "1");

		
		// ====================================================================================================����
		// ���̽� ��
		// -----------------------------------------------------------------����
		// �޴� ����
		panelorder = new JPanel(new GridLayout(0, 10, 5, 5));
		panelorder.setBackground(Color.ORANGE);

		panelordershow.add(panelorder);
		panelorder.setBounds(25, 50, 1035, 890);

		showorder = new JPanel[100];
		lbtablename = new JLabel[100];
		lbcondition = new JLabel[100];
		btnshoworder = new JButton[100];
		for (int i = 0; i < 100; i++) {
			showorder[i] = new JPanel(new BorderLayout());
			showorder[i].setBackground(Color.WHITE);
			lbtablename[i] = new JLabel("Table " + i);
			btnshoworder[i] = new JButton("내용 보기");
			lbcondition[i] = new JLabel();
			panelorder.add(showorder[i]);
			showorder[i].add(lbtablename[i], "North");
			showorder[i].add(lbcondition[i], "Center");
			showorder[i].add(btnshoworder[i], "South");
			showorder[i].setBackground(Color.LIGHT_GRAY);
			btnshoworder[i].addActionListener(this);
		}
		threadshoworder = new Thread(new Runnable() {
			// ===========================
			// =============================================������ ����

			@Override
			public void run() {
				while (true) {
					try {
						
						for(int i = 0 ; i <100 ; i++){

							showorder[i].setBackground(Color.LIGHT_GRAY);
							lbcondition[i].setText("");
						}
								

						AnorderDAO orderdao = new AnorderDAO();

						String query = "SELECT DISTINCT tablecode FROM anorder WHERE `condition`=2";
						orderdao.showorder(query);
						n = orderdao.n;
						tablecodechecks = new int[n];

						ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
						list = orderdao.showorder(query);
						for (AnorderDTO dto : list) {

							tablecodechecks[i] = dto.getTablecode();
							tablecodecheck = tablecodechecks[i];

							System.out.println(tablecodechecks[i]);

							lbcondition[tablecodecheck].setText("완료");

							showorder[tablecodecheck]
									.setBackground(Color.GREEN);

							i++;

						}

						query = "SELECT DISTINCT tablecode FROM anorder WHERE `condition`=3";
						orderdao.showorder(query);
						n = orderdao.n;
						tablecodechecks = new int[n];

						
						list = orderdao.showorder(query);
						for (AnorderDTO dto : list) {

							tablecodechecks[i] = dto.getTablecode();
							tablecodecheck = tablecodechecks[i];

							System.out.println(tablecodechecks[i]);

							lbcondition[tablecodecheck].setText("미완료");

							showorder[tablecodecheck].setBackground(Color.RED);

							i++;

						}
	

						System.out.println("*****************************");
						System.out.println(n + "��");

						Thread.sleep(5000);

						x = 0;

						i = 0;

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		});
		threadshoworder.start();

		// ============================================================������ ��
		 

	}
	public void framelogin(){

		framelogin = new JFrame("로그인");
		framelogin.setLayout(null);
		framelogin.setSize(300, 200);
		framelogin.setLocation(60, 400);

		lbid = new JLabel("ID    : ");
		lbpasswd = new JLabel("P/S   : ");
		txtid = new TextField();
		txtpasswd = new TextField();
		lblogin = new JLabel("");
		lblogin.setForeground(Color.RED);
		txtpasswd.setEchoChar('*');
		btnlogin = new JButton("로그인");
		btncancle = new JButton("취소");
		
		framelogin.add(lbid);
		lbid.setBounds(60, 20, 40, 20);
		framelogin.add(lbpasswd);
		lbpasswd.setBounds(60, 60, 40, 20);
		
		framelogin.add(txtid);
		txtid.setBounds(100, 20, 110, 20);
		framelogin.add(txtpasswd);
		txtpasswd.setBounds(100, 60, 110, 20);
		
		framelogin.add(lblogin);
		lblogin.setBounds(60, 93, 180, 20);
		
		framelogin.add(btnlogin);
		btnlogin.setBounds(10, 125, 130, 30);
		framelogin.add(btncancle);
		btncancle.setBounds(145, 125, 130, 30);
		framelogin.setVisible(true);
		
		btnlogin.addActionListener(this);
		btncancle.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnorder) {
			System.out.println("sd");
			cardleft.show(panelmainshow, "2");

		}
		for (int i = 0; i < 100; i++) {
			if (e.getSource() == btnshoworder[i]) {
				Billorder billorder = new Billorder(i);
				panelmainshow.add(billorder.panelbillorderall,"3");
				System.out.println(lbtablename[i].getText().toString());
				cardleft.show(panelmainshow, "3");
			}
		}

		if(e.getSource()==btnmanager){

			framelogin();
			
		}

		if (e.getSource() == btnlogin) {

			if (txtid.getText().toString().equals(id)) {
				if (txtpasswd.getText().toString().equals(passwd)) {
					lblogin.setText("");
					//=====
					cardleft.show(panelmainshow, "4");
					//=======
					framelogin.dispose();
				} else {
					lblogin.setText("비밀번호가 일치하지 않습니다.");
				}

			} else {
				lblogin.setText("아이디가 일치하지 않습니다.");
			}

		} else if (e.getSource() == btncancle) {

			framelogin.dispose();
		}
	}

}
