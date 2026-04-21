import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SearchEngine {

    static Map<String, java.util.List<String>> graph = new HashMap<>();
    static boolean dark = false;

    public static void main(String[] args) {

        graph.put("Главная", Arrays.asList("Новости", "Контакты", "О нас"));
        graph.put("Новости", Arrays.asList("Спорт", "Технологии"));
        graph.put("Контакты", Arrays.asList("Email", "Телефон"));
        graph.put("О нас", Arrays.asList("Команда"));
        graph.put("Спорт", new ArrayList<>());
        graph.put("Технологии", new ArrayList<>());
        graph.put("Emxлефон", new ArrayList<>());
        graph.put("Команда", new ArrayList<>());

        JFrame frame = new JFrame("Mini_Project_TGoogle");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel logo = new JLabel("TGoogle");
        logo.setFont(new Font("Arial", Font.BOLD, 36));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField searchField = new JTextField();
        searchField.setMaximumSize(new Dimension(300, 35));
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton searchBtn = new JButton("Поиск");
        styleButton(searchBtn);

        JButton themeBtn = new JButton("Тёмная тема");
        styleButton(themeBtn);

        JTextArea result = new JTextArea();
        result.setEditable(false);
        result.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(result);
        scroll.setMaximumSize(new Dimension(400, 200));

        searchBtn.addActionListener(e -> {
            result.setText(bfs("Главная", searchField.getText()));
        });

        searchField.addActionListener(e -> {
            result.setText(bfs("Главная", searchField.getText()));
        });

        themeBtn.addActionListener(e -> {
            dark = !dark;
            applyTheme(panel, logo, searchField, result);

            themeBtn.setText(dark ? "Светлая тема" : "Тёмная тема");
        });

        panel.add(Box.createVerticalStrut(40));
        panel.add(logo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(searchField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(searchBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(themeBtn);
        panel.add(Box.createVerticalStrut(20));
        panel.add(scroll);

        frame.add(panel);

        applyTheme(panel, logo, searchField, result);

        frame.setVisible(true);
    }

    public static String bfs(String start, String target) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        StringBuilder res = new StringBuilder("Обход:\n");

        while (!queue.isEmpty()) {
            String current = queue.poll();
            res.append(current).append("\n");

            if (current.equalsIgnoreCase(target)) {
                return res + "\nНАЙДЕНО!";
            }

            for (String n : graph.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(n)) {
                    queue.add(n);
                    visited.add(n);
                }
            }
        }
        return res + "\nНе найдено";
    }

    static void styleButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(new Color(66, 133, 244));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
    }

    static void applyTheme(JPanel panel, JLabel logo, JTextField field, JTextArea area) {
        if (dark) {
            panel.setBackground(new Color(30, 30, 30));
            logo.setForeground(Color.WHITE);
            field.setBackground(new Color(50, 50, 50));
            field.setForeground(Color.WHITE);
            area.setBackground(new Color(40, 40, 40));
            area.setForeground(Color.WHITE);
        } else {
            panel.setBackground(Color.WHITE);
            logo.setForeground(Color.BLACK);
            field.setBackground(Color.WHITE);
            field.setForeground(Color.BLACK);
            area.setBackground(Color.WHITE);
            area.setForeground(Color.BLACK);
        }
    }
}
