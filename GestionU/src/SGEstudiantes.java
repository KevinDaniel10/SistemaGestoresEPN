import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SGEstudiantes {
    public JPanel Sistema;
    private JPasswordField contrasenaE;
    private JTabbedPane registro;
    private JTabbedPane datos;
    private JButton ingresarButton;
    private JTextField Isemestre;
    private JTextField Inombre;
    private JTextField Icurso;
    private JTextField Ipromedio;
    private JLabel titulo;
    private JLabel nombre;
    private JLabel curso;
    private JLabel semestre;
    private JLabel promedio;
    private JLabel contrasena;
    private JMenu Color;
    private JTable tabler;
    private JTable tabled;
    private DefaultTableModel tableModel;
    private DefaultTableModel tabledModel;
    private int numeroEstudiante = 0; // Inicializar el número de estudiante

    public SGEstudiantes() {
        //  botón "Ingresar"
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarButtonActionPerformed(e);
            }
        });

        // tabla tabler
        String[] columnNames = {"N°E", "Nombre", "Curso", "Semestre", "Promedio"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tabler.setModel(tableModel);

        // Agregar una fila por defecto a la tabla tabler
        Object[] defaultRow = {"N|E."+numeroEstudiante++, "Nombre", "Curso", "Semestre", "Promedio"};
        tableModel.addRow(defaultRow);

        // tabla tabled
        String[] columnNamesTabled = {"Nombre", "Estado"};
        tabledModel = new DefaultTableModel(columnNamesTabled, 0);
        tabled.setModel(tabledModel);
    }

    public void ingresarButtonActionPerformed(ActionEvent e) {
        // Obtener los datos del estudiante
        String nombreEstudiante = Inombre.getText();
        String cursoEstudiante = Icurso.getText();
        String semestreEstudiante = Isemestre.getText();
        String promedioEstudiante = Ipromedio.getText();
        char[] contrasenaEstudiante = contrasenaE.getPassword();

        // Validar la contraseña
        String contrasenaIngresada = new String(contrasenaEstudiante);
        if (!contrasenaIngresada.equals("epn2024")) {
            JOptionPane.showMessageDialog(Sistema, "Contraseña incorrecta. No se pueden guardar los datos.", "Error de Contraseña", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si la contraseña no es válida
        }

        // Agregar una fila a la tabla tabler
        Object[] rowData = {numeroEstudiante++, nombreEstudiante, cursoEstudiante, semestreEstudiante, promedioEstudiante};
        tableModel.addRow(rowData);

        // Calcular el estado del estudiante
        double promedioDouble = Double.parseDouble(promedioEstudiante);
        String estado = calcularEstado(promedioDouble);

        // Agregar una fila a la tabla tabled
        Object[] rowDataTabled = { nombreEstudiante, estado};
        tabledModel.addRow(rowDataTabled);

        // Limpiar los campos
        Inombre.setText("");
        Icurso.setText("");
        Isemestre.setText("");
        Ipromedio.setText("");
        contrasenaE.setText("");
    }

    public String calcularEstado(double promedio) {
        if (promedio >= 14 && promedio <= 20) {
            return "Aprobado";
        } else if (promedio >= 9 && promedio < 14) {
            return "Recuperación";
        } else if (promedio >= 0 && promedio < 9) {
            return "Reprobado";
        }
        return "";
    }
}

