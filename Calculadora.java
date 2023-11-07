import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JPanel {

    private JTextField display;
    private double primeiroNumero;
    private String operador = "";
    private boolean novoNumero = true;

    private int width = 400; // Aumente a largura da janela
    private int height = 500; // Aumente a altura da janela

    public Calculadora() {
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]")) {
                if (novoNumero) {
                    display.setText(command);
                    novoNumero = false;
                } else {
                    display.setText(display.getText() + command);
                }
            } else if (command.matches("[+\\-*/]")) {
                if (!operador.isEmpty()) {
                    // Já existe um operador definido, execute o cálculo atual
                    double segundoNumero = Double.parseDouble(display.getText());
                    double resultado = 0;

                    switch (operador) {
                        case "+":
                            resultado = primeiroNumero + segundoNumero;
                            break;
                        case "-":
                            resultado = primeiroNumero - segundoNumero;
                            break;
                        case "*":
                            resultado = primeiroNumero * segundoNumero;
                            break;
                        case "/":
                            if (segundoNumero != 0) {
                                resultado = primeiroNumero / segundoNumero;
                            } else {
                                display.setText("Erro");
                                return;
                            }
                            break;
                    }

                    display.setText(Double.toString(resultado));
                    novoNumero = true;
                }

                operador = command;
                primeiroNumero = Double.parseDouble(display.getText());
                novoNumero = true;
            } else if (command.equals("=")) {
                if (!operador.isEmpty()) {
                    double segundoNumero = Double.parseDouble(display.getText());
                    double resultado = 0;

                    switch (operador) {
                        case "+":
                            resultado = primeiroNumero + segundoNumero;
                            break;
                        case "-":
                            resultado = primeiroNumero - segundoNumero;
                            break;
                        case "*":
                            resultado = primeiroNumero * segundoNumero;
                            break;
                        case "/":
                            if (segundoNumero != 0) {
                                resultado = primeiroNumero / segundoNumero;
                            } else {
                                display.setText("Erro");
                                return;
                            }
                            break;
                    }

                    display.setText(Double.toString(resultado));
                    novoNumero = true;
                    operador = "";
                }
            } else if (command.equals("C")) {
                display.setText("0");
                novoNumero = true;
                operador = "";
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calculadora");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Calculadora calculadora = new Calculadora();
            frame.add(calculadora);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
