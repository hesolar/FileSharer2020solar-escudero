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

import TrabajoFinal_LineaComandos.CA;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JSpinner;

public class InterfazDirectorio {
	
	// codigo propio escribir aqui
	private Cliente Operador;

	// autogeneradas
	private JFrame frame;
	private JTextField txfIP1;
	private JTextField txfIP2;
	private JTextField txfIP3;
	private JTextField txfIP4;
	private JTextField txfPuerto;
	private Checkbox chbModoServidor;
	private Checkbox chbmodoCliente;
	private JLabel lblClientesConectados;
	private JTextField txfRecursosClonados;
	private JTextField txfDirectorioActual;
	private JTextField txfDirectorioDestino;
	private JLabel lblDirectorioDondeSe;
	private JLabel lblDirectorioDondeTe;
	private JPanel pnlServidor;

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
		frame.setBounds(100, 100, 550, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txfIP1 = new JTextField();
		txfIP1.setBounds(46, 10, 30, 20);
		frame.getContentPane().add(txfIP1);
		txfIP1.setColumns(10);

		txfIP2 = new JTextField();
		txfIP2.setColumns(10);
		txfIP2.setBounds(86, 10, 30, 20);
		frame.getContentPane().add(txfIP2);

		txfIP3 = new JTextField();
		txfIP3.setColumns(10);
		txfIP3.setBounds(126, 10, 30, 20);
		frame.getContentPane().add(txfIP3);

		txfIP4 = new JTextField();
		txfIP4.setColumns(10);
		txfIP4.setBounds(166, 10, 30, 20);
		frame.getContentPane().add(txfIP4);

		JList list = new JList();
		list.setBounds(10, 65, 1, 1);
		frame.getContentPane().add(list);

		txfPuerto = new JTextField();
		txfPuerto.setColumns(10);
		txfPuerto.setBounds(273, 10, 38, 20);
		frame.getContentPane().add(txfPuerto);
		
		chbModoServidor = new Checkbox("Modo Servidor");
		
		
		chbModoServidor.addItemListener(c->chbStateChange(c));
		
		
		chbmodoCliente = new Checkbox("Modo Cliente");
		chbmodoCliente.addItemListener(e->	chbStateChange(e));
			

			
	
		
		
		
		chbModoServidor.setBounds(335, 10, 89, 22);
		frame.getContentPane().add(chbModoServidor);
		chbmodoCliente.setBounds(441, 10, 80, 22);
		frame.getContentPane().add(chbmodoCliente);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(21, 13, 15, 14);
		frame.getContentPane().add(lblIp);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(230, 13, 46, 14);
		frame.getContentPane().add(lblPuerto);
		
		JPanel pnlCliente = new JPanel();
		pnlCliente.setBackground(Color.WHITE);
		pnlCliente.setBounds(10, 41, 251, 259);
		frame.getContentPane().add(pnlCliente);
		pnlCliente.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setVerticalAlignment(SwingConstants.TOP);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBounds(96, 11, 43, 14);
		pnlCliente.add(lblCliente);
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDirectorioActual = new JLabel("Directorio Actual");
		lblDirectorioActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDirectorioActual.setBounds(10, 99, 102, 14);
		pnlCliente.add(lblDirectorioActual);
		
		txfDirectorioActual = new JTextField();
		txfDirectorioActual.setEditable(false);
		txfDirectorioActual.setBounds(122, 96, 119, 20);
		pnlCliente.add(txfDirectorioActual);
		txfDirectorioActual.setColumns(10);
		
		JLabel lblDirectorioDestino = new JLabel("Directorio Destino");
		lblDirectorioDestino.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDirectorioDestino.setBounds(10, 45, 102, 14);
		pnlCliente.add(lblDirectorioDestino);
		
		txfDirectorioDestino = new JTextField();
		txfDirectorioDestino.setEditable(false);
		txfDirectorioDestino.setColumns(10);
		txfDirectorioDestino.setBounds(122, 42, 119, 20);
		pnlCliente.add(txfDirectorioDestino);
		
		lblDirectorioDondeSe = new JLabel("Directorio donde se guardar\u00E1n los archivos");
		lblDirectorioDondeSe.setBounds(20, 70, 205, 14);
		pnlCliente.add(lblDirectorioDondeSe);
		
		lblDirectorioDondeTe = new JLabel("Directorio del servidor en el que te encuentras");
		lblDirectorioDondeTe.setBounds(20, 124, 231, 14);
		pnlCliente.add(lblDirectorioDondeTe);
		
		pnlServidor = new JPanel();
		pnlServidor.setBackground(Color.DARK_GRAY);
		pnlServidor.setBounds(273, 41, 251, 259);
		frame.getContentPane().add(pnlServidor);
		pnlServidor.setLayout(null);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(101, 11, 48, 14);
		lblServidor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblServidor.setForeground(Color.WHITE);
		pnlServidor.add(lblServidor);
		
		lblClientesConectados = new JLabel("Clientes Conectados");
		lblClientesConectados.setForeground(Color.WHITE);
		lblClientesConectados.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClientesConectados.setBounds(10, 39, 106, 14);
		pnlServidor.add(lblClientesConectados);
		
		JComboBox cmbClientesConectados = new JComboBox();
		cmbClientesConectados.setBounds(10, 65, 106, 22);
		pnlServidor.add(cmbClientesConectados);
		
		JLabel lblRecursosClonaados = new JLabel("Recursos Clonados");
		lblRecursosClonaados.setForeground(Color.WHITE);
		lblRecursosClonaados.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblRecursosClonaados.setBounds(135, 39, 106, 14);
		pnlServidor.add(lblRecursosClonaados);
		
		txfRecursosClonados = new JTextField();
		txfRecursosClonados.setBounds(126, 64, 115, 184);
		pnlServidor.add(txfRecursosClonados);
		txfRecursosClonados.setColumns(10);
		
		JButton btnExpulsar = new JButton("Expulsar");
		btnExpulsar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExpulsar.setBackground(Color.RED);
		btnExpulsar.setForeground(new Color(0, 0, 0));
		btnExpulsar.setBounds(20, 98, 89, 23);
		pnlServidor.add(btnExpulsar);

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
			CA.NumeroCorrecto(contenido);
			b[0]=(byte) Integer.parseInt(contenido);
			
			
			contenido=this.txfIP2.getText();
			if(contenido.length()==0)throw new NumberFormatException();
			CA.NumeroCorrecto(contenido);
			b[1]=(byte) Integer.parseInt(contenido);
			
			
			contenido=this.txfIP3.getText();
			if(contenido.length()==0)throw new NumberFormatException();
			CA.NumeroCorrecto(contenido);
			b[2]=(byte) Integer.parseInt(contenido);
			
			
			contenido=this.txfIP4.getText();
			if(contenido.length()==0)throw new NumberFormatException();
			CA.NumeroCorrecto(contenido);
			b[3]=(byte) Integer.parseInt(contenido);
		

			Integer port = Integer.parseInt(this.txfPuerto.getText());
			if(this.txfPuerto.getText().length()==0)throw new NumberFormatException();

			CA.NumeroCorrecto(port);
		
			
			
			
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
