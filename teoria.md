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

	