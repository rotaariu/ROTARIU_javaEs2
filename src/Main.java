import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

class RichiestaHttp {
    String ip;
    String path;
    int statusCode;
    long tempoRispostaMs;
    long timestamp;

    public RichiestaHttp(String ip, String path, int statusCode, long tempoRispostaMs, long timestamp) {
        this.ip = ip;
        this.path = path;
        this.statusCode = statusCode;
        this.tempoRispostaMs = tempoRispostaMs;
        this.timestamp = timestamp;
    }
}

public class AnalizzatoreLog {

    private ArrayList<RichiestaHttp> storico = new ArrayList<>();

    private LinkedList<RichiestaHttp> finestraScorrevole = new LinkedList<>();

    private HashSet<String> ipSospetti = new HashSet<>();

    private TreeSet<Long> tempiRisposta = new TreeSet<>();

    public void aggiungiRichiesta(RichiestaHttp req) {
        storico.add(req);

        if (req.statusCode >= 400) {
            ipSospetti.add(req.ip);
        }

        tempiRisposta.add(req.tempoRispostaMs);

        finestraScorrevole.addLast(req);
        if (finestraScorrevole.size() > 10) {
            finestraScorrevole.removeFirst();
        }
    }
}