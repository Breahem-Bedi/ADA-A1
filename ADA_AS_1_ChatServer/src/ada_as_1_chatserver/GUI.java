/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_as_1_chatserver;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Bedi
 */
public class GUI extends javax.swing.JFrame {
    private Client client;
    private String clients[];
    /**
     * Creates new form GUI
     */
    public GUI(Client client) {
        initComponents();
        this.client = client;
        this.client.setgui(this);
        
        this.client.getClientName();
        getUserName();
        
    }
    
    public String getUserName()
    {
        
        String username = "";         
        while(username != null && username.length() == 0)
        {
             username = (JOptionPane.showInputDialog(null,
                    "Enter your username:",
                    "Username", JOptionPane.OK_CANCEL_OPTION));
        }
        if (username == null) {
            System.exit(0);
        }
        this.client.setClientName(username); 
         //boolean valid = false;


        
        return username;
             
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
        chatbox = new javax.swing.JTextArea();
        messagebox = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        client_list = new javax.swing.JList<>();
        private_btn = new javax.swing.JButton();
        sendall_btn = new javax.swing.JButton();
        disconnect_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatbox.setColumns(20);
        chatbox.setRows(5);
        jScrollPane1.setViewportView(chatbox);

        client_list.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        client_list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Online Clients" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(client_list);

        private_btn.setText("Private Chat");

        sendall_btn.setText("Send All");
        sendall_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendall_btnActionPerformed(evt);
            }
        });

        disconnect_btn.setText("Disconnect");
        disconnect_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnect_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(messagebox)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(private_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendall_btn))
                    .addComponent(jScrollPane2)
                    .addComponent(disconnect_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(private_btn)
                            .addComponent(sendall_btn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disconnect_btn))
                    .addComponent(messagebox))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        disconnect_btn.getAccessibleContext().setAccessibleName("DisconnectBtn");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendall_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendall_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendall_btnActionPerformed

    private void disconnect_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnect_btnActionPerformed
        // TODO add your handling code here:
        System.out.println(client.getClientName() + " User Quit");
        System.exit(0);
    }//GEN-LAST:event_disconnect_btnActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatbox;
    private javax.swing.JList<String> client_list;
    private javax.swing.JButton disconnect_btn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField messagebox;
    private javax.swing.JButton private_btn;
    private javax.swing.JButton sendall_btn;
    // End of variables declaration//GEN-END:variables
}