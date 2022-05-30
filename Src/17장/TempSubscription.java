import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.*;

/**
 * Subscriber에게 TempInfo 스트림을 전송하는 Subscription
 */
public class TempSubscription implements Subscription {
    private final Subscriber<? super TempInfo> subscriber;
    private final String town;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public TempSubscription(Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {
        executor.submit(() -> { /** 재귀 호출을 막기 위해 다른 스레드에서 TempSubscriber로 새 요소를 전달하는 방법 **/
            for(long i = 0L; i < n; i++){ // Subscriber가 만든 요청을 한 개씩 반복
                try{
                    subscriber.onNext(TempInfo.fetch(town)); // 현재 온도를 Subscriber로 전달
                }catch (Exception e){
                    subscriber.onError(e); // 온도 가져오기를 실패하면 Subscribe로 에러를 전달
                    break;
                }
            }
        });

    }

    @Override
    public void cancel() {
        subscriber.onComplete(); // 구독이 취소되면 완료(onComplete) 신호를 Subscriber로 전달
    }
}
