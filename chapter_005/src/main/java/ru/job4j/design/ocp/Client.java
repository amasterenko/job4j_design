package ru.job4j.design.ocp;

/**
 * При необходимости добавить еще одно соединение с новым сервером,
 * потребуется вносить измения в класс Client
 */
public class Client {
    public void connectToServer(int serverParam) {
        switch (serverParam) {
            case 1:
                connectToServer1();
                break;
            case 2:
                connectToServer2();
                break;
            default:
        }
    }

    private void connectToServer1() {
    }

    private void connectToServer2() {
    }
}
