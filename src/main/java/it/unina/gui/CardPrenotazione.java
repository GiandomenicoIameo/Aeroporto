package it.unina.gui;

import it.unina.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class CardPrenotazione extends JPanel {

    private String chiaveSessione;
    private Controller controller;

    private JPanel pannelloCardPrenotazione = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JLabel jLabel62 = new JLabel();
    private JLabel jLabel63 = new JLabel();
    private JLabel jLabel64 = new JLabel();
    private JLabel jLabel66 = new JLabel();
    private JLabel jLabel67 = new JLabel();
    private JLabel jLabel68 = new JLabel();
    private JLabel jLabel69 = new JLabel();
    private JLabel jLabel70 = new JLabel();
    private JLabel jLabel71 = new JLabel();

    private JLabel cardPrenotazioneAeroportoOrigineLabel = new JLabel();
    private JLabel cardPrenotazioneAeroportoDestinazioneLabel = new JLabel();
    private JButton cardPrenotazionePulsanteCancellaPrenotazione = new JButton();
    private JLabel cardPrenotazioneNomePasseggeroInput = new JLabel();
    private JLabel cardPrenotazioneCognomePasseggeroInput = new JLabel();
    private JLabel cardPrenotazioneNumeroBiglietto = new JLabel();
    private JLabel cardPrenotazioneNumeroBagagliInput = new JLabel();
    private JLabel cardPrenotazioneStatoVoloInput = new JLabel();
    private JLabel cardPrenotazionePostoAssegnatoInput = new JLabel();
    private JLabel cardPrenotazioneCodiceVoloInput = new JLabel();

    public CardPrenotazione( Controller controller, String chiaveSessione, String codice, String partenza,
                             String arrivo, String nomePasseggero, String cognomePasseggero,
                             String numeroBagagli, String stato, String numeroBiglietto, String postoAssegnato ) {

        this.chiaveSessione = chiaveSessione;
        this.controller = controller;

        pannelloCardPrenotazione.setBackground(new java.awt.Color(255, 255, 255));
        pannelloCardPrenotazione.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        pannelloCardPrenotazione.setMaximumSize(new java.awt.Dimension(1100, 300));
        pannelloCardPrenotazione.setPreferredSize(new java.awt.Dimension(1100, 300));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        cardPrenotazioneAeroportoOrigineLabel.setFont(new java.awt.Font("Z003", 0, 48)); // NOI18N
        cardPrenotazioneAeroportoOrigineLabel.setForeground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneAeroportoOrigineLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneAeroportoOrigineLabel.setText( partenza );

        cardPrenotazioneAeroportoDestinazioneLabel.setFont(new java.awt.Font("Z003", 0, 48)); // NOI18N
        cardPrenotazioneAeroportoDestinazioneLabel.setForeground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneAeroportoDestinazioneLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneAeroportoDestinazioneLabel.setText( arrivo );

        jLabel62.setFont(new java.awt.Font("Z003", 1, 20)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Partenza");

        jLabel63.setFont(new java.awt.Font("Z003", 1, 20)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Destinazione");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel62)
                                        .addComponent(cardPrenotazioneAeroportoOrigineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel63)
                                        .addComponent(cardPrenotazioneAeroportoDestinazioneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cardPrenotazioneAeroportoOrigineLabel)
                                        .addComponent(cardPrenotazioneAeroportoDestinazioneLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel62)
                                        .addComponent(jLabel63))
                                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel64.setBackground(new java.awt.Color(255, 255, 255));
        jLabel64.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 51, 51));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("Nome passeggero:");

        jLabel66.setBackground(new java.awt.Color(255, 255, 255));
        jLabel66.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 51, 51));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("Numero bagagli:");

        jLabel67.setBackground(new java.awt.Color(255, 255, 255));
        jLabel67.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 51, 51));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("Codice volo:");

        jLabel68.setBackground(new java.awt.Color(255, 255, 255));
        jLabel68.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 51, 51));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Stato prenotazione:");

        cardPrenotazionePulsanteCancellaPrenotazione.setFont(new java.awt.Font("Z003", 0, 36)); // NOI18N
        cardPrenotazionePulsanteCancellaPrenotazione.setForeground(new java.awt.Color(0, 153, 153));
        cardPrenotazionePulsanteCancellaPrenotazione.setText("Annulla");
        cardPrenotazionePulsanteCancellaPrenotazione.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 2, true));
        cardPrenotazionePulsanteCancellaPrenotazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardPrenotazionePulsanteCancellaPrenotazioneActionPerformed(evt);
            }
        });

        jLabel69.setBackground(new java.awt.Color(255, 255, 255));
        jLabel69.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 51, 51));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Cognome passeggero:");

        cardPrenotazioneNomePasseggeroInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneNomePasseggeroInput.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        cardPrenotazioneNomePasseggeroInput.setForeground(new java.awt.Color(102, 102, 255));
        cardPrenotazioneNomePasseggeroInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneNomePasseggeroInput.setText( nomePasseggero );

        cardPrenotazioneNumeroBagagliInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneNumeroBagagliInput.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        cardPrenotazioneNumeroBagagliInput.setForeground(new java.awt.Color(102, 102, 255));
        cardPrenotazioneNumeroBagagliInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneNumeroBagagliInput.setText( numeroBagagli );

        cardPrenotazioneCodiceVoloInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneCodiceVoloInput.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        cardPrenotazioneCodiceVoloInput.setForeground(new java.awt.Color(102, 102, 255));
        cardPrenotazioneCodiceVoloInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneCodiceVoloInput.setText( codice );

        jLabel70.setBackground(new java.awt.Color(255, 255, 255));
        jLabel70.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(0, 51, 51));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("Numero biglietto:");

        jLabel71.setBackground(new java.awt.Color(255, 255, 255));
        jLabel71.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(0, 51, 51));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("Posto assegnato:");

        cardPrenotazionePostoAssegnatoInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazionePostoAssegnatoInput.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        cardPrenotazionePostoAssegnatoInput.setForeground(new java.awt.Color(102, 102, 255));
        cardPrenotazionePostoAssegnatoInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazionePostoAssegnatoInput.setText( postoAssegnato );

        cardPrenotazioneNumeroBiglietto.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneNumeroBiglietto.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        cardPrenotazioneNumeroBiglietto.setForeground(new java.awt.Color(102, 102, 255));
        cardPrenotazioneNumeroBiglietto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneNumeroBiglietto.setText( numeroBiglietto );

        cardPrenotazioneCognomePasseggeroInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneCognomePasseggeroInput.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        cardPrenotazioneCognomePasseggeroInput.setForeground(new java.awt.Color(102, 102, 255));
        cardPrenotazioneCognomePasseggeroInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneCognomePasseggeroInput.setText( cognomePasseggero );

        cardPrenotazioneStatoVoloInput.setBackground(new java.awt.Color(255, 255, 255));
        cardPrenotazioneStatoVoloInput.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        cardPrenotazioneStatoVoloInput.setForeground(new java.awt.Color(102, 102, 255));
        cardPrenotazioneStatoVoloInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardPrenotazioneStatoVoloInput.setText( stato );

        javax.swing.GroupLayout pannelloCardPrenotazioneLayout = new javax.swing.GroupLayout(pannelloCardPrenotazione);
        pannelloCardPrenotazione.setLayout(pannelloCardPrenotazioneLayout);
        pannelloCardPrenotazioneLayout.setHorizontalGroup(
                pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addComponent(jLabel67)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardPrenotazioneCodiceVoloInput, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(54, 54, 54)
                                                                .addComponent(jLabel68))
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addComponent(jLabel64)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardPrenotazioneNomePasseggeroInput, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addComponent(jLabel66)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardPrenotazioneNumeroBagagliInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(cardPrenotazioneStatoVoloInput, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(cardPrenotazionePulsanteCancellaPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                                .addComponent(jLabel70)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(cardPrenotazioneNumeroBiglietto, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                                                .addComponent(jLabel69)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(cardPrenotazioneCognomePasseggeroInput, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                .addComponent(jLabel71)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cardPrenotazionePostoAssegnatoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        pannelloCardPrenotazioneLayout.setVerticalGroup(
                pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pannelloCardPrenotazioneLayout.createSequentialGroup()
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel64)
                                                        .addComponent(cardPrenotazioneNomePasseggeroInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel69)
                                                        .addComponent(cardPrenotazioneCognomePasseggeroInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel66)
                                                        .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(cardPrenotazioneNumeroBagagliInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel70)
                                                                .addComponent(cardPrenotazioneNumeroBiglietto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel71)
                                                        .addComponent(cardPrenotazionePostoAssegnatoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                                .addGroup(pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloCardPrenotazioneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(cardPrenotazioneCodiceVoloInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel68)
                                                                .addComponent(cardPrenotazioneStatoVoloInput, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

    public String getNomePasseggero() {
        return cardPrenotazioneNomePasseggeroInput.getText();
    }

    public String getCognomePasseggero() {
        return cardPrenotazioneCognomePasseggeroInput.getText();
    }

    public String getCodice() {
        return cardPrenotazioneCodiceVoloInput.getText();
    }
}
