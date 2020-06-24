package observable;

public interface Subject {

    void add(Subsciper subsciper);

    void delete();

    void notifyObservers();
}
