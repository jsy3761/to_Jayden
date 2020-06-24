package observable;

public class Sub implements Subsciper {

    private Subject subject;

    public Sub(Subject subject){
        this.subject = subject;
        subject.add(this);
    }

    @Override
    public void update() {
        System.out.println(this + "업데이트 !");
    }
}
