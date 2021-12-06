package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private static final int MIN_NUM = 0;
    private static final int MAX_NUM = 9;
    private static final int CAN_MOVE_NUM = 4;

    private final String name;
    private int position = 0;
    public static int maxPosition = 0;
    public static List<Car> carList = new ArrayList<>();

    public Car(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void addCarList(Car car) {
        carList.add(car);
    }

    public void move() {
        position++;
    }

    public static void canMove(Car car) {
        int randomNumber = Randoms.pickNumberInRange(MIN_NUM, MAX_NUM);
        if (randomNumber >= CAN_MOVE_NUM) {
            car.move();
        }
    }

    public static void getWinner() {
        getMaxPosition();
        getWinnerList();
        OutputView.printWinner(getWinnerList());
    }

    private static List<String> getWinnerList() {
        List<String> winnerList = new ArrayList<>();
        for (Car car : carList) {
            if (car.position == maxPosition) {
                winnerList.add(car.getName());
            }
        }
        return winnerList;
    }

    private static int getMaxPosition() {
        for (Car car : carList) {
            if (car.position > maxPosition) {
                maxPosition = car.position;
            }
        }
        return maxPosition;
    }
    // 추가 기능 구현
}