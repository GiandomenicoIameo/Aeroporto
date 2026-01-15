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
		UNIQUE ("Nome", "Stato", "Citta")
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
			-- ON DELETE RESTRICT viene utilizzato per garantire
			-- il vincolo di partecipazione totale da parte di Volo,
			-- anche all'atto della cancellazione.
	CONSTRAINT "VoloChiaveEsternaCompagniaAerea"
		FOREIGN KEY ("IdCompagnia")
		REFERENCES AEROPORTO."CompagniaAerea" ("IdCompagnia")
			ON DELETE RESTRICT ON UPDATE CASCADE,
			-- ON DELETE RESTRICT viene utilizzato per garantire
			-- il vincolo di partecipazione totale da parte di Volo,
			-- anche all'atto della cancellazione.
	CONSTRAINT "VoloChiaveEsternaAeroportoPartenza"
		FOREIGN KEY ("CodiceAeroportoPartenza")
		REFERENCES AEROPORTO."Aeroporto" ("Codice")
			ON DELETE RESTRICT ON UPDATE CASCADE,
			-- ON DELETE RESTRICT viene utilizzato per garantire
			-- il vincolo di partecipazione totale da parte di Volo,
			-- anche all'atto della
		-- cancellazione.
	CONSTRAINT "VoloChiaveEsternaAeroportoDestinazione"
		FOREIGN KEY ("CodiceAeroportoDestinazione")
		REFERENCES AEROPORTO."Aeroporto" ("Codice")
			ON DELETE RESTRICT ON UPDATE CASCADE,
			-- ON DELETE RESTRICT viene utilizzato per garantire
			-- il vincolo di partecipazione totale da parte di Volo,
			-- anche all'atto della cancellazione.
	CONSTRAINT "ArrivoMaggioreDiPartenza"
		CHECK ("OrarioArrivo" > "OrarioPartenza")
);

CREATE TABLE AEROPORTO."StoricoPrenotazione" (
	"IdStoricoPrenotazione"	SERIAL 									NOT NULL,
	"CodicePrenotazione"	INTEGER									NOT NULL,
	"Stato"					AEROPORTO."StatoStoricoPrenotazione"	NOT NULL,	
	"PostoAssegnato"		INTEGER									NOT NULL,
	"NumeroBagagli"			INTEGER									NOT NULL,
	"NumeroBiglietto"		INTEGER,							

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
		-- di StoricoPrenotazione, anche all'atto della
		-- cancellazione.
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
	"NumeroBiglietto"	INTEGER							NOT NULL,							
	
	/*Definizione dei vincoli di partecipazione*/
	"IdGenerico"		INTEGER							NOT NULL,
	"Codice"			INTEGER							NOT NULL,
	"NumeroDocumento"	VARCHAR(20)						NOT NULL,
	
	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "PrenotazioneChiavePrimaria"
		PRIMARY KEY ("IdPrenotazione"),
	CONSTRAINT "NumeroBigliettoChiaveUnica"
		UNIQUE ("NumeroBiglietto"),
	CONSTRAINT "PrenotazioneChiaveEsternaVolo"
		FOREIGN KEY ("Codice") 
		REFERENCES AEROPORTO."Volo" ("Codice")
			ON UPDATE CASCADE,
			-- Qui non è stato specificato ON DELETE RESTRICT
			-- perché una prenotazione potrebbe essere eliminata
			-- dall'utente quando desidera.
	CONSTRAINT "PrenotazioneChiaveEsternaGenerico"
		FOREIGN KEY ("IdGenerico")
		REFERENCES AEROPORTO."Generico" ("IdGenerico")
			ON UPDATE CASCADE,
			-- Qui non è stato specificato ON DELETE
			-- perché le prenotazioni devono rimanere
			-- nel sistema anche se l'utente cancella l'account.
	CONSTRAINT "PrenotazioneChiaveEsternaPasseggero"
		FOREIGN KEY ("NumeroDocumento")
		REFERENCES AEROPORTO."Passeggero" ("NumeroDocumento")
			ON DELETE RESTRICT	ON UPDATE CASCADE
			-- ON DELETE RESTRICT viene utilizzato per garantire
			-- il vincolo di partecipazione totale da parte 
			-- di Prenotazione. Una prenotazione non dovrebbe
			-- esistere nel sistema senza essere associata
			-- ad alcun passeggero.
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
		-- valori nulli dovuti a un volo cancellato
		-- oppure per evitare che un gate sia assegnato
		-- inesistente.
		-- Si suppone che l'assegnazione sia fatta
		-- dall'amministratore e non dal software.
	CONSTRAINT "AssegnazioneChiaveEsternaGate"
		FOREIGN KEY ("Numero")
		REFERENCES AEROPORTO."Gate" ("Numero")
			ON DELETE CASCADE ON UPDATE CASCADE,
		-- ON DELETE CASCADE è necessario per garantire
		-- che nella tabella Assegnazione non ci siano
		-- valori nulli dovuti a un volo cancellato
		-- oppure per evitare che un volo sia assegnato
		-- a un gate
	CONSTRAINT "ControlloOrarioAssegnazione"
		CHECK ("OrarioFineAssegnazione" > "OrarioInizioAssegnazione")
);

-- ============================================================
-- 2. DEFINIZIONE DEI VINCOLI DI INTEGRITÀ SEMANTICI
-- ============================================================

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

-- controllo passeggeri
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
	SELECT volo."Prenotabile" 
		INTO prenotabile
	FROM AEROPORTO."Volo" AS volo
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
CREATE FUNCTION AEROPORTO.cancellazioneVolo()
RETURNS TRIGGER AS $$
DECLARE
	stato VARCHAR;
BEGIN
	SELECT "Stato"
		INTO stato
	FROM  AEROPORTO."Volo"
	WHERE "Codice" = NEW."Codice";

	IF stato <> 'Programmato' AND stato <> 'InRitardo' AND stato <> 'Cancellato' THEN
		RAISE EXCEPTION 'Non è possibile annullare la prenotazione!';
	END IF;
	
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

CREATE TRIGGER CancellazioneVolo
BEFORE UPDATE OF "Stato" ON AEROPORTO."Prenotazione"
FOR EACH ROW
	WHEN (NEW."Stato" = 'Cancellata')
		EXECUTE FUNCTION AEROPORTO.CancellazioneVolo();

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
		RAISE EXCEPTION 'Conflitto di orari: Modificare gli orari del
		volo con codice %', NEW."Codice";
	END IF;
	
	RETURN NEW;
END;

$$ LANGUAGE PLPGSQL; 

-- Questo trigger viene eseguito
-- dopo i trigger conflitto e Orario.

-- Non è necessario confrontare gli orari di arrivo e partenza di un
-- volo per verificare un conflitto. Sono sufficienti solo gli orari di
-- di assegnazione. Infatti, per il precedente trigger di ha che
-- OrarioInizioAssegnazione < OrarioPartenza < OrarioFineAssegnazione.
CREATE TRIGGER OrarioConflittoAssegnazioneGate
BEFORE INSERT OR UPDATE ON AEROPORTO."Assegnazione"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.orarioConflittoAssegnazioneGate();

-- orario assegnamento Gate (verificato!)
-- Questo trigger aggiorna gli orari di inizio e fine assegnazione
-- ogni qual volta un volo viene inserito o aggiornato. 
-- Quando un volo viene creato e il gate non ancora assegnato
-- gli orari di partenza dei voli potrebbero essere in conflitto.
-- Successivamente, quando l'admin assegna o gate, il software
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
		-- assegnazioneGate.
		UPDATE AEROPORTO."Assegnazione"
		SET    "Codice" = "Codice"
		WHERE  "Codice" = NEW."Codice";
	END IF;
	
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL; 

CREATE TRIGGER AggiornamentoAssegnazioniGate
AFTER INSERT OR UPDATE ON AEROPORTO."Volo"
FOR EACH ROW
	EXECUTE FUNCTION AEROPORTO.aggiornamentoAssegnazioniGate();