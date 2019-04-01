package com.combatmanager.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.combatmanager.security.Configuration;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame implements View {
	private Configuration config;
	private JPanel contentPane;

	private final String NAME = "Tela Principal";
	private final int ACCESS = 3 * 5 * 7 * 11;

	@Override
	public int getAccess() {
		return this.ACCESS;
	}

	@Override
	public String getName() {
		return this.NAME;
	}
	

	/**
	 * @author Romulo Create the frame.
	 */
	public MainWindow(Configuration config) {

		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		    	if ("Winows".equals(info.getName())) {
		        	
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}
		
		this.config = config; // TO DO
		setTitle("Combat Manager 1.0");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/combat.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
	

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSystem = new JMenu("Sistema");
		menuBar.add(mnSystem);

		JMenuItem mntmUsers = new JMenuItem( new AbstractAction("Usu�rios") {
		
			public void actionPerformed(ActionEvent e)
			{
			
				CreateContentPane(new Users());
				revalidate();
			}
				
		});
		mntmUsers.setIcon(new ImageIcon(Modality.class.getResource("/img16/aplicacao.png")));
		mnSystem.add(mntmUsers);

		JMenuItem mntmExit = new JMenuItem( new AbstractAction("Sair") {
			
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
				
		});
		
		mntmExit.setIcon(new ImageIcon(Modality.class.getResource("/img16/sair.png")));
		mnSystem.add(mntmExit);

		JMenu mnRegisted = new JMenu("Cadastros");
		menuBar.add(mnRegisted);

		JMenuItem mntmStudents = new JMenuItem(new AbstractAction("Alunos"){
		
			public void actionPerformed(ActionEvent e)
			{
			
				CreateContentPane(new Students());
				revalidate();
			}
				
		});
		mntmStudents.setIcon(new ImageIcon(Modality.class.getResource("/img16/aplicacao.png")));
		mnRegisted.add(mntmStudents);

		JMenuItem mntmModalities = new JMenuItem(new AbstractAction("Modalidades"){
			
			public void actionPerformed(ActionEvent e)
			{
			
				CreateContentPane(new Modality());
				revalidate();
			}
				
		});
		mntmModalities.setIcon(new ImageIcon(Modality.class.getResource("/img16/aplicacao.png")));
		mnRegisted.add(mntmModalities);
		
		JMenuItem mntmPlans = new JMenuItem(new AbstractAction("Planos"){
			
			public void actionPerformed(ActionEvent e)
			{
			
				CreateContentPane(new Plans());
				revalidate();
			}
				
		});
		
		mntmPlans.setIcon(new ImageIcon(Modality.class.getResource("/img16/aplicacao.png")));
		mnRegisted.add(mntmPlans);

		JMenu mnProcesses = new JMenu("Processos");
		menuBar.add(mnProcesses);

		;
		JMenu mnRegister = new JMenu("Matricular");
		mnRegister.setIcon(new ImageIcon(Modality.class.getResource("/img16/aplicacao.png")));
		mnRegister.setHorizontalAlignment(SwingConstants.LEFT);
		mnProcesses.add(mnRegister);

		JMenuItem mntmStudent = new JMenuItem("Aluno");
		mnRegister.add(mntmStudent);

		JMenu mnBilling = new JMenu("Faturamento");
		mnBilling.setIcon(new ImageIcon(Modality.class.getResource("/img16/aplicacao.png")));
		mnProcesses.add(mnBilling);

		JMenuItem mntmGenerateBill = new JMenuItem("Gerar Faturas");
		mnBilling.add(mntmGenerateBill);

		JMenuItem mntmCheckBills = new JMenuItem("Consultar Faturas");
		mnBilling.add(mntmCheckBills);

		JMenuItem mntmMakePayment = new JMenuItem("Realizar Pagamento");
		mntmMakePayment.setSelected(true);
		mnBilling.add(mntmMakePayment);

		JMenu mnReports = new JMenu("Relat\u00F3rios");
		menuBar.add(mnReports);

		JMenuItem mntmMtrcula = new JMenuItem("Matr\u00EDcula");
		mnReports.add(mntmMtrcula);

		JMenu mnBills = new JMenu("Faturas");
		mnReports.add(mnBills);

		JMenu mnUtilities = new JMenu("Utilit\u00E1rios");
		menuBar.add(mnUtilities);

		JMenu mnHelp = new JMenu("Ajuda");
		menuBar.add(mnHelp);

		Container contentPane= this.CreateContentPane(new HomeWindow());
		
	}

	public Container CreateContentPane(View view) {
		Container c = view.run();
		setContentPane(c);
		return c;

	}

	@Override
	public JPanel run() {
		return null;
	}

}
