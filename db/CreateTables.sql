CREATE TABLE enigmi (
    tipo text NOT NULL,
    titolo text NOT NULL,
    testo text NOT NULL,
    autore text NOT NULL,
    soluzione text
);

CREATE TABLE connessione_con_autore (
    utente text NOT NULL,
    autore text NOT NULL
);

CREATE TABLE connessione_con_tipo (
    utente text NOT NULL,
    tipo text NOT NULL
);