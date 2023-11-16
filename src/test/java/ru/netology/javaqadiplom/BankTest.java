package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {


    @Test
    public void ShouldTransferMoney() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                2000,
                1000,
                5000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );


        Assertions.assertTrue(bank.transfer(SavingAccount, CreditAccount, 1000));
        // стандартный денежный перевод
    }

    @Test
    public void ShouldNotTransferMoneyWithZeroInitBalance() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                0,
                0,
                5000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, 2000));
        // не должен переводить деньги с 0 счета

    }

    @Test
    public void ShouldNotTransferNegativeAmount() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                3000,
                1000,
                5000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, -500));
        //перевод отрицательной суммы
    }

    @Test
    public void shouldNotTransferMoreTheMaxBalance() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                5000,
                0,
                5000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, 5100));
        // не должен переводить больше чем максимальный баланс
    }

    @Test
    public void ShouldNotTransferMoreThenInitialBalance() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                2000,
                0,
                5000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );


        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, 2500));
        // не должен переводить больше чем на есть на счету
    }

    @Test
    public void ShouldNotTransferZero() {
        Bank bank = new Bank();
        SavingAccount SavingAccount = new SavingAccount(
                2000,
                0,
                5000,
                15
        );

        CreditAccount CreditAccount = new CreditAccount(
                0,
                5_000,
                15
        );


        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, 0));
        // пробуем перевести 0
    }


}


