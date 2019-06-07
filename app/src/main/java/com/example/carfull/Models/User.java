package com.example.carfull.Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User {
    private long id;
    private String name;
    private String prename;
    private List<Expenses> expensesList = new ArrayList<Expenses>();

    public User() {
    }

    public User(long id, String name, String prename) {
        this.id = id;
        this.name = name;
        this.prename = prename;
    }

    public User(String name, String prename) {
        this.name = name;
        this.prename = prename;

    }

    public User(long id, String name, String prename, List<Expenses> expensesList) {
        this.id = id;
        this.name = name;
        this.prename = prename;
        this.expensesList.add(new Expenses());

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expenses> expensesList) {
        this.expensesList = expensesList;
    }

    @Override
    public String toString() {
         String expensesStrings = "";

         try {
             if (!expensesList.isEmpty()){
                 for (Iterator<Expenses> i = expensesList.iterator(); i.hasNext();) {
                     Expenses item = i.next();
                     expensesStrings += item.toString();
                 }}
         }catch (Exception e){
            //error handling code
            expensesStrings = "";
        }

        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prename='" + prename + '\'' +
                ", expensesList="+/* expensesStrings +*/
                '}';
    }
}
