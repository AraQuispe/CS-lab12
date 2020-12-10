import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Problivador - Suscriptor
 */
class Publicador {

    private final BlockingQueue<String> in;
    private final BlockingQueue<Suscriptor> out;
    // Rep invariant: in, out != null

    /**
     * Make a squarer that will listen for requests and generate replies.
     * 
     * @param requests queue to receive requests from
     * @param replies  queue to send replies to
     */
    public Publicador(BlockingQueue<String> requests, BlockingQueue<Suscriptor> replies) {
        this.in = requests;
        this.out = replies;
    }

    /**
     * Start handling squaring requests.
     */
    public void start() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    // TODO: we may want a way to stop the thread
                    try {
                        // block until a request arrives
                        String x = in.take();
                        // compute the answer and send it back
                        String y = "P1: " + x;
                        out.put(new Suscriptor(x, y));
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

/**
 * An immutable squaring result message.
 */
class Suscriptor {
    private final String input;
    private final String output;

    /**
     * Make a new result message.
     * 
     * @param input  input number
     * @param output square of input
     */
    public Suscriptor(String input, String output) {
        this.input = input;
        this.output = output;
    }

    // TODO: we will want more observers, but for now...

    @Override
    public String toString() {
        return "Un nuevo Suscriptor reviso información: confirmado = " + output; // mensaje
    }
}

public class PSQueue {

    /**
     * Create and use a squarer.
     */
    public static void main(String[] args) {

        BlockingQueue<String> requests = new LinkedBlockingQueue<>();
        BlockingQueue<Suscriptor> replies = new LinkedBlockingQueue<>();

        Publicador publicador = new Publicador(requests, replies);
        publicador.start();

        try {
            // make a request
            requests.put("Nueva publicación");
            requests.put("Nueva publicación");
            // maybe do something concurrently...

            // read the reply
            System.out.println(replies.take());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
