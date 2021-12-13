/*
4. Crear una clase Punto representada por sus coordenadas X e Y y con los métodos habituales. 
Cree una clase Recta con los atributos m (para la pendiente) y punto_Inicial (objeto de la clase Punto). 
Implemente un método llamado getVer_EcuacionDeRecta el cual deberá mostrar por pantalla la ecuación de recta 
(mediante una ecuación del tipo y = mx + b, donde x, y son variables en un plano cartesiano) 
que pasa por el punto_Inicial (utilizando JFrame Form, JTable, utilice con JPoPMenu las 
opciones de modificar y eliminar, además utilice archivo de texto en Java para almacenar y cargar la información).
 */
package punto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Punto {
    //atributos
    double x,m,b;
    
    //metodo constructor
    Punto(){
        
    }
    
    //metodos setter y getter
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
    
     //creando archivo txt para almacenar los datos ingresados
    public void crearArchivo(JTable jtab){
        try{
           String fileRectangulo = "D:\\Documents\\NetBeansProjects\\Punto\\src\\punto\\datos.txt";
           BufferedWriter bfw = new BufferedWriter(new FileWriter(fileRectangulo));
            for (int i = 0; i < jtab.getColumnCount(); i++) {
                bfw.write(jtab.getColumnName(i) + "      ");
            }
            bfw.write("\n");
            for (int i = 0; i < jtab.getRowCount(); i++) {
                for (int j = 0; j < jtab.getColumnCount(); j++) {
                    bfw.write(jtab.getValueAt(i, j).toString() + "           ");
                }
                bfw.newLine();
            }
            bfw.close();
            JOptionPane.showMessageDialog(null, "El archivo fue creado correctamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo." + e.getMessage());
        }
    }
    
    //llenar tabla con los registros
    public void llenarTabla(double y2, double m2, double x2, double b2, JTable tabla){
       //asignar los valores obtenidos
        setX(x2);
        setM(m2);
        setB(b2);
        //añadimos el registro a la tabla
        try {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object nuevaTab[] = {y2,m2,x2,b2};
            tb.addRow(nuevaTab);
            JOptionPane.showMessageDialog(null, "Registro Añadido exitosamente"); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El registro no se pudo añadir "+e.getMessage());
        }
    }
    
    // Eliminar datos de una tabla
    public void eliminaRegistro(JTable tabla){
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        if (tabla.getSelectedRow()>=0){
            tb.removeRow(tabla.getSelectedRow());
        } 
    }
    
    //guardar datos modificados
    public void guardarDatosModificados(JTable tabla, int filaSelec, double x, double m, double b, double pendiente){
        pendiente=getVer_EcuacionDeRecta(x, m, b);
        try {            
            tabla.setValueAt(x, filaSelec, 1);
            tabla.setValueAt(m, filaSelec, 2);
            tabla.setValueAt(b, filaSelec, 3);
            tabla.setValueAt(pendiente, filaSelec, 0);
            JOptionPane.showMessageDialog(null, "Registro Modificado exitosamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //metodo ver ecuacion y su resultado
    public double getVer_EcuacionDeRecta(double x, double m, double b){
        double y=0;
        //calcular pendiente y = mx + b
        y = ((m*x)+b);
        return y;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
