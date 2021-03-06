/*
 * The MIT License
 *
 * Copyright 2014 Rui Martinho (rmartinho@gmail.com), António Braz (antoniocbraz@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.poreid.dialogs.pindialogs.verifypin;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import org.poreid.config.POReIDConfig;
import org.poreid.dialogs.DialogEventListener;
import org.poreid.dialogs.pindialogs.MyDocument;

/**
 *
 * @author POReID
 */

class VerifyPinDialog extends javax.swing.JDialog {
    private String infoMessage;
    private final String pinLabel;
    private final byte[] pinIcon;
    private final int pinMinLength;
    private final int pinMaxLength;
    private DialogEventListener<ByteBuffer> listener;
    private final ResourceBundle bundle;
    
    /**
     * Creates new form VerifyPinDialog
     */
    public VerifyPinDialog(String pinLabel, byte[] pinIcon, int pinMinLength, int pinMaxLength, Locale locale, DialogEventListener<ByteBuffer> listener){
        super();
        this.pinLabel = pinLabel;
        this.pinIcon = pinIcon;
        this.pinMinLength = pinMinLength;
        this.pinMaxLength = pinMaxLength;
        this.listener = listener;
        bundle = POReIDConfig.getBundle(VerifyPinDialog.class.getSimpleName(),locale);
        initComponents();
        
        this.setTitle(MessageFormat.format(bundle.getString("dialog.title"),pinLabel));
        this.getAccessibleContext().setAccessibleDescription(MessageFormat.format(bundle.getString("dialog.description"),pinLabel));
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                VerifyPinDialog.this.listener.onDiagloclosed();
            }

            @Override
            public void windowOpened(WindowEvent e) {                
                pin.requestFocus();
            }
        });
        
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelar");
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "aceitar");
        this.getRootPane().getActionMap().put("cancelar", new CancelAbstractAction());
        this.getRootPane().getActionMap().put("aceitar", new OKAbstractAction());
    }
    
    
    public VerifyPinDialog(String pinLabel, byte[] pinIcon, int pinMinLength, int pinMaxLength, Locale locale, DialogEventListener<ByteBuffer> listener, String infoMsg){
        super();
        this.pinLabel = pinLabel;
        this.pinIcon = pinIcon;
        this.pinMinLength = pinMinLength;
        this.pinMaxLength = pinMaxLength;
        this.listener = listener;
        this.infoMessage = infoMsg;
        bundle = POReIDConfig.getBundle(VerifyPinDialog.class.getSimpleName(),locale);
        initComponents();
        
        this.setTitle(MessageFormat.format(bundle.getString("dialog.title"),pinLabel));
        this.getAccessibleContext().setAccessibleDescription(MessageFormat.format(bundle.getString("dialog.description"),pinLabel));
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                VerifyPinDialog.this.listener.onDiagloclosed();
            }

            @Override
            public void windowOpened(WindowEvent e) {                
                pin.requestFocus();
            }
        });
        
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelar");
        this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "aceitar");
        this.getRootPane().getActionMap().put("cancelar", new CancelAbstractAction());
        this.getRootPane().getActionMap().put("aceitar", new OKAbstractAction());
    }
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        pin = new javax.swing.JPasswordField();
        labelPin = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        lblInfoMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setIconImage(null);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);

        btnOK.setText(bundle.getString("ok.button"));
        btnOK.getAccessibleContext().setAccessibleDescription(MessageFormat.format(bundle.getString("ok.button.description"),pinLabel));
        btnOK.setEnabled(false);
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnCancel.setText(bundle.getString("cancel.button"));
        btnCancel.getAccessibleContext().setAccessibleDescription(bundle.getString("cancel.button.description"));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        pin.setDocument(new MyDocument(pinMaxLength));
        pin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pin.setToolTipText(MessageFormat.format(bundle.getString("password.tooltip"),pinLabel));
        pin.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                pinCaretUpdate(evt);
            }
        });

        labelPin.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelPin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelPin.setText("<html><b>"+pinLabel+"</b></html>");

        icon.setMaximumSize(new java.awt.Dimension(64, 64));
        icon.setMinimumSize(new java.awt.Dimension(64, 64));
        icon.setPreferredSize(new java.awt.Dimension(64, 64));
        icon.setIcon(new ImageIcon(pinIcon));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        if (null != infoMessage && !infoMessage.isEmpty()){
            lblInfoMessage.setText("<html><body style='width: 260px'>"+infoMessage);
            lblInfoMessage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            lblInfoMessage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        } else {
            lblInfoMessage.setVisible(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblInfoMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPin))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(pin))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnOK});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfoMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancel, btnOK});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        listener.onCancel();
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        listener.onContinue(StandardCharsets.UTF_8.encode(CharBuffer.wrap(pin.getPassword())));
        this.dispose();
    }//GEN-LAST:event_btnOKActionPerformed

    private void pinCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_pinCaretUpdate

        if (pin.getPassword().length > pinMinLength - 1){
            btnOK.setEnabled(true);
        } else {
            btnOK.setEnabled(false);
        }
    }//GEN-LAST:event_pinCaretUpdate
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JLabel icon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelPin;
    private javax.swing.JLabel lblInfoMessage;
    private javax.swing.JPasswordField pin;
    // End of variables declaration//GEN-END:variables

    private final class OKAbstractAction extends AbstractAction {

        OKAbstractAction() {
            super();
        }

        @Override
        public void actionPerformed(final ActionEvent event) {
            if (btnOK.isEnabled()){
                btnOK.doClick();
            }
        }
    }
    
    private final class CancelAbstractAction extends AbstractAction {

        CancelAbstractAction() {
            super();
        }

        @Override
        public void actionPerformed(final ActionEvent event) {
            if (btnCancel.isEnabled()){
                btnCancel.doClick();
            }
        }
    }
}
