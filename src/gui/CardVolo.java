package gui;

import controller.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CardVolo extends JPanel {

    private Login app;
    private Controller controller;

    //private JPanel pannelloSuperioreCardVolo = new javax.swing.JPanel();
    private JPanel pannelloCardVolo = new javax.swing.JPanel();
    private JButton pannelloCardVoloPulsanteCancellaVolo = new javax.swing.JButton();
    private JButton pannelloCardVoloPulsanteModificaVolo = new javax.swing.JButton();

    private JLabel cardVoloAeroportoDestinazioneLabel = new javax.swing.JLabel();
    private JLabel cardVoloAeroportoOrigineLabel = new javax.swing.JLabel();
    private JLabel cardVoloOrarioPartenzaLabel = new javax.swing.JLabel();
    private JLabel cardVoloOrarioArrivoLabel = new javax.swing.JLabel();
    private JLabel cardVoloCompagniaAereaLabel = new javax.swing.JLabel();
    private JLabel cardVoloCodiceVoloLabel = new javax.swing.JLabel();
    private JLabel cardVoloDataLabel = new javax.swing.JLabel();
    private JLabel cardVoloGateLabel = new javax.swing.JLabel();
    private JLabel cardVoloStatoVoloLabel = new javax.swing.JLabel();

    private JLabel jLabel19 = new javax.swing.JLabel();
    private JLabel jLabel20 = new javax.swing.JLabel();
    private JLabel jLabel21 = new javax.swing.JLabel();
    private JLabel jLabel22 = new javax.swing.JLabel();
    private JLabel jLabel23 = new javax.swing.JLabel();
    private JLabel jLabel24 = new javax.swing.JLabel();

    private JLabel jLabel43 = new javax.swing.JLabel();
    private JLabel jLabel44 = new javax.swing.JLabel();

    private JPanel jPanel1 = new JPanel();

    public CardVolo( Login app, Controller controller, String codice, String compagnia, String gate, String partenza,
                     String arrivo, String data, String orarioPartenza, String orarioArrivo ) {

        this.app = app;
        this.controller = controller;

        pannelloCardVolo.setBackground(new java.awt.Color(255, 255, 255));
        pannelloCardVolo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        pannelloCardVolo.setMaximumSize(new java.awt.Dimension(1100, 300));
        pannelloCardVolo.setPreferredSize(new java.awt.Dimension(1100, 300));

        jPanel1.setBackground(new java.awt.Color(255, 51, 102));

        cardVoloAeroportoOrigineLabel.setFont(new java.awt.Font("Z003", 0, 48)); // NOI18N
        cardVoloAeroportoOrigineLabel.setForeground(new java.awt.Color(255, 255, 255));
        cardVoloAeroportoOrigineLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardVoloAeroportoOrigineLabel.setText( partenza );

        cardVoloAeroportoDestinazioneLabel.setFont(new java.awt.Font("Z003", 0, 48)); // NOI18N
        cardVoloAeroportoDestinazioneLabel.setForeground(new java.awt.Color(255, 255, 255));
        cardVoloAeroportoDestinazioneLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardVoloAeroportoDestinazioneLabel.setText( arrivo );

        jLabel43.setFont(new java.awt.Font("Z003", 1, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Partenza");

        jLabel44.setFont(new java.awt.Font("Z003", 1, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Destinazione");

        cardVoloStatoVoloLabel.setBackground(new java.awt.Color(255, 255, 255));
        cardVoloStatoVoloLabel.setFont(new java.awt.Font("Z003", 1, 36)); // NOI18N
        cardVoloStatoVoloLabel.setForeground(new java.awt.Color(204, 255, 0));
        cardVoloStatoVoloLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardVoloStatoVoloLabel.setText( "CONFERMATO".toLowerCase() );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel43)
                                        .addComponent(cardVoloAeroportoOrigineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel44)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(cardVoloAeroportoDestinazioneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cardVoloStatoVoloLabel)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cardVoloAeroportoOrigineLabel)
                                        .addComponent(cardVoloAeroportoDestinazioneLabel)
                                        .addComponent(cardVoloStatoVoloLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel43)
                                        .addComponent(jLabel44))
                                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Orario partenza:");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 51));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Codice:");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 51));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Compagnia aerea:");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 51));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Data:");

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 51));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Gate:");

        pannelloCardVoloPulsanteCancellaVolo.setFont(new java.awt.Font("Z003", 0, 36)); // NOI18N
        pannelloCardVoloPulsanteCancellaVolo.setForeground(new java.awt.Color(255, 51, 102));
        pannelloCardVoloPulsanteCancellaVolo.setText("Cancella");
        pannelloCardVoloPulsanteCancellaVolo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 102), 2, true));
        pannelloCardVoloPulsanteCancellaVolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pannelloCardVoloPulsanteCancellaVoloActionPerformed(evt);
            }
        });

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 51, 51));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Orario arrivo:");

        cardVoloOrarioPartenzaLabel.setBackground(new java.awt.Color(255, 255, 255));
        cardVoloOrarioPartenzaLabel.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 18)); // NOI18N
        cardVoloOrarioPartenzaLabel.setForeground(new java.awt.Color(102, 102, 255));
        cardVoloOrarioPartenzaLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardVoloOrarioPartenzaLabel.setText( orarioPartenza );

        cardVoloCompagniaAereaLabel.setBackground(new java.awt.Color(255, 255, 255));
        cardVoloCompagniaAereaLabel.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 18)); // NOI18N
        cardVoloCompagniaAereaLabel.setForeground(new java.awt.Color(102, 102, 255));
        cardVoloCompagniaAereaLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardVoloCompagniaAereaLabel.setText( compagnia );

        cardVoloDataLabel.setBackground(new java.awt.Color(255, 255, 255));
        cardVoloDataLabel.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 18)); // NOI18N
        cardVoloDataLabel.setForeground(new java.awt.Color(102, 102, 255));
        cardVoloDataLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardVoloDataLabel.setText( data );

        cardVoloGateLabel.setBackground(new java.awt.Color(255, 255, 255));
        cardVoloGateLabel.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 18)); // NOI18N
        cardVoloGateLabel.setForeground(new java.awt.Color(102, 102, 255));
        cardVoloGateLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardVoloGateLabel.setText( gate );

        cardVoloOrarioArrivoLabel.setBackground(new java.awt.Color(255, 255, 255));
        cardVoloOrarioArrivoLabel.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 18)); // NOI18N
        cardVoloOrarioArrivoLabel.setForeground(new java.awt.Color(102, 102, 255));
        cardVoloOrarioArrivoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardVoloOrarioArrivoLabel.setText( orarioArrivo );

        cardVoloCodiceVoloLabel.setBackground(new java.awt.Color(255, 255, 255));
        cardVoloCodiceVoloLabel.setFont(new java.awt.Font("DejaVu Math TeX Gyre", 1, 18)); // NOI18N
        cardVoloCodiceVoloLabel.setForeground(new java.awt.Color(102, 102, 255));
        cardVoloCodiceVoloLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cardVoloCodiceVoloLabel.setText( codice );

        pannelloCardVoloPulsanteModificaVolo.setFont(new java.awt.Font("Z003", 0, 36)); // NOI18N
        pannelloCardVoloPulsanteModificaVolo.setForeground(new java.awt.Color(255, 51, 102));
        pannelloCardVoloPulsanteModificaVolo.setText("Modifica");
        pannelloCardVoloPulsanteModificaVolo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 102), 2, true));
        pannelloCardVoloPulsanteModificaVolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pannelloCardVoloPulsanteModificaVoloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pannelloCardVoloLayout = new javax.swing.GroupLayout(pannelloCardVolo);
        pannelloCardVolo.setLayout(pannelloCardVoloLayout);
        pannelloCardVoloLayout.setHorizontalGroup(
                pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pannelloCardVoloLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pannelloCardVoloLayout.createSequentialGroup()
                                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(pannelloCardVoloLayout.createSequentialGroup()
                                                                .addComponent(jLabel22)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardVoloDataLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(139, 139, 139))
                                                        .addGroup(pannelloCardVoloLayout.createSequentialGroup()
                                                                .addComponent(jLabel19)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardVoloOrarioPartenzaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pannelloCardVoloLayout.createSequentialGroup()
                                                                .addComponent(jLabel23)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cardVoloGateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel24)))
                                        .addGroup(pannelloCardVoloLayout.createSequentialGroup()
                                                .addComponent(jLabel21)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cardVoloCompagniaAereaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloCardVoloLayout.createSequentialGroup()
                                                .addComponent(cardVoloOrarioArrivoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(84, 84, 84)
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cardVoloCodiceVoloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(232, 232, 232))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloCardVoloLayout.createSequentialGroup()
                                                .addComponent(pannelloCardVoloPulsanteModificaVolo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pannelloCardVoloPulsanteCancellaVolo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        pannelloCardVoloLayout.setVerticalGroup(
                pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pannelloCardVoloLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pannelloCardVoloLayout.createSequentialGroup()
                                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel19)
                                                        .addComponent(cardVoloOrarioPartenzaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel24)
                                                        .addComponent(cardVoloOrarioArrivoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel21)
                                                        .addComponent(cardVoloCompagniaAereaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(cardVoloDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel22))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(cardVoloGateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel23))))
                                        .addGroup(pannelloCardVoloLayout.createSequentialGroup()
                                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel20)
                                                        .addComponent(cardVoloCodiceVoloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                                .addGroup(pannelloCardVoloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(pannelloCardVoloPulsanteCancellaVolo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(pannelloCardVoloPulsanteModificaVolo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );
        this.add( pannelloCardVolo );
    }

    public void setCodice( String codice ) {
        cardVoloCodiceVoloLabel.setText( codice );
    }

    public void setData( String data ) {
        cardVoloDataLabel.setText( data );
    }

    public void setOrarioPartenza( String orarioPartenza ) {
        cardVoloOrarioPartenzaLabel.setText( orarioPartenza );
    }

    public void setOrarioArrivo( String orarioArrivo ) {
        cardVoloOrarioArrivoLabel.setText( orarioArrivo );
    }

    public void setGate( String gate ) {
        cardVoloGateLabel.setText( gate );
    }

    public void setCompagnia( String compagnia ) {
        cardVoloCompagniaAereaLabel.setText( compagnia );
    }

    public void setAeroportoOrigine( String aeroportoOrigine ) {
        cardVoloAeroportoDestinazioneLabel.setText( aeroportoOrigine );
    }

    public String getAeroportoOrigine() {
        return cardVoloAeroportoOrigineLabel.getText();
    }

    public void setAeroportoDestinazione( String aeroportoDestinazione ) {
        cardVoloAeroportoDestinazioneLabel.setText( aeroportoDestinazione );
    }

    public void setStatoVolo( String stato ) {
        cardVoloStatoVoloLabel.setText( stato.toLowerCase() );
    }

    public String getAeroportoDestinazione() {
        return cardVoloAeroportoDestinazioneLabel.getText();
    }

    public String getGate() {
        return cardVoloCompagniaAereaLabel.getText();
    }

    public String getOrarioPartenza() {
        return cardVoloOrarioPartenzaLabel.getText();
    }

    public String getOrarioArrivo() {
        return cardVoloOrarioArrivoLabel.getText();
    }


    public String getCompagniaAerea() {
        return cardVoloCodiceVoloLabel.getText();
    }

    public String getData() {
        return cardVoloDataLabel.getText();
    }

    private void pannelloCardVoloPulsanteCancellaVoloActionPerformed(java.awt.event.ActionEvent evt) {

        if( pannelloCardVoloPulsanteCancellaVolo.getText().equals( "Cancella" ) ) {

            String codice = cardVoloCodiceVoloLabel.getText();
            controller.cancellaVolo( codice );

            app.cancellaCardVolo( codice );
        } else {
            app.setPannelloGenericoDestroAttivoComeAggiungiPrenotazione();
        }
    }

    public String getCodice() {
        return cardVoloCodiceVoloLabel.getText();
    }

    public void setTestoPulsanteCancella( String testo ) {
        pannelloCardVoloPulsanteCancellaVolo.setText( testo );
    }

    public void nascondiPulsanteModifica() {
        pannelloCardVoloPulsanteModificaVolo.setVisible( false );
    }

    public void nascondiPulsanti() {
        pannelloCardVoloPulsanteModificaVolo.setVisible( false );
        pannelloCardVoloPulsanteCancellaVolo.setVisible( false );
    }

    private void pannelloCardVoloPulsanteModificaVoloActionPerformed(ActionEvent evt) {
        app.setPannelloAmministratoreDestroAttivoComeModificaVolo();
    }
}
