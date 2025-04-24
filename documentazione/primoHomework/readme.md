Inserire in questa cartella il class diagram di dominio, un eventuale documento che spieghi il significato delle classi e il nome o indirizzo della repository github che contiene il codice realizzato.

# Documentazione sintetica
## Struttura gerarchica dei ruoli associati agli utenti

Ogni utente che utilizza il sistema riveste un ruolo specifico e ben distinto: utente generico e amministratore di sistema. In tale contesto, sia utente generico che amministratore di sistema sono utenti, pertanto risulta naturale collegare queste classi attraverso una relazione di ereditarietà. Sono state così definite una classe Utente come superclasse, e le classi Generico e Amministratore come sottoclassi della superclasse Utente.

Sia le istanze di Generico che quelle di Amministratore utilizzano gli stessi metodi per accedere alla piattaforma, come ad esempio il metodo responsabile dell'autenticazione degli utenti. Di conseguenza, la relazione è stata introdotta per prevenire la ridondanza di metodi tra le classi.

## La classe Prenotazione come modello associativo.

Dal diagramma delle classi si può vedere chiaramente che la classe Prenotazione è una classe associativa, che collega le classi Volo e Generico. La scelta di considerare classe Prenotazione come classe associativa si basa sulle seguenti due asserzioni:
  1) Se non esiste alcuna istanza della classe Volo, allora non possono essere effettuate prenotazioni;
  2) Se non esiste alcuna istanza della classe Generico, allora sicuramente il sistema non riceverà nessuna prenotazione;
