package ru.netology.javaqadiplom;

public class Bank {

    /**
     * Операция перевода указанной суммы с одного счёта на другой.
     * Если операция прошла успешно, то баланс счёта from должен
     * уменьшиться на эту сумму, а баланс счёта to увеличиться.
     * Если операция прошла неуспешно, балансы обоих счетов никак
     * измениться не должны.
     *
     * @param from   - счёт с которого переводим
     * @param to     - счёт на который переводим
     * @param amount - сумма перевода
     * @return - true если операция прошла успешно, false иначе
     */
    public boolean transfer(Account from, Account to, int amount) {
        if (amount <= 0) {
            return false;
        }

        if (from instanceof CreditAccount) {
            CreditAccount creditAccount = (CreditAccount) from;
            if (amount > creditAccount.getCreditLimit()) {
                return false;
            }
        }

        if (from.pay(amount)) {
            if (to.add(amount)) {
                if (to instanceof SavingAccount) {
                    SavingAccount savingAccount = (SavingAccount) to;
                    if (savingAccount.getBalance() > savingAccount.getMaxBalance() || savingAccount.getBalance() < savingAccount.getMinBalance()) {
                        // Откатываем операцию только при нарушении условий SavingAccount
                        from.add(amount);
                        to.pay(amount);
                        return false;
                    }
                }
            } else {
                from.add(amount); // Откатываем операцию при неудачном зачислении на счет to
                return false;
            }
        }

        return true;
    }
}
