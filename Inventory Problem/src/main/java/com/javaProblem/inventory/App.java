package com.javaProblem.inventory;

import java.util.ArrayList;
import java.util.Scanner;

enum Action {
    create, updateBuy, updateSell, report, delete, updateSellPrice
}

public class App {
    ArrayList<Record> records = new ArrayList<Record>();
    double profit = 0;

    public static void main(String[] args) {
        App myApp = new App();
        myApp.run();
    }

    public void run() {
        while (true) {
            this.profit = 0;
            System.out.println("Waiting for input.....");
            Scanner sc = new Scanner(System.in);
            String cmd = sc.nextLine();
            String input[] = cmd.split(" ");
            Action currentAction = null;
            for (Action action : Action.values()) {
                if (action.name().equals(input[0]))
                    currentAction = action;
            }

            switch (currentAction) {
            case create: {
                if (input.length == 4) {
                    createRecord(input[1], input[2], input[3]);
                    break;
                } else {
                    System.out.println("Invalid command : " + cmd);
                    break;
                }
            }
            case updateBuy: {
                if (input.length == 3) {
                    updateRecords(input[1], input[2], true);
                    break;
                } else {
                    System.out.println("Invalid command : " + cmd);
                    break;
                }
            }
            case updateSell: {
                if (input.length == 3) {
                    updateRecords(input[1], input[2], false);
                    break;
                } else {
                    System.out.println("Invalid command : " + cmd);
                    break;
                }
            }
            case report: {
                if (input.length == 1) {
                    report();
                    this.profit = 0;
                    break;
                } else {
                    System.out.println("Invalid command : " + cmd);
                    break;
                }
            }
            case delete: {
                if (input.length == 3) {
                    updateRecords(input[1], input[2], false);
                    break;
                } else {
                    System.out.println("Invalid command : " + cmd);
                    break;
                }
            }
            case updateSellPrice: {
                if (input.length == 3) {
                    updateRecords(input[1], input[2]);
                } else {
                    System.out.println("Invalid command : " + cmd);
                }
            }
            }
        }
    }

    private void updateRecords(String item, String sellingPrice) {
        boolean exists = false;
        for (Record record : records) {
            if (record.getItem().equals(item)) {
                record.setSoldAt(new Float(sellingPrice));
                exists = true;
                break;
            }
        }
      if(!exists)
          System.out.println("This item does not exists : " + item);
    }

    private void report() {
        for (int i = 0; i < 10; i++)
            System.out.print("-------");
        System.out.println();
        for (Record r : records) {
            //need to add value along with other values
            System.out.printf("%-10s  %2.1f %2.1f %5d", r.getItem(), r.getBoughtAt(), r.getSoldAt(), r.getAvailableQty());
            System.out.println();
            System.out.println("profit :" + this.profit);
        }
        System.out.println();
    }

    private void updateRecords(String item, String quantity, boolean isBuy) {
        int numberOfItems = Integer.valueOf(quantity);
        for (Record record : records) {
            if (record.getItem().equals(item)) {
                if (isBuy) {
                    //buying
                    record.setAvailableQty(record.getAvailableQty() + numberOfItems);
                    break;
                } else {
                    //selling
                    if (record.getAvailableQty() > numberOfItems) {
                        record.setAvailableQty(record.getAvailableQty() - numberOfItems);
                        this.profit = this.profit + numberOfItems * (record.getSoldAt() - record.getBoughtAt());
                        break;
                    } else {
                        System.out.println("cannot sell that quantity, try less");
                        break;
                    }
                }
            }
        }
    }

    private void createRecord(String item, String costPrice, String sellingPrice) {
        Record r = new Record(item, new Float(costPrice), new Float(sellingPrice));
        boolean existsAlready = false;
        for (Record record : records) {
            if (record.getItem().equals(item)) {
                System.out.println("Item exists already" + item);
                existsAlready = true;
                break;
            }
        }
        if (!existsAlready) {
            records.add(r);
        }
    }
}
