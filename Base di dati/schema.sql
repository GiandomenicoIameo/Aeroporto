/*Distruzione dello schema*/
DROP SCHEMA IF EXISTS AEROPORTO CASCADE;

/*Creazione dello schema*/
CREATE SCHEMA AEROPORTO AUTHORIZATION postgres;

/*Creazione dei domini*/
CREATE DOMAIN AEROPORTO."StatoPrenotazione" AS VARCHAR(10)
	CHECK (VALUE IN ('Confermata','InAttesa','Cancellata'));
CREATE DOMAIN AEROPORTO."StatoVolo" AS VARCHAR(11)
	CHECK (VALUE IN ('Programmato','Atterrato','Cancellato','InRitardo','Decollato'));
CREATE DOMAIN AEROPORTO."Sesso" AS CHAR(1)
	CHECK (VALUE IN ('M','F','X'));
CREATE DOMAIN AEROPORTO."StatoGate" AS VARCHAR(6)
	CHECK (VALUE IN ('Aperto','Chiuso'));
CREATE DOMAIN AEROPORTO."StatoOccupazione" AS VARCHAR(11)
	CHECK (VALUE IN ('Pianificato','Attivo','Annullato','Completato'));

/*Creazione delle tabelle*/
CREATE TABLE AEROPORTO."Generico" (
	/*Definizione del dati*/
	"Username"	VARCHAR(15)	NOT NULL,
	"Password"	VARCHAR(15)	NOT NULL,
	
	/* Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "GenericoChiavePrimaria"
		PRIMARY KEY ("Username")
);

CREATE TABLE AEROPORTO."Amministratore" (
	/*Definizione dei dati*/
	"Username"	VARCHAR(15)	NOT NULL,
	"Password"	VARCHAR(15)	NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "AmministratoreChiavePrimaria"
		PRIMARY KEY ("Username")
);

CREATE TABLE AEROPORTO."Prenotazione" (
	/*Definizione dei dati*/
	"IDPrenotazione"	SERIAL,
	"Stato"				AEROPORTO."StatoPrenotazione"	NOT NULL,	
	"PostoAssegnato"	INTEGER							NOT NULL,
	"NumeroBagagli"		INTEGER							NOT NULL,
	"NumeroBiglietto"	INTEGER							NOT NULL,

	/*Definizione dei vincoli di partecipazione*/
	"Username"			VARCHAR(15)						NOT NULL,
	"Codice"			INTEGER							NOT NULL,
	
	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "PrenotazioneChiavePrimaria"
		PRIMARY KEY ("IDPrenotazione"),
	CONSTRAINT "BigliettoPasseggero"
		UNIQUE ("NumeroBiglietto")
);

CREATE TABLE AEROPORTO."Passeggero" (
	/*Definizione dei dati*/
	"NumeroDocumento"	VARCHAR(20)				NOT NULL,
	"Nome"			  	VARCHAR(30) 			NOT NULL,
	"Cognome"		  	VARCHAR(30) 			NOT NULL,
	"DataNascita"	  	DATE					NOT NULL,
	"Sesso"			  	AEROPORTO."Sesso"	  	NOT NULL,

	/*Definizione dei vincoli di partecipazione*/
	"IDPrenotazione"  	INTEGER	  				NOT NULL,  -- spiegare il significato di questo vincolo di
											   			   -- di partecipazione.
	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "PasseggeroChiavePrimaria"
		PRIMARY KEY ("NumeroDocumento"),
	CONSTRAINT "PasseggeroChiaveEsterna"
		FOREIGN KEY ("IDPrenotazione") 
		REFERENCES AEROPORTO."Prenotazione" ("IDPrenotazione")
);

CREATE TABLE AEROPORTO."CompagniaAerea" (
	/*Definizione dei dati*/
	"IDCompagnia"	INTEGER		NOT NULL,
	"Nome"			VARCHAR(20)	NOT NULL,
	"Nazione"		VARCHAR(20)	NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "CompagniaAereaChiavePrimaria"
		PRIMARY KEY ("IDCompagnia")
);

CREATE TABLE AEROPORTO."Aeroporto" (
	/*Definzione dei dati*/
	"Codice"	INTEGER		NOT NULL,
	"Nome"		VARCHAR(20)	NOT NULL,
	"Nazione"	VARCHAR(20)	NOT NULL,
	"Citta"		VARCHAR(20)	NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "AeroportoChiavePrimaria"
		PRIMARY KEY ("Codice")
);

CREATE TABLE AEROPORTO."Volo" (
	"Codice"						SERIAL,
	"Stato"							AEROPORTO."StatoVolo"		NOT NULL,
	"OrarioPartenza"				TIMESTAMP WITH TIME ZONE	NOT NULL,
	"OrarioArrivo"					TIMESTAMP WITH TIME ZONE	NOT NULL,

	/*Definizione dei vincoli di partecipazione*/
	"Username"						VARCHAR(15)					NOT NULL,
	"IDCompagnia"					INTEGER						NOT NULL,
	"CodiceAeroportoPartenza"		INTEGER						NOT NULL,
	"CodiceAeroportoDestinazione"	INTEGER						NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "VoloChiavePrimaria"
		PRIMARY KEY ("Codice"),
	CONSTRAINT "VoloChiaveEsternaAmministratore"
		FOREIGN KEY ("Username")
		REFERENCES AEROPORTO."Amministratore" ("Username"),
	CONSTRAINT "VoloChiaveEsternaCompagnia"
		FOREIGN KEY ("IDCompagnia")
		REFERENCES AEROPORTO."CompagniaAerea" ("IDCompagnia"),
	CONSTRAINT "VoloChiaveEsternaAeroportoPartenza"
		FOREIGN KEY ("CodiceAeroportoPartenza")
		REFERENCES AEROPORTO."Aeroporto" ("Codice"),
	CONSTRAINT "VoloChiaveEsternaAeroportoDestinazione"
		FOREIGN KEY ("CodiceAeroportoDestinazione")
		REFERENCES AEROPORTO."Aeroporto" ("Codice")
);

CREATE TABLE AEROPORTO."Gate" (
	/*Definizione dei dati*/
	"Numero"	INTEGER					NOT NULL,
	"Stato"		AEROPORTO."StatoGate"	NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "GateChiavePrimaria"
		PRIMARY KEY ("Numero")
);

CREATE TABLE AEROPORTO."Assegnazione" (
	/*Definizione dei dati*/
	"Codice"					INTEGER							NOT NULL,
	"Numero"					INTEGER							NOT NULL,
	"OraInizioAssegnazione"		TIMESTAMP WITH TIME ZONE,		-- spiegare perché non è stato specificato NOT NULL.
	"OraFineAssegnazione"		TIMESTAMP WITH TIME ZONE,		
	"Stato"						AEROPORTO."StatoOccupazione"	NOT NULL,

	/*Definizione dei vincoli basati sullo schema*/
	CONSTRAINT "AssegnazioneChiavePrimaria"
		PRIMARY KEY ("Codice"),
	CONSTRAINT "AssegnazioneChiaveEsternaVolo"
		FOREIGN KEY ("Codice")
		REFERENCES AEROPORTO."Volo" ("Codice"),
	CONSTRAINT "AssegnazioneChiaveEsternaGate"
		FOREIGN KEY ("Numero")
		REFERENCES AEROPORTO."Gate" ("Numero")
);
