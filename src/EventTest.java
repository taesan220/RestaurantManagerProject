import java.awt.*;
import java.awt.event.*;

class EventTest {
	static int i = 0; // �̺�Ʈ�� �߻����� �� ȣ��Ǵ� �޼����� ��ȣ�� ���.

	static void print(String event, AWTEvent e) {
		System.out.println("["+ ++i + "] "+ event +" - " + e.getSource());	
	}

	public static void main(String[] args) 
	{	
		Frame f = new Frame("Event Test");
		f.setLayout(new FlowLayout());		
		f.setSize(500, 300);
		f.setLocation(200, 200);

		Button button = new Button("Button");
		Checkbox cb = new Checkbox("Option");
		Canvas can = new Canvas();
		can.setSize(50, 50);
		can.setBackground(Color.blue);
		Choice choice = new Choice();
		choice.add("SUN");
		choice.add("MON");
		choice.add("TUE");
		Panel panel = new Panel();
		Label label = new Label("Label");
		List list = new List();
		list.add("Student");
		list.add("Teacher");
		list.add("Engineer");
		list.add("Programmer");
		Scrollbar scroll = new Scrollbar(Scrollbar.HORIZONTAL, 0, 10, 0, 100);
		TextArea ta = new TextArea(10, 40);
		TextField tf = new TextField(10);
		
		f.add(button);		f.add(cb);			f.add(can);
		f.add(choice);		f.add(panel);		f.add(label);
		f.add(list);	 	f.add(scroll);		f.add(ta);			f.add(tf);

		/*------------------------------------------------------------------------------*/
		/* Frame�� �޴��� �߰��Ѵ�.                                                     */
		/* Menu�� MenuItem�� Event23Ŭ������ ������� �ʰ�, AnonymousŬ������ ó���ߴ�. */
		/*------------------------------------------------------------------------------*/
		MenuBar mb = new MenuBar();
		Menu mFile = new Menu("File");

		MenuItem miNew = new MenuItem("New");
		mFile.add(miNew);							// Menu������ � �̺�Ʈ�� �߻����� �ʴ´�.
		miNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
					print("actionPerformed", e);
				}
		});

		mb.add(mFile);
		f.setMenuBar(mb);	
		/*------------------------------------------------------------------------------*/
		/* Frame�� ���Ե� Component�鿡 Listener�� ����Ѵ�.                            */
		/*------------------------------------------------------------------------------*/
		Component[]  c = f.getComponents();	//	Frame�� ���Ե� Component�� ����� ��´�.
		for(int i =0; i < c.length; i++) {
			addAllListener(c[i]);			//	Frame�� ���Ե� Component�� Listener�� �߰��Ѵ�.
		}
		addAllListener(f);					//	Frame�ڽſ� Listener�� �߰��Ѵ�.
		f.setVisible(true);
	}	// end of main method

	static void addAllListener(Component c) {
		// ��� ������ Event Listener Interface���� ��� ������ Ŭ����.
		// Event�� �߻��ϸ�, ȣ��� �޼����� �̸��� EventSource�� ���� ������ ����ϵ��� �ߴ�.

		class EventHandler
		implements ActionListener, AdjustmentListener, ComponentListener, ContainerListener
				 , FocusListener, ItemListener, KeyListener, MouseListener
				 , MouseMotionListener, MouseWheelListener, TextListener
				 , WindowFocusListener, WindowListener, WindowStateListener
		{
			// ActionListener�� �޼���
			public void actionPerformed(ActionEvent e)  { print("actionPerformed",e);}
			// AdjustmentListener�� �޼���
			public void adjustmentValueChanged(AdjustmentEvent e)  { print("adjustmentValueChanged",e);}
			// ComponentListener�� �޼���
			public void componentResized(ComponentEvent e)  { print("componentResized",e);}
			public void componentMoved(ComponentEvent e)  { print("componentMoved",e);}
			public void componentShown(ComponentEvent e) { print("componentShown",e);}
			public void componentHidden(ComponentEvent e) { print("componentHidden",e);}
			// ContainerListener�� �޼���
			public void componentAdded(ContainerEvent e) { print("componentAdded",e);}
			public void componentRemoved(ContainerEvent e)  { print("componentRemoved",e);}
			// FocusListener�� �޼���
			public void focusGained(FocusEvent e)  { print("focusGained",e);}
			public void focusLost(FocusEvent e)  { print("focusLost",e);}
			// ItemListener�� �޼���
			public void itemStateChanged(ItemEvent e)  { print("itemStateChanged",e);}
			// KeyListener�� �޼���
			public void keyTyped(KeyEvent e)  { print("keyTyped",e);}
			public void keyPressed(KeyEvent e)  { print("keyPressed",e);}
			public void keyReleased(KeyEvent e)  { print("keyReleased",e);}
			// MouseListener�� �޼���
			public void mouseClicked(MouseEvent e)  { print("mouseClicked",e);}
			public void mousePressed(MouseEvent e)  { print("mousePressed",e);}
			public void mouseReleased(MouseEvent e)	 { print("mouseReleased",e);}
			public void mouseEntered(MouseEvent e)  { print("mouseEntered",e);}
			public void mouseExited(MouseEvent e) { print("mouseExited",e);}
			// MouseMotionListener�� �޼���
			public void mouseDragged(MouseEvent e) { print("mouseDragged",e);}
			public void mouseMoved(MouseEvent e) { 
				//print("mouseMoved",e);
			}
			// MouseWheelListener�� �޼���(JDK1.4�����߰�)
			public void mouseWheelMoved(MouseWheelEvent e) { print("mouseWheelMoved",e);}
			// TextListener�� �޼���
			public void textValueChanged(TextEvent e) { print("textValueChanged",e);}
			// WindowFocusListener�� �޼���(JDK1.4�����߰�)
			public void windowGainedFocus(WindowEvent e) { print("windowGainedFocus",e);}
			public void windowLostFocus(WindowEvent e) { print("windowLostFocus",e);}
			// WindowListener�� �޼���
			public void windowOpened(WindowEvent e) { print("windowOpened",e);}
			public void windowClosing(WindowEvent e) {
				print("windowClosing",e);
				e.getWindow().setVisible(false);
				e.getWindow().dispose();
				System.exit(0);
			}
			public void windowClosed(WindowEvent e)  { print("windowClosed",e);}
			public void windowIconified(WindowEvent e) { print("windowIconified",e);}
			public void windowDeiconified(WindowEvent e) { print("windowDeiconified",e);}
			public void windowActivated(WindowEvent e) { print("windowActivated",e);}
			public void windowDeactivated(WindowEvent e) { print("windowDeactivated",e);}
			// WindowStateListener�� �޼���(JDK1.4�����߰�)
			public void windowStateChanged(WindowEvent e) { print("windowStateChanged",e);}

		} // End of EventHandler class

		EventHandler handler = new EventHandler();

		// ��� Component�� ���������� �߰������� Listener��
		c.addComponentListener(handler);
		c.addFocusListener(handler);
		c.addKeyListener(handler);
		c.addMouseListener(handler);
		c.addMouseMotionListener(handler);
		c.addMouseWheelListener(handler);

		// Component�� ������ ���� �߰������� Listener�� �ٸ��� ������, Component�� ������ Ȯ���� �Ŀ�
		// �߰������� Listener�� ���������� �߰��Ѵ�.
		if (c instanceof Container)		{
			((Container)c).addContainerListener(handler);
			if(c instanceof Frame) {
				Frame f = (Frame)c;
				f.addWindowListener(handler);
				f.addWindowFocusListener(handler);
				((Frame)c).addWindowStateListener(handler);			
			} else if (c instanceof Window) {
				Window w = (Window)c;
				w.addWindowFocusListener(handler);			
				w.addWindowListener(handler);			
				w.addWindowStateListener(handler);			
			}			
		} else if (c instanceof Button)	{		((Button)c).addActionListener(handler);
//		} else if (c instanceof Canvas)	{		// ���� �߰����� Listener�� ����.
		} else if (c instanceof Checkbox)	{	((Checkbox)c).addItemListener(handler);
		} else if (c instanceof Choice)	{		((Choice)c).addItemListener(handler);
//		} else if (c instanceof Label)		{	// ���� �߰����� Listener�� ����.
		} else if (c instanceof List)		{
			((List)c).addActionListener(handler);
			((List)c).addItemListener(handler);
		} else if (c instanceof Scrollbar)	{	((Scrollbar)c).addAdjustmentListener(handler);
		} else if (c instanceof TextArea)	{	((TextArea)c).addTextListener(handler);
		} else if (c instanceof TextField) {
			TextField tf = (TextField)c;
			tf.addActionListener(handler);
			tf.addTextListener(handler);
		}
	}	// End of addAllListener method
} // End of EventTest class