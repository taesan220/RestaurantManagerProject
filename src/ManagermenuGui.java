import java.awt.Choice;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import DAODTO.MenuDAO;
import DAODTO.MenuDTO;

public class ManagermenuGui extends JPanel implements ActionListener,
		ItemListener {

	JPanel panelstoremenu;

	Choice choicekind, choicemenu;
	JButton btnunlock;

	JButton btnaddmenu;

	JButton btnbackmain;

	JButton btnsave;

	JLabel lbkind, lbname;

	ImageIcon imgmenu, imgshow,imagex;

	JLabel lbimage, lbmenu, lbmeterial, lbexplain, lbcalories, lbprice, lbkcal,
			lbwon;

	TextField txtmenu, txtmeterial, txtcalories, txtprice;
	JTextArea txtexplain;

	String name, kind;
	int n, i, x, imgwidth, imghight;
	String[] menus, meterials, explains;
	int[] calories, prices;

	FileDialog filedialog;

	MenuDAO menudao = new MenuDAO();


	public ManagermenuGui() {
		panelstoremenu = new JPanel(null);

		choicekind = new Choice();
		lbkind = new JLabel("종류 :");
		lbname = new JLabel("상품명 :");

		choicekind.add("메뉴 종류 선택");
		choicekind.addItem("햄버거");
		choicekind.addItem("치킨");
		choicekind.addItem("디저트");
		choicekind.addItem("드링크");
		choicekind.addItem("햄버거 세트");

		choicemenu = new Choice();
		choicemenu.add("메뉴 항목을 선택하세요");

		lbmenu = new JLabel("메뉴명 :");
		lbmeterial = new JLabel("재료 :");
		lbexplain = new JLabel("설명:");
		lbcalories = new JLabel("칼로리 :");
		lbprice = new JLabel("가격 :");
		lbkcal = new JLabel("Kcal");
		lbwon = new JLabel("원");

		lbimage = new JLabel(imgmenu);

		txtmenu = new TextField();
		txtmenu.setEditable(false);
		txtmeterial = new TextField();
		txtmeterial.setEditable(false);
		txtexplain = new JTextArea();
		txtexplain.setEditable(false);
		txtcalories = new TextField();
		txtcalories.setEditable(false);
		txtprice = new TextField();
		txtprice.setEditable(false);

		btnunlock = new JButton("");
		btnaddmenu = new JButton("메뉴 추가");
		btnbackmain = new JButton("메인으로");
		btnsave = new JButton("저장");

		panelstoremenu.add(lbkind);
		lbkind.setBounds(50, 60, 50, 30);
		panelstoremenu.add(lbname);
		lbname.setBounds(50, 100, 50, 30);

		panelstoremenu.add(choicekind);
		choicekind.setBounds(110, 60, 120, 30);
		panelstoremenu.add(choicemenu);
		choicemenu.setBounds(110, 100, 160, 30);

		panelstoremenu.add(lbimage);
		lbimage.setBounds(40, 170, 140, 80);

		panelstoremenu.add(lbmenu);
		lbmenu.setBounds(40, 285, 50, 30);
		panelstoremenu.add(lbmeterial);
		lbmeterial.setBounds(40, 335, 50, 30);

		panelstoremenu.add(lbcalories);
		lbcalories.setBounds(40, 385, 50, 30);

		panelstoremenu.add(lbprice);
		lbprice.setBounds(40, 435, 50, 30);

		panelstoremenu.add(lbexplain);
		lbexplain.setBounds(40, 485, 50, 30);

		panelstoremenu.add(lbkcal);
		lbkcal.setBounds(210, 385, 50, 30);
		panelstoremenu.add(lbwon);
		lbwon.setBounds(210, 435, 50, 30);

		panelstoremenu.add(txtmenu);
		txtmenu.setBounds(120, 290, 150, 20);
		panelstoremenu.add(txtmeterial);
		txtmeterial.setBounds(120, 340, 150, 20);

		panelstoremenu.add(txtcalories);
		txtcalories.setBounds(120, 390, 80, 20);
		panelstoremenu.add(txtprice);
		txtprice.setBounds(120, 440, 80, 20);
		panelstoremenu.add(txtexplain);
		txtexplain.setBounds(120, 490, 220, 100);

		panelstoremenu.add(btnunlock);
		btnunlock.setBounds(200, 220, 111, 30);
		panelstoremenu.add(btnbackmain);
		btnbackmain.setBounds(10, 640, 111, 40);
		panelstoremenu.add(btnaddmenu);
		btnaddmenu.setBounds(131, 640, 112, 40);
		panelstoremenu.add(btnsave);
		btnsave.setBounds(254, 640, 111, 40);

		choicekind.addItemListener(this);
		btnunlock.addActionListener(this);
		btnaddmenu.addActionListener(this);
		btnsave.addActionListener(this);

	}

	public void choicekind(String kind) {

		String query = "SELECT * FROM menu WHERE kind =? ORDER BY name";

		String name = null;

		choicemenu.removeAll();

		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
		list = menudao.select(query, kind, name);

		n = menudao.n;
		x = n;
		menus = new String[n];
		meterials = new String[n];
		explains = new String[n];
		calories = new int[n];
		prices = new int[n];

		choicemenu.addItem("");

		for (MenuDTO dto : list) {

			menus[i] = new String(dto.getName());
			meterials[i] = new String(dto.getMeterial());
			explains[i] = new String(dto.getExplain());
			calories[i] = dto.getCalorie();
			prices[i] = dto.getPrice();

			choicemenu.addItem(menus[i]);
			choicemenu.addItemListener(this);
			i++;

		}

	}

	public void showmenu(int index, int imgwidth, int imghight) {

		txtmenu.setText(menus[index]);
		txtmeterial.setText(meterials[index]);
		txtcalories.setText(Integer.toString(calories[index]));
		txtprice.setText(Integer.toString(prices[index]));
		txtexplain.setText(explains[index]);
		txtexplain.setLineWrap(true);

		imgmenu = new ImageIcon("image/"
				+ kind + "/" + name + ".jpg");
		imgmenu = new ImageIcon(imgmenu.getImage().getScaledInstance(imgwidth,
				imghight, Image.SCALE_REPLICATE));

		lbimage.setIcon(imgmenu);

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItem().equals("메뉴 종류 선택")) {
			choicemenu.removeAll();
			choicemenu.add("메뉴 항목을 선택하세요");
			n = 0;
		}

		if (e.getItem().equals("햄버거")) {
			kind = "hamburger";
			n = 0;
			i = 0;
			choicekind(kind);
			imgwidth = 90;
			imghight = 80;

		} else if (e.getItem().equals("햄버거 세트")) {
			kind = "hamburgerset";
			n = 0;
			i = 0;
			choicekind(kind);
			imgwidth = 140;
			imghight = 80;

		} else if (e.getItem().equals("음료")) {

			kind = "drink";
			n = 0;
			i = 0;
			choicekind(kind);
			imgwidth = 80;
			imghight = 80;
		} else if (e.getItem().equals("디저트")) {

			kind = "dessert";
			n = 0;
			i = 0;
			choicekind(kind);
			imgwidth = 90;
			imghight = 80;
		} else if (e.getItem().equals("치킨")) {
			kind = "chicken";
			n = 0;
			i = 0;
			choicekind(kind);
			imgwidth = 120;
			imghight = 80;
		}

		for (int i = 0; i < x; i++) {

			if (e.getItem().equals(menus[i])) {
				name = menus[i];
				showmenu(i, imgwidth, imghight);
				txtmenu.setEditable(false);
				txtmeterial.setEditable(false);
				txtexplain.setEditable(false);
				txtcalories.setEditable(false);
				txtprice.setEditable(false);
				btnunlock.setText("메뉴 수정");

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnunlock) {

			if (btnunlock.getText().equals("메뉴 수정")) {
				txtmenu.setEditable(true);
				txtmeterial.setEditable(true);
				txtexplain.setEditable(true);
				txtcalories.setEditable(true);
				txtprice.setEditable(true);

			} else if (btnunlock.getText().equals("상품 이미지")) {

				System.out.println("버튼 클릭");

				if (kind == null) {
					JOptionPane.showMessageDialog(this, "메뉴 종류를 선택하신 후에 저장을 해주시기 바랍니다.");
				} else {

					n = 0;
					if (kind.equals("햄버거")) {
						kind = "hamburger";
						n = 0;
						i = 0;
						choicekind(kind);
						imgwidth = 90;
						imghight = 80;

					} else if (kind.equals("햄버거 세트")) {
						kind = "hamburgerset";
						n = 0;
						i = 0;
						choicekind(kind);
						imgwidth = 140;
						imghight = 80;

					} else if (kind.equals("음료수")) {
						kind = "drink";
						n = 0;
						i = 0;
						choicekind(kind);
						imgwidth = 80;
						imghight = 80;

					} else if (kind.equals("디저트")) {
						kind = "dessert";
						n = 0;
						i = 0;
						choicekind(kind);
						imgwidth = 90;
						imghight = 80;

					} else if (kind.equals("치킨")) {
						kind = "chicken";
						n = 0;
						i = 0;
						choicekind(kind);
						imgwidth = 120;
						imghight = 80;
					}

					filedialog = new FileDialog(Main.framemanager, "파일 열기", FileDialog.LOAD);
					filedialog.setDirectory("C:\\");
					filedialog.setVisible(true);
					String fileaddress = filedialog.getDirectory() + filedialog.getFile();
					File filebefore = new File(fileaddress);

					imgshow = new ImageIcon(fileaddress);
					System.out.println(fileaddress);

					imgshow = new ImageIcon(imgshow.getImage().getScaledInstance(imgwidth, imghight, Image.SCALE_REPLICATE));

					imagex = new ImageIcon();
					lbimage.setIcon(imgshow);

					try {
						FileInputStream fileinput = new FileInputStream(filebefore);
						FileOutputStream fileout = new FileOutputStream("image/" + kind + "/" + txtmenu.getText() + ".jpg");

						int b;
						while ((b = fileinput.read()) != -1) {
							fileout.write(b);
							fileout.flush();
						}
						fileinput.close();
						fileout.close();

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == btnaddmenu) {

			lbname.setVisible(false);
			choicemenu.removeAll();
			choicemenu.setVisible(false);
			txtmenu.setEditable(true);
			txtmeterial.setEditable(true);
			txtexplain.setEditable(true);
			txtcalories.setEditable(true);
			txtprice.setEditable(true);

			txtmenu.setText("");
			txtmeterial.setText("");
			txtexplain.setText("");
			txtcalories.setText("");
			txtprice.setText("");

			btnunlock.setText("상품 이미지");

			lbimage.setIcon(imagex);

			n = 0;

		} else if (e.getSource() == btnsave) {

			if (!txtmenu.getText().equals("") && !txtmeterial.getText().equals("")
					&& !txtexplain.getText().equals("") && !txtcalories.getText().equals("")
					&& !txtprice.getText().equals("") && kind != null) {

				if (n == 0 && !lbimage.getIcon().equals(imgmenu)) {

					String query = "SELECT * FROM menu";
					kind = null;
					name = null;
					menudao.n = 0;
					menudao.select(query, kind, name);

					n = menudao.n;

					System.out.println(n + "개의 메뉴");
					System.out.println("메뉴가 등록되었습니다.");
					kind = choicekind.getSelectedItem();
					if (kind.equals("햄버거")) {
						kind = "hamburger";
					} else if (kind.equals("햄버거 세트")) {
						kind = "hamburgerset";
					} else if (kind.equals("음료수")) {
						kind = "drink";
					} else if (kind.equals("디저트")) {
						kind = "dessert";
					} else if (kind.equals("치킨")) {
						kind = "chicken";
					}
					name = txtmenu.getText();
					String meterial = txtmeterial.getText();
					String explain = txtexplain.getText();
					int calorie = Integer.parseInt(txtcalories.getText());
					int price = Integer.parseInt(txtprice.getText());
					int num = n + 1;

					menudao.insertmenu(kind, num, name, meterial, explain, calorie, price);

					txtmenu.setText("");
					txtmeterial.setText("");
					txtexplain.setText("");
					txtcalories.setText("");
					txtprice.setText("");

					lbimage.setIcon(imagex);

					lbname.setVisible(true);
					choicemenu.setVisible(true);

				} else if (n != 0) {

					System.out.println("수정이 완료되었습니다.");
					System.out.println(name);
					String meterial = txtmeterial.getText();
					String explain = txtexplain.getText();
					int calorie = Integer.parseInt(txtcalories.getText());
					int price = Integer.parseInt(txtprice.getText());

					menudao.updatetmenu(kind, name, meterial, explain, calorie, price);

				}
			} else {
				JOptionPane.showMessageDialog(this, "모든 항목을 입력해 주세요.");
				System.out.println(n + "3216");
			}
		}
	}

}
