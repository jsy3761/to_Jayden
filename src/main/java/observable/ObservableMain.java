package observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableMain {
    public static void main(String[] args) {
        Random r = new Random();
        ExecutorService poll = Executors.newFixedThreadPool(3);

//            Observable<String> observable = Observable.just(1,2,3)
//                            .flatMap(dan -> Observable.range(1,9)
//                            .map(row-> dan + " x " + row + " = " + dan*row + " -- " + Thread.currentThread().getName()))
//                            .subscribeOn(Schedulers.newThread())
//                            .observeOn(Schedulers.newThread());
//
//            observable.subscribe(s->{
//                System.out.println(s);
//                System.out.println();
//                System.out.println("ObserveOn Thread : "+Thread.currentThread().getName());
//            });
        NewPaper newPaper = new NewPaper();
        Sub s1 = new Sub(newPaper);
        Sub s2 = new Sub(newPaper);
        Sub s3 = new Sub(newPaper);

        Observable<Integer> integerObservable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(1);
            emitter.onNext(5);
            emitter.onNext(5);
            emitter.onNext(6);
            emitter.onNext(6);
            emitter.onNext(6);
            emitter.onNext(6);
            emitter.onComplete();
        });

        integerObservable.observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .map(n -> n*n)
                .subscribe(s -> {
                    System.out.println(Thread.currentThread().getName()+s);
                    if (s > 10) {
                        newPaper.notifyObservers();
                    }
                });




        Observable<Integer> observable = Observable.just(r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10))
                .observeOn(Schedulers.from(poll));


        Observable<String> stringObservable = observable
                .subscribeOn(Schedulers.from(poll))
                .filter(n -> n > 5)
                .flatMap(s -> Observable.just(String.valueOf(s)));


        stringObservable.flatMap(s ->
                Observable.fromCallable(() -> {
                    return Thread.currentThread().getName();
                })
                        .subscribeOn(Schedulers.from(poll))
        ).subscribe(System.out::println);


        observable.subscribe(s -> {
            System.out.println(Thread.currentThread().getName() + "발행온도:  " + s);
            Thread.sleep(500);
        });

        stringObservable
                .subscribeOn(Schedulers.from(poll))
                .map(m -> Thread.currentThread().getName() + " 5도 초과 " + m + "도")
                .subscribe(n -> {
                    System.out.println(n);
                    newPaper.notifyObservers();

                    Thread.sleep(1000);
                });
    }
}
