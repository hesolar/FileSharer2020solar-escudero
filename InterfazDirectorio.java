import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InterfazDirectorio {

	// codigo propio escribir aqui
	private Cliente Operador;

	// autogeneradas
	private JFrame frame;
	private JTextField txfIP1;
	private JTextField txfIP2;
	private JTextField txfIP3;
	private JTextField txfIP4;
	private JTextField txfListaDescargas;
	private JTextField txfDescarga;
	private JTextField txfPuerto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazDirectorio window = new InterfazDirectorio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ESTE CÓDIGO ES AUTOGENERADO POR JAVA NO TOCAR
	/**
	 * Create the application.
	 */
	public InterfazDirectorio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton ntnConectar = new JButton("Conectar");
		ntnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickBotonConectar(e);
			}
		});
		
		
		
		ntnConectar.setBounds(237, 28, 102, 23);
		frame.getContentPane().add(ntnConectar);

		txfIP1 = new JTextField();
		txfIP1.setBounds(21, 29, 45, 20);
		frame.getContentPane().add(txfIP1);
		txfIP1.setColumns(10);

		txfIP2 = new JTextField();
		txfIP2.setColumns(10);
		txfIP2.setBounds(76, 29, 38, 20);
		frame.getContentPane().add(txfIP2);

		txfIP3 = new JTextField();
		txfIP3.setColumns(10);
		txfIP3.setBounds(124, 29, 38, 20);
		frame.getContentPane().add(txfIP3);

		txfIP4 = new JTextField();
		txfIP4.setColumns(10);
		txfIP4.setBounds(172, 29, 38, 20);
		frame.getContentPane().add(txfIP4);

		JList list = new JList();
		list.setBounds(10, 65, 1, 1);
		frame.getContentPane().add(list);

		JComboBox CmbDirectorio = new JComboBox();
		CmbDirectorio.setBounds(10, 87, 164, 125);
		frame.getContentPane().add(CmbDirectorio);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 223, 89, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(190, 223, 89, 23);
		frame.getContentPane().add(btnDelete);

		txfListaDescargas = new JTextField();
		txfListaDescargas.setBounds(190, 87, 115, 125);
		frame.getContentPane().add(txfListaDescargas);
		txfListaDescargas.setColumns(10);

		txfDescarga = new JTextField();
		txfDescarga.setColumns(10);
		txfDescarga.setBounds(324, 100, 99, 20);
		frame.getContentPane().add(txfDescarga);

		JButton btnDescarga = new JButton("Descarga");
		btnDescarga.setBounds(334, 138, 89, 23);
		frame.getContentPane().add(btnDescarga);

		txfPuerto = new JTextField();
		txfPuerto.setColumns(10);
		txfPuerto.setBounds(172, 1, 38, 20);
		frame.getContentPane().add(txfPuerto);

	}
	// HASTA AQUI BASICAMENTE

	// tomar la ip y el puerto y crear el primer clienteservidor
	//se comprueba que los numeros esten bien metidos 
	public void clickBotonConectar(ActionEvent e) {

		try {
			byte[] b= new byte[]{};
			String contenido;
			
			contenido=this.txfIP1.getText();
			ClaseMetodosAuxiliares.NumeroCorrecto(contenido);
			b[0]=(byte) Integer.parseInt(contenido);
			
			contenido=this.txfIP2.getText();
			ClaseMetodosAuxiliares.NumeroCorrecto(contenido);
			b[1]=(byte) Integer.parseInt(contenido);
			
			
			contenido=this.txfIP3.getText();
			ClaseMetodosAuxiliares.NumeroCorrecto(contenido);
			b[2]=(byte) Integer.parseInt(contenido);
			
			
			contenido=this.txfIP4.getText();
			ClaseMetodosAuxiliares.NumeroCorrecto(contenido);
			b[3]=(byte) Integer.parseInt(contenido);
		

			Integer port = Integer.parseInt(this.txfPuerto.getText());
			ClaseMetodosAuxiliares.NumeroCorrecto(port);
			this.Operador = new Cliente(port, b);
		
			
			
			//Operador.inicioCliente();
			//decidir si se desea iniciar el modo cliente o el modo servidor

		}

		catch (NumberFormatException f) {
			JOptionPane.showMessageDialog(null,"Error en el formato númerico", "Error en formato númerico", 0, null);
		}

	}

	
	

}
