package gui;

import controller.*;
import javax.swing.*;
import java.awt.*;

public class CardPrenotazione extends JPanel {

    private String chiaveSessione;
    private Controller controller;

    private JPanel pannelloCardPrenotazione = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JLabel cardPrenotazioneAeroportoOrigineLabel = new JLabel();
    private JLabel cardPrenotazioneAeroportoDestinazioneLabel = new JLabel();
    private JLabel jLabel33 = new JLabel();
    private JLabel jLabel34 = new JLabel();;
    private JLabel jLabel35 = new JLabel();;
    private JLabel jLabel36 = new JLabel();;
    private JLabel jLabel37 = new JLabel();;
    private JLabel jLabel38 = new JLabel();;
    private JLabel jLabel39 = new JLabel();;

    private JButton cardPrenotazionePulsanteCancellaPrenotazione = new JButton();
    private JLabel cardPrenotazioneNomePasseggeroInput = new JLabel();;
    private JLabel cardPrenotazioneCognomePasseggeroInput = new JLabel();;
    private JLabel cardPrenotazioneNumeroBiglietto = new JLabel();;
    private JLabel cardPrenotazioneNumeroBagagliInput = new JLabel();;
    private JLabel cardPrenotazioneStatoVoloInput = new JLabel();;
    private JLabel cardPrenotazionePostoAssegnatoInput = new JLabel();;
    private JLabel cardPrenotazioneCodiceVoloInput = new JLabel();;

    public CardPrenotazione( Controller controller, String chiaveSessione, String codice, String partenza,
                             String arrivo, String nomePasseggero, String cognomePasseggero,
                             String numeroBagagli, String stato, String numeroBiglietto, String postoAssegnato ) {

        this.chiaveSessione = chiaveSessione;
        this.controller = controller;

        pannelloCardPrenotazione.setBackground(new java.awt.Color(255, 255, 255));
        pannelloCardPrenotazione.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 1, true));
        pannelloCardPrenotazione.setMaximumSize(new java.awt.Dimension(940, 300));
        pannelloCardPrenotazione.setPreferredSize(new java.awt.Dimension(940, 300));

        jPanel2.setBackground(new java.awt.Color(153, 51, 255));

        cardPrenotazioneAeroportoOrigineLabel.setFont(new java.awt.Font("DialogInput", 0, 36)); // NOI18N
        cardPrenotazioneAeroportoOrigineLabel.setForeground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneAeroportoOrigineLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneAeroportoOrigineLabel.setText( partenza );

        cardPrenotazioneAeroportoDestinazioneLabel.setFont(new java.awt.Font("DialogInput", 0, 36)); // NOI18N
        cardPrenotazioneAeroportoDestinazioneLabel.setForeground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneAeroportoDestinazioneLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneAeroportoDestinazioneLabel.setText( arrivo );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cardPrenotazioneAeroportoOrigineLabel)
                                .addGap(168, 168, 168)
                                .addComponent(cardPrenotazioneAeroportoDestinazioneLabel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cardPrenotazioneAeroportoOrigineLabel)
                                        .addComponent(cardPrenotazioneAeroportoDestinazioneLabel))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 51, 51));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Nome passeggero:");

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 51, 51));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Codice volo:");

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Numero bagagli:");

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 51, 51));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Numero biglietto:");

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 51, 51));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Stato volo:");

        cardPrenotazionePulsanteCancellaPrenotazione.setBackground(new java.awt.Color(51, 102, 255));
        cardPrenotazionePulsanteCancellaPrenotazione.setFont(new java.awt.Font("DialogInput", 0, 24)); // NOI18N
        cardPrenotazionePulsanteCancellaPrenotazione.setForeground(new java.awt.Color(255, 255, 255));
        cardPrenotazionePulsanteCancellaPrenotazione.setText("Cancella");
        cardPrenotazionePulsanteCancellaPrenotazione.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 2, true));
        cardPrenotazionePulsanteCancellaPrenotazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardPrenotazionePulsanteCancellaPrenotazioneActionPerformed(evt);
            }
        });

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 51, 51));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("Cognome passeggero:");

        cardPrenotazioneNomePasseggeroInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneNomePasseggeroInput.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        cardPrenotazioneNomePasseggeroInput.setForeground(new java.awt.Color(0, 51, 51));
        cardPrenotazioneNomePasseggeroInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneNomePasseggeroInput.setText( nomePasseggero );

        cardPrenotazioneNumeroBagagliInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneNumeroBagagliInput.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        cardPrenotazioneNumeroBagagliInput.setForeground(new java.awt.Color(0, 51, 51));
        cardPrenotazioneNumeroBagagliInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneNumeroBagagliInput.setText( numeroBagagli );

        cardPrenotazioneNumeroBiglietto.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneNumeroBiglietto.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        cardPrenotazioneNumeroBiglietto.setForeground(new java.awt.Color(0, 51, 51));
        cardPrenotazioneNumeroBiglietto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneNumeroBiglietto.setText( numeroBiglietto );

        cardPrenotazioneStatoVoloInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneStatoVoloInput.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        cardPrenotazioneStatoVoloInput.setForeground(new java.awt.Color(0, 51, 51));
        cardPrenotazioneStatoVoloInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneStatoVoloInput.setText( stato );

        cardPrenotazioneCodiceVoloInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneCodiceVoloInput.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        cardPrenotazioneCodiceVoloInput.setForeground(new java.awt.Color(0, 51, 51));
        cardPrenotazioneCodiceVoloInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneCodiceVoloInput.setText( codice );

        cardPrenotazioneCognomePasseggeroInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneCognomePasseggeroInput.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        cardPrenotazioneCognomePasseggeroInput.setForeground(new java.awt.Color(0, 51, 51));
        cardPrenotazioneCognomePasseggeroInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneCognomePasseggeroInput.setText( cognomePasseggero );

        jLabel39.setBackground(new java.awt.Color(255, 255, 255));
        jLabel39.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 51, 51));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("Posto assegnato:");

        cardPrenotazionePostoAssegnatoInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazionePostoAssegnatoInput.setFont(new java.awt.Font("DialogInput", 0, 16)); // NOI18N
        cardPrenotazionePostoAssegnatoInput.setForeground(new java.awt.Color(0, 51, 51));
        cardPrenotazionePostoAssegnatoInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazionePostoAssegnatoInput.setText( postoAssegnato );

        javax.swing.GroupLayout pannelloCardPrenotazioneLayout = new javax.swing.GroupLayout(pannelloCardPrenotazione);
        pannelloCardPrenotazione.setLayout(pannelloCardPrenotazioneLayout);
        pannelloCardPrenotazioneLayout.setHorizontalGroup(
                pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addComponent(jLabel34)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardPrenotazioneCodiceVoloInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addComponent(jLabel39)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardPrenotazionePostoAssegnatoInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addComponent(jLabel33)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardPrenotazioneNomePasseggeroInput, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addComponent(jLabel35)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardPrenotazioneNumeroBagagliInput, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 4, Short.MAX_VALUE)))
                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                .addComponent(jLabel37)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cardPrenotazioneStatoVoloInput, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                .addGap(0, 62, Short.MAX_VALUE)
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addComponent(jLabel36)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardPrenotazioneNumeroBiglietto, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addComponent(jLabel38)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardPrenotazioneCognomePasseggeroInput, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(38, 38, 38)
                                .addComponent(cardPrenotazionePulsanteCancellaPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        pannelloCardPrenotazioneLayout.setVerticalGroup(
                pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel33)
                                                .addComponent(cardPrenotazioneNomePasseggeroInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cardPrenotazioneCognomePasseggeroInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel38))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel35)
                                                        .addComponent(cardPrenotazioneNumeroBagagliInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel36)
                                                        .addComponent(cardPrenotazioneNumeroBiglietto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel39)
                                                                        .addComponent(cardPrenotazionePostoAssegnatoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(cardPrenotazioneStatoVoloInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(cardPrenotazioneCodiceVoloInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jLabel37))))
                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cardPrenotazionePulsanteCancellaPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        this.add( pannelloCardPrenotazione );
    }

    private void cardPrenotazionePulsanteCancellaPrenotazioneActionPerformed( java.awt.event.ActionEvent evt) {

        controller.cancellaPrenotazione( chiaveSessione, cardPrenotazioneNomePasseggeroInput.getText(),
                cardPrenotazioneCognomePasseggeroInput.getText(), cardPrenotazioneCodiceVoloInput.getText(),
                cardPrenotazioneNumeroBagagliInput.getText()
        );

        Container parent = this.getParent();
        if( parent != null ) {
            parent.remove( this ); parent.revalidate();
            parent.repaint();
        }
    }
}