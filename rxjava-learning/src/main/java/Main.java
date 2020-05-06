import io.reactivex.Flowable;

/**
 * @author xieziwei99
 * 2020-04-18
 */
public class Main {

    public static void main(String[] args) {
        Flowable.fromArray("a", "b", "c").subscribe(s -> System.out.println("hello " + s));
    }
}
