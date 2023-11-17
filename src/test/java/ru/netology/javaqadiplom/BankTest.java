package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {


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

        Assertions.assertTrue(bank.transfer(SavingAccount, CreditAccount, 1_000));

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

        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, 2_000));
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

        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, -500));

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

        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, 5_100));
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


        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, 2_500));
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


        Assertions.assertFalse(bank.transfer(SavingAccount, CreditAccount, 0));
        Assertions.assertEquals(2_000, SavingAccount.getBalance());
        Assertions.assertEquals(0, CreditAccount.getBalance());
        // пробуем перевести 0
    }


}


