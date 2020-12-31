import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import TrabajoFinal_LineaComandos.CA;
import TrabajoFinal_LineaComandos.Servidor;
import TrabajoFinal_LineaComandos.d;

public class InterfazDirectorio {

	// escritas
	private Servidor servidor;

	// autogeneradas
	private JFrame frame;
	private JTextField txfIP1;
	private JTextField txfIP2;
	private JTextField txfIP3;
	private JTextField txfIP4;
	private JTextField txfPuerto;
	private Checkbox chbModoServidor;
	private Checkbox chbmodoCliente;
	private JComboBox cmbClientesConectados;
	private JLabel lblClientesConectados;
	private JTextField txfRecursosClonados;
	private JPanel pnlCliente;
	private JPanel pnlServidor;
	private JTextField textField;
	private JLabel lblExpulsarClientes;
	private JButton btnConectar;
	private JLabel lblSeleccionarDestino;
	private JLabel lblEstado;
	private JTable table;
	private JLabel lblFicheroOrigen;
	private JButton btnClonar;
	private JButton btnDesconectar;
	private JPanel pnlArbolSeleccionados_1;
	private JTree tree_3;
	private JComboBox cmbDestino;
	private DefaultMutableTreeNode root;
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
		frame.setBounds(100, 100, 705, 472);
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

		chbModoServidor.addItemListener(c -> chbStateChange(c));

		chbmodoCliente = new Checkbox("Modo Cliente");
		chbmodoCliente.setFont(new Font("Dialog", Font.BOLD, 12));
		chbmodoCliente.addItemListener(e -> chbStateChange(e));

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
		lblCliente.setBounds(10, 11, 310, 14);
		pnlCliente.add(lblCliente);
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);

		lblSeleccionarDestino = new JLabel("Fichero Destino");
		lblSeleccionarDestino.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarDestino.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccionarDestino.setBounds(10, 45, 109, 14);
		pnlCliente.add(lblSeleccionarDestino);

		table = new JTable();
		table.setBounds(201, 315, 1, 1);
		pnlCliente.add(table);

		lblFicheroOrigen = new JLabel("Seleccionar Archivos ");
		lblFicheroOrigen.setHorizontalAlignment(SwingConstants.CENTER);
		lblFicheroOrigen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFicheroOrigen.setBounds(97, 81, 142, 14);
		pnlCliente.add(lblFicheroOrigen);

		btnClonar = new JButton("Clonar");
		btnClonar.setBackground(Color.GREEN);
		btnClonar.setForeground(Color.BLACK);
		btnClonar.setBounds(37, 327, 109, 29);
		pnlCliente.add(btnClonar);

		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBackground(Color.RED);
		btnDesconectar.setBounds(183, 327, 109, 29);
		pnlCliente.add(btnDesconectar);

		pnlArbolSeleccionados_1 = new JPanel();
		pnlArbolSeleccionados_1.setBounds(10, 106, 310, 210);
		pnlCliente.add(pnlArbolSeleccionados_1);
		pnlArbolSeleccionados_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 310, 210);
		pnlArbolSeleccionados_1.add(scrollPane);
		
		
		root = new DefaultMutableTreeNode("C://users", true);
		File f = new File("C://");
		d d = new d();
		d.createChildren(f, root);		
//		this.tree_3=new JTree(root.getRoot());		
		this.tree_3 = new JTree(root);
		scrollPane.setColumnHeaderView(this.tree_3);
		this.tree_3.setVisibleRowCount(50);
		
		cmbDestino = new JComboBox();
		cmbDestino.setEditable(true);
		cmbDestino.setBounds(139, 45, 181, 23);
		pnlCliente.add(cmbDestino);

		pnlServidor = new JPanel();
		pnlServidor.setBackground(Color.DARK_GRAY);
		pnlServidor.setBounds(350, 41, 326, 377);
		frame.getContentPane().add(pnlServidor);
		pnlServidor.setLayout(null);

		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setBounds(21, 11, 179, 14);
		lblServidor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblServidor.setForeground(Color.WHITE);
		pnlServidor.add(lblServidor);

		lblClientesConectados = new JLabel("Clientes Conectados");
		lblClientesConectados.setForeground(Color.WHITE);
		lblClientesConectados.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblClientesConectados.setBounds(21, 39, 106, 14);
		pnlServidor.add(lblClientesConectados);

		cmbClientesConectados = new JComboBox();
		cmbClientesConectados.setBounds(21, 286, 106, 22);
		pnlServidor.add(cmbClientesConectados);

		JLabel lblRecursosClonaados = new JLabel("Recursos Clonados");
		lblRecursosClonaados.setForeground(Color.WHITE);
		lblRecursosClonaados.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblRecursosClonaados.setBounds(172, 39, 106, 14);
		pnlServidor.add(lblRecursosClonaados);

		txfRecursosClonados = new JTextField();
		txfRecursosClonados.setEditable(false);
		txfRecursosClonados.setBounds(137, 65, 179, 288);
		pnlServidor.add(txfRecursosClonados);
		txfRecursosClonados.setColumns(10);

		JButton btnExpulsar = new JButton("Expulsar");
//		btnExpulsar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				this.servidor.eliminameEsteMAn(cmbClientesConectados.getSelectedItem());
//			}
//		});

		btnExpulsar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExpulsar.setBackground(Color.RED);
		btnExpulsar.setForeground(new Color(0, 0, 0));
		btnExpulsar.setBounds(21, 319, 106, 23);
		pnlServidor.add(btnExpulsar);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 7));
		textField.setEditable(false);
		textField.setBounds(21, 64, 106, 186);
		pnlServidor.add(textField);
		textField.setColumns(10);

		lblExpulsarClientes = new JLabel("Expulsar Clientes");
		lblExpulsarClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpulsarClientes.setForeground(Color.WHITE);
		lblExpulsarClientes.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblExpulsarClientes.setBounds(21, 261, 106, 14);
		pnlServidor.add(lblExpulsarClientes);

		lblEstado = new JLabel("Apagado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setForeground(Color.RED);
		lblEstado.setBounds(210, 11, 106, 14);
		pnlServidor.add(lblEstado);

		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(e -> btnConectarClick(e));
		btnConectar.setBounds(326, 9, 92, 23);
		frame.getContentPane().add(btnConectar);
	}

	// comprueba que los formatos sean correctos
	private boolean ComprobarPuerto() {
		if (this.txfPuerto.getText().length() == 0)
			return false;
		Integer port = Integer.parseInt(this.txfPuerto.getText());
		return true;
	}

	private boolean ComprobarIp() {

		try {
			byte[] b = new byte[] {};
			String contenido;

			contenido = this.txfIP1.getText();
			if (contenido.length() == 0)
				return false;
			CA.NumeroCorrecto(contenido);
			b[0] = (byte) Integer.parseInt(contenido);

			contenido = this.txfIP2.getText();
			if (contenido.length() == 0)
				return false;
			CA.NumeroCorrecto(contenido);
			b[1] = (byte) Integer.parseInt(contenido);

			contenido = this.txfIP3.getText();
			if (contenido.length() == 0)
				return false;
			CA.NumeroCorrecto(contenido);
			b[2] = (byte) Integer.parseInt(contenido);

			contenido = this.txfIP4.getText();
			if (contenido.length() == 0)
				return false;
			CA.NumeroCorrecto(contenido);
			b[3] = (byte) Integer.parseInt(contenido);

			Integer port = Integer.parseInt(this.txfPuerto.getText());
			if (this.txfPuerto.getText().length() == 0)
				return false;

			CA.NumeroCorrecto(port);

			return true;
		}

		catch (NumberFormatException f) {
			JOptionPane.showMessageDialog(null, "Error en el formato númerico", "Error en formato númerico", 0, null);
			return false;
		}

	}

	// construyo la ip con los tb.text
	private byte[] ConstruirIp() {

		byte[] b = new byte[] {};

		b[0] = (byte) Integer.parseInt(this.txfIP1.getText());
		b[1] = (byte) Integer.parseInt(this.txfIP2.getText());
		b[2] = (byte) Integer.parseInt(this.txfIP3.getText());
		b[3] = (byte) Integer.parseInt(this.txfIP4.getText());

		return b;

	}

	// Comportaminto checkBox Modo cliente / servidor

	private void chbStateChange(ItemEvent e) {
		if (this.chbmodoCliente.getState() && this.chbModoServidor.getState()) {
			JOptionPane.showMessageDialog(null, "No puede activar ambos modos a la vez", "Error", 0, null);
			Checkbox a = (Checkbox) e.getItemSelectable();
			a.setState(false);
		}

		else {

			txfIP1.setText("");txfIP1.enable(true);
			txfIP2.setText("");	txfIP2.enable(true);
			txfIP3.setText("");	txfIP3.enable(true);
			txfIP4.setText("");	txfIP4.enable(true);
			this.lblEstado.setText("Apagado");
			this.lblEstado.setForeground(Color.RED);
			if (!this.chbmodoCliente.getState() && !this.chbModoServidor.getState()) {
				this.btnConectar.enable(true);
				
				if(servidor!=null) {
					this.servidor.apagar();
					this.servidor.stop();
				}
				this.btnConectar.show();
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

					if (this.servidor != null) {
						this.servidor.apagar();
						this.servidor.stop();
					}
						

					
				}
			}
		}
	}

	public void seleccionDirectorioTrabajo() {
		
		this.tree_3.removeAll();
		
//		File f = new File(linea);
		//File f = new File("C:\\Users\\usuario\\Desktop\\workspace");
		//----------------arreglar
//		if(f.isDirectory()) path= f.getAbsolutePath();
//		else {
//			System.out.println("El directorio no es correcto");
//			seleccionDirectorioTrabajo();
//		}
		
	}
	
	
	
	private void btnConectarClick(ActionEvent e) {

		// si no ha seleccionado modo error
		if (!this.chbmodoCliente.getState() && !this.chbModoServidor.getState()) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un modo", "Error", 0, null);

		}

		else {
			// si esta el modo cliente
			if (this.chbmodoCliente.getState()) {
				if(ComprobarIp()&&ComprobarPuerto()) {
					this.lblFicheroOrigen.hide();
					this.btnClonar.hide();
					this.btnDesconectar.hide();
					this.pnlArbolSeleccionados_1.hide();
					seleccionDirectorioTrabajo();
					
				}
				else {
					if(!ComprobarIp())JOptionPane.showMessageDialog(null, "IP incorrecta", "Error", 0, null);
					if(!ComprobarPuerto())JOptionPane.showMessageDialog(null, "Puerto incorrecto", "Error", 0, null);

				}

			}
			// ModoServidor
			else {
				if (this.ComprobarPuerto()) {
					this.txfPuerto.setBackground(Color.white);
					this.servidor = new Servidor(Integer.parseInt(this.txfPuerto.getText()));
					this.btnConectar.hide();
					servidor.start();
					String IP = CA.ipPublica();
					System.out.println(IP);
					String[] IPublica = IP.split("\\.");
					this.txfIP1.setText(IPublica[0]);
					this.txfIP1.enable(false);
					this.txfIP2.setText(IPublica[1]);
					this.txfIP2.enable(false);
					this.txfIP3.setText(IPublica[2]);
					this.txfIP3.enable(false);
					this.txfIP4.setText(IPublica[3]);
					this.txfIP4.enable(false);
					this.lblEstado.setText("Encendido");
					this.lblEstado.setForeground(Color.GREEN);
				} else {
					JOptionPane.showMessageDialog(null, "El puerto es incorrecto o esta en uso", "Error", 0, null);
					this.txfPuerto.setBackground(Color.LIGHT_GRAY);
				}
			}
		}
	}
}
