package com.example.splitwise.strategy;

import com.example.splitwise.models.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.*;

public class HeapSettleUpStrategy implements SettleUpStrategy{
    @Override
    public List<Expense> settleUp(List<Expense> expenseList) {
        Map<User,Integer> extraMoney = new HashMap<>();

        List<Expense> settledUpExpenses = new ArrayList<>();

        for(Expense expense : expenseList){
            for(UserExpense userExpense : expense.getUserExpenses()){
                if(userExpense.getUserExpenseType()== UserExpenseType.PAID_BY){
                    extraMoney.put(userExpense.getUser(),extraMoney.getOrDefault(userExpense.getUser(),0)+userExpense.getAmount());
                }
                else{
                    extraMoney.put(userExpense.getUser(),extraMoney.getOrDefault(userExpense.getUser(),0)-userExpense.getAmount());
                }
            }
        }

        Queue<Transaction> positiveHeap = new PriorityQueue<>((a,b)->b.getAmount()-a.getAmount());
        Queue<Transaction> negativeHeap = new PriorityQueue<>(Comparator.comparingInt(Transaction::getAmount));

        for(User user : extraMoney.keySet()){
            if(extraMoney.get(user)>0){
                positiveHeap.add(new Transaction(user,extraMoney.get(user)));
            }
            else if(extraMoney.get(user)<0){
                negativeHeap.add(new Transaction(user,extraMoney.get(user)));
            }
        }

        while(positiveHeap.isEmpty()==false && negativeHeap.isEmpty()==false){
            Transaction positiveTransaction  = positiveHeap.poll();
            Transaction negativeTransaction = negativeHeap.poll();

            int positiveamount = positiveTransaction.getAmount();
            int negativeamount = Math.abs(negativeTransaction.getAmount());

            UserExpense from = new UserExpense();
            from.setUser(negativeTransaction.getUser());
            from.setUserExpenseType(UserExpenseType.PAID_BY);

            UserExpense to = new UserExpense();
            to.setUser(positiveTransaction.getUser());
            to.setUserExpenseType(UserExpenseType.HAD_TO_PAY);

            if(positiveamount>negativeamount){
                from.setAmount(negativeamount);
                to.setAmount(negativeamount);
                positiveHeap.offer(new Transaction(positiveTransaction.getUser(), positiveamount-negativeamount));
            }
            else if(positiveamount<negativeamount){
                from.setAmount(positiveamount);
                to.setAmount(positiveamount);
                negativeHeap.offer(new Transaction(negativeTransaction.getUser(), positiveamount-negativeamount));
            }
            else{
                from.setAmount(positiveamount);
                to.setAmount(positiveamount);
            }

            Expense expense = new Expense();
            expense.setAmount(from.getAmount());
            expense.setUserExpenses(new ArrayList<>());
            expense.getUserExpenses().add(from);
            expense.getUserExpenses().add(to);
            expense.setExpenseType(ExpenseType.PAYMENT);

            settledUpExpenses.add(expense);
        }

        return settledUpExpenses;


    }
}
