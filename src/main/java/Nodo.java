import java.util.ArrayList;
import java.util.List;

public class Nodo implements Runnable {

    private List<Integer> lista;

    public Nodo(List<Integer> lista) {
        this.lista = lista;
    }

    public List<Integer> getLista() {
        return lista;
    }

    private void añade(List<Integer> l, int desde, int hasta) {
              l.addAll(lista.subList(desde, hasta));
    }

    private void mezcla(List<Integer> l1, List<Integer> l2) {
        List<Integer> dosListas = new ArrayList<>();

        while (l1.size() > 0 && l2.size() > 0) {
            if (l1.get(0) < l2.get(0)) {
                dosListas.add(l1.get(0));
                l1.remove(l1.get(0));
            } else {
                dosListas.add(l2.get(0));
                l2.remove(l2.get(0));
            }
        }
        if (l1.size() > 0){
            dosListas.addAll(l1);
        } else if (l2.size() > 0) {
            dosListas.addAll(l2);
        }
        lista = dosListas;
    }


    @Override
    public void run() {
        if (lista.size() > 1) {
            List<Integer> l1 = new ArrayList<>();
            List<Integer> l2 = new ArrayList<>();
            añade(l1, 0, lista.size() / 2);
            añade(l2, lista.size() / 2, lista.size());
            Nodo n1 = new Nodo(l1);
            Nodo n2 = new Nodo(l2);
            Thread t1 = new Thread(n1);
            Thread t2 = new Thread(n2);
            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            mezcla(n1.getLista(),n2.getLista());

        }


    }
}
