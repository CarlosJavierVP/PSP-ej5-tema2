import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        double rango = Math.random()*100;
        List<Integer> lista = new ArrayList<>();

        for (int i = 0; i<rango; i++){
            int num = (int) (Math.random()*100)+1;
            lista.add(num);
        }

        Nodo nodo = new Nodo(lista);

       Thread tnodo = new Thread(nodo);
       tnodo.start();

       try{
           tnodo.join();
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }

        System.out.println(lista);
        System.out.println(nodo.getLista());


    }
}
