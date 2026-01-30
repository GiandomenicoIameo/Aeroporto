/*Distruzione dello schema*/
DROP SCHEMA IF EXISTS AEROPORTO CASCADE;

/*Creazione dello schema*/
CREATE SCHEMA AEROPORTO AUTHORIZATION postgres;

-- ============================================================
-- 1. DEFINIZIONE DEI DATI
-- ============================================================

/*Creazione di una sequenza*/
CREATE SEQUENCE AEROPORTO."IdUtente"
START WITH 1
INCREMENT BY 1;

/*Creazione dei domini*/
CREATE DOMAIN AEROPORTO."StatoPrenotazione" AS VARCHAR(10)
	CHECK (VALUE IN ('Confermata','InAttesa','Cancellata','Usufruita'));
/*Sottodominio*/
CREATE DOMAIN AEROPORTO."StatoStoricoPrenotazione" AS AEROPORTO."StatoPrenotazione"
	CHECK (VALUE IN ('Cancellata','Usufruita'));
CREATE DOMAIN AEROPORTO."StatoVolo" AS VARCHAR(11)
	CHECK (VALUE IN ('Programmato','InRitardo','Chiuso','Atterrato','Cancellato','Decollato'));
CREATE DOMAIN AEROPORTO."Sesso" AS CHAR(1)
	CHECK (VALUE IN ('M','F','X'));
CREATE DOMAIN AEROPORTO."StatoOccupazione" AS VARCHAR(11)
	CHECK (VALUE IN ('Pianificato','Attivo','Annullato','Completato'));

/*Creazione delle tabelle*/

-- L'attributo password è stato impostato con vincolo NOT NULL. 
-- Se avessi permesso record con valore di questo attributo NULL, starei 
-- consentendo a un utente di autenticarsi senza alcuna password o 
-- registrare un proprio account senza la chiave di accesso. Questo 
-- perché la database accetterà record dove il campo password 
-- è vuoto o nullo.
CREATE TABLE AEROPORTO."Generico" (
	/*Definizione dei dati*/
	"IdGenerico" INTEGER	 NOT NULL,
	"Username"	 VARCHAR(30) NOT NULL,
	"Password"   VARCHAR(30) NOT NULL,
	
	/* Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "GenericoChiavePrimaria"
		PRIMARY KEY ("IdGenerico"),
	CONSTRAINT "GenericoChiaveUnica"
		UNIQUE ("Username"),
	CONSTRAINT "LunghezzaPasswordGenerico"
		CHECK (LENGTH("Password") >= 6)
);

-- A differenza del passeggero (che è un cliente privato), l'amministratore 
-- è un dipendente che compie azioni critiche. Se un domani si scopre che 
-- un volo è stato cancellato per errore o per dolo, causando un danno 
-- economico, bisogna poter dire: "È stato l'account ID 45 (Mario Rossi)", 
-- anche se Mario Rossi si è licenziato due anni fa.
-- Pertanto, ogni Amministratore deve rimanere nel sistema.
CREATE TABLE AEROPORTO."Amministratore" (
	/*Definizione dei dati*/
	"IdAmministratore" INTEGER		NOT NULL,
	"Username"		   VARCHAR(30)	NOT NULL,
	"Password"		   VARCHAR(30)	NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "AmministratoreChiavePrimaria"
		PRIMARY KEY ("IdAmministratore"),
	CONSTRAINT "AmministratoreChiaveUnica"
		UNIQUE ("Username"),
	CONSTRAINT "LunghezzaPasswordAmministratore"
		CHECK (LENGTH("Password") >= 6)
);

CREATE TABLE AEROPORTO."CompagniaAerea" (
	/*Definizione dei dati*/
	"IdCompagnia"	SERIAL		NOT NULL,
	"Nome"			VARCHAR(50)	NOT NULL,
	"Nazione"		VARCHAR(50)	NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "CompagniaAereaChiavePrimaria"
		PRIMARY KEY ("IdCompagnia"),
	CONSTRAINT "CompagniaAereaChiaveUnivoca"
		UNIQUE ("Nome")
);

CREATE TABLE AEROPORTO."Aeroporto" (
	/*Definzione dei dati*/
	"Codice"	CHAR(3)	     NOT NULL,
	"Nome"		VARCHAR(100) NOT NULL,
	"Stato"		VARCHAR(50)	 NOT NULL,
	"Citta"		VARCHAR(50)	 NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "AeroportoChiavePrimaria"
		PRIMARY KEY ("Codice"),
	CONSTRAINT "CodiceMaiuscolo"
		CHECK ("Codice" = UPPER("Codice")),
	CONSTRAINT "AeroportoFisicoUnivoco"
		UNIQUE ("Nome","Stato","Citta")
);

CREATE TABLE AEROPORTO."Volo" (
	"Codice"						SERIAL,
	"Stato"							AEROPORTO."StatoVolo"		DEFAULT 'Programmato',
	"OrarioPartenza"				TIMESTAMP WITH TIME ZONE	NOT NULL,
	"OrarioArrivo"					TIMESTAMP WITH TIME ZONE	NOT NULL,
	"Prenotabile"					BOOLEAN						DEFAULT TRUE,

	/*Definizione dei vincoli di partecipazione*/
	"IdAmministratore"				INTEGER						NOT NULL,
	"IdCompagnia"					INTEGER						NOT NULL,
	"CodiceAeroportoPartenza"		CHAR(3)						NOT NULL,
	"CodiceAeroportoDestinazione"	CHAR(3)						NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "VoloChiavePrimaria"
		PRIMARY KEY ("Codice"),
	CONSTRAINT "VoloChiaveEsternaAmministratore"
		FOREIGN KEY ("IdAmministratore")
		REFERENCES AEROPORTO."Amministratore" ("IdAmministratore")
			ON DELETE RESTRICT ON UPDATE CASCADE,
			-- ON DELETE RESTRICT garantisce il vincolo di partecipazione
			-- totale di Volo al tipo di associazione Effettuazione.
			-- Un volo non può esistere senza l'amministratore che lo
			-- gestisce. L'azione RESTRICT impedisce di rimuovere
			-- l'amministratore se è legata a un certo numero di voli.
			
			-- Se per ipotesi nessun volo viene eliminato dal sistema,
			-- allora il vincolo ON DELETE RESTRICT garantisce che
			-- anche gli amministratori non vengano eliminati.
	CONSTRAINT "VoloChiaveEsternaCompagniaAerea"
		FOREIGN KEY ("IdCompagnia")
		REFERENCES AEROPORTO."CompagniaAerea" ("IdCompagnia")
			ON DELETE RESTRICT ON UPDATE CASCADE,
			-- ON DELETE RESTRICT garantisce il vincolo di partecipazione
			-- totale di Volo al tipo di associazione Appartenenza.
			-- Un volo non può esistere senza la compagnia aerea.
			-- L'azione RESTRICT impedisce di rimuovere la compagnia
			-- se è legata a un certo numero di voli.
	CONSTRAINT "VoloChiaveEsternaAeroportoPartenza"
		FOREIGN KEY ("CodiceAeroportoPartenza")
		REFERENCES AEROPORTO."Aeroporto" ("Codice")
			ON DELETE RESTRICT ON UPDATE CASCADE,
			-- ON DELETE RESTRICT garantisce il vincolo di partecipazione
			-- totale di Volo al tipo di associazione Partenza.
			-- Un volo non può esistere senza l'aeroporto di partenza.
			-- L'azione RESTRICT impedisce di rimuovere
			-- l'aeroporto di partenza se costituisce la partenza 
			-- di un certo numero di voli.
	CONSTRAINT "VoloChiaveEsternaAeroportoDestinazione"
		FOREIGN KEY ("CodiceAeroportoDestinazione")
		REFERENCES AEROPORTO."Aeroporto" ("Codice")
			ON DELETE RESTRICT ON UPDATE CASCADE,
			-- ON DELETE RESTRICT garantisce il vincolo di partecipazione
			-- totale di Volo al tipo di associazione Destinazione.
			-- Un volo non può esistere senza l'aeroporto di destinazione.
			-- L'azione RESTRICT impedisce di rimuovere l'aeroporto di
			-- destinazione se se costituisce la destinazione di 
			-- un certo numero di voli.
	CONSTRAINT "ArrivoMaggioreDiPartenza"
		CHECK ("OrarioArrivo" > "OrarioPartenza")
);


-- Per questo progetto si è deciso che quando una prenotazione 
-- si trova nello stato di cancellata, i dati del passeggero 
-- devono essere cancellati per questioni legate alla privacy
-- ma la prenotazione deve comunque rimanere. Infatti, vengono
-- memorizzate nello StoricoPrenotazione. Uno dei motivi per cui
-- si intende mantenerle è per avanzare delle statistiche riguardanti 
-- la compagnia aerea: numero di posti venduti, ecc.

CREATE TABLE AEROPORTO."StoricoPrenotazione" (
	"IdStoricoPrenotazione"	SERIAL 									NOT NULL,
	"CodicePrenotazione"	INTEGER									NOT NULL,
	"Stato"					AEROPORTO."StatoStoricoPrenotazione"	NOT NULL,	
	"PostoAssegnato"		INTEGER									NOT NULL,
	"NumeroBagagli"			INTEGER									NOT NULL,
	"NumeroBiglietto"		INTEGER									NOT NULL,
	
	/*Definizione dei vincoli di partecipazione*/
	"Codice"				INTEGER									NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "StoricoPrenotazioneChiavePrimaria"
		PRIMARY KEY ("IdStoricoPrenotazione"),
	CONSTRAINT "StoricoPrenotazoneChiaveEsternaVolo"
		FOREIGN KEY ("Codice")
		REFERENCES AEROPORTO."Volo" ("Codice")
			ON DELETE RESTRICT ON UPDATE CASCADE
		-- ON DELETE RESTRICT viene utilizzato per garantire
		-- il vincolo di partecipazione totale da parte 
		-- di StoricoPrenotazione al tipo di associazione
		-- "Relazione". Un storicoPrenotazione non può esistere senza 
		-- un volo associato.
);

CREATE TABLE AEROPORTO."Passeggero" (
	/*Definizione dei dati*/
	"NumeroDocumento"	VARCHAR(20)				NOT NULL,
	"Nome"			  	VARCHAR(50) 			NOT NULL,
	"Cognome"		  	VARCHAR(50) 			NOT NULL,
	"DataNascita"	  	DATE					NOT NULL,
	"Sesso"			  	AEROPORTO."Sesso"	  	NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "PasseggeroChiavePrimaria"
		PRIMARY KEY ("NumeroDocumento")
);

CREATE TABLE AEROPORTO."Prenotazione" (
	/*Definizione dei dati*/
	"IdPrenotazione"	SERIAL,
	"Stato"				AEROPORTO."StatoPrenotazione"	DEFAULT 'InAttesa',	
	"PostoAssegnato"	INTEGER							NOT NULL,
	"NumeroBagagli"		INTEGER							NOT NULL,
	"NumeroBiglietto"	INTEGER,
	-- NumeroBiglietto nel tipo di entità Prenotazione non può essere una 
	-- chiave candidata, quindi essere specificata tramite UNIQUE("NumeroBiglietto").
    -- Una chiave candidata non dovrebbe mai essere nulla. Se un utente può effettuare 
	-- una prenotazione e "bloccare il posto" ma il biglietto viene emesso solo 
	-- dopo il pagamento (magari giorni dopo), allora al momento della creazione 
	-- della prenotazione il NumeroBiglietto non esiste ancora.
	
	/*Definizione dei vincoli di partecipazione*/
	"IdGenerico"		INTEGER							NOT NULL,
	"Codice"			INTEGER							NOT NULL,
	"NumeroDocumento"	VARCHAR(20)						NOT NULL, 
	-- NOT NULL definisce un vincolo di partecipazione totale. Infatti, 
	-- ogni occorrenza di Prenotazione per esistere deve partecipare ad almeno 
	-- un'istanza di associazione (ovvero essere legata ad almeno un'occorrenza 
	-- di Passeggero). Non possono esistere Prenotazioni non legate a nessuna 
	-- occorrenza di Passeggero.
	-- Questo vincolo viene confermato dall'azione referenziale innescata ON DELETE 
	-- RESTRICT. Se un'occorrenza di Passeggero è legata a un'occorrenza di Prenotazione 
	-- ma si tenta di eliminarla, il database blocca tale operazione.
	
	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "PrenotazioneChiavePrimaria"
		PRIMARY KEY ("IdPrenotazione"),
	CONSTRAINT "NumeroBigliettoChiaveUnica"
		UNIQUE ("NumeroBiglietto"),
	CONSTRAINT "PrenotazioneChiaveEsternaVolo"
		FOREIGN KEY ("Codice") 
		REFERENCES AEROPORTO."Volo" ("Codice")
			 ON DELETE RESTRICT ON UPDATE CASCADE,
		-- ON DELETE RESTRICT garantisce il vincolo di partecipazione
		-- totale di Prenotazione al tipo di associazione "Inclusione".
		-- Una prenotazione non può esistere senza essere associata 
		-- a un volo.
	CONSTRAINT "PrenotazioneChiaveEsternaGenerico"
		FOREIGN KEY ("IdGenerico")
		REFERENCES AEROPORTO."Generico" ("IdGenerico")
			ON DELETE SET NULL ON UPDATE CASCADE,
			-- Le prenotazioni devono rimanere
			-- nel sistema anche se l'utente cancella l'account.
			-- Pertanto, all'atto della cancellazione dell'account
			-- utente la prenotazione non subisce lo stesso
			-- destino ma resta nel sistema.
	CONSTRAINT "PrenotazioneChiaveEsternaPasseggero"
		FOREIGN KEY ("NumeroDocumento")
		REFERENCES AEROPORTO."Passeggero" ("NumeroDocumento")
			ON DELETE RESTRICT ON UPDATE CASCADE
			-- ON DELETE RESTRICT viene utilizzato per garantire
			-- il vincolo di partecipazione totale da parte 
			-- di Prenotazione al tipo di associazione Intestazione.
			-- Una prenotazione non dovrebbe esistere nel sistema 
			-- senza essere associata ad alcun passeggero.
);

CREATE TABLE AEROPORTO."Gate" (
	/*Definizione dei dati*/
	"Numero" INTEGER NOT NULL,
	
	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "GateChiavePrimaria"
		PRIMARY KEY ("Numero")
);

CREATE TABLE AEROPORTO."Assegnazione" (
	/*Definizione dei dati*/
	"Codice"					INTEGER							 NOT NULL,
	"Numero"					INTEGER							 NOT NULL,
	"OrarioInizioAssegnazione"	TIMESTAMP WITH TIME ZONE		 NOT NULL,
	"OrarioFineAssegnazione"	TIMESTAMP WITH TIME ZONE		 NOT NULL,
	"Stato"					    AEROPORTO."StatoOccupazione" 	 DEFAULT 'Pianificato',

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "AssegnazioneChiavePrimaria"
		PRIMARY KEY ("Codice"),
	CONSTRAINT "AssegnazioneChiaveEsternaVolo"
		FOREIGN KEY ("Codice")
		REFERENCES AEROPORTO."Volo" ("Codice")
			ON DELETE CASCADE ON UPDATE CASCADE,
		-- ON DELETE CASCADE è necessario per garantire
		-- che nella tabella Assegnazione non ci siano
		-- valori nulli in corrispondenza dell'attributo
		-- "Codice" di Volo. Quindi anche per evitare che 
		-- un gate gestisca voli inesistenti.
		-- Si suppone che l'assegnazione sia fatta
		-- dall'amministratore e non dal software.
	CONSTRAINT "AssegnazioneChiaveEsternaGate"
		FOREIGN KEY ("Numero")
		REFERENCES AEROPORTO."Gate" ("Numero")
			ON DELETE CASCADE ON UPDATE CASCADE,
		-- ON DELETE CASCADE è necessario per garantire
		-- che nella tabella Assegnazione non ci siano
		--  valori nulli in corrispondenza dell'attributo
		-- "Numero" di Gate. Quindi per evitare che un 
		-- volo sia assegnato a un gate inesistente.
	CONSTRAINT "ControlloOrarioAssegnazione"
		CHECK ("OrarioFineAssegnazione" > "OrarioInizioAssegnazione")
);

-- Attivo: Viene impostato poco prima dell'atterraggio o della partenza 
-- di un aereo. Solo una tupla per Gate può avere questo stato nello stesso 
-- momento.

-- Pianificato: Sono i voli futuri prenotati per quel gate. Sono validi, 
-- ma "dormienti" finché non tocca a loro.

-- Annullato: Gate libero, volo mai arrivato. Quell'ora è tornata 
-- vergine. È possibile inserire un altro volo proprio nell'orario 
-- in cui doveva atterrare il volo annullato.

-- Completato: Il volo ha liberato il Gate. Il gate è disponibile 
-- per il prossimo volo.

-- ============================================================
-- 2. DEFINIZIONE DEI VINCOLI DI INTEGRITÀ SEMANTICI
-- ============================================================

-- Username generico unico
CREATE FUNCTION AEROPORTO.usernameGenericoUnico()
RETURNS TRIGGER AS $$
BEGIN
	IF EXISTS (
		SELECT 1
		FROM AEROPORTO."Amministratore"
		WHERE "Username" = NEW."Username" ) 
	THEN
		RAISE EXCEPTION 'Username già presente';	
	END IF;

	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER UsernameGenericoUnico
BEFORE INSERT ON AEROPORTO."Generico"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.usernameGenericoUnico();

-- Username Amministratore unico
CREATE FUNCTION AEROPORTO.usernameAmministratoreUnico()
RETURNS TRIGGER AS $$
BEGIN
	IF EXISTS (
		SELECT 1
		FROM AEROPORTO."Generico"
		WHERE "Username" = NEW."Username" ) 
	THEN
		RAISE EXCEPTION 'Username già presente';	
	END IF;

	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER UsernameAmministratoreUnico
BEFORE INSERT ON AEROPORTO."Amministratore"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.usernameAmministratoreUnico();

-- Assegnazione IdUtente a Generico
CREATE FUNCTION AEROPORTO.assegnazioneIdGenerico()
RETURNS TRIGGER AS $$
BEGIN
	NEW."IdGenerico" := nextval('AEROPORTO."IdUtente"');
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER AssegnazioneIdGenerico
BEFORE INSERT ON AEROPORTO."Generico"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.assegnazioneIdGenerico();

-- Assegnazione IdUtente ad Amministratoore
CREATE FUNCTION AEROPORTO.assegnazioneIdAmministratore()
RETURNS TRIGGER AS $$
BEGIN
	NEW."IdAmministratore" := nextval('AEROPORTO."IdUtente"');
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER AssegnazioneIdAmministratore
BEFORE INSERT ON AEROPORTO."Amministratore"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.assegnazioneIdAmministratore();

CREATE FUNCTION AEROPORTO.partenzaArrivoNapoli()
RETURNS TRIGGER AS $$
DECLARE
	arrivo   VARCHAR;
	partenza VARCHAR;
BEGIN
	SELECT Partenza."Codice", Arrivo."Codice" 
		INTO arrivo, partenza
	FROM   AEROPORTO."Aeroporto" AS Partenza, 
		   AEROPORTO."Aeroporto" AS Arrivo 
	WHERE Partenza."Codice" = NEW."CodiceAeroportoPartenza"
	AND   Arrivo."Codice"   = NEW."CodiceAeroportoDestinazione";

	IF partenza = 'NAP' AND arrivo = 'NAP' THEN
		RAISE EXCEPTION 'Non sono ammessi voli interni 
		nella stessa città.';
	ELSIF partenza <> 'NAP' AND arrivo <> 'NAP' THEN
		RAISE EXCEPTION 'Il volo deve necessariamente 
		partire o arrivare a Napoli.';
	END IF;
	
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER PartenzaArrivoNapoli
BEFORE INSERT OR UPDATE OF 
	"CodiceAeroportoPartenza", "CodiceAeroportoDestinazione" 
ON AEROPORTO."Volo"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.partenzaArrivoNapoli();

-- Univocità del numero di documento in un volo.
CREATE FUNCTION AEROPORTO.unicoDocumentoPerVolo()
RETURNS TRIGGER AS $$
BEGIN
	IF EXISTS (
		SELECT 1 FROM AEROPORTO."Prenotazione"
		WHERE  "Codice" 		 = NEW."Codice"
		AND    "NumeroDocumento" = NEW."NumeroDocumento")
	THEN
		RAISE EXCEPTION 'Numero documento già esistente!';
	END IF;

	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER UnicoDocumentoPerVolo
BEFORE INSERT ON AEROPORTO."Prenotazione"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.unicoDocumentoPerVolo();

-- Univocità del numero di biglietto.
CREATE FUNCTION AEROPORTO.unicoNumeroBiglietto()
RETURNS TRIGGER AS $$
BEGIN
	IF EXISTS (
		SELECT 1
		FROM   AEROPORTO."Prenotazione"
		WHERE  "NumeroBiglietto" = NEW."NumeroBiglietto")
	THEN
		RAISE EXCEPTION 'Numero biglietto già esistente!';
	END IF;

	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER UnicoNumeroBiglietto
BEFORE INSERT ON AEROPORTO."Prenotazione"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.unicoNumeroBiglietto();

-- Pulizia orfani (passeggeri) di prenotazione.
CREATE FUNCTION AEROPORTO.puliziaOrfani()
RETURNS TRIGGER AS $$
BEGIN
	DELETE FROM AEROPORTO."Passeggero"
	WHERE "NumeroDocumento" = OLD."NumeroDocumento";
	RETURN NULL;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER PuliziaOrfani
AFTER DELETE ON AEROPORTO."Prenotazione"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.puliziaOrfani();

-- Inserimento in StoricoPrenotazione.
CREATE FUNCTION AEROPORTO.archiviazionePrenotazioni()
RETURNS TRIGGER AS $$
BEGIN
	INSERT INTO AEROPORTO."StoricoPrenotazione" ("CodicePrenotazione",
		"Stato","PostoAssegnato","NumeroBagagli","NumeroBiglietto","Codice")
	VALUES (NEW."IdPrenotazione",NEW."Stato",NEW."PostoAssegnato",
		NEW."NumeroBagagli",NEW."NumeroBiglietto",NEW."Codice");
		
	DELETE FROM AEROPORTO."Prenotazione"
	WHERE  "IdPrenotazione" = NEW."IdPrenotazione";
	
	RETURN NULL;
 END;
 $$ LANGUAGE PLPGSQL;

 CREATE TRIGGER ArchiviazionePrenotazioni
 AFTER UPDATE OF "Stato" ON AEROPORTO."Prenotazione"
 FOR EACH ROW
	WHEN (NEW."Stato" = 'Cancellata' OR NEW."Stato" = 'Usufruita')
		EXECUTE FUNCTION AEROPORTO.archiviazionePrenotazioni();

-- volo prenotabile: inserimento prenotazione
CREATE FUNCTION AEROPORTO.voloPrenotabile()
RETURNS TRIGGER AS $$
DECLARE
	prenotabile BOOLEAN;
BEGIN
	SELECT "Prenotabile" 
		INTO prenotabile
	FROM AEROPORTO."Volo"
	WHERE "Codice" = NEW."Codice";

	IF prenotabile = FALSE THEN
		RAISE EXCEPTION 'Il volo non è prenotabile!';
	END IF;

	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER VoloPrenotabile
BEFORE INSERT ON AEROPORTO."Prenotazione"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.voloPrenotabile();

-- cancellazione prenotazione da parte dell'utente (verificato!)
-- Il punto di non ritorno è rappresentato dallo stato 'Chiuso'. Dopo
-- questo stato, l'utente non può più annullare la prenotazione.
CREATE FUNCTION AEROPORTO.cancellazionePrenotazione()
RETURNS TRIGGER AS $$
DECLARE
	stato VARCHAR;
BEGIN
	SELECT "Stato"
		INTO stato
	FROM  AEROPORTO."Volo"
	WHERE "Codice" = NEW."Codice";

	IF stato <> 'Programmato' AND 
	   stato <> 'InRitardo'   AND 
	   stato <> 'Cancellato' 
	THEN
		RAISE EXCEPTION 'Non è possibile annullare la prenotazione!';
	END IF;
	
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER CancellazionePrenotazione
BEFORE UPDATE OF "Stato" ON AEROPORTO."Prenotazione"
FOR EACH ROW
	WHEN (NEW."Stato" = 'Cancellata')
		EXECUTE FUNCTION AEROPORTO.CancellazionePrenotazione();

-- Posto unico 
CREATE FUNCTION AEROPORTO.controlloPostoUnicoPerVolo()
RETURNS TRIGGER AS $$
BEGIN
	IF EXISTS (
		SELECT 1 FROM AEROPORTO."Prenotazione"
		WHERE  "Codice" = NEW."Codice"   
		AND    "PostoAssegnato" = NEW."PostoAssegnato"
		AND    "IdPrenotazione" <> NEW."IdPrenotazione"
	) THEN
		RAISE EXCEPTION 'Conflitto di posti a sedere!';
	END IF;
	
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL; 

CREATE TRIGGER ControlloPostoUnicoPerVolo
BEFORE INSERT OR UPDATE ON AEROPORTO."Prenotazione"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.controlloPostoUnicoPerVolo();

-- Volo cancellato
CREATE FUNCTION AEROPORTO.statoVoloCancellato()
RETURNS TRIGGER AS $$
BEGIN
	IF OLD."Stato" <> 'Programmato' AND 
	   OLD."Stato" <> 'InRitardo'   AND
	   OLD."Stato" <> 'Cancellato'
	THEN
		RAISE EXCEPTION 'Non è possibile
		cambiare lo stato in Cancellato';
	ELSE
		UPDATE AEROPORTO."Prenotazione"
		SET    "Stato"  = 'Cancellata'
		WHERE  "Codice" = NEW."Codice";

		UPDATE AEROPORTO."Assegnazione"
		SET	   "Stato"  = 'Annullato'
		WHERE  "Codice" = NEW."Codice";
	
		IF NEW."Prenotabile" <> FALSE THEN
		   NEW."Prenotabile" := FALSE;
		END IF;
	
		RETURN NEW;
	END IF;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER StatoVoloCancellato
BEFORE UPDATE OF "Stato" ON AEROPORTO."Volo"
FOR EACH ROW
	WHEN (NEW."Stato" = 'Cancellato') 
		EXECUTE FUNCTION AEROPORTO.statoVoloCancellato();

-- Volo atterrato
CREATE FUNCTION AEROPORTO.statoVoloAtterrato()
RETURNS TRIGGER AS $$
BEGIN
	IF OLD."Stato" <> 'Decollato' AND
	   OLD."Stato" <> 'Atterrato'
	THEN
		RAISE EXCEPTION 'Non è possibile
		cambiare lo stato in Atterrato';
	ELSE
		UPDATE AEROPORTO."Prenotazione"
		SET    "Stato" = 'Usufruita'
		WHERE  "Codice" = NEW."Codice";
	
		UPDATE AEROPORTO."Assegnazione"
		SET    "Stato"  = 'Completato'
		WHERE  "Codice" = NEW."Codice";
	
		RETURN NEW;
	END IF;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER StatoVoloAtterrato
BEFORE UPDATE OF "Stato" ON AEROPORTO."Volo"
FOR EACH ROW
	WHEN (NEW."Stato" = 'Atterrato') 
		EXECUTE FUNCTION AEROPORTO.statoVoloAtterrato();

-- Volo chiuso 
CREATE FUNCTION AEROPORTO.statoVoloChiuso()
RETURNS TRIGGER AS $$
BEGIN
	IF OLD."Stato" <> 'Programmato' AND
	   OLD."Stato" <> 'Chiuso'
	THEN
		RAISE EXCEPTION 'Non è possibile
		cambiare lo stato in Chiuso';
	ELSE
		UPDATE AEROPORTO."Prenotazione"
		SET    "Stato"  = 'Confermata'
		WHERE  "Codice" = NEW."Codice";
	
		-- Questo caso si verifica quando le prenotazioni sono state chiuse
		-- prima della transizione nello stato chiuso. Quindi quando lo stato corrente
		-- di Volo era 'Programato' oppure 'InRitardo'.
		IF NEW."Prenotabile" <> FALSE THEN
		   NEW."Prenotabile" := FALSE;
		END IF;
		
		RETURN NEW;
	END IF;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER StatoVoloChiuso
BEFORE UPDATE OF "Stato" ON AEROPORTO."Volo"
FOR EACH ROW
	WHEN (NEW."Stato" = 'Chiuso') 
		EXECUTE FUNCTION AEROPORTO.statoVoloChiuso();

-- Volo programmato 
CREATE FUNCTION AEROPORTO.statoVoloProgrammato()
RETURNS TRIGGER AS $$
BEGIN
	IF OLD."Stato" <> 'InRitardo' AND
	   OLD."Stato" <> 'Programmato'
	THEN
		RAISE EXCEPTION 'Non è possibile
		cambiare lo stato in Programmato';
	ELSE		
		RETURN NEW;
	END IF;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER StatoVoloProgrammato
BEFORE UPDATE OF "Stato" ON AEROPORTO."Volo"
FOR EACH ROW
	WHEN (NEW."Stato" = 'Programmato') 
		EXECUTE FUNCTION AEROPORTO.statoVoloProgrammato();

-- Volo InRitardo
CREATE FUNCTION AEROPORTO.statoVoloInRitardo()
RETURNS TRIGGER AS $$
BEGIN
	IF OLD."Stato" <> 'Programmato' AND
	   OLD."Stato" <> 'InRitardo'
	THEN
		RAISE EXCEPTION 'Non è possibile
		cambiare lo stato in Programmato';
	ELSE		
		RETURN NEW;
	END IF;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER StatoVoloInRitardo
BEFORE UPDATE OF "Stato" ON AEROPORTO."Volo"
FOR EACH ROW
	WHEN (NEW."Stato" = 'InRitardo') 
		EXECUTE FUNCTION AEROPORTO.statoVoloInRitardo();

-- Stato occupazione Attivo
CREATE FUNCTION AEROPORTO.statoOccupazioneAttivo()
RETURNS TRIGGER AS $$
BEGIN
	IF OLD."Stato" <> 'Pianificato' AND
	   OLD."Stato" <> 'Attivo'
	THEN
		RAISE EXCEPTION 'Non è possibile
		cambiare lo stato in Attivo';
	ELSE
		RETURN NEW;
	END IF;
END;
$$ LANGUAGE PLPGSQL; 

CREATE TRIGGER StatoOccupazioneAttivo
BEFORE UPDATE OF "Stato" ON AEROPORTO."Assegnazione"
FOR EACH ROW
	WHEN (NEW."Stato" = 'Attivo') 
		EXECUTE FUNCTION AEROPORTO.statoOccupazioneAttivo();

-- assegnazione gate
CREATE FUNCTION AEROPORTO.assegnazioneGate()
RETURNS TRIGGER AS $$
DECLARE
	OrarioPartenza 	  TIMESTAMP WITH TIME ZONE;
	OrarioArrivo	  TIMESTAMP WITH TIME ZONE;
	AeroportoPartenza CHAR(3); 
	AeroportoArrivo	  CHAR(3);
BEGIN
	SELECT "OrarioPartenza", "CodiceAeroportoPartenza",
		   "OrarioArrivo",   "CodiceAeroportoDestinazione"
	INTO 	OrarioPartenza, AeroportoPartenza, 
			OrarioArrivo,   AeroportoArrivo 
	FROM  AEROPORTO."Volo"
	WHERE "Codice" = NEW."Codice";

	-- Se il volo sta partendo dall'aeroporto di Napoli, il Gate viene assegnato
	-- 50 minuti prima dell'orario di partenza. Successivamente viene rilasciato
	-- 10 minuti dopo la partenza.
	IF AeroportoPartenza = 'NAP' THEN
		NEW."OrarioInizioAssegnazione" := (OrarioPartenza - INTERVAL '45 min');
		NEW."OrarioFineAssegnazione"   := (OrarioPartenza + INTERVAL '10 min');
	-- Se il volo sta arrivando all'aeroporto di Napoli, il Gate viene assegnato
	-- 10 minuti prima dell'orario di arrivo. Successivamente viene rilasciato
	-- 45 minuti dopo l'arrivo.
	ELSIF AeroportoArrivo = 'NAP' THEN
		NEW."OrarioInizioAssegnazione" := (OrarioArrivo - INTERVAL '10 min');
		NEW."OrarioFineAssegnazione"   := (OrarioArrivo + INTERVAL '50 min');
	END IF;

	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER AssegnazioneGate
BEFORE INSERT OR UPDATE ON AEROPORTO."Assegnazione"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.assegnazioneGate();

-- conflitto assegnazione
-- Prima di inserire una tupla in Assegnazione il sistema 
-- chiede: "Il Gate A è Libero dalle 10:00 alle 11:00?" Cioè il sistema 
-- verifica che esista un riga nella tabella Assegnazione con quell'orario.

-- Se esiste non viene creata nessuna tupla: "Conflitto di orario".
-- Se non esiste il sistema crea una nuova riga nella tabella 
-- Assegnazione e imposta lo stato a Pianificato. Lo stato Pianificato
-- viene impostato automaticamente perché per impostazione predefinita
-- ogni nuova tupla in Assegnazione ha per l'attributo StatoOccupazione
-- il valore Pianificato.

CREATE FUNCTION AEROPORTO.orarioConflittoAssegnazioneGate()
RETURNS TRIGGER AS $$
BEGIN
	IF EXISTS (
		SELECT 1 FROM AEROPORTO."Assegnazione" 
	    WHERE "Numero" = NEW."Numero"
			AND	"Codice" <> NEW."Codice"
			AND "OrarioInizioAssegnazione" < NEW."OrarioFineAssegnazione"
			AND	"OrarioFineAssegnazione"   > NEW."OrarioInizioAssegnazione"
			AND "Stato" IN ('Pianificato','Attivo')
	) THEN
		-- La transizione fallisce e viene annullata (rollback)
		RAISE EXCEPTION 'Conflitto di orari: Modificare gli orari del
		volo con codice %', NEW."Codice";
	END IF;
	
	RETURN NEW;
END;

$$ LANGUAGE PLPGSQL; 

CREATE TRIGGER OrarioConflittoAssegnazioneGate
BEFORE INSERT OR UPDATE ON AEROPORTO."Assegnazione"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.orarioConflittoAssegnazioneGate();

-- orario assegnamento Gate (verificato!)
-- Questo trigger aggiorna gli orari di inizio e fine assegnazione
-- ogni qual volta un volo viene inserito o aggiornato. 
-- Quando un volo viene creato e il gate non ancora assegnato
-- gli orari di partenza dei voli potrebbero essere in conflitto.
-- Successivamente, quando l'admin assegna un gate il software
-- rileva i conflitti.

CREATE FUNCTION AEROPORTO.aggiornamentoAssegnazioniGate()
RETURNS TRIGGER AS $$
BEGIN
	IF EXISTS (
		SELECT 1 FROM AEROPORTO."Assegnazione"
		WHERE "Codice" = NEW."Codice"
	) THEN	
		-- Il seguente comando è innoquo. Serve solo
		-- a far scattare il successivo trigger
		-- assegnazioneGate. Il trigger assegnazioneGate scatta solo
	    -- se è stato modificato un volo che è
	    -- referenziato da una voce nella tabella
	    -- Assegnazione.
		UPDATE AEROPORTO."Assegnazione"
		SET    "Codice" = "Codice"
		WHERE  "Codice" = NEW."Codice";
	END IF;

	 -- Se il volo è stato appena creato, allora sicuramente
	 -- non è associato a nessun gate nella tabella Assegnazione,
	 -- pertanto viene solo creata la nuova tupla. 
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL; 

CREATE TRIGGER AggiornamentoAssegnazioniGate
AFTER INSERT OR UPDATE ON AEROPORTO."Volo"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.aggiornamentoAssegnazioniGate();
