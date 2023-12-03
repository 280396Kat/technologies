package com.example.technologies.service;

import com.example.technologies.model.OrderTest;
import com.example.technologies.model.UserTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceTest {

    private final List<UserTest> userTests = new ArrayList<>();

    public List<OrderTest> getOrderTest() {
        return userTests.stream()
                .map(UserTest::getOrders)
                .findFirst()
                .get();
    }

    public List<OrderTest> getOrderTest(Long userId) {
        return userTests.stream()
                .filter(tmp -> tmp.getId().equals(userId))
                .map(userTest -> userTest.getOrders())
                .findFirst()
                .get();
    }

    public List<String> getOrderNames() {
        return userTests.stream()
                .map(userTest -> userTest.getOrders().stream()
                        .map(orderTest -> orderTest.getName())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList())
                .get(0);
    }

    //Найти пользователей у у которых есть не оплаченные заказы.
    public List<UserTest> getOrderIsNotPay() {
        return userTests.stream()
                .filter(tmp -> tmp.getOrders().stream().anyMatch(order -> !order.isPay())) // получаем список заказов,
                //ищем неоплаченные заказы, смотрим есть ли данные заказы
                .collect(Collectors.toList()); // собираем всё в коллекцию
    }

    //Получить информацию по заказам конкретного пользователя
    // И вывод должен быть таким образом что заказы идут в порядке (первые оплаченные, следующие не оплаченные)
    public List<OrderTest> getUserOrders(UserTest user) {
        return user.getOrders() // получаем список заказов
                .stream()
                .sorted(Comparator.comparing(OrderTest::isPay).reversed()) // сортируем,
                // с помощью компоратора сраниваем оплаты заказов, с помощю метода реверсед меняем последовательность заказов(оплаченные идут первыми)
                .collect(Collectors.toList()); // собираем всё в коллекцию
    }

    //Получить информацию по определенному заказу пользователя
    public OrderTest getOrderById(long userId, long orderId) {
        return userTests.stream()
                .filter(userTest -> userTest.getId() == userId) // ищем, фильтруем юзеров по id
                .flatMap(userTest -> userTest.getOrders().stream())// элементы юзер тест преобразуем в заказы пользователей
                .filter(orderTest -> orderTest.getId() == orderId)// ищем, фильтруем заказы по id
                .findFirst()// получаем первый найденный заказ
                .orElse(null); // если ничего не нашли, возвращаем null
    }
}



