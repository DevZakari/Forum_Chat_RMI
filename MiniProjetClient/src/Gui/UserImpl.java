package Gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import rmipq.Forum;
import rmipq.ProxyImpl;
import rmipq.proxy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class UserImpl extends UnicastRemoteObject implements User, ActionListener {
	private Forum forum;
	private int id;
	private JFrame frame;
	private JTextArea chatArea;
	private JTextField inputField;
	private JButton enterButton;
	private JButton quitButton;
	private JButton listButton;

	private JMenuItem mntmNewMenuItem;
	private JFrame bienvenue;
	

	public UserImpl(Forum forum) throws RemoteException {
		this.forum = forum;
		showtheGUI();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		frame.setTitle("Forum - User " + id);
	}

	public proxy getProxy() {
		try {
			return new ProxyImpl(this);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	public void ecrire(String msg) {
		chatArea.append(msg + "\n");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterButton) {
			try {
				// Call the 'entrer' method on the server to connect the user to the forum
				id = forum.entrer(getProxy());
				frame.dispose(); // close the Enter button frame
				createChatFrame(id);
				forum.dire(id, "a ete connecté");
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		} else if(e.getSource() == listButton) {
			
			try {
				
				String [] users = forum.qui().split(",");
				 JDialog dialog = new JDialog();
	                dialog.setTitle("Who s connected ...");	        
	                JList<String> userList = new JList<>(users);
	                JScrollPane scrollPane = new JScrollPane(userList);
	                dialog.add(scrollPane);
	                
	                dialog.setSize(new Dimension(200, 200));	         
	                dialog.setLocationRelativeTo(frame);
	                dialog.setVisible(true);
	                
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		else {
			String message = inputField.getText();
			inputField.setText("");
			try {
				forum.dire(id, message);
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void showtheGUI() {

		bienvenue = new JFrame("Bienvenue");
		bienvenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bienvenue.setBounds(100, 100, 613, 376);
		
		
		JPanel enterPanel = new JPanel();
		enterPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		bienvenue.setContentPane(enterPanel);
		enterPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 214, 376);
		enterPanel.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		ImageIcon img1 = new ImageIcon("Z:\\JAVA Distributed System\\MiniProjetClient\\src\\assets\\discforum.jpg");
		Image image1 = img1.getImage();
		Image newImg1 = image1.getScaledInstance(200, 140, Image.SCALE_DEFAULT);
		img1 = new ImageIcon(newImg1);
		lblNewLabel_1.setIcon(img1);
		
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 194, 187);
		panel.add(lblNewLabel_1);
		
		enterButton = new JButton("Entrer dans le forum");
		enterButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		enterButton.setBounds(31, 234, 150, 40);
		enterButton.addActionListener(this);
		panel.add(enterButton);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setToolTipText("");
		ImageIcon img2 = new ImageIcon("Z:\\JAVA Distributed System\\MiniProjetClient\\src\\assets\\forum.jpg");
		Image image2 = img2.getImage();
		Image newImg2 = image2.getScaledInstance(300, 140, Image.SCALE_DEFAULT);
		img2 = new ImageIcon(newImg2);
		lblNewLabel.setIcon(img2);
		lblNewLabel.setBounds(297, 47, 214, 172);
		enterPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Bienvenue sur notre Forum de disscussion");
		lblNewLabel_2.setFont(new Font("Samsung Sans", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(224, 0, 363, 65);
		enterPanel.add(lblNewLabel_2);
		
			
		bienvenue.setVisible(true);
		
		
		frame=bienvenue;

	}

	public void createChatFrame(int id) {
		

		frame = new JFrame("Forum - User " + id + " Chat");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setBounds(100, 100, 613, 376);

	    JMenuBar menuBar = createMenuBar();
	    frame.setJMenuBar(menuBar);

	    JPanel mainPanel = createMainPanel();
	    frame.getContentPane().add(mainPanel);

	    frame.pack();
	    frame.setVisible(true);

	}

	private JMenuBar createMenuBar() {
	    JMenuBar menuBar = new JMenuBar();

	    JMenu actionMenu = new JMenu("Action");
	    JMenuItem quitMenuItem = new JMenuItem("Quit");
	    quitMenuItem.addActionListener(e -> handleQuit());
	    actionMenu.add(quitMenuItem);
	    menuBar.add(actionMenu);

	    return menuBar;
	}

	private JPanel createMainPanel() {
	    JPanel mainPanel = new JPanel(new BorderLayout());

	    JPanel chatPanel = createChatPanel();
	    mainPanel.add(chatPanel, BorderLayout.CENTER);

	    JPanel inputPanel = createInputPanel();
	    mainPanel.add(inputPanel, BorderLayout.SOUTH);

	    return mainPanel;
	}

	private JPanel createChatPanel() {
	    JPanel chatPanel = new JPanel(new BorderLayout());

	    chatArea = new JTextArea(20, 50);
	    chatArea.setEditable(false);
	    chatArea.setFont(new Font("Monospace", Font.PLAIN, 14));
	    chatArea.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // add a border
	    chatArea.setBackground(Color.cyan); // set the background color

	    JScrollPane scrollPane = new JScrollPane(chatArea);
	    chatPanel.add(scrollPane, BorderLayout.CENTER);


	    return chatPanel;
	}

	private JPanel createInputPanel() {
	    JPanel inputPanel = new JPanel(new BorderLayout());

	    inputField = new JTextField();
	    inputField.addActionListener(this);
	    inputField.setFont(new Font("Monospace", Font.PLAIN, 14));
	    inputField.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // add a border
	    inputField.setBackground(Color.WHITE); // set the background color

	    listButton = new JButton("Qui");
	    listButton.setFont(new Font("Tahoma", Font.BOLD, 11));
	    listButton.addActionListener(this);

	    JPanel buttonPanel = new JPanel();
	    buttonPanel.add(listButton);

	    inputPanel.add(inputField, BorderLayout.CENTER);
	    inputPanel.add(buttonPanel, BorderLayout.EAST);

	    return inputPanel;
	}

	private void handleQuit() {
		int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION);
	    if (choice == JOptionPane.YES_OPTION) {
	        try {
	            frame.dispose();
	            showtheGUI();
	            forum.quitter(id);
	        } catch (RemoteException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	

}