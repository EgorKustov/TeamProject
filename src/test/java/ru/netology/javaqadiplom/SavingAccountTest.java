package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    //тесты на пополнение сбер.счета
    @Test
    public void shouldAddLessThanMaxBalance() { //
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000); // пополняем сбер. счет

        Assertions.assertEquals(2_000 + 3_000, account.getBalance()); // не учитывает initialBalance
    }

    @Test
    public void shouldNotAddZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0); // не пополняем счет

        Assertions.assertEquals(2_000, account.getBalance()); // баланс не изменился
    }

    @Test
    public void shouldNotAddNegativeAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-10); // пополняем счет на отрицательное значение

        Assertions.assertEquals(2_000, account.getBalance()); // сумма на счету не изменилась
    }

    @Test
    public void shouldNotAddMoreThenMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(10_500); // пополняем сбер. счет на сумму большую чем максимальный баланс

        Assertions.assertEquals(2_000, account.getBalance()); // баланс не изменился
    }

    @Test
    public void shouldNotAddLessThenMinBalance() {
        SavingAccount account = new SavingAccount(
                0,
                1_000,
                10_000,
                5
        );

        account.add(500); // пополняем сбер. счет на сумму меньшую чем минимальный баланс

        Assertions.assertEquals(0, account.getBalance()); // на счету должно быть 0
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );


        account.add(11_000); // пополняем счет на сумму большую чем максимальный баланс

        Assertions.assertEquals(2_000, account.getBalance()); // баланс не изменился
    }


    //тесты на оплату со сбер.счета

    @Test
    public void shouldNotPayWithZeroBalance() {
        SavingAccount account = new SavingAccount(
                0,
                1_000,
                5_000,
                2
        );

        Assertions.assertFalse(account.pay(3_000)); // пытаемся оплатить с 0 балансом

        Assertions.assertEquals(0, account.getBalance()); //баланс уходит в минус

    }

    @Test
    public void shouldPayWithinLimits() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                5_000,
                2
        );

        Assertions.assertTrue(account.pay(500)); // оплачиваем покупку в пределах лимитов

        Assertions.assertEquals(1_500, account.getBalance()); //оплата проходит

    }

    @Test
    public void shouldNotPayNegativeAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                3_000,
                5_000,
                2
        );

        Assertions.assertFalse(account.pay(-1_000)); // пытаемся оплатить отрицательную сумму

        Assertions.assertEquals(2_000, account.getBalance()); //баланс не изменился

    }

    @Test
    public void shouldNotPayZeroAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                3_000,
                5_000,
                2
        );

        Assertions.assertFalse(account.pay(0)); // пытаемся оплатить 0 сумму

        Assertions.assertEquals(2_000, account.getBalance()); //баланс не изменился

    }

    @Test
    public void shouldNotPayMoreThanBalance() {

        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                5_000,
                2
        );


        Assertions.assertFalse(account.pay(3_000)); // пытаемся оплатить покупку на сумму больше чем на счету

        Assertions.assertEquals(1_000, account.getBalance()); //баланс уходит в минус

    }


    @Test
    public void shouldNotPayMoreThanMinBalance() {

        SavingAccount account = new SavingAccount(
                3_000,
                2_000,
                5_000,
                2
        );


        Assertions.assertFalse(account.pay(3_000)); // пытаемся оплатить покупку на сумму большую, чем минимальный баланс

        Assertions.assertEquals(3_000, account.getBalance()); // на счету остается 0

    }


    @Test
    public void shouldNotPayMoreThanMaxBalance() {

        SavingAccount account = new SavingAccount(
                5_000,
                0,
                5_000,
                2
        );


        Assertions.assertFalse(account.pay(5_100)); // пытаемся оплатить покупку на сумму большую чем максимальный баланс

        Assertions.assertEquals(5_000, account.getBalance()); // баланс уходит в минус


    }


    @Test
    public void shouldPayLessThanMinBalance() {

        SavingAccount account = new SavingAccount(
                3_000,
                2_000,
                5_000,
                2
        );


        Assertions.assertTrue(account.pay(1_000));//оплачиваем покупку, остаток равень минимальному балансу
        Assertions.assertEquals(2_000, account.getBalance()); // должен позволять проводить оплату

    }

    // тесты по начислению процентов


    @Test
    public void shouldCountYearChange() {
        SavingAccount account = new SavingAccount(
                200,
                100,
                5_000,
                15
        );


        Assertions.assertEquals(30, account.yearChange()); // стандартное начисление процентов


    }

    @Test
    public void shouldNotCountYearChange() {
        SavingAccount account = new SavingAccount(
                0,
                0,
                5_000,
                15
        );


        Assertions.assertEquals(0, account.yearChange()); // проценты не начисляются при 0 балансе


    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(
                        500,
                        200,
                        1000,
                        -5
                ));

    }

    @Test
    public void testGetMinBalance() {
        SavingAccount account = new SavingAccount(
                0,
                1_000,
                5_000,
                15
        );

        Assertions.assertEquals(1_000, account.getMinBalance());

    }

    @Test
    public void testGetMaxBalance() {
        SavingAccount account = new SavingAccount(
                0,
                1_000,
                5_000,
                15
        );

        Assertions.assertEquals(5_000, account.getMaxBalance());

    }
}

