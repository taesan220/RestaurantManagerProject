import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.prefs.BackingStoreException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAODTO.GameDAO;
import DAODTO.GameDTO;

public class Gamemanager extends JPanel implements ActionListener {

	JPanel panelgamemanagerall, panelgamemanager, panelgameshow,
			panelgameinsert;

	CardLayout cardgame;

	JScrollPane jscrollgame;

	JTable tablegmae;

	DefaultTableModel model;

	DefaultTableColumnModel columnmodel;

	TableColumn column1, column2, column3;

	int x = 1, v, s, i, n;

	JButton btnback, btnnext, btninsert, btnbackmain, btncancle, btnregister;
	
	JLabel lbquestion,lbword,lbfinger;
	
	Checkbox checkfinger;
	
	TextField txtquestion,txtword;
	
	String question,word;
	int num,finger;

	
	GameDAO gamedao = new GameDAO();


	public Gamemanager() {

		cardgame = new CardLayout();

		panelgamemanagerall = new JPanel(null);
		panelgamemanager = new JPanel(cardgame);
		panelgameshow = new JPanel(null);
		panelgameinsert = new JPanel(null);

		panelgamemanager.add(panelgameshow, "1");
		panelgamemanager.add(panelgameinsert, "2");

		String[] title = { "번호", "이름", "점수" };
		model = new DefaultTableModel(title, 34);
		columnmodel = new DefaultTableColumnModel();

		tablegmae = new JTable(model, columnmodel){
		//	private final Color white = new Color(256,256,256);
		//	private final Color skyblue =new Color(215,200,256);
			public Component prepareRenderer(TableCellRenderer tcr, int row,int column){
				Component c = super.prepareRenderer(tcr,row,column);
				if(isRowSelected(row)){
					c.setForeground(getSelectionForeground());
					c.setBackground(getSelectionBackground());
				}else{
					c.setForeground(getForeground());
					c.setBackground((row%2==0)?Color.white:Color.cyan);
				}
				return c;
			}
		};

		column1 = new TableColumn(0, 30);
		column1.setHeaderValue("번호"); // "Number"
		column2 = new TableColumn(1, 300);
		column2.setHeaderValue("질문"); // "Name"
		column3 = new TableColumn(2, 30);
		column3.setHeaderValue("점수"); // "Score"

		columnmodel.addColumn(column1);
		columnmodel.addColumn(column2);
		columnmodel.addColumn(column3);

		jscrollgame = new JScrollPane(tablegmae);
		panelgameshow.add(jscrollgame);
		jscrollgame.setBounds(10, 50, 350, 560);

		btnback = new JButton("뒤로"); // "Back"
		btnnext = new JButton("다음"); // "Next"
		btninsert = new JButton("질문 추가하기"); // "Insert"
		btnbackmain = new JButton("메인으로"); // "Back to Main"

		btncancle = new JButton("취소"); // "Cancel"
		btnregister = new JButton("등록"); // "Register"

		panelgamemanagerall.add(panelgamemanager);
		panelgamemanager.setBounds(0, 0, 365, 690);

		panelgameshow.add(jscrollgame);
		jscrollgame.setBounds(10, 20, 357, 569);

		panelgameshow.add(btnback);
		btnback.setBounds(100, 610, 80, 20);
		panelgameshow.add(btnnext);
		btnnext.setBounds(200, 610, 80, 20);
		panelgameshow.add(btninsert);
		btninsert.setBounds(35, 660, 150, 30);
		panelgameshow.add(btnbackmain);
		btnbackmain.setBounds(190, 660, 150, 30);


		//=========================

		lbquestion = new JLabel("질문"); // "Question"
		lbword = new JLabel("PC 답변"); // "PC answer"

		lbfinger = new JLabel("PC 손가락");

		panelgameinsert.add(lbquestion);
		lbquestion.setBounds(45, 220, 50, 20);

		panelgameinsert.add(lbword);
		lbword.setBounds(45, 270, 50, 20);
		panelgameinsert.add(lbfinger);

		lbfinger.setBounds(45, 320, 80, 20);

		txtquestion = new TextField();
		txtword = new TextField();

		panelgameinsert.add(txtquestion);
		txtquestion.setBounds(130, 220, 200, 20);
		panelgameinsert.add(txtword);
		txtword.setBounds(130, 270, 200, 20);

		checkfinger = new Checkbox("접기", false); // "Fingerprint Recognition"

		panelgameinsert.add(checkfinger);
		checkfinger.setBounds(200, 320, 80, 20);

		btnregister = new JButton("등록"); // "Register"
		btncancle = new JButton("취소"); // "Cancel"

		panelgameinsert.add(btnregister);
		btnregister.setBounds(38, 400, 145, 30);
		panelgameinsert.add(btncancle);
		btncancle.setBounds(188, 400, 145, 30);



		//-----------------------------------


		cardgame.show(panelgamemanager, "1");




		btnnext.addActionListener(this);
		btnback.addActionListener(this);
		btninsert.addActionListener(this);
		btnbackmain.addActionListener(this);
		btnregister.addActionListener(this);
		btncancle.addActionListener(this);

		gamequestion();

	}

	public void gamequestion() {
		gamedao.n=0;
		s = 0;
		i = 0;
		for(int i = 0; i<34;i++){
			model.setValueAt("", i, 0);
			model.setValueAt("", i, 1);
			model.setValueAt("", i, 2);
		}
		
		
		ArrayList<GameDTO> list = new ArrayList<GameDTO>();
		list = gamedao.selectgame();
		n = gamedao.n;

		System.out.println(n+"aaaaaaaaaaaaaaaa12309-83-0598-3460981-4509812-5098231-50931285-189901235");
		for (GameDTO dto : list) {
			if (17 * v <= i && i < 17 * x) {

				model.setValueAt(dto.getNum(), s, 0);
				model.setValueAt(dto.getQuestion(), s, 1);
				model.setValueAt(dto.getFinger(), s, 2);
				model.setValueAt(dto.getWord(), s + 1, 1);

				s = s + 2;
			}
			i++;
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnback) {
			if (v > 0) {
				v--;
				x--;
				gamequestion();
			} else {
				JOptionPane.showMessageDialog(this, "첫 페이지입니다.");
			}
		} else if (e.getSource() == btnnext) {
			if (17 * x < i) {
				v++;
				x++;
				gamequestion();
			} else {
				JOptionPane.showMessageDialog(this, "마지막 페이지입니다.");
			}
		} else if (e.getSource() == btninsert) {
			panelgamemanager.add(panelgameinsert, "2");
			cardgame.show(panelgamemanager, "2");
		} else if (e.getSource() == btnbackmain) {
			v = 0;
			x = 1;
			gamequestion();
		} else if (e.getSource() == btnregister) {
			if (!txtquestion.getText().toString().equals("") && !txtword.getText().toString().equals("")) {
				num = n;
				question = txtquestion.getText().toString();
				word = txtword.getText().toString();

				if (checkfinger.getState() == true) {
					finger = -1;
				}

				gamedao.insertgame(num, question, finger, word);
				finger = 0;
				gamequestion();
				cardgame.show(panelgamemanager, "1");
			} else {
				JOptionPane.showMessageDialog(this, "모든 필드를 입력해야 합니다.");
			}
		} else if (e.getSource() == btncancle) {
			cardgame.show(panelgamemanager, "1");
		}
	}
}
