package Calculator;

import GUI.MainWindow;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();
        mainWindow.loadData();
        mainWindow.createWindow();
    }
}
