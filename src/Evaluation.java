import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import DAODTO.EvaluationDAO;
import DAODTO.EvaluationDTO;


public class Evaluation extends JPanel implements ActionListener {

	JPanel panelevaluationall, panelevaluationchart, panelevaluationSC;

	JButton btnall, btnhamburger, btnhamburgerset, btndessert, btndrink, btnchicken, btnback, btnnext, btnbackmain;

	JScrollPane jscrollevaluation, jscrollSC;

	JTable tableevaluation, tableSC;
	DefaultTableColumnModel columnmodel, columnmodelSC;
	DefaultTableModel model, modelSC;

	TableColumn column1, column2, column3, column4, columnSC1, columnSc2, columnSC3, columnSC4;


	String query = "SELECT * FROM evaluation WHERE checkname != 'clean' and checkname != 'service' ORDER BY checkscore/checknum DESC";
	String sql = "SELECT checkname, SUM(checkscore) AS checkscore, SUM(checknum) AS checknum FROM evaluation WHERE checkname = 'clean' OR checkname = 'service' GROUP BY checkname ORDER BY checkname;";
	int checkscore, checknum, s, v, x = 1, n, i, z;
	double checksaverage;
	public Evaluation() {
		// 패널 초기화
		panelevaluationall = new JPanel(null);
		panelevaluationchart = new JPanel(null);
		panelevaluationSC = new JPanel(null);

		// 테이블 제목 정의
		String[] title1 = {"서비스", "평균", "총점", "평가 수"};
		String[] title2 = {"주문번호", "평균", "총점", "평가 수"};

		//--------------------------------------------------------------SERVICE, CLEAN

		// DefaultTableModel 및 JTable 생성
		modelSC = new DefaultTableModel(title1, 2);
		columnmodelSC = new DefaultTableColumnModel();
		tableSC = new JTable(modelSC, columnmodelSC);
		jscrollSC = new JScrollPane(tableSC);

		// 테이블 열 정의
		columnSC1 = new TableColumn(0, 150);
		columnSc2 = new TableColumn(1, 40);
		columnSC3 = new TableColumn(2, 40);
		columnSC4 = new TableColumn(3, 40);

		// 열에 헤더 값 설정
		columnmodelSC.addColumn(columnSC1);
		columnSC1.setHeaderValue("서비스");
		columnmodelSC.addColumn(columnSc2);
		columnSc2.setHeaderValue("평균");
		columnmodelSC.addColumn(columnSC3);
		columnSC3.setHeaderValue("총점");
		columnmodelSC.addColumn(columnSC4);
		columnSC4.setHeaderValue("평가 수");

		//--------------------------------------------------------------

		// DefaultTableModel 및 JTable 생성
		model = new DefaultTableModel(title2, 25);
		columnmodel = new DefaultTableColumnModel();
		tableevaluation = new JTable(model, columnmodel);
		jscrollevaluation = new JScrollPane(tableevaluation);

		// 테이블 열 정의
		column1 = new TableColumn(0, 150);
		column2 = new TableColumn(1, 40);
		column3 = new TableColumn(2, 40);
		column4 = new TableColumn(3, 40);

		// 열에 헤더 값 설정
		columnmodel.addColumn(column1);
		column1.setHeaderValue("메뉴명");
		columnmodel.addColumn(column2);
		column2.setHeaderValue("평균");
		columnmodel.addColumn(column3);
		column3.setHeaderValue("총점");
		columnmodel.addColumn(column4);
		column4.setHeaderValue("평가 수");

		//-----------------------------------------------------------------

		// 버튼 초기화
		btnall = new JButton("전체");
		btnhamburger = new JButton("햄버거");
		btnhamburgerset = new JButton("햄버거세트");
		btndessert = new JButton("디저트");
		btndrink = new JButton("음료");
		btnchicken = new JButton("치킨");
		btnback = new JButton("이전");
		btnnext = new JButton("다음");
		btnbackmain = new JButton("메인화면");


		panelevaluationall.add(panelevaluationSC);
		panelevaluationSC.setBounds(10, 110, 357, 55);
		panelevaluationall.add(panelevaluationchart);
		panelevaluationchart.setBounds(10, 180, 357, 425);


		panelevaluationSC.add(jscrollSC);
		jscrollSC.setBounds(0, 0, 357, 57);


		panelevaluationchart.add(jscrollevaluation);
		jscrollevaluation.setBounds(0, 0, 357, 505);


		panelevaluationall.add(btnall);
		btnall.setBounds(40, 20, 90, 30);
		panelevaluationall.add(btnhamburger);
		btnhamburger.setBounds(140, 20, 90, 30);
		panelevaluationall.add(btnhamburgerset);
		btnhamburgerset.setBounds(240, 20, 90, 30);
		panelevaluationall.add(btndessert);
		btndessert.setBounds(40, 60, 90, 30);
		panelevaluationall.add(btndrink);
		btndrink.setBounds(140, 60, 90, 30);
		panelevaluationall.add(btnchicken);
		btnchicken.setBounds(240, 60, 90, 30);

		panelevaluationall.add(btnback);
		btnback.setBounds(95, 620, 80, 20);
		panelevaluationall.add(btnnext);
		btnnext.setBounds(195, 620, 80, 20);

		panelevaluationall.add(btnbackmain);
		btnbackmain.setBounds(247, 663, 120, 30);


		btnall.addActionListener(this);
		btnhamburger.addActionListener(this);
		btnhamburgerset.addActionListener(this);
		btndessert.addActionListener(this);
		btndrink.addActionListener(this);
		btnchicken.addActionListener(this);
		btnback.addActionListener(this);
		btnnext.addActionListener(this);
		btnbackmain.addActionListener(this);


		evaluationcheck(query, v, x);

		evaluationSC(sql);


		ManagerGui.btnevaluation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String query = "SELECT checkname, SUM(checkscore) AS checkscore, SUM(checknum) AS checknum FROM evaluation WHERE checkname != 'clean' AND checkname != 'service' GROUP BY checkname;";
				String sql = "SELECT checkname, SUM(checkscore) AS checkscore, SUM(checknum) AS checknum FROM evaluation WHERE checkname = 'clean' OR checkname = 'service';";


				s = 0;
				i = 0;
				v = 0;
				x = 1;
				z = 0;

				evaluationcheck(query, v, x);
				evaluationSC(sql);

			}
		});
	}


	public void evaluationcheck(String query,int v,int x){

		for(int i = 0 ; i <25 ; i++){

		model.setValueAt("",i, 0);
		model.setValueAt("",i, 1);
		model.setValueAt("",i, 2);
		model.setValueAt("",i, 3);
		}

		EvaluationDAO evaluationdao = new EvaluationDAO();
		ArrayList<EvaluationDTO> list = new ArrayList<EvaluationDTO>();
		list = evaluationdao.selectevaluation(query);
		n = evaluationdao.n;


		for(EvaluationDTO dto : list){


			if(25*v <= i && i < 25*x){


				checkscore = dto.getCheckscore();

				checknum=dto.getChecknum();


				checksaverage = checkscore/checknum;


				model.setValueAt(dto.getName(),s, 0);
				model.setValueAt(checksaverage,s, 1);
				model.setValueAt(checkscore, s, 2);
				model.setValueAt(checknum,s, 3);

				s++;

			}


			i++;
		}

		}
	public void evaluationSC(String sql){

		EvaluationDAO evaluationdao = new EvaluationDAO();
		ArrayList<EvaluationDTO> list = new ArrayList<EvaluationDTO>();
		list = evaluationdao.selectevaluation(sql);
		n = evaluationdao.n;


		for(EvaluationDTO dto : list){
				checkscore = dto.getCheckscore();

				checknum=dto.getChecknum();


				checksaverage = (double) checkscore / checknum;


				modelSC.setValueAt(dto.getName(),z, 0);
				modelSC.setValueAt(checksaverage,z, 1);
				modelSC.setValueAt(checkscore, z, 2);
				modelSC.setValueAt(checknum,z, 3);

				z++;

			}


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnall){

			query = "SELECT menu.name, kind, evaluation.checkname, SUM(checkscore) AS checkscore, SUM(checknum) AS checknum FROM menu JOIN evaluation ON menu.name = evaluation.checkname WHERE checkname != 'clean' and checkname != 'service' GROUP BY menu.name, kind, evaluation.checkname ORDER BY menu.name DESC;";

			s=0;
			i = 0;
			v=0;
			x=1;
			evaluationcheck(query,v,x);

		}else if (e.getSource()==btnhamburger){
			query = "SELECT menu.name, kind, evaluation.checkname, SUM(checkscore) AS checkscore, SUM(checknum) AS checknum FROM menu JOIN evaluation ON menu.name = evaluation.checkname WHERE kind = 'hamburger' GROUP BY menu.name, kind, evaluation.checkname ORDER BY menu.name DESC;";

			s=0;
			i = 0;
			v=0;
			x=1;
			evaluationcheck(query,v,x);

		}else if (e.getSource()==btnhamburgerset){
			query = "SELECT menu.name, kind, evaluation.checkname, SUM(checkscore) AS checkscore, SUM(checknum) AS checknum FROM menu JOIN evaluation ON menu.name = evaluation.checkname WHERE kind = 'hamburgerset' GROUP BY menu.name, kind, evaluation.checkname ORDER BY menu.name DESC;";

			s=0;
			i = 0;
			v=0;
			x=1;
			evaluationcheck(query,v,x);

		}else if (e.getSource()==btndessert){
			query = "SELECT menu.name, kind, evaluation.checkname, SUM(checkscore) AS checkscore, SUM(checknum) AS checknum FROM menu JOIN evaluation ON menu.name = evaluation.checkname WHERE kind = 'dessert' GROUP BY menu.name, kind, evaluation.checkname ORDER BY menu.name DESC;";

			s=0;
			i = 0;
			v=0;
			x=1;
			evaluationcheck(query,v,x);

		}else if(e.getSource()==btndrink){

			query = "SELECT menu.name, kind, evaluation.checkname, SUM(checkscore) AS checkscore, SUM(checknum) AS checknum FROM menu JOIN evaluation ON menu.name = evaluation.checkname WHERE kind = 'drink' GROUP BY menu.name, kind, evaluation.checkname ORDER BY menu.name DESC;";

			s=0;
			i = 0;
			v=0;
			x=1;
			evaluationcheck(query,v,x);
		}else if(e.getSource()==btnchicken){
			query = "SELECT menu.name, kind, evaluation.checkname, SUM(checkscore) AS checkscore, SUM(checknum) AS checknum FROM menu JOIN evaluation ON menu.name = evaluation.checkname WHERE kind = 'chicken' GROUP BY menu.name, kind, evaluation.checkname ORDER BY menu.name DESC;";

			s=0;
			i = 0;
			v=0;
			x=1;
			evaluationcheck(query,v,x);


		}else if(e.getSource()==btnback){

			if(v>0){
			s=0;
			i = 0;
			x--;
			v--;
			evaluationcheck(query,v,x);
			}else{

				JOptionPane.showMessageDialog(this, "이전 페이지가 없습니다.");
			}
		}else if(e.getSource()==btnnext){
			if (25 * x < i) {

			s=0;
			i = 0;
			x++;
			v++;
			evaluationcheck(query,v,x);
			}else{

				JOptionPane.showMessageDialog(this, "다음 페이지가 없습니다.");
			}
		}

	}
	
}
