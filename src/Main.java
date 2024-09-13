import java.awt.CardLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;




public class Main {

static Frame framemanager;
CardLayout cardmanager;
String id = "test";
String passwd = "test";
ManagermenuGui managermenugui = new ManagermenuGui();

public Main(){
	
	
	
	
	framemanager = new Frame("오늘도 친절하게!");
	
	cardmanager = new CardLayout();
	

	framemanager.setLayout(cardmanager);

	
	MainGui maingui= new MainGui(id,passwd);
	
	framemanager.add(maingui.panelmanagerall,"1");
	
	framemanager.setLocation(0, 0);
	framemanager.setSize(1500, 1000);
	framemanager.setVisible(true);
	
	
	cardmanager.show(framemanager , "1");
	

	
	framemanager.addWindowListener(new WindowListener() {
		public void windowOpened(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowDeactivated(WindowEvent arg0) {}	
		@Override
		public void windowClosing(WindowEvent arg0) {
			System.exit(0);	
		}
		public void windowClosed(WindowEvent arg0) {}
		public void windowActivated(WindowEvent arg0) {}
	});
	
}

	public static void main(String[] args) {
		
Main main = new	Main();

	}

}
