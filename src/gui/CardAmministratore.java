package gui;

import javax.swing.*;

public class CardAmministratore extends JPanel {

    private JPanel pannelloCardAmministratore = new JPanel();
    private JRadioButton pulsanteRadio = new JRadioButton();
    private JLabel infoAmministratore = new JLabel();
    private JLabel numeroVoli = new JLabel();

    public CardAmministratore( String username ) {

        pannelloCardAmministratore.setBorder( null );
        //pannelloCardAmministratore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pannelloCardAmministratore.setMaximumSize(new java.awt.Dimension(476, 53));
        pannelloCardAmministratore.setPreferredSize(new java.awt.Dimension(476, 53));

        infoAmministratore.setText( username );
        numeroVoli.setText( "" );

        pulsanteRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulsanteRadioPremuto(evt);
            }
        });

        javax.swing.GroupLayout pannelloCardAmministratoreLayout = new javax.swing.GroupLayout(pannelloCardAmministratore);
        pannelloCardAmministratore.setLayout(pannelloCardAmministratoreLayout);
        pannelloCardAmministratoreLayout.setHorizontalGroup(
                pannelloCardAmministratoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pannelloCardAmministratoreLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pulsanteRadio)
                                .addGap(18, 18, 18)
                                .addComponent(infoAmministratore)
                                .addGap(18, 18, 18)
                                .addComponent(numeroVoli)
                                .addContainerGap(276, Short.MAX_VALUE))
        );
        pannelloCardAmministratoreLayout.setVerticalGroup(
                pannelloCardAmministratoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pannelloCardAmministratoreLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(pannelloCardAmministratoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pannelloCardAmministratoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(infoAmministratore)
                                                .addComponent(numeroVoli))
                                        .addComponent(pulsanteRadio))
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        this.add( pannelloCardAmministratore );
    }

    private void pulsanteRadioPremuto( java.awt.event.ActionEvent evt ) {

    }

    public boolean isPressedRadio() {
        return pulsanteRadio.isSelected();
    }

    public String getUsernameAmministratore() {
        return infoAmministratore.getText();
    }
}