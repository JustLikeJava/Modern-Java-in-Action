import java.util.concurrent.Flow.*;

public class Main {
    public static void main(String[] args) {
        getTemperatures("New York").subscribe(new TempSubscriber()); // 뉴욕에 새 Publisher를 만들고 TempSubscriber를 구독 시킴.
    }

    /**
     * 이 메서드는 Subscriber를 인수로 받아 Subscriber의 onSubscribe 메서드를 호출한다.
     * 람다의 시그니처가 Publisher의 함수형 인터페이스의 유일한 메서드와
     * 같은 시그니처를 가지므로 자바 컴파일러는 자동으로 람다를 Publisher로 바꿀 수 있다.(3장 참고)
     */
    private static Publisher<TempInfo> getTemperatures(String town){ // 구독한 Subscriber에게 TempSubscription을 전송하는 Publisher를 반환.
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }
}

