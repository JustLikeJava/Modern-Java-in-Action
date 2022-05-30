import java.util.Random;

public class TempInfo {
    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp){
        this.town = town;
        this.temp = temp;
    }

    public static TempInfo fetch(String town){ // 정적 팩토리 메서드를 이용해 해당 도시의 TempInfo 인스턴스를 만든다.
        if(random.nextInt(10) == 0) // 10% 확률로 온도 가져오기 작업 실패.
            throw new RuntimeException("Error!");
        return new TempInfo(town, random.nextInt(100)); // 0~99 사이에서 임의의 화씨 온도를 반환.
    }

    @Override
    public String toString() {
        return town + " : " + temp;
    }

    public String getTown() {
        return town;
    }

    public int getTemp() {
        return temp;
    }
}
