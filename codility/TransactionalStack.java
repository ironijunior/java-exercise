package codility;

import java.util.Stack;

public class TransactionalStack {

    private Stack<Integer> values;
    private Stack<TransactionalStack> transactions;

    public TransactionalStack() {
        values = new Stack<>();
        transactions = new Stack<>();
        transactions.push(this);
    }

    public void push(int value) {
        if (!transactions.isEmpty()) {
            transactions.peek().values.push(value);
        }
    }

    public int top() {
        if (!transactions.isEmpty()
            && !transactions.peek().values.isEmpty()) {
            return transactions.peek().values.peek();
        }
        return 0;
    }

    public void pop() {
        if (!transactions.isEmpty()
            && !transactions.peek().values.isEmpty()) {
            transactions.peek().values.pop();
        }
    }

    public void begin() {
        TransactionalStack transaction = new TransactionalStack();
        //transaction.values = values; //need to copy/clone
        transactions.add(transaction);
    }

    public boolean rollback() {
        if (transactions.peek() != this) {
            transactions.pop();
            return true;
        } else {
            return false;
        }
    }

    public boolean commit() {
        TransactionalStack latestTransaction = transactions.peek();
        if (latestTransaction != this) {
            latestTransaction = transactions.pop();
//            transactions.peek().values = latestTransaction.values;
            for(Integer i: latestTransaction.values) {
                transactions.peek().values.push(i);
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TransactionalStack transactionalStack = new TransactionalStack();
        transactionalStack.push(4);
        transactionalStack.rollback(); // false;
        transactionalStack.begin();                    // start transaction 1
        transactionalStack.push(7);                    // stack: [4,7]
        transactionalStack.begin();                    // start transaction 2
        transactionalStack.push(2);                    // stack: [4,7,2]
        System.out.println(transactionalStack.rollback());// == true;  // rollback transaction 2
        System.out.println(transactionalStack.top());// == 7;          // stack: [4,7]
        transactionalStack.begin();                    // start transaction 3
        transactionalStack.push(10);                   // stack: [4,7,10]
        System.out.println(transactionalStack.commit());// == true;    // transaction 3 is committed
        System.out.println(transactionalStack.top()); //== 10;
        System.out.println(transactionalStack.commit());// == true;  // rollback transaction 1
        System.out.println(transactionalStack.top());// == 4;          // stack: [4]
        System.out.println(transactionalStack.commit());// == false;   // there is no open transaction

    }
}
