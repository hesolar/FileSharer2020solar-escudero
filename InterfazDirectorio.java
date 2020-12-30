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
import javax.swing.JFormattedTextField;

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
	private JPanel pnlCliente;
	private JPanel pnlServidor;
	private JButton btnIr;
	private JButton btnAtras;
	private JComboBox cmbSeleccionarDirectorio;
	private JComboBox comboBox_1;
	private JLabel lblArchivos;
	private JButton btnAñadir;
	private JButton btnEliminar;
	private JTextField textField;
	private JLabel lblExpulsarClientes;
	private JButton btnConectar;
	private JLabel lblTuIpPublica;
	private JTextField textField_1;
	
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
		frame.setResizable(false);
		frame.setBounds(100, 100, 692, 458);
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
		txfPuerto.setBounds(250, 10, 56, 20);
		frame.getContentPane().add(txfPuerto);
		
		chbModoServidor = new Checkbox("Modo Servidor");
		chbModoServidor.setFont(new Font("Dialog", Font.BOLD, 12));
		
		
		chbModoServidor.addItemListener(c->chbStateChange(c));
		
		
		chbmodoCliente = new Checkbox("Modo Cliente");
		chbmodoCliente.setFont(new Font("Dialog", Font.BOLD, 12));
		chbmodoCliente.addItemListener(e->	chbStateChange(e));
			

			
	
		
		
		
		chbModoServidor.setBounds(437, 10, 112, 22);
		frame.getContentPane().add(chbModoServidor);
		chbmodoCliente.setBounds(555, 10, 105, 22);
		frame.getContentPane().add(chbmodoCliente);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIp.setBounds(21, 13, 15, 14);
		frame.getContentPane().add(lblIp);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPuerto.setBounds(206, 13, 46, 14);
		frame.getContentPane().add(lblPuerto);
		
		pnlCliente = new JPanel();
		pnlCliente.setBackground(Color.WHITE);
		pnlCliente.setBounds(10, 41, 330, 377);
		frame.getContentPane().add(pnlCliente);
		pnlCliente.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setVerticalAlignment(SwingConstants.TOP);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setBounds(135, 11, 43, 14);
		pnlCliente.add(lblCliente);
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDirectorioActual = new JLabel("Directorio Actual");
		lblDirectorioActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDirectorioActual.setBounds(10, 115, 102, 14);
		pnlCliente.add(lblDirectorioActual);
		
		txfDirectorioActual = new JTextField();
		txfDirectorioActual.setEditable(false);
		txfDirectorioActual.setBounds(123, 112, 197, 20);
		pnlCliente.add(txfDirectorioActual);
		txfDirectorioActual.setColumns(10);
		
		JLabel lblDirectorioDestino = new JLabel("Directorio Destino");
		lblDirectorioDestino.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDirectorioDestino.setBounds(10, 45, 102, 14);
		pnlCliente.add(lblDirectorioDestino);
		
		txfDirectorioDestino = new JTextField();
		txfDirectorioDestino.setColumns(10);
		txfDirectorioDestino.setBounds(122, 42, 198, 20);
		pnlCliente.add(txfDirectorioDestino);
		
		lblDirectorioDondeSe = new JLabel("Directorio donde se guardar\u00E1n los archivos");
		lblDirectorioDondeSe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDirectorioDondeSe.setBounds(10, 70, 310, 31);
		pnlCliente.add(lblDirectorioDondeSe);
		
		btnIr = new JButton("Ir");
		btnIr.setBackground(Color.GREEN);
		btnIr.setForeground(Color.BLACK);
		btnIr.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnIr.setBounds(160, 155, 75, 23);
		pnlCliente.add(btnIr);
		
		btnAtras = new JButton("Atras");
		btnAtras.setBackground(Color.ORANGE);
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnAtras.setBounds(244, 155, 75, 23);
		pnlCliente.add(btnAtras);
		
		cmbSeleccionarDirectorio = new JComboBox();
		cmbSeleccionarDirectorio.setBounds(10, 154, 129, 22);
		pnlCliente.add(cmbSeleccionarDirectorio);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 223, 129, 22);
		pnlCliente.add(comboBox_1);
		
		lblArchivos = new JLabel("Selecionar Archivos");
		lblArchivos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArchivos.setBounds(10, 198, 129, 14);
		pnlCliente.add(lblArchivos);
		
		btnAñadir = new JButton("A\u00F1adir");
		btnAñadir.setForeground(Color.BLACK);
		btnAñadir.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnAñadir.setBackground(Color.GREEN);
		btnAñadir.setBounds(160, 223, 75, 23);
		pnlCliente.add(btnAñadir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnEliminar.setBackground(Color.ORANGE);
		btnEliminar.setBounds(244, 223, 75, 23);
		pnlCliente.add(btnEliminar);
		
		pnlServidor = new JPanel();
		pnlServidor.setBackground(Color.DARK_GRAY);
		pnlServidor.setBounds(350, 41, 326, 377);
		frame.getContentPane().add(pnlServidor);
		pnlServidor.setLayout(null);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(137, 11, 48, 14);
		lblServidor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblServidor.setForeground(Color.WHITE);
		pnlServidor.add(lblServidor);
		
		lblClientesConectados = new JLabel("Clientes Conectados");
		lblClientesConectados.setForeground(Color.WHITE);
		lblClientesConectados.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClientesConectados.setBounds(21, 98, 106, 14);
		pnlServidor.add(lblClientesConectados);
		
		JComboBox cmbClientesConectados = new JComboBox();
		cmbClientesConectados.setBounds(21, 286, 106, 22);
		pnlServidor.add(cmbClientesConectados);
		
		JLabel lblRecursosClonaados = new JLabel("Recursos Clonados");
		lblRecursosClonaados.setForeground(Color.WHITE);
		lblRecursosClonaados.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblRecursosClonaados.setBounds(172, 39, 106, 14);
		pnlServidor.add(lblRecursosClonaados);
		
		txfRecursosClonados = new JTextField();
		txfRecursosClonados.setBounds(137, 65, 179, 288);
		pnlServidor.add(txfRecursosClonados);
		txfRecursosClonados.setColumns(10);
		
		JButton btnExpulsar = new JButton("Expulsar");
		btnExpulsar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExpulsar.setBackground(Color.RED);
		btnExpulsar.setForeground(new Color(0, 0, 0));
		btnExpulsar.setBounds(21, 319, 106, 23);
		pnlServidor.add(btnExpulsar);
		
		textField = new JTextField();
		textField.setBounds(21, 123, 106, 127);
		pnlServidor.add(textField);
		textField.setColumns(10);
		
		lblExpulsarClientes = new JLabel("Expulsar Clientes");
		lblExpulsarClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpulsarClientes.setForeground(Color.WHITE);
		lblExpulsarClientes.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblExpulsarClientes.setBounds(21, 261, 106, 14);
		pnlServidor.add(lblExpulsarClientes);
		
		lblTuIpPublica = new JLabel("Tu IP Publica");
		lblTuIpPublica.setHorizontalAlignment(SwingConstants.CENTER);
		lblTuIpPublica.setForeground(Color.WHITE);
		lblTuIpPublica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTuIpPublica.setBounds(20, 39, 106, 14);
		pnlServidor.add(lblTuIpPublica);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(20, 65, 106, 22);
		pnlServidor.add(textField_1);
		
		btnConectar = new JButton("Conectar");
		btnConectar.setBounds(326, 9, 92, 23);
		frame.getContentPane().add(btnConectar);

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

	// Comportaminto checkBox Modo cliente / servidor
	
	private void chbStateChange(ItemEvent e) {
		if (this.chbmodoCliente.getState() && this.chbModoServidor.getState()) {
			JOptionPane.showMessageDialog(null, "Error", "No puede activar ambos modos a la vez", 0, null);
			Checkbox a = (Checkbox) e.getItemSelectable();
			a.setState(false);
		}

		else {
			if (!this.chbmodoCliente.getState() && !this.chbModoServidor.getState()) {
				this.pnlCliente.show();
				this.pnlServidor.show();

			}

			else {
				if (this.chbmodoCliente.getState()) {
					this.pnlCliente.enable();
					this.pnlServidor.enable(false);

					this.pnlCliente.show();
					this.pnlServidor.hide();

				}

				if (this.chbModoServidor.getState()) {
					this.pnlServidor.enable();
					this.pnlCliente.enable(false);

					this.pnlServidor.show();
					this.pnlCliente.hide();
				}
			}
		}

	}
}
