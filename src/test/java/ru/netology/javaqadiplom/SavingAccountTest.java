package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { // должен прибавить initialBalance
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldNotAddZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddNegativeAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-10);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddMoreThenMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(10_500);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddLessThenMinBalance() {
        SavingAccount account = new SavingAccount(
                0,
                1_000,
                10_000,
                5
        );

        account.add(500);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );


        boolean expected = false;
        boolean actual = account.add(11_000);

        Assertions.assertEquals(expected, actual);
    }



    @Test
    public void shouldNotPayWithZeroBalance() {
        SavingAccount account = new SavingAccount(
                0,
                1_000,
                5_000,
                2
        );


        boolean expected = false;
        boolean actual = account.pay(3_000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotPayMoreThanBalance() {

        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                5_000,
                2
        );


        boolean expected = false;
        boolean actual = account.pay(3_000);

        Assertions.assertEquals(expected, actual);

    }

    ;

    @Test
    public void shouldNotPayMoreThanMinBalance() {

        SavingAccount account = new SavingAccount(
                3_000,
                2_000,
                5_000,
                2
        );


        boolean expected = false;
        boolean actual = account.pay(3_000);

        Assertions.assertEquals(expected, actual);

    }



    @Test
    public void shouldNotPayMoreThanMaxBalance() { // можно ли исходя из условия делать такой тест?

        SavingAccount account = new SavingAccount(
                6_000,
                0,
                5_000,
                2
        );


        boolean expected = false;
        boolean actual = account.pay(5_100);

        Assertions.assertEquals(expected, actual);

    }

    ;

    @Test
    public void shouldPayLessThanMinBalance() { //должен оплатить поскольку баланс будет равен минимальному

        SavingAccount account = new SavingAccount(
                3_000,
                2_000,
                5_000,
                2
        );


        boolean expected = true;
        boolean actual = account.pay(1_000);

        Assertions.assertEquals(expected, actual);

    }



    @Test
    public void shouldPay() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                5_000,
                2
        );


        boolean expected = true;
        boolean actual = account.pay(1_000);

        Assertions.assertEquals(expected, actual);


    }

    @Test
    public void shouldThrowException() { //???
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                -1
        );


        Assertions.assertThrows(IllegalAccessError.class,
                () -> account.yearChange());


    }

    @Test
    public void shouldCountYearChange() {
        SavingAccount account = new SavingAccount(
                200,
                100,
                5_000,
                5
        );


        int expected = 200 / 100 * 5;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);


    }


}


