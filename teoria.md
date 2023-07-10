Prima cosa che si vuole fare è ESPLORARE IL GRAFO, ossia partendo da un vertice noto, vedere quali sono gli altri vertici raggiungibili tramite un meccanismo grazie al quale partendo da un vertice riesco a trovare tutti i cammini che mi portano al vertice di arrivo.
Così scopriremo se il grafo è connesso, quali sono i vertici raggiungibili, e con quali percorsi raggiungerli. Ci saranno anche vertici non raggiungibili.
VISITA IN AMPIEZZA: agisce per livelli con un ciclo while NO RICORSIONE 
1. Abbiamo un vertice di partenza, chiamato F0, quindi la sorgente è l'insieme dei vertici raggiungibili dalla sorgente con distanza 0, quindi io conosco un vertice arrivo che è a distanza 0 dal vertice di partenza, cioè il vertice stesso.
2. Poi mi chiedo quali sono i vertici raggiungibili che sono a distanza 1 rispetto alla sorgente --> saranno tutti i vertici collegati alla sorgente stessa, quindi posso costruirmi i vertici di livello 1 definiti come i VERTICI ADIACENTI (collegati tramite un arco solo diretto) alla sorgente
3. Creo l'insieme dei vertici L+1 prendendo i vertici che siano adiacenti almeno ad un vertice presente nell'insieme L (Es: Per ogni vertice collegato a F0 dunque quelli dell'insieme L1, itero creando L2 con tutti i vertici che sono adiacenti almeno ad un vertice di L1 e che NON SIANO GIA' STATI VISITATI NEI LIVELLI PRECEDENTI. (no vertici già presenti in L1 o L0)... E così via
Questo meccanismo TERMINA o perchè i vertici del livello L non hanno più un adiacente nuovo oppure perchè ho esaurito tutto il grafo
Ho idealmente creato dei cerchi concentrici rispetto alla sorgente, con la proprietà che l'insieme dei vertici di livello 3 è composto da vertici che sono raggiungibili dalla sorgente tramite un cammino di lunghezza 3.
Se teniamo conto degli archi utilizzati per scoprire nuovi vertici, l'insieme di questi archi formano un albero (grafo connesso aciclico), chiamato ALBERO DI VISITA, il quale è un sottinsieme di vertici e di archi del grafo di partenza, comprendente tutti i vertici raggiungibili da quello di partenza e tutti gli archi utilizzati per scoprire tali vertici
Riassume tutti i percorsi unici e più brevi (di lunghezza minima inteso come numero di archi, indipendentemente dal peso) che partono dalla sorgente e raggiungono il nodo desiderato
Se il grafo fosse pesato questo metodo mi trova un cammino, ma non è detto che sia quello di lunghezza minima.
Dato un vertice: RICORSIVO (non mi dà nessuna garanzia riuardo la lunghezza dei percorsi)
	1. Prendo i vertici adiacenti;
	2. Se non l'ho ancora visitato, lo aggiungoa quelli adiacenti;
	3. Richiamo di nuovo il metodo riferendomi all'adiacenti
I metodi variano per il modo in cui consideriamo gli archi
Esistono vari algoritmi DI ATTRAVERSAMENTO appartenenti all'interfaccia generica GraphIterator<V,E>
BreadthFirstIterator --> iteratore per vita e ampiezza (orientato/non orientato)
DepthFirstIterator --> iteratore per la visita in profondità (orientato/non orientato)
Può essere inizializzato con il vertice di partenza e poi chiamare hasNext() per avere il successivo
Se è falso vuol dire che non ci sono altri vertici
I cammini sono sottintesi dall'albero di visita, il quale non viene esplicitamente salvato, ma può essere interrogato poichè l'iteratore si salva delle informazioni che successivamente posso interrogare.
Solo la classe BreadthFirstIterator (iteratore in ampiezza) mette a disposizione tale funzione, ossia
ha un metodo in più--> getSpanningTreeEdge cioè dammi un arco dell'albero di visita appena calcolato e
posso chiamarlo solo dopo che l'iteratore ha esaurito tutta la visita del grafo... mi dà un arco di quell'albero, come v gli passo il vertice di arrivo e lui mi dà l'arco per arrivare a quel vertice da un vertice del passo precedente, quindi dal risultato di questo posso richiamarlo ripetutamente, così da ottenere la sequenza di vertici in ordine inverso.

PRIORITY QUEUE (coda prioritaria):
Struttura dati (Collection) che permette di inserire ed estrarre elementi in base alla priorità (!= FIFO/LIFO) [Es: all'ospedale le persone vengono chiamate in base al codice di priorità che gli viene assegnato... meno grave, più attesa]
Come implementarla?
Più i numeri sono piccoli, più è alta la priorità
- List: estrai il max/min di un valore trovandolo ogni volta scandendo la lista
- sorted List: lista ordinata 
- l'albero di ricerca binaria: complesso
Esiste una classe particolare, chiamata PriorityQueue (con metodi add, remove(estrae), examine (lettura del prossimo elemento da estrarre, ma senza estrarlo ancora))
Non posso chiedere qual è un elemento casuale della coda con un get() posso sapere qual è il primo o l'ultimo soltanto.
Deve avere una funzione di comparazione, dunque o lavora con l'ordinamento naturale degli elementi oppure bisogna implementare un comparable.

SIMULAZIONE AD EVENTI DISCRETI
Si cerca di arrivare in teoria alla soluzione migliore, molto spesso provo dei dati e vedo cosa viene fuori --> simulo una situazione.
Facciamo un programma con il quesito "cosa succederebbe se..?", sottoforma di esperimenti computazionali, ciò ci permette di esplorare vari scenari. La simulazione mi permette di condurre degli esperimenti su un modello, più o meno accurato in base alla precisione di cui ho bisogno. Mi permette di provare varie vie e di capire qual è la migliore per me. Mi permette di fare analisi 'what-if' != previsione.
Noi ci soffermeremo su modelli in cui il tempo è concepito in maniera DISCRETA, sistema in cui l'evoluzione di esso, il cambiamento dei valori avviene solamente in determinati istanti di tempo precisi, in cui succede qualcosa, mentre tra due istanti non succede nulla dunque non mi interessa modellare il tempo(es: superamento esami). STATICO se la risposta dipende solo dagli input; DINAMICO se dipende anche dal tempo.
SIMULAZIONI DI EVENTI DISCRETI E DINAMICI.
Gli eventi possono essere sia deterministici che stocastici. possiamo astrarre il concetto di istanti di tempo importanti tramite il concetto di evento. Abbiamo una lista di eventi che accadranno e che possono essere conseguenza di input esterni oppure conseguenza di azioni interne del sistema. Gestirò con una coda prioritaria la lista degli eventi che dovranno capitare ordinati rispetto al tempo simulato, in cui questi eventi potranno succedere.
Il simulatore prende un evento per volta e prende quello che avviene nel tempo simulato più vicino, cosa succede? Vado a valutare le conseguenze di tale evento, da cui possono scaturire nuovi eventi e mutazioni del sistema(aggiornando informazioni). Nel frattempo tiene conto di un insieme di variabili di stato del sistema che aiutano a descrivere cosa sta succedendo tra un evento e l'altro, si tiene conto solo di variabili utili nell'ambito.
Abbiamo quattro cose:
- i parametri in ingresso;
- gli indicatori in uscita;
- i descrittori dello stato del sistema
- gli eventi che sono i descrittori delle cause

La cosa più semplice è pensare alla lista degli eventi come una coda prioritaria, fatta di oggetti di tipo evento, la cui caratteristica principale è che deve avvenire in un certo istante di tempo discreto. Quindi l'oggetto evento sarà una classe che avrà come attributo sicuramente il tempo, in cui si presume quell'evento avverrà, e il tipo di evento successo.
Si estrae il primo evento, poichè è quello che avviene per primo nel tempo, si valutano le condizioni e ciò che valuta va a modificare lo stato del mondo, va a modificare le misure di interesse in uscita e molto probabilmente va a generare nuovi eventi che si aggiungono alla lista. Si finisce o per il termine del periodo di interesse o per l'esaurimento della coda.
In java si avrà un classe Simulatore, che ci permette di gestire queste quattro cose, il world status sono variabili interne al simulatore, avrà la possibilità di impostare dei parametri di input, di caricare degli eventi iniziali, metodo run che mi farà partire la macchina (più importante) e metodi che mi permetteranno di estrarre alla fine della simulazione le misure di interesse. Tutto si baserà sull'elaborazione del singolo evento.
CAR SHARING:
Si vuole aprire un negozio di affitto auto, si parte acquistando un certo numero di auto e si ipotizzi l'arrivo di un cliente ogni tot di minuti (possibile studio sulla prob di arrivo di clienti). Se arriva il cliente, ed ho un auto disponibile gliela affitto per un certo periodo di tempo. OBIETTIVO: capire qual è il numero a fine giornata di clienti insoddisfatti in funzione delle macchine acquistate. Qualè il numero min di auto che devo acquistare per mandare via meno clienti possibili?
T_IN --> tempo di arrivo di un cliente nuovo
T_TRAVEL --> tempo di noleggio
Quattro domande:
- Quali sono gli eventi? Cosa può capitare?
  1. Cliente arriva e trova la macchina oppure no;
  2. Cliente a cui avevo affittato la macchina me la restituisce;
  L'evoluzione del sistema si bas sicuramente sul numero toale di auto (costante) e sul numero di auto disponibili in quel momento, il confronto tra questi due valori decide cosa devo fare quando mi arriva un cliente
E alla fine posso tener conto di quanti clienti ho soddisfatto e di quanti ne ho mandati via.
- Qual è il modello del mondo?
- Quali sono i parametri di ingrsso?
- Quali sono le quantità di uscita che devo calcolare?







	