import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
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
	private Checkbox chbModoServidor;
	private Checkbox chbmodoCliente;
	private JComboBox CmbDirectorio;

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
		ntnConectar.addActionListener(e->clickBotonConectar(e));
		
		
		
		
		ntnConectar.setBounds(220, 11, 102, 23);
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
		
		chbModoServidor = new Checkbox("Modo Servidor");
		
		
		chbModoServidor.addItemListener(c->chbStateChange(c));
		
		
		chbmodoCliente = new Checkbox("Modo Cliente");
		chbmodoCliente.addItemListener(e->	chbStateChange(e));
			

			
	
		
		
		
		chbModoServidor.setBounds(334, 29, 89, 22);
		frame.getContentPane().add(chbModoServidor);
		chbmodoCliente.setBounds(334, 46, 80, 22);
		frame.getContentPane().add(chbmodoCliente);
		
		CmbDirectorio = new JComboBox();
		CmbDirectorio.setBounds(16, 69, 164, 125);
		frame.getContentPane().add(CmbDirectorio);

	}
	// HASTA AQUI BASICAMENTE

	// tomar la ip y el puerto y crear el primer clienteservidor
	//se comprueba que los numeros esten bien metidos 
	private void clickBotonConectar(ActionEvent e) {

		try {
			ComprobarPuertoIp();
			
			
			Integer port = Integer.parseInt(this.txfPuerto.getText());
			this.Operador = new Cliente(port, ConstruirIp());
		
			if(this.chbmodoCliente.getState()) {
				Operador.inicioCliente();
				
				actualizarDirectorios(Operador.getListaLectura());
			}
			else 							   Operador.inicioServidor();
			
			//Operador.inicioCliente();
			//decidir si se desea iniciar el modo cliente o el modo servidor

		}

		catch (NumberFormatException f) {
			JOptionPane.showMessageDialog(null,"Error en el formato númerico", "Error en formato númerico", 0, null);
		}

	}
	
	private void actualizarDirectorios(List<File> lf) {
		
		this.CmbDirectorio.removeAllItems();
		for(File f:lf) {
			
			this.CmbDirectorio.addItem(f.getName());
		}
	}
	
	//comprueba que los formatos sean correctos
	private boolean ComprobarPuertoIp() {
		
		try {
			byte[] b= new byte[]{};
			String contenido;
			
			contenido=this.txfIP1.getText();
			if(contenido.length()==0)throw new NumberFormatException();
			ClaseMetodosAuxiliares.NumeroCorrecto(contenido);
			b[0]=(byte) Integer.parseInt(contenido);
			
			
			contenido=this.txfIP2.getText();
			if(contenido.length()==0)throw new NumberFormatException();
			ClaseMetodosAuxiliares.NumeroCorrecto(contenido);
			b[1]=(byte) Integer.parseInt(contenido);
			
			
			contenido=this.txfIP3.getText();
			if(contenido.length()==0)throw new NumberFormatException();
			ClaseMetodosAuxiliares.NumeroCorrecto(contenido);
			b[2]=(byte) Integer.parseInt(contenido);
			
			
			contenido=this.txfIP4.getText();
			if(contenido.length()==0)throw new NumberFormatException();
			ClaseMetodosAuxiliares.NumeroCorrecto(contenido);
			b[3]=(byte) Integer.parseInt(contenido);
		

			Integer port = Integer.parseInt(this.txfPuerto.getText());
			if(this.txfPuerto.getText().length()==0)throw new NumberFormatException();

			ClaseMetodosAuxiliares.NumeroCorrecto(port);
		
			
			
			
			return true;
		}
		
	
		catch (NumberFormatException f) {
			JOptionPane.showMessageDialog(null,"Error en el formato númerico", "Error en formato númerico", 0, null);
			return false;
		}
		
		
		
	}
	
	//construyo la ip con los tb.text
	private byte[] ConstruirIp() {
		
		byte[] b= new byte[]{};
		
		
		b[0]=(byte) Integer.parseInt(this.txfIP1.getText());
		b[1]=(byte) Integer.parseInt(this.txfIP2.getText());
		b[2]=(byte) Integer.parseInt(this.txfIP3.getText());
		b[3]=(byte) Integer.parseInt(this.txfIP4.getText());

		return b;
		
	}
	
	
	//bloquear que el modo servidor y cliente se activen a la vez
	private void chbStateChange(ItemEvent e) {
		if(this.chbmodoCliente.getState()&&this.chbModoServidor.getState()) {
			JOptionPane.showMessageDialog(null,"Error", "No puede activar ambos modos a la vez", 0, null);
			Checkbox a= (Checkbox)e.getItemSelectable();
			a.setState(false);
		}

		
	}
}
