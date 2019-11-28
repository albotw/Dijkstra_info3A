/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

/**
 *
 * @author Yann
 */
public class CommandUI extends javax.swing.JDialog {
    Main main;
    /**
     * Creates new form CommandUI
     */
    public CommandUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        main = Main.main;
        PointsTF.setText(""+ main.POINTS);
        ObstaclesTF.setText(""+ main.OBSTACLES);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        North = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PointsLabel = new javax.swing.JLabel();
        PointsTF = new javax.swing.JTextField();
        ObstaclesLabel = new javax.swing.JLabel();
        ObstaclesTF = new javax.swing.JTextField();
        ActivateGeneratorButton = new javax.swing.JButton();
        ApplyDijkstraButton = new javax.swing.JButton();
        ResetButton = new javax.swing.JButton();
        StatusLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        QuitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Commandes dijkstra");
        North.add(jLabel1);

        getContentPane().add(North, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(4, 4));

        PointsLabel.setText("Points");
        jPanel1.add(PointsLabel);

        PointsTF.setText("0");
        jPanel1.add(PointsTF);

        ObstaclesLabel.setText("Obstacles");
        jPanel1.add(ObstaclesLabel);

        ObstaclesTF.setText("0");
        jPanel1.add(ObstaclesTF);

        ActivateGeneratorButton.setText("Générer un graphe");
        ActivateGeneratorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActivateGeneratorButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ActivateGeneratorButton);

        ApplyDijkstraButton.setText("Appliquer Dijkstra");
        ApplyDijkstraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApplyDijkstraButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ApplyDijkstraButton);

        ResetButton.setText("Remettre a zéro");
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ResetButton);

        StatusLabel.setText(" ");
        jPanel1.add(StatusLabel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        QuitButton.setText("Quitter");
        QuitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitButtonActionPerformed(evt);
            }
        });
        jPanel2.add(QuitButton);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ApplyDijkstraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApplyDijkstraButtonActionPerformed
        main.applyDijktra();
        ActivateGeneratorButton.setEnabled(false);
        PointsTF.setEnabled(false);
        ObstaclesTF.setEnabled(false);
    }//GEN-LAST:event_ApplyDijkstraButtonActionPerformed

    private void ActivateGeneratorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActivateGeneratorButtonActionPerformed
        
        main.POINTS = Integer.parseInt(PointsTF.getText());
        main.OBSTACLES = Integer.parseInt(ObstaclesTF.getText());
        main.init();
        
    }//GEN-LAST:event_ActivateGeneratorButtonActionPerformed

    private void QuitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitButtonActionPerformed
        main.quit();
    }//GEN-LAST:event_QuitButtonActionPerformed

    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        main.reset();
        ActivateGeneratorButton.setEnabled(true);
        PointsTF.setEnabled(true);
        ObstaclesTF.setEnabled(true);
    }//GEN-LAST:event_ResetButtonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActivateGeneratorButton;
    private javax.swing.JButton ApplyDijkstraButton;
    private javax.swing.JPanel North;
    private javax.swing.JLabel ObstaclesLabel;
    private javax.swing.JTextField ObstaclesTF;
    private javax.swing.JLabel PointsLabel;
    private javax.swing.JTextField PointsTF;
    private javax.swing.JButton QuitButton;
    private javax.swing.JButton ResetButton;
    private javax.swing.JLabel StatusLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}