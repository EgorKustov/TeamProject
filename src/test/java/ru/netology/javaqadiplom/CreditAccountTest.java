package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    // Тесты на пополнение баланса
    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000); //пополняем баланс

        Assertions.assertEquals(3_000, account.getBalance()); //проверяем баланс
    }

    @Test
    public void testAddWithPositiveAmountAndNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -100,
                500,
                10
        );

        Assertions.assertTrue(account.add(150)); //пытаемся пополнить отрицательный баланс

        Assertions.assertEquals(50, account.getBalance()); //проверяем, что баланс пополнился
    }

    @Test
    public void testAddWithNegativeAmount() {
        CreditAccount account = new CreditAccount(
                100,
                500,
                10
        );

        Assertions.assertFalse(account.add(-50)); //пытаемся пополнить баланс отрицательной суммой

        Assertions.assertEquals(100, account.getBalance()); //проверяем, что баланс не изменился
    }

    @Test
    public void testAddWithZeroAmount() {
        CreditAccount account = new CreditAccount(
                100,
                500,
                10
        );

        Assertions.assertFalse(account.add(0)); //пытаемся пополнить баланс на 0

        Assertions.assertEquals(100, account.getBalance()); //проверяем, что баланс не изменился
    }

    //проверки на списание средств
    @Test
    public void testPayWithNegativeAmount() {
        CreditAccount account = new CreditAccount(
                100,
                500,
                10
        );

        Assertions.assertFalse(account.pay(-50)); // пытаемся списать отрицательную сумму

        Assertions.assertEquals(100, account.getBalance()); // проверяем, что баланс не изменился
    }

    @Test
    public void testPayWithZeroAmount() {
        CreditAccount account = new CreditAccount(
                100,
                500,
                10
        );

        Assertions.assertFalse(account.pay(0)); // пытаемся списать 0

        Assertions.assertEquals(100, account.getBalance()); // проверяем, что баланс не изменился
    }

    @Test
    public void testPayExceedingCreditLimit() {
        CreditAccount account = new CreditAccount(
                -200,
                500,
                10
        );

        Assertions.assertFalse(account.pay(800)); //пытаемся списать сумму больше, чем кредитный лимит

        Assertions.assertEquals(-200, account.getBalance()); //проверяем, что баланс не изменился
    }

    @Test
    void testPayMoreThanBalance() {
        CreditAccount account = new CreditAccount(
                100,
                500,
                10
        );

        Assertions.assertTrue(account.pay(200)); // пытаемся списать сумму больше баланса

        Assertions.assertEquals(-100, account.getBalance()); // проверяем, что баланс ушёл в минус, но за пределы лимита не вышел
    }

    @Test
    void testPayStandartBalance() {
        CreditAccount account = new CreditAccount(
                500,
                500,
                10
        );

        Assertions.assertTrue(account.pay(200)); // пытаемся списать обычную сумму

        Assertions.assertEquals(300, account.getBalance()); // проверяем, что баланс уменьшился
    }

    //Тесты на начисление процентов
    @Test
    public void testYearChangeWithPositiveBalance() {
        CreditAccount account = new CreditAccount(
                100,
                500,
                10
        ); // указываем положительный баланс

        Assertions.assertEquals(0, account.yearChange()); // проверяем, что проценты не начилслились
    }

    @Test
    public void testYearChangeWithNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -200,
                500,
                10
        );
        // указываем отрицательный баланс

        Assertions.assertEquals(-20, account.yearChange()); // проверяем, что проценты начислились
    }

    @Test
    public void testYearChangeWithZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                500,
                10
        ); //указываем нулевой баланс

        Assertions.assertEquals(0, account.yearChange()); //проверяем, что проценты не начислились
    }

    @Test
    void testConstructorWithNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CreditAccount(
                        100,
                        500,
                        -10
                ));
    }

    @Test
    public void testGetCreditLimit() {
        CreditAccount account = new CreditAccount(
                100,
                500,
                10
        );

        Assertions.assertEquals(500, account.getCreditLimit());
    }
}
