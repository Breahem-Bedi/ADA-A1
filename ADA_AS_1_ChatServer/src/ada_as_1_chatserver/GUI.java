/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ada_as_1_chatserver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        client.getClientNameGUI();
        getUserName();
        getIPAddress();

        client.startClient();
    }

    public String getUserName() {

        String username = "";
        while (username != null && username.length() == 0) {
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

    public void updateOnlineClients(String rec) {
        clients = rec.split("_");
        client_list.setListData(clients);
    }

    public String getIPAddress() {
        String input;
        String[] options = {"localhost", "192.168.1.72", "DESKTOP-9UO9SO7", "other"};
        do {
            input = (String) (JOptionPane.showInputDialog(null,
                    "Enter IP address to connect to:",
                    "IPAddress", JOptionPane.QUESTION_MESSAGE, null, options, options[0]));

        } while ((input != null || input != "other") && input.length() == 0);

        // Cancel option selected
        if (input == null) {
            System.exit(0);
        } else if (input == "other") {
            do {
                input = (JOptionPane.showInputDialog(null,
                        "Enter IP address to connect to:",
                        "Other IPAdress", JOptionPane.PLAIN_MESSAGE));
            } while (input != null && input.length() == 0);
        }
        this.client.setIPAddress(input);

        return input;
    }

    public void append(String s) {
        messagebox.append(s);
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
        messagebox = new javax.swing.JTextArea();
        message = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        client_list = new javax.swing.JList<>();
        private_btn = new javax.swing.JButton();
        sendall_btn = new javax.swing.JButton();
        disconnect_btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        messagebox.setEditable(false);
        messagebox.setColumns(20);
        messagebox.setRows(5);
        jScrollPane1.setViewportView(messagebox);

        client_list.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Online");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(message)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(disconnect_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sendall_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(private_btn))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sendall_btn)
                            .addComponent(private_btn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disconnect_btn))
                    .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        disconnect_btn.getAccessibleContext().setAccessibleName("DisconnectBtn");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendall_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendall_btnActionPerformed
        try {
            // TODO add your handling code here:
            client.sendMessage(new Broadcaster(client.getClientNameGUI() + "(ALL): " + message.getText() + "\n"));
            message.setText("");
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_sendall_btnActionPerformed

    private void disconnect_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnect_btnActionPerformed
        // TODO add your handling code here:
        System.out.println(client.getClientNameGUI() + " User Quit");
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
    private javax.swing.JList<String> client_list;
    private javax.swing.JButton disconnect_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField message;
    private javax.swing.JTextArea messagebox;
    private javax.swing.JButton private_btn;
    private javax.swing.JButton sendall_btn;
    // End of variables declaration//GEN-END:variables

}
