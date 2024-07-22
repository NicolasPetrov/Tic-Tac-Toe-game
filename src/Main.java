import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private boolean gameWon = false;

    public Main() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        JMenuItem resetMenuItem = new JMenuItem("Reset");
        resetMenuItem.addActionListener(e -> resetGame());
        menu.add(resetMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(3, 3));
        initializeButtons(pane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    private void initializeButtons(Container pane) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].setForeground(Color.BLUE);
                buttons[row][col].addActionListener(this);
                pane.add(buttons[row][col]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("") && !gameWon) {
            buttonClicked.setText(String.valueOf(currentPlayer));
            buttonClicked.setForeground(currentPlayer == 'X' ? Color.BLUE : Color.RED);
            if (checkWin()) {
                gameWon = true;
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin() {

        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[row][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[row][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][col].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][col].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        currentPlayer = 'X';
        gameWon = false;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setBackground(Color.WHITE);
            }
        }
    }
}