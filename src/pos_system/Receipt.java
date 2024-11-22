/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pos_system;

import java.awt.print.PrinterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Sadahiru
 */
public class Receipt extends javax.swing.JFrame {

    /**
     * Creates new form Print
     */
    public Receipt() {
        initComponents();
    }
    String lsub;
    String lpay;
    String lbal;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    String date = dtf.format(now);

    public Receipt(String sub, String pay, String bal, TableModel tableModel) throws PrinterException {
        initComponents();

        this.lsub = sub;
        this.lpay = pay;
        this.lbal = bal;
        StringBuilder receipt = new StringBuilder();

        receipt.append("*************************************************\n");
        receipt.append("                    Tech Trend                   \n");
        receipt.append("         Bodiya Road, Mirihana, Nugegoda         \n");
        receipt.append("                  Tel: 011-311-9139              \n");
        receipt.append("         https://www.TechTrend.com               \n");
        receipt.append("                  Mobile: 071-333-6669           \n");
        receipt.append("              ").append(date).append("           \n");
        receipt.append("-------------------------------------------------\n");
        receipt.append("\n");
        receipt.append(String.format("%-20s %10s %10s\n", "Product", "Price", "Total"));

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String product = (String) tableModel.getValueAt(i, 1);
            String price = (String) tableModel.getValueAt(i, 2);
            int total = (int) tableModel.getValueAt(i, 4);

            receipt.append(String.format("%-20s %10s %10d\n", product, price, total));
        }

        receipt.append("\n");
        receipt.append("\n");
        receipt.append("-------------------------------------------------\n");

        receipt.append(String.format("%-20s %20s\n", "SubTotal:", sub));
        receipt.append(String.format("%-20s %20s\n", "Pay:", pay));
        receipt.append(String.format("%-20s %20s\n", "Balance:", bal));
        receipt.append("\n");

        receipt.append("*************************************************\n");
        receipt.append("             Thank you for shopping!             \n");
        receipt.append("*************************************************\n");

        Printtxt.setText(receipt.toString());
        Printtxt.print();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Printtxt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Receipt Preview");
        setName("BillingFrame"); // NOI18N
        setResizable(false);

        Printtxt.setEditable(false);
        Printtxt.setBackground(new java.awt.Color(255, 255, 255));
        Printtxt.setColumns(20);
        Printtxt.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        Printtxt.setRows(5);
        jScrollPane1.setViewportView(Printtxt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Printtxt;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
