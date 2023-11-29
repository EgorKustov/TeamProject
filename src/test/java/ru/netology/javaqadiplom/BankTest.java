package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {
    @Test

    public void transferFromCreditToSaving() {
        // создаем объекты счетов
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                500,
                5_000,
                5
        );

        CreditAccount creditAccount = new CreditAccount(
                1_000,
                5_000,
                5
        );

        // создаем объект банка
        Bank bank = new Bank();

        // выполняем операцию перевода
        bank.transfer(creditAccount, savingAccount, 1_000);

        Assertions.assertEquals(0, creditAccount.getBalance());
        Assertions.assertEquals(3_000, savingAccount.getBalance());

    }


    @Test

    public void transferFromCreditToSavingWithNegativeBalance() {
        // создаем объекты счетов
        SavingAccount savingAccount = new SavingAccount(
                2_000,
                500,
                5_000,
                5
        );

        CreditAccount creditAccount = new CreditAccount(
                1_000,
                5_000,
                5
        );

        // создаем объект банка
        Bank bank = new Bank();

        // выполняем операцию перевода
        bank.transfer(creditAccount, savingAccount, 2_000);

        Assertions.assertEquals(-1_000, creditAccount.getBalance());
        Assertions.assertEquals(4_000, savingAccount.getBalance());
    }

    @Test

    public void transferFromCreditToSavingMoreThanCreditLimit() {
        // создаем объекты счетов
        SavingAccount savingAccount = new SavingAccount(
                1_000,
                500,
                8_000,
                5);

        CreditAccount creditAccount = new CreditAccount(
                0,
                5_000,
                5
        );

        // создаем объект банка
        Bank bank = new Bank();

        // выполняем операцию перевода
        bank.transfer(creditAccount, savingAccount, 6_000);

        Assertions.assertEquals(0, creditAccount.getBalance());
        Assertions.assertEquals(1_000, savingAccount.getBalance());
    }

    @Test

    public void transferFromCreditToSavingBorderCreditLimit() {
        // создаем объекты счетов
        SavingAccount savingAccount = new SavingAccount(
                1_000,
                500,
                8_000,
                5
        );

        CreditAccount creditAccount = new CreditAccount(
                0,
                5_000,
                5
        );

        // создаем объект банка
        Bank bank = new Bank();

        // выполняем операцию перевода
        bank.transfer(creditAccount, savingAccount, 5_000);

        Assertions.assertEquals(-5_000, creditAccount.getBalance());
        Assertions.assertEquals(6_000, savingAccount.getBalance());
    }

    @Test

    public void transferFromCreditToSavingMoreThanMaxBalance() {
        // создаем объекты счетов
        SavingAccount savingAccount = new SavingAccount(
                1_000,
                500,
                3_000,
                5
        );

        CreditAccount creditAccount = new CreditAccount(
                5_000,
                5_000,
                5
        );

        // создаем объект банка
        Bank bank = new Bank();

        // выполняем операцию перевода
        bank.transfer(creditAccount, savingAccount, 4_000);

        Assertions.assertEquals(5_000, creditAccount.getBalance()); // сумма списывается с credit аккаунта, хотя перевод не должен пройти
        Assertions.assertEquals(1_000, savingAccount.getBalance());
    }

    @Test

    public void transferFromCreditToSavingLessThanMinBalance() {
        // создаем объекты счетов
        SavingAccount savingAccount = new SavingAccount(
                0,
                1_000,
                8_000,
                5
        );

        CreditAccount creditAccount = new CreditAccount(
                5_000,
                5_000,
                5
        );

        // создаем объект банка
        Bank bank = new Bank();

        // выполняем операцию перевода
        bank.transfer(creditAccount, savingAccount, 500);

        Assertions.assertEquals(5_000, creditAccount.getBalance()); // сумма списывается с credit аккаунта, хотя перевод не должен пройти
        Assertions.assertEquals(0, savingAccount.getBalance());
    }

    @Test

    public void transferFromCreditToSavingMinBorderForMinBalance() {
        // создаем объекты счетов
        SavingAccount savingAccount = new SavingAccount(
                0,
                1_000,
                5_000,
                5
        );

        CreditAccount creditAccount = new CreditAccount(
                5_000,
                5_000,
                5
        );

        // создаем объект банка
        Bank bank = new Bank();

        // выполняем операцию перевода
        bank.transfer(creditAccount, savingAccount, 1_000);

        Assertions.assertEquals(4_000, creditAccount.getBalance()); //
        Assertions.assertEquals(1_000, savingAccount.getBalance());
    }

    @Test

    public void transferFromCreditToSavingHighBorderForMaxBalance() {
        // создаем объекты счетов
        SavingAccount savingAccount = new SavingAccount(
                0,
                1_000,
                5_0000,
                5
        );

        CreditAccount creditAccount = new CreditAccount(
                5_000,
                5_000,
                5
        );

        // создаем объект банка
        Bank bank = new Bank();

        // выполняем операцию перевода
        bank.transfer(creditAccount, savingAccount, 5_000);

        Assertions.assertEquals(0, creditAccount.getBalance()); // сумма списывается с credit аккаунта, но не зачисляется в saving account
        Assertions.assertEquals(5_000, savingAccount.getBalance());
    }

    @Test
    public void ShouldTransferMoney() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                2_000,
                1_000,
                5_000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );

        bank.transfer(SavingAccount, CreditAccount, 1_000);

        Assertions.assertEquals(1_000, SavingAccount.getBalance());
        Assertions.assertEquals(1_000, CreditAccount.getBalance());
        // стандартный денежный перевод
    }

    @Test
    public void ShouldNotTransferMoneyWithZeroInitBalance() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                0,
                0,
                5_000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );

        bank.transfer(SavingAccount, CreditAccount, 2_000);
        Assertions.assertEquals(0, SavingAccount.getBalance());
        Assertions.assertEquals(0, CreditAccount.getBalance());
        // не должен переводить деньги с 0 счета

    }

    @Test
    public void ShouldNotTransferNegativeAmount() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                3_000,
                1_000,
                5_000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );

        bank.transfer(SavingAccount, CreditAccount, -500);

        Assertions.assertEquals(3_000, SavingAccount.getBalance());
        Assertions.assertEquals(0, CreditAccount.getBalance());
        //перевод отрицательной суммы

    }

    @Test
    public void shouldNotTransferMoreTheMaxBalance() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                5_000,
                0,
                5_000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );

        bank.transfer(SavingAccount, CreditAccount, 5_100);
        Assertions.assertEquals(5_000, SavingAccount.getBalance());
        Assertions.assertEquals(0, CreditAccount.getBalance());
        // не должен переводить больше чем максимальный баланс
    }

    @Test
    public void ShouldNotTransferMoreThenInitialBalance() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                2_000,
                0,
                5_000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );


        bank.transfer(SavingAccount, CreditAccount, 2_500);
        Assertions.assertEquals(2_000, SavingAccount.getBalance());
        Assertions.assertEquals(0, CreditAccount.getBalance());
        // не должен переводить больше чем на есть на счету
    }

    @Test
    public void ShouldNotTransferZero() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                2_000,
                0,
                5_000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );


        bank.transfer(SavingAccount, CreditAccount, 0);
        Assertions.assertEquals(2_000, SavingAccount.getBalance());
        Assertions.assertEquals(0, CreditAccount.getBalance());
        // пробуем перевести 0
    }

    @Test
    public void ShouldNotTransferMoreThenMinBalance() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                2_000,
                1_000,
                5_000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );


        bank.transfer(SavingAccount, CreditAccount, 2_000);
        Assertions.assertEquals(2_000, SavingAccount.getBalance());
        Assertions.assertEquals(0, CreditAccount.getBalance());
        // на счету сбер. счета не должно быть меньше минимума
    }


}