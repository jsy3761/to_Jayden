package observable;

import java.util.ArrayList;
import java.util.List;


public class NewPaper implements Subject {
    private List<Subsciper> observers;

    public NewPaper(){
        observers = new ArrayList<>();
    }


    @Override
    public void add(Subsciper subsciper) {
        observers.add(subsciper);
    }

    @Override
    public void delete() {

    }

    @Override
    public void notifyObservers() {

        for (Subsciper s: observers) {
            s.update();
        }
    }
}
