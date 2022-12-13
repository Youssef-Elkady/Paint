package Frontend;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Backend.DrawingEngine;
import Backend.LineSegment;
import Backend.MyPanel;
import Backend.Node;
import Backend.Oval;
import Backend.Rectangle;
import Backend.Shape;
import Backend.Triangle;

public class VectorDrawingApp extends javax.swing.JFrame implements Node {

    private OvalInfo cin;
    private RectInfo rin;
    private LineInfo lin;
    private TriangleInfo tin;

    private static int ovalC = 0, rectC = 0, lineC = 0, triangleC = 0;
    private Node parent;
    private DrawingEngine brush;

    public VectorDrawingApp() {
        initComponents();
        brush = (DrawingEngine) jPanel1;
        lin = null;
        cin = null;
        rin = null;
        tin = null;
        setTitle(("Paint App"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFileChooser1 = new javax.swing.JFileChooser();
        Oval = new javax.swing.JButton();
        LineSegment = new javax.swing.JButton();
        Triangle = new javax.swing.JButton();
        Rectangle = new javax.swing.JButton();
        jPanel1 = new MyPanel(this)
        ;
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        Colorize = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Copy = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        SaveAs = new javax.swing.JMenuItem();
        Load = new javax.swing.JMenuItem();
        ExitNoSave = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Oval.setText("Oval");
        Oval.setOpaque(true);
        Oval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OvalActionPerformed(evt);
            }
        });

        LineSegment.setText("Line Segment");
        LineSegment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LineSegmentActionPerformed(evt);
            }
        });

        Triangle.setText("Triangle");
        Triangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TriangleActionPerformed(evt);
            }
        });

        Rectangle.setText("Rectangle");
        Rectangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RectangleActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
        );

        jLabel1.setText("Select Shape ");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        Colorize.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Colorize.setText("Colorize");
        Colorize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorizeActionPerformed(evt);
            }
        });

        Delete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Copy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Copy.setText("Copy");
        Copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopyActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        SaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        SaveAs.setText("Save as");
        SaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsActionPerformed(evt);
            }
        });
        jMenu1.add(SaveAs);

        Load.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Load.setText("Load");
        Load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadActionPerformed(evt);
            }
        });
        jMenu1.add(Load);

        ExitNoSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        ExitNoSave.setText("Exit without saving");
        ExitNoSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitNoSaveActionPerformed(evt);
            }
        });
        jMenu1.add(ExitNoSave);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Copy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Colorize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Delete)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 32, Short.MAX_VALUE)
                        .addComponent(Oval)
                        .addGap(34, 34, 34)
                        .addComponent(LineSegment, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(Triangle)
                        .addGap(18, 18, 18)
                        .addComponent(Rectangle)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(Oval))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Rectangle)
                                .addComponent(Triangle))
                            .addComponent(LineSegment, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Colorize)
                            .addComponent(Delete))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Copy)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   public void addPaneladdCombo(Shape s, String s1) {
        brush.addShape(s);
        brush.refresh(null);
        int here = 0;
        if (s instanceof Oval) {
            here = ++ovalC;
        } else if (s instanceof Rectangle) {
            here = ++rectC;
        } else if (s1.equals("Triangle_")) {
            here = ++triangleC;
        } else if (s instanceof LineSegment) {
            here = ++lineC;
        }

        jComboBox1.addItem(s1 + here);
    }
    private void OvalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OvalActionPerformed
        if (cin == null) {
            cin = new OvalInfo();
            cin.setParentNode(this);
        }
        this.setVisible(false);
        cin.setVisible(true);

    }//GEN-LAST:event_OvalActionPerformed

    private void LineSegmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LineSegmentActionPerformed
        if (lin == null) {
            lin = new LineInfo();
            lin.setParentNode(this);
        }
        this.setVisible(false);
        lin.setVisible(true);
    }//GEN-LAST:event_LineSegmentActionPerformed
    public void setComboBoxIndex(int i) {
        jComboBox1.setSelectedIndex(i);
    }
    public int getComboBoxIndex (){
        return jComboBox1.getSelectedIndex();
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void ColorizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorizeActionPerformed
        Color col = Color.BLUE;     //initial colour
        int choice;
        choice = jComboBox1.getSelectedIndex();      //returns -1 when nothing is selected 
        if (choice < 0) {
            return;
        }
        Shape[] arr = brush.getShapes();
        Shape a = arr[choice];

        Color color = JColorChooser.showDialog(null, "Select a color", col);
        if (!(a instanceof LineSegment)) {
            Color FColor = JColorChooser.showDialog(null, "Select a filling color", col);
            a.setFillColor(FColor);
        }
        a.setColor(color);
        brush.refresh(null);

    }//GEN-LAST:event_ColorizeActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void RectangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RectangleActionPerformed
        if (rin == null) {
            rin = new RectInfo();
            rin.setParentNode(this);
        }
        this.setVisible(false);
        rin.setVisible(true);
    }//GEN-LAST:event_RectangleActionPerformed

    private void TriangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TriangleActionPerformed
        if (tin == null) {
            tin = new TriangleInfo();
            tin.setParentNode(this);
        }
        this.setVisible(false);
        tin.setVisible(true);
    }//GEN-LAST:event_TriangleActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        int choice;
        choice = jComboBox1.getSelectedIndex();
        if (choice < 0) {
            return;
        }
        Shape[] arr = brush.getShapes();
        Shape a = arr[choice];
        jComboBox1.removeItemAt(choice);
        brush.removeShape(a);
        brush.refresh(null);
    }//GEN-LAST:event_DeleteActionPerformed

    private void CopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopyActionPerformed
        int choice;
        choice = jComboBox1.getSelectedIndex();
        if (choice < 0) {
            return;
        }
        Shape[] arr = brush.getShapes();
        Shape s = arr[choice];
        if (s instanceof Oval oval)
        {
            Oval copy=new Oval(s.getPosition(), oval.gethRadius(), oval.getvRadius());
            copy.setColor(s.getColor());
            copy.setFillColor(s.getFillColor());
             addPaneladdCombo(copy, "Oval_");
            brush.refresh(null);
           
        }
        else if(s instanceof LineSegment lineSegment)
        {
            LineSegment copy=new LineSegment(s.getPosition(), lineSegment.getP2());
            copy.setColor(s.getColor());
            addPaneladdCombo(copy, "Line_");
            brush.refresh(null);
            
        }
        else if (s instanceof Rectangle rectangle)
        {
            Rectangle copy= new Rectangle(s.getPosition(), rectangle.getWidth(),rectangle.getHeight());
            copy.setColor(s.getColor());
            copy.setFillColor(s.getFillColor());
             addPaneladdCombo(copy, "Rectangle_");
            brush.refresh(null);
           
        }
        else if (s instanceof Triangle triangle)
        {
            Triangle copy=new Triangle(s.getPosition(), triangle.getP2(),triangle.getP3());
            copy.setColor(s.getColor());
            copy.setFillColor(s.getFillColor());
            addPaneladdCombo(copy, "Triangle_");
            brush.refresh(null);
            
        }
        
    }//GEN-LAST:event_CopyActionPerformed

    private void SaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAsActionPerformed
        
        jFileChooser1.showSaveDialog(this);
        File f = jFileChooser1.getSelectedFile();
        Path path = f.toPath();
        ((MyPanel) brush).save(f.getAbsolutePath());          //myPanel
        System.out.println("Saved ");
    }//GEN-LAST:event_SaveAsActionPerformed

    private void LoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadActionPerformed
        jComboBox1.removeAllItems();
        jFileChooser1.showOpenDialog(this);
        File f = jFileChooser1.getSelectedFile();
        Path path = f.toPath();
        try {
            ((MyPanel) brush).load(f.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VectorDrawingApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VectorDrawingApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("loaded Successfully");
    }//GEN-LAST:event_LoadActionPerformed

    private void ExitNoSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitNoSaveActionPerformed
        //in case not saved
        JFrame frame = new JFrame("Exit");
        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit without saving?", "EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_ExitNoSaveActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VectorDrawingApp().setVisible(true);
            }
        });
    }
//hello
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Colorize;
    private javax.swing.JButton Copy;
    private javax.swing.JButton Delete;
    private javax.swing.JMenuItem ExitNoSave;
    private javax.swing.JButton LineSegment;
    private javax.swing.JMenuItem Load;
    private javax.swing.JButton Oval;
    private javax.swing.JButton Rectangle;
    private javax.swing.JMenuItem SaveAs;
    private javax.swing.JButton Triangle;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public Node getParentNode() {
        return parent;
    }

    @Override
    public void setParentNode(Node node) {
        this.parent = node;
    }
}
