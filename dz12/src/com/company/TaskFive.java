package com.company;

import com.company.menu.MenuExecutor;
import com.company.menu.MenuPublisher;

public class TaskFive {
    public static void main(String[] args) {
        MenuExecutor executor = new MenuExecutor();
        MenuPublisher publisher = new MenuPublisher(executor);
        publisher.showMenu();
    }
}
