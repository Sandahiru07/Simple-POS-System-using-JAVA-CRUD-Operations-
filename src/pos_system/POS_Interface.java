/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pos_system;

import com.mysql.cj.jdbc.Driver;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.print.PrinterException;

/**
 *
 * @author Sadahiru
 */
public class POS_Interface extends javax.swing.JFrame {

    /**
     * Creates new form POS_Interface
     */
    public POS_Interface() {
        initComponents();
        Table_Category();
        Table_Brand();
        //product table
        JoinTable();
        Category();
        Brand();
    }
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();

    //Product page category combobox 
    public class CategoryItem {

        int ID;
        String Name;

        public CategoryItem(int ID, String Name) {
            this.ID = ID;
            this.Name = Name;
        }

        public String toString() {
            return Name;
        }
    }

    //Product page brand combobox 
    public class BrandItem {

        int ID;
        String Name;

        public BrandItem(int ID, String Name) {
            this.ID = ID;
            this.Name = Name;
        }

        public String toString() {
            return Name;
        }
    }

    //load to Product page combobox to category item
    private void Category() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:MYSQL://localhost:3307/pos_system", "root", "");
            String query = "SELECT * FROM category";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ProductCategorycmb.removeAllItems();

            while (rs.next()) {
                ProductCategorycmb.addItem(new CategoryItem(rs.getInt(1), rs.getString(2)));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //load to Product page combobox to Brand Names
    private void Brand() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:MYSQL://localhost:3307/pos_system", "root", "");
            String query = "SELECT * FROM brand";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ProductBrandcmb.removeAllItems();

            while (rs.next()) {
                ProductBrandcmb.addItem(new BrandItem(rs.getInt(1), rs.getString(2)));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Table Update
    private void Table_Category() {
        try {
            int c;
            String SUrl, SUser, Spass;
            SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
            SUser = "root";
            Spass = "";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

                String query = "SELECT * FROM category";
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                ResultSetMetaData rsd = rs.getMetaData();
                c = rsd.getColumnCount();

                DefaultTableModel d = (DefaultTableModel) TableCategory.getModel();
                d.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= c; i++) {
                        v.add(rs.getString("ID"));
                        v.add(rs.getString("Category"));
                        v.add(rs.getString("Status"));
                    }
                    d.addRow(v);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    private void Table_Brand() {
        try {
            int c;
            String SUrl, SUser, Spass;
            SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
            SUser = "root";
            Spass = "";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

                String query = "SELECT * FROM brand";
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                ResultSetMetaData rsd = rs.getMetaData();
                c = rsd.getColumnCount();

                DefaultTableModel d = (DefaultTableModel) TableBrand.getModel();
                d.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= c; i++) {
                        v.add(rs.getString("ID"));
                        v.add(rs.getString("Brand"));
                        v.add(rs.getString("Status"));
                    }
                    d.addRow(v);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    //for product frame
    private void JoinTable() {
        try {
            int c;
            String SUrl, SUser, Spass;
            SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
            SUser = "root";
            Spass = "";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

                String query = ("SELECT p.ID,p.Product_Name,p.Description,c.Category,b.Brand,p.Cost_price,p.Retail_price,p.Quantity,p.Barcode,p.Status FROM product p,category c,brand b WHERE p.Category_ID = c.ID and p.Brand_ID = b.ID");
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                ResultSetMetaData rsd = rs.getMetaData();
                c = rsd.getColumnCount();

                DefaultTableModel d = (DefaultTableModel) ProductTable.getModel();
                d.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();

                    for (int i = 1; i <= c; i++) {
                        v.add(rs.getString("ID"));
                        v.add(rs.getString("Product_Name"));
                        v.add(rs.getString("Description"));
                        v.add(rs.getString("Category"));
                        v.add(rs.getString("Brand"));
                        v.add(rs.getString("Cost_price"));
                        v.add(rs.getString("Retail_price"));
                        v.add(rs.getString("Quantity"));
                        v.add(rs.getString("Barcode"));
                        v.add(rs.getString("Status"));
                    }
                    d.addRow(v);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    //Mouse clicked
    Color selectionColour = new Color(34, 48, 62);
    Color sidecolour = new Color(0, 255, 0);
    Color textselectionclolour = new Color(255, 255, 255);
    //Mouse Exited
    Color eselectionColour = new Color(0, 102, 102);
    Color esidecolour = new Color(0, 102, 102);
    Color etextselectionclolour = new Color(102, 120, 138);

    //Exit Buttons Colour change
    public void ECategorybtn() {
        jPanel9.setBackground(eselectionColour);
        jPanel13.setBackground(esidecolour);
        Categorylbl.setForeground(etextselectionclolour);
    }

    public void EBrandbtn() {
        jPanel6.setBackground(eselectionColour);
        jPanel7.setBackground(esidecolour);
        Brandlbl.setForeground(etextselectionclolour);
    }

    public void EProductbtn() {
        jPanel8.setBackground(eselectionColour);
        jPanel11.setBackground(esidecolour);
        Productlbl.setForeground(etextselectionclolour);
    }

    public void EBillingbtn() {
        jPanel12.setBackground(eselectionColour);
        jPanel10.setBackground(esidecolour);
        Billinglbl.setForeground(etextselectionclolour);
    }

    private void pos() {

        String name = Productcodetxt.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:MYSQL://localhost:3307/pos_system", "root", "");
            pst = con.prepareStatement("SELECT * FROM product WHERE Barcode=?");
            pst.setString(1, name);
            rs = pst.executeQuery();

            while (rs.next()) {
                int currentQty;

                currentQty = rs.getInt("Quantity");

                int price = Integer.parseInt(Productpricetxt.getText());
                int newQuantity = Integer.parseInt(Productquantitytxt.getText());
                int total = price * newQuantity;

                if (newQuantity >= currentQty) {
                    JOptionPane.showMessageDialog(this, "Quantity is not enoght!");
                    JOptionPane.showMessageDialog(this, "Available Product" + " = " + currentQty);
                } else {
                    model = (DefaultTableModel) Billingtable.getModel();
                    model.addRow(new Object[]{
                        Productcodetxt.getText(),
                        Productnametxt.getText(),
                        Productpricetxt.getText(),
                        Productquantitytxt.getText(),
                        total,});

                    int sum = 0;
                    for (int i = 0; i < Billingtable.getRowCount(); i++) {
                        sum = sum + Integer.parseInt(Billingtable.getValueAt(i, 4).toString());
                    }
                    Subtotallbl.setText(Integer.toString(sum));

                    Productcodetxt.setText("");
                    Productnametxt.setText("");
                    Productpricetxt.setText("");
                    Productquantitytxt.setText("");
                    Productcodetxt.setEnabled(true);
                    Productcodetxt.requestFocus();

                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void seles() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        String subtot = Subtotallbl.getText();
        String pay = paytxt.getText();
        String bal = Balancelbl.getText();
        int lastinsertid = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:MYSQL://localhost:3307/pos_system", "root", "");
            String query = "INSERT INTO sales(Date,Subtotal,Pay,Balance) VALUES(?,?,?,?)";
            pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, date);
            pst.setString(2, subtot);
            pst.setString(3, pay);
            pst.setString(4, bal);
            pst.executeUpdate();
            ResultSet generatedKeyResult = pst.getGeneratedKeys();

            if (generatedKeyResult.next()) {
                lastinsertid = generatedKeyResult.getInt(1);
            }

            int row = Billingtable.getRowCount();
            String query2 = "INSERT INTO sales_product(Sales_ID,Product_ID,Sell_Price,Quantity,Total) VALUES(?,?,?,?,?)";
            pst = con.prepareStatement(query2);

            String product_ID = "";
            String Price = "";
            String qty = "";
            int total = 0;
            for (int i = 0; i < Billingtable.getRowCount(); i++) {
                product_ID = (String) Billingtable.getValueAt(i, 0);
                Price = (String) Billingtable.getValueAt(i, 2);
                qty = (String) Billingtable.getValueAt(i, 3);
                total = (int) Billingtable.getValueAt(i, 4);

                pst.setInt(1, lastinsertid);
                pst.setString(2, product_ID);
                pst.setString(3, Price);
                pst.setString(4, qty);
                pst.setInt(5, total);
                pst.executeUpdate();
            }
            String query3 = "UPDATE product set Quantity = Quantity - ? WHERE Barcode = ?";
            pst = con.prepareStatement(query3);

            for (int i = 0; i < Billingtable.getRowCount(); i++) {
                product_ID = (String) Billingtable.getValueAt(i, 0);
                qty = (String) Billingtable.getValueAt(i, 3);

                pst.setString(1, qty);
                pst.setString(2, product_ID);
                pst.execute();
            }

            pst.addBatch();
            JOptionPane.showMessageDialog(null, "Recorded Saved");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void print() {
        String sub = Subtotallbl.getText();
        String pay = paytxt.getText();
        String bal = Balancelbl.getText();

        try {
            new Receipt(sub, pay, bal, Billingtable.getModel()).setVisible(true);
        } catch (PrinterException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel14 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        Categorylbl1 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        Brandlbl1 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        Productlbl1 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        Billinglbl1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel23 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        Categorylbl = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Brandlbl = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        Productlbl = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        Billinglbl = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Categorttxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Statuscmb = new javax.swing.JComboBox<>();
        AddCategory = new javax.swing.JLabel();
        UpdateCategory = new javax.swing.JLabel();
        DeleteCategory = new javax.swing.JLabel();
        ResetCategory = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TableCategory = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableBrand = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        Brandtxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Brandcmb = new javax.swing.JComboBox<>();
        ResetBrand = new javax.swing.JLabel();
        UpdateBrand = new javax.swing.JLabel();
        AddBrand = new javax.swing.JLabel();
        DeleteBrand = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ProductCostPricetxt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        ProductRetailPricetxt = new javax.swing.JTextField();
        ProductQuantitytxt = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        ProductNametxt = new javax.swing.JTextField();
        ProductCodetxt = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ProductDescriptiontxt = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        ProductCategorycmb = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        ProductStatuscmb = new javax.swing.JComboBox<>();
        ProductBrandcmb = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        ProductAdd = new javax.swing.JLabel();
        ProductUpdate = new javax.swing.JLabel();
        ProductDelete = new javax.swing.JLabel();
        ProductReset = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        Productnametxt = new javax.swing.JTextField();
        Productcodetxt = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        Productpricetxt = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        Productquantitytxt = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Billingtable = new javax.swing.JTable();
        BilllingCalculate = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        Balancelbl = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        paytxt = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        Subtotallbl = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        Checkquantity = new javax.swing.JLabel();
        Billingreset = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        AddtoBillingtable = new javax.swing.JLabel();
        Billingdelete = new javax.swing.JLabel();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jFrame1.setUndecorated(true);
        jFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(0, 102, 102));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos_system/Login_Logo-removebg-preview.png"))); // NOI18N
        jPanel14.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 6, 129, 88));

        jLabel8.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("GENIUS TECH");
        jPanel14.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 112, -1, 42));

        jPanel15.setBackground(new java.awt.Color(34, 48, 62));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Categorylbl1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Categorylbl1.setForeground(new java.awt.Color(255, 255, 255));
        Categorylbl1.setText("Category");
        Categorylbl1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Categorylbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Categorylbl1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Categorylbl1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Categorylbl1MouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                Categorylbl1MouseReleased(evt);
            }
        });
        jPanel15.add(Categorylbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 130, 30));

        jPanel16.setBackground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel15.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 200, -1));

        jPanel17.setBackground(new java.awt.Color(0, 102, 102));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Brandlbl1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Brandlbl1.setForeground(new java.awt.Color(102, 120, 138));
        Brandlbl1.setText("Brand");
        Brandlbl1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Brandlbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Brandlbl1MouseClicked(evt);
            }
        });
        jPanel17.add(Brandlbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 130, 30));

        jPanel18.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel17.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 30));

        jPanel14.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 200, -1));

        jPanel19.setBackground(new java.awt.Color(0, 102, 102));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Productlbl1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Productlbl1.setForeground(new java.awt.Color(102, 120, 138));
        Productlbl1.setText("Product");
        Productlbl1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Productlbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Productlbl1MouseClicked(evt);
            }
        });
        jPanel19.add(Productlbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 130, 30));

        jPanel20.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel19.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 30));

        jPanel14.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 200, 30));

        jPanel21.setBackground(new java.awt.Color(0, 102, 102));

        jPanel22.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        Billinglbl1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Billinglbl1.setForeground(new java.awt.Color(102, 120, 138));
        Billinglbl1.setText(" Billing");
        Billinglbl1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Billinglbl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Billinglbl1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(Billinglbl1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Billinglbl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel14.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 200, -1));

        jFrame1.getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 460));

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel23.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 333, -1, -1));

        jTable2.setBackground(new java.awt.Color(211, 211, 211));
        jTable2.setForeground(new java.awt.Color(0, 102, 102));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "ID", "Category", "Status"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel23.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 49, 420, 310));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("Category :");
        jPanel23.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, 20));

        jTextField2.setForeground(new java.awt.Color(0, 102, 102));
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel23.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 372, 180, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("Category :");
        jPanel23.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, 20));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(0, 102, 102));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Deactive" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel23.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 120, -1));

        jTabbedPane2.addTab("Catagory", jPanel23);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Brand", jPanel24);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Product", jPanel25);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Billing", jPanel26);

        jFrame1.getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 620, 460));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos_system/Login_Logo-removebg-preview.png"))); // NOI18N
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 6, 129, 88));

        jLabel6.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("GENIUS TECH");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 112, -1, 42));

        jPanel9.setBackground(new java.awt.Color(34, 48, 62));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Categorylbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Categorylbl.setForeground(new java.awt.Color(255, 255, 255));
        Categorylbl.setText("Category");
        Categorylbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Categorylbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CategorylblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CategorylblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CategorylblMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CategorylblMouseReleased(evt);
            }
        });
        jPanel9.add(Categorylbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 130, 30));

        jPanel13.setBackground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 200, -1));

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Brandlbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Brandlbl.setForeground(new java.awt.Color(102, 120, 138));
        Brandlbl.setText("Brand");
        Brandlbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Brandlbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BrandlblMouseClicked(evt);
            }
        });
        jPanel6.add(Brandlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 130, 30));

        jPanel7.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 30));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 200, -1));

        jPanel8.setBackground(new java.awt.Color(0, 102, 102));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Productlbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Productlbl.setForeground(new java.awt.Color(102, 120, 138));
        Productlbl.setText("Product");
        Productlbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Productlbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductlblMouseClicked(evt);
            }
        });
        jPanel8.add(Productlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 130, 30));

        jPanel11.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, 30));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 200, 30));

        jPanel12.setBackground(new java.awt.Color(0, 102, 102));

        jPanel10.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        Billinglbl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Billinglbl.setForeground(new java.awt.Color(102, 120, 138));
        Billinglbl.setText(" Billing");
        Billinglbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Billinglbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BillinglblMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(Billinglbl, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Billinglbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 200, -1));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pos_system/undo_26px.png"))); // NOI18N
        jLabel47.setText("Sign Out");
        jLabel47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 100, 30));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 460));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(740, 460));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 333, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setText("Status      :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 60, 20));

        Categorttxt.setForeground(new java.awt.Color(0, 102, 102));
        Categorttxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel1.add(Categorttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 372, 180, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setText("Category :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, 20));

        Statuscmb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Statuscmb.setForeground(new java.awt.Color(0, 102, 102));
        Statuscmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Deactive" }));
        Statuscmb.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Statuscmb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Statuscmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatuscmbActionPerformed(evt);
            }
        });
        jPanel1.add(Statuscmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 120, -1));

        AddCategory.setBackground(new java.awt.Color(255, 255, 255));
        AddCategory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddCategory.setForeground(new java.awt.Color(0, 102, 102));
        AddCategory.setText("       Add");
        AddCategory.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        AddCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddCategoryMouseClicked(evt);
            }
        });
        jPanel1.add(AddCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 70, 20));

        UpdateCategory.setBackground(new java.awt.Color(255, 255, 255));
        UpdateCategory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpdateCategory.setForeground(new java.awt.Color(0, 102, 102));
        UpdateCategory.setText("     Update");
        UpdateCategory.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        UpdateCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UpdateCategory.setEnabled(false);
        UpdateCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateCategoryMouseClicked(evt);
            }
        });
        jPanel1.add(UpdateCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 70, 20));

        DeleteCategory.setBackground(new java.awt.Color(255, 255, 255));
        DeleteCategory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DeleteCategory.setForeground(new java.awt.Color(0, 102, 102));
        DeleteCategory.setText("      Delete");
        DeleteCategory.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        DeleteCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteCategory.setEnabled(false);
        DeleteCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteCategoryMouseClicked(evt);
            }
        });
        jPanel1.add(DeleteCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 70, -1));

        ResetCategory.setBackground(new java.awt.Color(255, 255, 255));
        ResetCategory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ResetCategory.setForeground(new java.awt.Color(0, 102, 102));
        ResetCategory.setText("      Reset");
        ResetCategory.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        ResetCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ResetCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetCategoryMouseClicked(evt);
            }
        });
        jPanel1.add(ResetCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, 70, -1));

        jLabel53.setBackground(new java.awt.Color(255, 255, 255));
        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 51, 51));
        jLabel53.setText("  X");
        jLabel53.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel53MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 20, 30));

        TableCategory.setBackground(new java.awt.Color(211, 211, 211));
        TableCategory.setForeground(new java.awt.Color(0, 102, 102));
        TableCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Category", "Status"
            }
        ));
        TableCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCategoryMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(TableCategory);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 49, 420, 310));

        jTabbedPane1.addTab("Catagory", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableBrand.setBackground(new java.awt.Color(211, 211, 211));
        TableBrand.setForeground(new java.awt.Color(0, 102, 102));
        TableBrand.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Status"
            }
        ));
        TableBrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableBrandMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableBrand);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 49, 420, 310));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 102));
        jLabel14.setText("Brand       :");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, 20));

        Brandtxt.setForeground(new java.awt.Color(0, 102, 102));
        Brandtxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel2.add(Brandtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 372, 180, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 102));
        jLabel15.setText("Status      :");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 60, 20));

        Brandcmb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Brandcmb.setForeground(new java.awt.Color(0, 102, 102));
        Brandcmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Deactive" }));
        Brandcmb.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Brandcmb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Brandcmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrandcmbActionPerformed(evt);
            }
        });
        jPanel2.add(Brandcmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 120, -1));

        ResetBrand.setBackground(new java.awt.Color(255, 255, 255));
        ResetBrand.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ResetBrand.setForeground(new java.awt.Color(0, 102, 102));
        ResetBrand.setText("      Reset");
        ResetBrand.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        ResetBrand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ResetBrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetBrandMouseClicked(evt);
            }
        });
        jPanel2.add(ResetBrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, 70, -1));

        UpdateBrand.setBackground(new java.awt.Color(255, 255, 255));
        UpdateBrand.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpdateBrand.setForeground(new java.awt.Color(0, 102, 102));
        UpdateBrand.setText("     Update");
        UpdateBrand.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        UpdateBrand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        UpdateBrand.setEnabled(false);
        UpdateBrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateBrandMouseClicked(evt);
            }
        });
        jPanel2.add(UpdateBrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 70, 20));

        AddBrand.setBackground(new java.awt.Color(255, 255, 255));
        AddBrand.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddBrand.setForeground(new java.awt.Color(0, 102, 102));
        AddBrand.setText("       Add");
        AddBrand.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        AddBrand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddBrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBrandMouseClicked(evt);
            }
        });
        jPanel2.add(AddBrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 70, 20));

        DeleteBrand.setBackground(new java.awt.Color(255, 255, 255));
        DeleteBrand.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DeleteBrand.setForeground(new java.awt.Color(0, 102, 102));
        DeleteBrand.setText("      Delete");
        DeleteBrand.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        DeleteBrand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteBrand.setEnabled(false);
        DeleteBrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteBrandMouseClicked(evt);
            }
        });
        jPanel2.add(DeleteBrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 70, -1));

        jLabel52.setBackground(new java.awt.Color(255, 255, 255));
        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 51, 51));
        jLabel52.setText("  X");
        jLabel52.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 20, 30));

        jTabbedPane1.addTab("Brand", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ProductCostPricetxt.setForeground(new java.awt.Color(0, 102, 102));
        ProductCostPricetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel3.add(ProductCostPricetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 160, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 102, 102));
        jLabel21.setText("Category           :");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 102, 102));
        jLabel22.setText("Cost Price    :");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, 30));

        ProductRetailPricetxt.setForeground(new java.awt.Color(0, 102, 102));
        ProductRetailPricetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel3.add(ProductRetailPricetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 160, 30));

        ProductQuantitytxt.setForeground(new java.awt.Color(0, 102, 102));
        ProductQuantitytxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        ProductQuantitytxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductQuantitytxtActionPerformed(evt);
            }
        });
        jPanel3.add(ProductQuantitytxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 90, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 102));
        jLabel23.setText("Retail Price :");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 102));
        jLabel24.setText("Quantity      :");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, -1, 30));

        ProductNametxt.setForeground(new java.awt.Color(0, 102, 102));
        ProductNametxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel3.add(ProductNametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 160, 30));

        ProductCodetxt.setForeground(new java.awt.Color(0, 102, 102));
        ProductCodetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel3.add(ProductCodetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 90, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 102, 102));
        jLabel25.setText("Product code :");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, -1, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 102, 102));
        jLabel26.setText("Description      :");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        ProductDescriptiontxt.setColumns(20);
        ProductDescriptiontxt.setForeground(new java.awt.Color(0, 102, 102));
        ProductDescriptiontxt.setRows(5);
        ProductDescriptiontxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jScrollPane4.setViewportView(ProductDescriptiontxt);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 160, 90));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 102));
        jLabel27.setText("Status          :");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 80, 30));

        ProductCategorycmb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ProductCategorycmb.setForeground(new java.awt.Color(0, 102, 102));
        ProductCategorycmb.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ProductCategorycmb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProductCategorycmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductCategorycmbActionPerformed(evt);
            }
        });
        jPanel3.add(ProductCategorycmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 120, 30));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 102));
        jLabel28.setText("Brand                :");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 90, 30));

        ProductStatuscmb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ProductStatuscmb.setForeground(new java.awt.Color(0, 102, 102));
        ProductStatuscmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Deactive" }));
        ProductStatuscmb.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ProductStatuscmb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProductStatuscmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductStatuscmbActionPerformed(evt);
            }
        });
        jPanel3.add(ProductStatuscmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 120, 30));

        ProductBrandcmb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ProductBrandcmb.setForeground(new java.awt.Color(0, 102, 102));
        ProductBrandcmb.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ProductBrandcmb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProductBrandcmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductBrandcmbActionPerformed(evt);
            }
        });
        jPanel3.add(ProductBrandcmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 120, 30));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 102, 102));
        jLabel29.setText("Product Name :");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        ProductTable.setBackground(new java.awt.Color(211, 211, 211));
        ProductTable.setForeground(new java.awt.Color(0, 102, 102));
        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Product", "Description", "Category", "Brand", "Cost ", "Retail", "Quantity", "Barcode", "Status"
            }
        ));
        ProductTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(ProductTable);

        jPanel3.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 530, 160));

        ProductAdd.setBackground(new java.awt.Color(255, 255, 255));
        ProductAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ProductAdd.setForeground(new java.awt.Color(0, 102, 102));
        ProductAdd.setText("       Add");
        ProductAdd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        ProductAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProductAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductAddMouseClicked(evt);
            }
        });
        jPanel3.add(ProductAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 70, 20));

        ProductUpdate.setBackground(new java.awt.Color(255, 255, 255));
        ProductUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ProductUpdate.setForeground(new java.awt.Color(0, 102, 102));
        ProductUpdate.setText("     Update");
        ProductUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        ProductUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProductUpdate.setEnabled(false);
        ProductUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductUpdateMouseClicked(evt);
            }
        });
        jPanel3.add(ProductUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 70, 20));

        ProductDelete.setBackground(new java.awt.Color(255, 255, 255));
        ProductDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ProductDelete.setForeground(new java.awt.Color(0, 102, 102));
        ProductDelete.setText("     Delete");
        ProductDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        ProductDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProductDelete.setEnabled(false);
        ProductDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductDeleteMouseClicked(evt);
            }
        });
        jPanel3.add(ProductDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 70, -1));

        ProductReset.setBackground(new java.awt.Color(255, 255, 255));
        ProductReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ProductReset.setForeground(new java.awt.Color(0, 102, 102));
        ProductReset.setText("      Reset");
        ProductReset.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        ProductReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ProductReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductResetMouseClicked(evt);
            }
        });
        jPanel3.add(ProductReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 70, -1));

        jLabel51.setBackground(new java.awt.Color(255, 255, 255));
        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 51, 51));
        jLabel51.setText("  X");
        jLabel51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 20, 30));

        jTabbedPane1.addTab("Product", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 102, 102));
        jLabel34.setText("Product Name :");
        jPanel4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, 30));

        Productnametxt.setEditable(false);
        Productnametxt.setBackground(new java.awt.Color(255, 255, 255));
        Productnametxt.setForeground(new java.awt.Color(0, 102, 102));
        Productnametxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        Productnametxt.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jPanel4.add(Productnametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 150, 30));

        Productcodetxt.setForeground(new java.awt.Color(0, 102, 102));
        Productcodetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        Productcodetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProductcodetxtKeyPressed(evt);
            }
        });
        jPanel4.add(Productcodetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 90, 30));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 102, 102));
        jLabel35.setText("Product code :");
        jPanel4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 30));

        Productpricetxt.setEditable(false);
        Productpricetxt.setBackground(new java.awt.Color(255, 255, 255));
        Productpricetxt.setForeground(new java.awt.Color(0, 102, 102));
        Productpricetxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        Productpricetxt.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jPanel4.add(Productpricetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 90, 30));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 102, 102));
        jLabel36.setText("Product Price :");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 30));

        Productquantitytxt.setForeground(new java.awt.Color(0, 102, 102));
        Productquantitytxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        Productquantitytxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductquantitytxtActionPerformed(evt);
            }
        });
        jPanel4.add(Productquantitytxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 90, 30));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 102, 102));
        jLabel37.setText("Quantity        :");
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, 30));

        Billingtable.setBackground(new java.awt.Color(211, 211, 211));
        Billingtable.setForeground(new java.awt.Color(0, 102, 102));
        Billingtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Code", "Product Name", "Price", "Quantity", "Total"
            }
        ));
        Billingtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BillingtableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(Billingtable);

        jPanel4.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 210));

        BilllingCalculate.setBackground(new java.awt.Color(255, 255, 255));
        BilllingCalculate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BilllingCalculate.setForeground(new java.awt.Color(0, 102, 102));
        BilllingCalculate.setText("Calculate Total");
        BilllingCalculate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        BilllingCalculate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BilllingCalculateMouseClicked(evt);
            }
        });
        jPanel4.add(BilllingCalculate, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, 90, -1));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 102, 102));
        jLabel39.setText("Balance :");
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, -1, 30));

        Balancelbl.setEditable(false);
        Balancelbl.setBackground(new java.awt.Color(255, 255, 255));
        Balancelbl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Balancelbl.setForeground(new java.awt.Color(0, 102, 102));
        Balancelbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel4.add(Balancelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, 90, 30));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 102, 102));
        jLabel40.setText("Rs.");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, 20, 30));

        paytxt.setForeground(new java.awt.Color(0, 102, 102));
        paytxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel4.add(paytxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 380, 90, 30));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 102, 102));
        jLabel41.setText("Pay   :");
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 40, 30));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 102, 102));
        jLabel42.setText("Rs.");
        jPanel4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 20, 30));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 102, 102));
        jLabel43.setText("Sub Total  :");
        jPanel4.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, 30));

        Subtotallbl.setEditable(false);
        Subtotallbl.setBackground(new java.awt.Color(255, 255, 255));
        Subtotallbl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Subtotallbl.setForeground(new java.awt.Color(0, 102, 102));
        Subtotallbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel4.add(Subtotallbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 90, 30));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 102, 102));
        jLabel44.setText("Rs.");
        jPanel4.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 20, 30));

        Checkquantity.setBackground(new java.awt.Color(255, 255, 255));
        Checkquantity.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Checkquantity.setForeground(new java.awt.Color(0, 102, 102));
        Checkquantity.setText("Check Quantity");
        Checkquantity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        Checkquantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckquantityMouseClicked(evt);
            }
        });
        jPanel4.add(Checkquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 90, -1));

        Billingreset.setBackground(new java.awt.Color(255, 255, 255));
        Billingreset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Billingreset.setForeground(new java.awt.Color(0, 102, 102));
        Billingreset.setText("      Reset");
        Billingreset.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        Billingreset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BillingresetMouseClicked(evt);
            }
        });
        jPanel4.add(Billingreset, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 70, -1));

        jLabel50.setBackground(new java.awt.Color(255, 255, 255));
        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 51, 51));
        jLabel50.setText("  X");
        jLabel50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 20, 30));

        AddtoBillingtable.setBackground(new java.awt.Color(255, 255, 255));
        AddtoBillingtable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AddtoBillingtable.setForeground(new java.awt.Color(0, 102, 102));
        AddtoBillingtable.setText("        Add");
        AddtoBillingtable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        AddtoBillingtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddtoBillingtableMouseClicked(evt);
            }
        });
        jPanel4.add(AddtoBillingtable, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 70, -1));

        Billingdelete.setBackground(new java.awt.Color(255, 255, 255));
        Billingdelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Billingdelete.setForeground(new java.awt.Color(0, 102, 102));
        Billingdelete.setText("     Delete");
        Billingdelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true));
        Billingdelete.setEnabled(false);
        Billingdelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BillingdeleteMouseClicked(evt);
            }
        });
        jPanel4.add(Billingdelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 70, -1));

        jTabbedPane1.addTab("Billing", jPanel4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 620, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CategorylblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CategorylblMouseClicked
        jPanel9.setBackground(selectionColour);
        jPanel13.setBackground(sidecolour);
        Categorylbl.setForeground(textselectionclolour);
        jTabbedPane1.setSelectedIndex(0);
        EBrandbtn();
        EBillingbtn();
        EProductbtn();
    }//GEN-LAST:event_CategorylblMouseClicked

    private void CategorylblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CategorylblMouseEntered

    }//GEN-LAST:event_CategorylblMouseEntered

    private void CategorylblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CategorylblMouseExited
        //        jPanel9.setBackground(eselectionColour);
        //        jPanel10.setBackground(esidecolour);
        //        jLabel10.setForeground(etextselectionclolour);
        //        jLabel11.setVisible(true);
    }//GEN-LAST:event_CategorylblMouseExited

    private void CategorylblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CategorylblMouseReleased

    }//GEN-LAST:event_CategorylblMouseReleased

    private void BrandlblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BrandlblMouseClicked
        jPanel6.setBackground(selectionColour);
        jPanel7.setBackground(sidecolour);
        Brandlbl.setForeground(textselectionclolour);
        jTabbedPane1.setSelectedIndex(1);
        ECategorybtn();
        EBillingbtn();
        EProductbtn();
    }//GEN-LAST:event_BrandlblMouseClicked

    private void ProductlblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductlblMouseClicked
        jPanel8.setBackground(selectionColour);
        jPanel11.setBackground(sidecolour);
        Productlbl.setForeground(textselectionclolour);
        jTabbedPane1.setSelectedIndex(2);
        EBrandbtn();
        EBillingbtn();
        ECategorybtn();
    }//GEN-LAST:event_ProductlblMouseClicked

    private void BillinglblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillinglblMouseClicked
        jPanel12.setBackground(selectionColour);
        jPanel10.setBackground(sidecolour);
        Billinglbl.setForeground(textselectionclolour);
        jTabbedPane1.setSelectedIndex(3);
        EBrandbtn();
        ECategorybtn();
        EProductbtn();
    }//GEN-LAST:event_BillinglblMouseClicked

    private void StatuscmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatuscmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatuscmbActionPerformed

    private void Categorylbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Categorylbl1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Categorylbl1MouseClicked

    private void Categorylbl1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Categorylbl1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Categorylbl1MouseEntered

    private void Categorylbl1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Categorylbl1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Categorylbl1MouseExited

    private void Categorylbl1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Categorylbl1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Categorylbl1MouseReleased

    private void Brandlbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Brandlbl1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Brandlbl1MouseClicked

    private void Productlbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Productlbl1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Productlbl1MouseClicked

    private void Billinglbl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Billinglbl1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Billinglbl1MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void BrandcmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrandcmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BrandcmbActionPerformed

    private void ProductCategorycmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductCategorycmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductCategorycmbActionPerformed

    private void ProductStatuscmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductStatuscmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductStatuscmbActionPerformed

    private void ProductBrandcmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductBrandcmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductBrandcmbActionPerformed

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        Login log = new Login();
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to close?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            log.setVisible(true);
            this.dispose();
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_jLabel47MouseClicked

    private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseClicked
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to close?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_jLabel53MouseClicked

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to close?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to close?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_jLabel51MouseClicked

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        int response = JOptionPane.showConfirmDialog(null, "Are you sure want to close?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_jLabel50MouseClicked

    private void AddCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddCategoryMouseClicked
        String Category, Status, query;
        String SUrl, SUser, Spass;
        SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
        SUser = "root";
        Spass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);
            Statement st = con.createStatement();
            if ("".equals(Categorttxt.getText())) {
                JOptionPane.showMessageDialog(null, "Category name is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Category = Categorttxt.getText();
                Status = Statuscmb.getSelectedItem().toString();
                query = "INSERT INTO category(Category,Status) VALUES('" + Category + "', '" + Status + "')";
                st.execute(query);
                JOptionPane.showMessageDialog(null, "Category added successfully!");
                Categorttxt.setText("");
                Statuscmb.setSelectedIndex(0);
                Table_Category();
                Categorttxt.requestFocus();
            }

        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }//GEN-LAST:event_AddCategoryMouseClicked

    private void UpdateCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateCategoryMouseClicked
        DefaultTableModel d = (DefaultTableModel) TableCategory.getModel();
        int selectIndex = TableCategory.getSelectedRow();

        int ID = Integer.parseInt(d.getValueAt(selectIndex, 0).toString());
        String Category, Status, query;
        String SUrl, SUser, Spass;
        SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
        SUser = "root";
        Spass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

            Category = Categorttxt.getText();
            Status = Statuscmb.getSelectedItem().toString();
            query = "UPDATE category SET Category=?,Status=? WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, Category);
            stmt.setString(2, Status);
            stmt.setInt(3, ID);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Category updated successfully!");
            Categorttxt.setText("");
            Statuscmb.setSelectedIndex(0);
            Table_Category();
            Categorttxt.requestFocus();
            UpdateCategory.setEnabled(false);
            DeleteCategory.setEnabled(false);

        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }//GEN-LAST:event_UpdateCategoryMouseClicked

    private void DeleteCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteCategoryMouseClicked
        DefaultTableModel d = (DefaultTableModel) TableCategory.getModel();
        int selectIndex = TableCategory.getSelectedRow();

        int ID = Integer.parseInt(d.getValueAt(selectIndex, 0).toString());
        String Category, Status, query;
        String SUrl, SUser, Spass;
        SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
        SUser = "root";
        Spass = "";

        int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to delete the record?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (dialogresult == JOptionPane.YES_OPTION) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

                Category = Categorttxt.getText();
                Status = Statuscmb.getSelectedItem().toString();
                query = "DELETE FROM category WHERE ID = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, ID);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Category deleted successfully!");
                Categorttxt.setText("");
                Statuscmb.setSelectedIndex(0);
                Table_Category();
                Categorttxt.requestFocus();
                UpdateCategory.setEnabled(false);
                DeleteCategory.setEnabled(false);
            } catch (Exception e) {
            }
        } else {
            Categorttxt.setText("");
            Statuscmb.setSelectedIndex(0);
            Table_Category();
            Categorttxt.requestFocus();
            DeleteCategory.setEnabled(false);
        }
    }//GEN-LAST:event_DeleteCategoryMouseClicked

    private void ResetCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetCategoryMouseClicked
        Table_Category();
        Categorttxt.setText("");
        Statuscmb.setSelectedIndex(0);
        UpdateCategory.setEnabled(false);
        DeleteCategory.setEnabled(false);
    }//GEN-LAST:event_ResetCategoryMouseClicked

    private void AddBrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddBrandMouseClicked
        String Brand, Status, query;
        String SUrl, SUser, Spass;
        SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
        SUser = "root";
        Spass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);
            Statement st = con.createStatement();
            if ("".equals(Brandtxt.getText())) {
                JOptionPane.showMessageDialog(null, "Brand name is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Brand = Brandtxt.getText();
                Status = Brandcmb.getSelectedItem().toString();
                query = "INSERT INTO brand(Brand,Status) VALUES('" + Brand + "', '" + Status + "')";
                st.execute(query);
                JOptionPane.showMessageDialog(null, "Brand added successfully!");
                Brandtxt.setText("");
                Brandcmb.setSelectedIndex(0);
                Table_Brand();
                Brandtxt.requestFocus();
            }

        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }//GEN-LAST:event_AddBrandMouseClicked

    private void UpdateBrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateBrandMouseClicked
        DefaultTableModel d = (DefaultTableModel) TableBrand.getModel();
        int selectIndex = TableBrand.getSelectedRow();

        int ID = Integer.parseInt(d.getValueAt(selectIndex, 0).toString());
        String Brand, Status, query;
        String SUrl, SUser, Spass;
        SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
        SUser = "root";
        Spass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

            Brand = Brandtxt.getText();
            Status = Brandcmb.getSelectedItem().toString();
            query = "UPDATE brand SET Brand=?,Status=? WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, Brand);
            stmt.setString(2, Status);
            stmt.setInt(3, ID);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Brand updated successfully!");
            Brandtxt.setText("");
            Brandcmb.setSelectedIndex(0);
            Table_Brand();
            Brandtxt.requestFocus();
            UpdateBrand.setEnabled(false);
            DeleteBrand.setEnabled(false);

        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }//GEN-LAST:event_UpdateBrandMouseClicked

    private void TableBrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableBrandMouseClicked
        DefaultTableModel d = (DefaultTableModel) TableBrand.getModel();
        int selectIndex = TableBrand.getSelectedRow();

        Brandtxt.setText(d.getValueAt(selectIndex, 1).toString());
        Brandcmb.setSelectedItem(d.getValueAt(selectIndex, 2).toString());
        UpdateBrand.setEnabled(true);
        DeleteBrand.setEnabled(true);
    }//GEN-LAST:event_TableBrandMouseClicked

    private void DeleteBrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteBrandMouseClicked
        DefaultTableModel d = (DefaultTableModel) TableBrand.getModel();
        int selectIndex = TableBrand.getSelectedRow();

        int ID = Integer.parseInt(d.getValueAt(selectIndex, 0).toString());
        String Brand, Status, query;
        String SUrl, SUser, Spass;
        SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
        SUser = "root";
        Spass = "";

        int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to delete the record?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (dialogresult == JOptionPane.YES_OPTION) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

                Brand = Brandtxt.getText();
                Status = Statuscmb.getSelectedItem().toString();
                query = "DELETE FROM brand WHERE ID = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, ID);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Brand deleted successfully!");
                Brandtxt.setText("");
                Brandcmb.setSelectedIndex(0);
                Table_Brand();
                Brandtxt.requestFocus();
                UpdateBrand.setEnabled(false);
                DeleteBrand.setEnabled(false);
            } catch (Exception e) {
            }
        } else {
            Brandtxt.setText("");
            Brandcmb.setSelectedIndex(0);
            Table_Brand();
            Brandtxt.requestFocus();
            DeleteBrand.setEnabled(false);
        }
    }//GEN-LAST:event_DeleteBrandMouseClicked

    private void ProductQuantitytxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductQuantitytxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductQuantitytxtActionPerformed

    private void ResetBrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetBrandMouseClicked
        Table_Brand();
        Brandtxt.setText("");
        Brandcmb.setSelectedIndex(0);
        UpdateBrand.setEnabled(false);
        DeleteBrand.setEnabled(false);
    }//GEN-LAST:event_ResetBrandMouseClicked

    private void ProductTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductTableMouseClicked
        DefaultTableModel d = (DefaultTableModel) ProductTable.getModel();
        int selectIndex = ProductTable.getSelectedRow();

        ProductNametxt.setText(d.getValueAt(selectIndex, 1).toString());
        ProductDescriptiontxt.setText(d.getValueAt(selectIndex, 2).toString());
        ProductCategorycmb.setSelectedItem(d.getValueAt(selectIndex, 3).toString());
        ProductBrandcmb.setSelectedItem(d.getValueAt(selectIndex, 4).toString());
        ProductCostPricetxt.setText(d.getValueAt(selectIndex, 5).toString());
        ProductRetailPricetxt.setText(d.getValueAt(selectIndex, 6).toString());
        ProductQuantitytxt.setText(d.getValueAt(selectIndex, 7).toString());
        ProductCodetxt.setText(d.getValueAt(selectIndex, 8).toString());
        ProductStatuscmb.setSelectedItem(d.getValueAt(selectIndex, 9).toString());

        ProductUpdate.setEnabled(true);
        ProductDelete.setEnabled(true);
    }//GEN-LAST:event_ProductTableMouseClicked

    private void ProductAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductAddMouseClicked
        String SUrl, SUser, Spass;
        SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
        SUser = "root";
        Spass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

            if ("".equals(ProductNametxt.getText())) {
                JOptionPane.showMessageDialog(null, "Product name is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(ProductDescriptiontxt.getText())) {
                JOptionPane.showMessageDialog(null, "Description is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(ProductCategorycmb.getSelectedIndex())) {
                JOptionPane.showMessageDialog(null, "Category name is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(ProductBrandcmb.getSelectedIndex())) {
                JOptionPane.showMessageDialog(null, "Brand name is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(ProductCostPricetxt.getText())) {
                JOptionPane.showMessageDialog(null, "Cost price is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(ProductRetailPricetxt.getText())) {
                JOptionPane.showMessageDialog(null, "Retail price is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(ProductQuantitytxt.getText())) {
                JOptionPane.showMessageDialog(null, "Quantity is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(ProductCodetxt.getText())) {
                JOptionPane.showMessageDialog(null, "Product code is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(ProductStatuscmb.getSelectedIndex())) {
                JOptionPane.showMessageDialog(null, "Status is require!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String product = ProductNametxt.getText();
                String desc = ProductDescriptiontxt.getText();
                CategoryItem citem = (CategoryItem) ProductCategorycmb.getSelectedItem();
                BrandItem britem = (BrandItem) ProductBrandcmb.getSelectedItem();
                String cprice = ProductCostPricetxt.getText();
                String rprice = ProductRetailPricetxt.getText();
                String qty = ProductQuantitytxt.getText();
                String barcode = ProductCodetxt.getText();
                String status = ProductStatuscmb.getSelectedItem().toString();

                pst = con.prepareStatement("INSERT INTO product(Product_Name,Description,Category_ID,Brand_ID,Cost_price,Retail_price,Quantity,Barcode,Status) VALUES(?,?,?,?,?,?,?,?,?)");

                pst.setString(1, product);
                pst.setString(2, desc);
                pst.setInt(3, citem.ID);
                pst.setInt(4, britem.ID);
                pst.setString(5, cprice);
                pst.setString(6, rprice);
                pst.setString(7, qty);
                pst.setString(8, barcode);
                pst.setString(9, status);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Product Added successfully!");

                ProductNametxt.setText("");
                ProductDescriptiontxt.setText("");
                ProductCategorycmb.setSelectedIndex(0);
                ProductBrandcmb.setSelectedIndex(0);
                ProductCostPricetxt.setText("");
                ProductRetailPricetxt.setText("");
                ProductQuantitytxt.setText("");
                ProductCodetxt.setText("");
                ProductStatuscmb.setSelectedIndex(0);
                JoinTable();
                ProductNametxt.requestFocus();
            }

        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }//GEN-LAST:event_ProductAddMouseClicked

    private void ProductUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductUpdateMouseClicked
        DefaultTableModel d = (DefaultTableModel) ProductTable.getModel();
        int selectIndex = ProductTable.getSelectedRow();

        int ID = Integer.parseInt(d.getValueAt(selectIndex, 0).toString());
        String SUrl, SUser, Spass;
        SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
        SUser = "root";
        Spass = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

            String product = ProductNametxt.getText();
            String desc = ProductDescriptiontxt.getText();
            CategoryItem citem = (CategoryItem) ProductCategorycmb.getSelectedItem();
            BrandItem britem = (BrandItem) ProductBrandcmb.getSelectedItem();
            String cprice = ProductCostPricetxt.getText();
            String rprice = ProductRetailPricetxt.getText();
            String qty = ProductQuantitytxt.getText();
            String barcode = ProductCodetxt.getText();
            String status = ProductStatuscmb.getSelectedItem().toString();

            pst = con.prepareStatement("UPDATE product SET Product_Name=?,Description=?,Category_ID=?,Brand_ID=?,Cost_price=?,Retail_price=?,Quantity=?,Barcode=?,Status=? WHERE ID = ?");
            pst.setString(1, product);
            pst.setString(2, desc);
            pst.setInt(3, citem.ID);
            pst.setInt(4, britem.ID);
            pst.setString(5, cprice);
            pst.setString(6, rprice);
            pst.setString(7, qty);
            pst.setString(8, barcode);
            pst.setString(9, status);
            pst.setInt(10, ID);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product updated successfully!");

            ProductNametxt.setText("");
            ProductDescriptiontxt.setText("");
            ProductCategorycmb.setSelectedIndex(0);
            ProductBrandcmb.setSelectedIndex(0);
            ProductCostPricetxt.setText("");
            ProductRetailPricetxt.setText("");
            ProductQuantitytxt.setText("");
            ProductCodetxt.setText("");
            ProductStatuscmb.setSelectedIndex(0);
            JoinTable();
            ProductNametxt.requestFocus();
            ProductUpdate.setEnabled(false);
            ProductDelete.setEnabled(false);

        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }
    }//GEN-LAST:event_ProductUpdateMouseClicked

    private void ProductDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductDeleteMouseClicked
        DefaultTableModel d = (DefaultTableModel) ProductTable.getModel();
        int selectIndex = ProductTable.getSelectedRow();

        int ID = Integer.parseInt(d.getValueAt(selectIndex, 0).toString());
        String SUrl, SUser, Spass;
        SUrl = "jdbc:MYSQL://localhost:3307/pos_system";
        SUser = "root";
        Spass = "";

        int dialogresult = JOptionPane.showConfirmDialog(null, "Do you want to delete the record?", "Warning!", JOptionPane.YES_NO_OPTION);
        if (dialogresult == JOptionPane.YES_OPTION) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(SUrl, SUser, Spass);

                pst = con.prepareStatement("DELETE FROM product WHERE ID = ?");
                pst.setInt(1, ID);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Product deleted successfully!");
                ProductNametxt.setText("");
                ProductDescriptiontxt.setText("");
                ProductCategorycmb.setSelectedIndex(0);
                ProductBrandcmb.setSelectedIndex(0);
                ProductCostPricetxt.setText("");
                ProductRetailPricetxt.setText("");
                ProductQuantitytxt.setText("");
                ProductCodetxt.setText("");
                ProductStatuscmb.setSelectedIndex(0);
                JoinTable();
                ProductNametxt.requestFocus();
                DeleteBrand.setEnabled(false);
            } catch (Exception e) {
            }
        } else {
            ProductNametxt.setText("");
            ProductDescriptiontxt.setText("");
            ProductCategorycmb.setSelectedIndex(0);
            ProductBrandcmb.setSelectedIndex(0);
            ProductCostPricetxt.setText("");
            ProductRetailPricetxt.setText("");
            ProductQuantitytxt.setText("");
            ProductCodetxt.setText("");
            ProductStatuscmb.setSelectedIndex(0);
            JoinTable();
            ProductNametxt.requestFocus();
            ProductUpdate.setEnabled(false);
            ProductDelete.setEnabled(false);
        }
    }//GEN-LAST:event_ProductDeleteMouseClicked

    private void ProductResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductResetMouseClicked
        ProductNametxt.setText("");
        ProductDescriptiontxt.setText("");
        ProductCategorycmb.setSelectedIndex(0);
        ProductBrandcmb.setSelectedIndex(0);
        ProductCostPricetxt.setText("");
        ProductRetailPricetxt.setText("");
        ProductQuantitytxt.setText("");
        ProductCodetxt.setText("");
        ProductStatuscmb.setSelectedIndex(0);
        JoinTable();
        ProductNametxt.requestFocus();
        DeleteBrand.setEnabled(false);
    }//GEN-LAST:event_ProductResetMouseClicked

    private void ProductcodetxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProductcodetxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Productquantitytxt.requestFocus();
            try {
                String name = Productcodetxt.getText();

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:MYSQL://localhost:3307/pos_system", "root", "");
                pst = con.prepareStatement("SELECT * FROM product WHERE Barcode=?");
                pst.setString(1, name);
                rs = pst.executeQuery();

                if (rs.next() == false) {
                    JOptionPane.showMessageDialog(this, "Barcode not found!");
                    Productcodetxt.requestFocus();
                } else {
                    String productname = rs.getString("Product_Name");
                    String price = rs.getString("Retail_price");

                    Productnametxt.setText(productname.trim());
                    Productpricetxt.setText(price.trim());
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ProductcodetxtKeyPressed

    private void CheckquantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CheckquantityMouseClicked
        String name = Productcodetxt.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:MYSQL://localhost:3307/pos_system", "root", "");
            pst = con.prepareStatement("SELECT * FROM product WHERE Barcode=?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                int Qty = rs.getInt("Quantity");
                JOptionPane.showMessageDialog(this, Qty);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Product Code!");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(POS_Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CheckquantityMouseClicked

    private void AddtoBillingtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddtoBillingtableMouseClicked
        if (Productcodetxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter barcode!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (Productnametxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Check if the correct barcode is entered and press enter button!", "Error", JOptionPane.ERROR_MESSAGE);
        }else if (Productquantitytxt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the quantity!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            pos();
        }
    }//GEN-LAST:event_AddtoBillingtableMouseClicked

    private void BillingtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillingtableMouseClicked
        Billingdelete.setEnabled(true);


    }//GEN-LAST:event_BillingtableMouseClicked

    private void BillingdeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillingdeleteMouseClicked
        model.removeRow(Billingtable.getSelectedRow());

        int sum = 0;
        for (int i = 0; i < Billingtable.getRowCount(); i++) {
            sum = sum + Integer.parseInt(Billingtable.getValueAt(i, 4).toString());
        }
        Subtotallbl.setText(Integer.toString(sum));
        Billingdelete.setEnabled(false);

    }//GEN-LAST:event_BillingdeleteMouseClicked

    private void BilllingCalculateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BilllingCalculateMouseClicked
        int pay = Integer.parseInt(paytxt.getText());
        int subtotal = Integer.parseInt(Subtotallbl.getText());
        int bal = pay - subtotal;
        Balancelbl.setText(String.valueOf(bal));
        print();
        seles();

    }//GEN-LAST:event_BilllingCalculateMouseClicked

    private void BillingresetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillingresetMouseClicked
        Productcodetxt.setText("");
        Productnametxt.setText("");
        Productpricetxt.setText("");
        Productquantitytxt.setText("");
        Productcodetxt.setEnabled(true);
        Productcodetxt.requestFocus();
        DefaultTableModel model = (DefaultTableModel) Billingtable.getModel();
        model.setRowCount(0);
        Billingdelete.setEnabled(false);
        Subtotallbl.setText("");
        paytxt.setText("");
        Balancelbl.setText("");
    }//GEN-LAST:event_BillingresetMouseClicked

    private void ProductquantitytxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductquantitytxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductquantitytxtActionPerformed

    private void TableCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCategoryMouseClicked
        DefaultTableModel d = (DefaultTableModel) TableCategory.getModel();
        int selectIndex = TableCategory.getSelectedRow();

        Categorttxt.setText(d.getValueAt(selectIndex, 1).toString());
        Statuscmb.setSelectedItem(d.getValueAt(selectIndex, 2).toString());
        UpdateCategory.setEnabled(true);
        DeleteCategory.setEnabled(true);
    }//GEN-LAST:event_TableCategoryMouseClicked

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
            java.util.logging.Logger.getLogger(POS_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(POS_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(POS_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(POS_Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new POS_Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddBrand;
    private javax.swing.JLabel AddCategory;
    private javax.swing.JLabel AddtoBillingtable;
    private javax.swing.JTextField Balancelbl;
    private javax.swing.JLabel Billingdelete;
    private javax.swing.JLabel Billinglbl;
    private javax.swing.JLabel Billinglbl1;
    private javax.swing.JLabel Billingreset;
    private javax.swing.JTable Billingtable;
    private javax.swing.JLabel BilllingCalculate;
    private javax.swing.JComboBox<String> Brandcmb;
    private javax.swing.JLabel Brandlbl;
    private javax.swing.JLabel Brandlbl1;
    private javax.swing.JTextField Brandtxt;
    private javax.swing.JTextField Categorttxt;
    private javax.swing.JLabel Categorylbl;
    private javax.swing.JLabel Categorylbl1;
    private javax.swing.JLabel Checkquantity;
    private javax.swing.JLabel DeleteBrand;
    private javax.swing.JLabel DeleteCategory;
    private javax.swing.JLabel ProductAdd;
    private javax.swing.JComboBox ProductBrandcmb;
    private javax.swing.JComboBox ProductCategorycmb;
    private javax.swing.JTextField ProductCodetxt;
    private javax.swing.JTextField ProductCostPricetxt;
    private javax.swing.JLabel ProductDelete;
    private javax.swing.JTextArea ProductDescriptiontxt;
    private javax.swing.JTextField ProductNametxt;
    private javax.swing.JTextField ProductQuantitytxt;
    private javax.swing.JLabel ProductReset;
    private javax.swing.JTextField ProductRetailPricetxt;
    private javax.swing.JComboBox<String> ProductStatuscmb;
    private javax.swing.JTable ProductTable;
    private javax.swing.JLabel ProductUpdate;
    private javax.swing.JTextField Productcodetxt;
    private javax.swing.JLabel Productlbl;
    private javax.swing.JLabel Productlbl1;
    private javax.swing.JTextField Productnametxt;
    private javax.swing.JTextField Productpricetxt;
    private javax.swing.JTextField Productquantitytxt;
    private javax.swing.JLabel ResetBrand;
    private javax.swing.JLabel ResetCategory;
    private javax.swing.JComboBox<String> Statuscmb;
    private javax.swing.JTextField Subtotallbl;
    private javax.swing.JTable TableBrand;
    private javax.swing.JTable TableCategory;
    private javax.swing.JLabel UpdateBrand;
    private javax.swing.JLabel UpdateCategory;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField paytxt;
    // End of variables declaration//GEN-END:variables
}
