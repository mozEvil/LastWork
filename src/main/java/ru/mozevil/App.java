package ru.mozevil;

import ru.mozevil.view.TableView;

import javax.swing.*;

public class App {

    public static void main( String[] args ) {

        SwingUtilities.invokeLater(TableView::new);

    }

}