package racingcar;

import java.io.IOException;
import java.util.List;

public class CarRacingGame {
    private static final List<Car> carList = Car.carList;
    private boolean result;

    public void startGame() throws IOException {
        String[] cars = askCarName();
        int numberOfAttempts = askNumberOfAttempts();
        makeCarList(cars);
        printDistance(carList, numberOfAttempts);
        Car.getWinner();
    }

    public static void printDistance(List<Car> carList, int numberOfAttempts) {
        OutputView.printGameResult();
        for (int i = 0; i < numberOfAttempts; i++) {
            for (Car car : carList) {
                Car.canMove(car);
                OutputView.printPlayerAndPosition(car);
            }
            System.out.println();
        }
    }

    private static void makeCarList(String[] cars) {
        for (String name : cars) {
            Car car = new Car(name, 0);
            car.addCarList(car);
        }
    }

    private String[] askCarName() throws IOException {
        String inputCars = InputView.getCarNameList();
        result = InputErrorCheck.isValidCar(inputCars);
        while (true) {
            try {
                throwErrorMessage();
                break;
            } catch (Exception exception) {
                inputCars = InputView.getCarNameList();
                result = InputErrorCheck.isValidCar(inputCars);
            }
        }
        String SEPARATOR = ",";
        return inputCars.split(SEPARATOR);
    }


    private int askNumberOfAttempts() throws IOException {
        String inputNumberOfAttempts = InputView.getNumberOfAttempts();
        result = InputErrorCheck.isValidNumber(inputNumberOfAttempts);
        while (true) {
            try {
                throwErrorMessage();
                break;
            } catch (Exception exception) {
                inputNumberOfAttempts = InputView.getNumberOfAttempts();
                result = InputErrorCheck.isValidNumber(inputNumberOfAttempts);
            }
        }
        return Integer.parseInt(inputNumberOfAttempts);
    }

    private void throwErrorMessage() {
        if (!result) {
            String RESTART_GAME_MESSAGE = "[ERROR] 잘못된 입력입니다.";
            throw new IllegalArgumentException(RESTART_GAME_MESSAGE);
        }
    }
}