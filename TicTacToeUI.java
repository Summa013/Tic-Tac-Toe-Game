import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeUI {
    private JFrame frame;
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public TicTacToeUI() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Frame background color
        // Hex color #5276b3
        frame.getContentPane().setBackground(new Color(0x52, 0x76, 0xB3));


        frame.setLayout(new GridLayout(3, 3, 5, 5)); // 5px gap between buttons

        // Buttons add করা
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));

                // Button background & text color
                buttons[i][j].setBackground(Color.LIGHT_GRAY);
                buttons[i][j].setForeground(Color.BLUE);

                frame.add(buttons[i][j]);
                int row = i;
                int col = j;

                buttons[i][j].addActionListener(e -> {
                    if (buttons[row][col].getText().equals("") && !isGameOver()) {
                        buttons[row][col].setText(String.valueOf(currentPlayer));

                        // X / O color আলাদা করা
                        if (currentPlayer == 'X') {
                            buttons[row][col].setForeground(Color.RED);
                        } else {
                            buttons[row][col].setForeground(Color.BLUE);
                        }

                        if (hasWon()) {
                            JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                        } else if (isBoardFull()) {
                            JOptionPane.showMessageDialog(frame, "It's a tie!");
                        } else {
                            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        }
                    }
                });
            }
        }

        frame.setVisible(true);
    }

    private boolean hasWon() {
        // Rows, columns, diagonals check
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer)))
                return true;
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][i].getText().equals(String.valueOf(currentPlayer)))
                return true;
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer)))
            return true;
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer)))
            return true;

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return false;
        return true;
    }

    private boolean isGameOver() {
        return hasWon() || isBoardFull();
    }

    public static void main(String[] args) {
        new TicTacToeUI();
    }
}
