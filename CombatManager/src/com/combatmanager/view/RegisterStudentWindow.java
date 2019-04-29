package com.combatmanager.view;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.combatmanager.database.dao.AttendanceDAO;
import com.combatmanager.database.dao.GraduationDAO;
import com.combatmanager.database.dao.MatriculationDAO;
import com.combatmanager.database.dao.MatriculationModalityDAO;
import com.combatmanager.database.dao.ModalityDAO;
import com.combatmanager.database.dao.StudentDAO;
import com.combatmanager.database.model.Graduation;
import com.combatmanager.database.model.Matriculation;
import com.combatmanager.database.model.MatriculationModality;
import com.combatmanager.database.model.Modality;
import com.combatmanager.database.model.Student;
import com.combatmanager.error.AccessException;
import com.combatmanager.security.Configuration;

public class RegisterStudentWindow extends JPanel implements View{
	private JTextField textFieldRegistration;
	private JTextField textFieldF9;
	private JTextField textFieldStudent;
	private JTextField textFieldRegisterDay;
	private JTextField textFieldFinishDay;
	private JTable table;
	private JButton btnSave;
	private JButton btnSearch;
	private JButton btnRemove;
	private JButton btnAdd;
	private JButton btnAdicionarModalidade;
	private Boolean search;
	private Matriculation save_matriculation;
	private List<MatriculationModality> save_matriculationModality;
	private final String NAME = "Tela Cadastro Alunos";
	private final int ACCESS = 0;

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
	
	/**
	 * Create the panel.
	 */
	public JPanel run(Configuration config) {
		JPanel contentPane= new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
		JInternalFrame internalFrame = new JInternalFrame("Matricular Aluno");
		internalFrame.setFrameIcon(new ImageIcon(RegisterStudentWindow.class.getResource("/img/combatvinte.png")));
		internalFrame.setBounds(0, 0, 526, 396);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = env.getMaximumWindowBounds();
		
		Dimension jInternalFrameSize = internalFrame.getSize();
		int width= (bounds.width - jInternalFrameSize.width)/2;
		int height= (bounds.height - jInternalFrameSize.height)/2;
		
		internalFrame.setLocation(width, height);
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 415, 39);
		toolBar.setFloatable(false);
		internalFrame.getContentPane().add(toolBar);
		
		btnSearch = new JButton("Buscar");
		btnSearch.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/localizar.png")));
	
		btnSearch.setMaximumSize(new Dimension(98, 80));
		toolBar.add(btnSearch);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/adicionar.png")));
		
		JLabel space1 = new JLabel("  ");
		toolBar.add(space1);
		btnAdd.setMaximumSize(new Dimension(98, 80));
		toolBar.add(btnAdd);
		
		JLabel space2 = new JLabel("  ");
		toolBar.add(space2);
		
		btnRemove = new JButton("Remover");
		btnRemove.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/remover.png")));
		btnRemove.setMaximumSize(new Dimension(98, 80));
		
		toolBar.add(btnRemove);
		
		JLabel space3 = new JLabel("  ");
		toolBar.add(space3);
		
		btnSave = new JButton("Salvar");
		btnSave.setIcon(new ImageIcon(ModalityWindow.class.getResource("/img22/salvar.png")));
		btnSave.setMaximumSize(new Dimension(98, 80));
	
		toolBar.add(btnSave);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula: ");
		lblMatrcula.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMatrcula.setBounds(10, 62, 84, 22);
		internalFrame.getContentPane().add(lblMatrcula);
		
		textFieldRegistration = new JTextField();
		textFieldRegistration.setBounds(143, 61, 84, 19);
		internalFrame.getContentPane().add(textFieldRegistration);
		textFieldRegistration.setColumns(10);
		
		JLabel lblAluno = new JLabel("Aluno: ");
		lblAluno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAluno.setBounds(10, 94, 84, 22);
		internalFrame.getContentPane().add(lblAluno);
		
		textFieldF9 = new JTextField();
		textFieldF9.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldF9.setBackground(Color.YELLOW);
		textFieldF9.setForeground(Color.BLACK);
		textFieldF9.setColumns(10);
		textFieldF9.setBounds(143, 97, 84, 19);
		internalFrame.getContentPane().add(textFieldF9);
		
		textFieldStudent = new JTextField();
		textFieldStudent.setEnabled(false);
		textFieldStudent.setColumns(10);
		textFieldStudent.setBounds(237, 97, 263, 19);
		internalFrame.getContentPane().add(textFieldStudent);
		
		textFieldRegisterDay = new JTextField();
		textFieldRegisterDay.setColumns(10);
		textFieldRegisterDay.setBounds(143, 127, 84, 19);
		internalFrame.getContentPane().add(textFieldRegisterDay);
		
		JLabel lblRegisterDay = new JLabel("Data da matr\u00EDcula: ");
		lblRegisterDay.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRegisterDay.setBounds(10, 124, 116, 22);
		internalFrame.getContentPane().add(lblRegisterDay);
		
		JLabel lblDiaDoVencimento = new JLabel("Dia do vencimento da fatura: ");
		lblDiaDoVencimento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiaDoVencimento.setBounds(237, 124, 183, 22);
		internalFrame.getContentPane().add(lblDiaDoVencimento);
		
		textFieldFinishDay = new JTextField();
		textFieldFinishDay.setBounds(422, 127, 78, 20);
		internalFrame.getContentPane().add(textFieldFinishDay);
		textFieldFinishDay.setColumns(10);
		
		btnAdicionarModalidade = new JButton("Adicionar Modalidade");
		btnAdicionarModalidade.setBounds(10, 157, 217, 23);
		internalFrame.getContentPane().add(btnAdicionarModalidade);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 191, 490, 160);
		internalFrame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Modalidade", "Gradua\u00E7\u00E3o", "Plano", "Data In\u00EDcio", "Data Fim"
			}
		));
		scrollPane.setViewportView(table);
		
		
		resetWindow();

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!search) {
					textFieldRegistration.setEnabled(true);
					btnAdd.setEnabled(false);
					search = true;
					config.addToSystemLog(getName()+","+"Iniciou a operacao de busca");
					return;
				}
				MatriculationDAO matriculationDao = null;
				MatriculationModalityDAO matriculationModalityDao = null;
				StudentDAO studentDao = null;
				//save
				save_matriculation = new Matriculation();
				save_matriculation.setCode(Integer.parseInt(textFieldRegistration.getText()));
				try {
					
					matriculationDao = new MatriculationDAO(config.getConnection());
					studentDao = new StudentDAO(config.getConnection());
					Matriculation auxiliar_matriculation = (Matriculation) matriculationDao.Select(save_matriculation);
					if(auxiliar_matriculation == null) {
						JOptionPane.showMessageDialog(null, "Matricula nao encontrada.");
						config.addToSystemLog(getName()+","+"Matricula nao encontrada.");
						resetWindow();
						return;
					}
					save_matriculation = auxiliar_matriculation;
					textFieldF9.setText(Integer.toString(save_matriculation.getStudent_code()));
					textFieldRegisterDay.setText(save_matriculation.getMatriculation_date());
					textFieldFinishDay.setText(save_matriculation.getClosing_date());
					Student auxiliar_student = new Student();
					auxiliar_student.setIndex(save_matriculation.getStudent_code());
					Student student = (Student) studentDao.Select(auxiliar_student);
					textFieldStudent.setText(student.getName());
					textFieldRegistration.setText(Integer.toString(save_matriculation.getCode()));
					
					matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
					save_matriculationModality = matriculationModalityDao.SelectGraduationByMatriculation(save_matriculation);
					for(int i=0;i<save_matriculationModality.size();i++) {
						System.out.println(save_matriculationModality.get(i).toString());
						//TO DO put on table
					}
					
					config.addToSystemLog(getName()+","+"Busca realizada com sucesso"+","+auxiliar_matriculation.toString());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "erro em buscar.");
					config.addToSystemLog(getName()+","+"Erro em buscar");
					resetWindow();
				} catch (AccessException e1) {
					e1.showAcessWindowDenied();
					config.addToSystemLog(getName()+","+"Erro em buscar");
					resetWindow();
				}
				
				btnRemove.setEnabled(true);
				btnSave.setEnabled(true);
				btnSearch.setEnabled(false);
				textFieldF9.setEnabled(true);
				textFieldFinishDay.setEnabled(true);
				textFieldRegisterDay.setEnabled(true);
				textFieldStudent.setEnabled(false);
				textFieldRegistration.setEnabled(false);
				btnAdicionarModalidade.setEnabled(true);
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.addToSystemLog(getName()+","+"Iniciou adicao de nova modalidade");
				textFieldFinishDay.setEnabled(true);
				textFieldRegisterDay.setEnabled(true);
				textFieldRegisterDay.setEnabled(true);
				textFieldStudent.setEnabled(false);
				textFieldF9.setEnabled(false);
				btnSave.setEnabled(true);
				btnSearch.setEnabled(false);
				btnAdd.setEnabled(false);
				btnRemove.setEnabled(false);
				btnAdicionarModalidade.setEnabled(true);
				
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				config.addToSystemLog(getName()+","+"Iniciou a opreacao de remocao de uma matricula");
				if (JOptionPane.showConfirmDialog(null, "Deletando essa matricula voce estara deletando todo o historico de pagamentos, concorda com isso?")
						== 0) {
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Operacao cancelada");
					config.addToSystemLog(getName()+","+"Cancelou a remocao da matricula");
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "Deletando essa matricula voce estara deletando todas as suas assuidades, concorda com isso?")
						== 0) {
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Operacao cancelada");
					config.addToSystemLog(getName()+","+"Cancelou a remocao da matricula");
					return;
				}
				
				MatriculationDAO matriculationDao = null;
				MatriculationModalityDAO matriculationModalityDao = null;
				AttendanceDAO attendenceDao = null;
				try {
					
					matriculationDao = new MatriculationDAO(config.getConnection());
					matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
					Matriculation auxiliar_matriculation = save_matriculation;
					
					matriculationModalityDao.DeleteByMatriculation(auxiliar_matriculation);
					matriculationDao.Delete(save_matriculation);
					config.addToSystemLog(getName()+","+"Deletou com sucesso"+","+save_matriculation.toString());
					
					
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Operacao cancelada, mediante a erro.");
					config.addToSystemLog(getName()+","+"Operacao cancelada, mediante a erro");
					e1.printStackTrace();
				} catch (AccessException e1) {
					System.out.println("BTN SEARCH REMOVE");
					config.addToSystemLog(getName()+","+"Erro em deletar");
					e1.printStackTrace();
				}
				resetWindow();
			}
		});
		
		/*
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				config.addToSystemLog(getName()+","+"Incio operacao de salvar");
				ModalityDAO modalityDao = null;
				GraduationDAO graduationDao = null;
				MatriculationModalityDAO matriculationModalityDao = null;
				if(textFieldModality.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Favor preencher o campo de modalidade");
					config.addToSystemLog(getName()+","+"Tentou salvar com campo em branco");
					return;
				}
				// 0=yes, 1=no, 2=cancel
				int save_option = JOptionPane.showConfirmDialog(null, "Deseja salvar as alteracoes?");
				if (save_option == 1) {
					JOptionPane.showMessageDialog(null, "As alteracoes NAO foram salvas.");
					config.addToSystemLog(getName()+","+"Negou em salvar e descartou as alteracoes");
					resetWindow();
					return;
				}
				else if(save_option == 2) {
					JOptionPane.showMessageDialog(null, "Operacao de salvar cancelada.");
					config.addToSystemLog(getName()+","+"Cancelou a operacao de salvar");
					return;
				}
				if(search) {
					
					try {
						graduationDao = new GraduationDAO(config.getConnection());
						matriculationModalityDao = new MatriculationModalityDAO(config.getConnection());
						//insert
						for(int i=0;i<model.getRowCount();i++) {
							String model_value_graduation = (String) model.getValueAt(i, 0);
							Boolean found = false;
							for(int j=0;j<save_graduation.size();j++) {
								if(save_graduation.get(j).getGraduation().equals(model_value_graduation)) {
									save_graduation.remove(j);
									found = true;
									break;
								}
							}
							if(!found) {
								Graduation local_graduation = new Graduation();
								local_graduation.setModality(save_modality.getModality());
								local_graduation.setGraduation(model_value_graduation);
								graduationDao.Insert(local_graduation);
							}
						}
						//delete
						for(int i=0;i<save_graduation.size();i++) {
							matriculationModalityDao.UpdateGraduation(save_graduation.get(i),null);
							graduationDao.Delete(save_graduation.get(i));
						}
						config.addToSystemLog(getName()+","+"Salvou com sucesso");
						JOptionPane.showMessageDialog(null, "Operacao de salvar realizada com sucesso.");
						resetWindow();
					} catch (SQLException e1) {
						config.addToSystemLog(getName()+","+"Erro ao salvar");
						e1.printStackTrace();
					} catch (AccessException e1) {
						config.addToSystemLog(getName()+","+"Erro ao salvar");
						e1.printStackTrace();
					}
					
					return;
				}
				
				//caso ja exista
				
				
				try {
					modalityDao = new ModalityDAO(config.getConnection());
					
					Modality local_modality = new Modality();
					local_modality.setModality(textFieldModality.getText());
					try {
						Modality temp_modality = (Modality) modalityDao.Select(local_modality);
						if(temp_modality == null) {
							
						}else {
							throw new Exception("Modalidade ja existente, favor utilizar a busca ou mudar de nome.");
						}
					} catch (Exception e1) {
						config.addToSystemLog(getName()+","+"Tentou criar modalidade ja existente");
						JOptionPane.showMessageDialog(null, e1.getMessage());
						resetWindow();
						return;
					}
					modalityDao.Insert(local_modality);
					
					graduationDao = new GraduationDAO(config.getConnection());
					for(int i=0;i<model.getRowCount();i++) {
						Graduation local_gradual = new Graduation();
						
						local_gradual.setModality(local_modality.getModality());
						local_gradual.setGraduation((String) model.getValueAt(i, 0));
						graduationDao.Insert(local_gradual);
						
					}
					JOptionPane.showMessageDialog(null, "Operacao de salvar realizada com sucesso.");
					config.addToSystemLog(getName()+","+"Salvou/inserio com sucesso"+","+local_modality.toString());
					
				} catch (SQLException e1) {
					
					config.addToSystemLog(getName()+","+"Erro ao salvar");
					e1.printStackTrace();
				} catch (AccessException e1) {
					config.addToSystemLog(getName()+","+"Erro ao salvar");
					e1.printStackTrace();
				}
				
				resetWindow();
			}

		});
		*/
		return contentPane;
	}
	
	
	public void resetWindow() {
		search = false;
		btnAdd.setEnabled(true);
		btnSearch.setEnabled(true);
		btnRemove.setEnabled(false);
		btnSave.setEnabled(false);
		textFieldF9.setText("Teclar F9");
		textFieldF9.setEnabled(false);
		textFieldRegisterDay.setText("");
		textFieldRegistration.setText("");
		textFieldStudent.setText("");
		textFieldFinishDay.setText("");
		textFieldRegisterDay.setEnabled(false);
		textFieldRegistration.setEnabled(false);
		textFieldStudent.setEnabled(false);
		textFieldFinishDay.setEnabled(false);
		
	}
	
}
