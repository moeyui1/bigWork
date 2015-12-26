package presentation.commonui;

import businesslogic.loginbl.LoginController;
import businesslogicservice.LoginblService;
import presentation.userui.LogInPanel;
import vo.UserVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class BigCardPanel extends JPanel implements ActionListener {

    LogInPanel lg = new LogInPanel();
    CardLayout bigCard;
    CommonPanel commonPanel = null;

    Font font = new Font("宋体", Font.PLAIN, 18);

    public BigCardPanel() {

        bigCard = new CardLayout();
        this.setLayout(bigCard);
        this.setBounds(0, 0, 900, 600);
        this.add("登录", lg);

        bigCard.show(this, "登录");

        initLoginPanel();


    }

    /**
     * 待完成
     */
    private void intiHint() {
        JPanel h = new JPanel();
        h.setBounds(this.getX(), this.getY(), 50, 50);
        JTextArea _message = new JTextArea();
        _message.setBackground(new Color(255, 255, 225));
        _message.setMargin(new Insets(4, 4, 4, 4));
        _message.setLineWrap(true);
        _message.setWrapStyleWord(true);
    }


    private void initLoginPanel() {

        lg.getButton().addActionListener(this);

        lg.getUsername().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });

        lg.getPassword().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        login();
    }

    private void login() {
        LoginblService bl = new LoginController();
        UserVO vo = null;
        String user = lg.getUsername().getText().trim();
        String password = lg.getPassword().getText().trim();
        boolean isNull;
        if ("请输入用户名".equalsIgnoreCase(user) && "请输入密码".equalsIgnoreCase(password)) {
            isNull = true;
            JLabel tip = new JLabel("请输入用户名和密码");
            tip.setFont(font);
            JOptionPane.showMessageDialog(null, tip);
        } else if ("请输入用户名".equalsIgnoreCase(user)) {
            isNull = true;
            JLabel tip = new JLabel("请输入用户名");
            tip.setFont(font);
            JOptionPane.showMessageDialog(null, tip);
        } else if ("请输入密码".equalsIgnoreCase(password)) {
            isNull = true;
            JLabel tip = new JLabel("请输入密码");
            tip.setFont(font);
            JOptionPane.showMessageDialog(null, tip);
        } else {
            isNull = false;
        }
        if (!isNull) {
            try {
                vo = bl.login(lg.getVO());

            } catch (RemoteException e1) {
                JLabel tip = new JLabel("提示：网络异常");
                tip.setFont(font);
                JOptionPane.showMessageDialog(null, tip);
                return;
            }
            if (vo == null) {
                JLabel tip = new JLabel("提示：用户名或密码错误");
                tip.setFont(font);
                JOptionPane.showMessageDialog(null, tip);
                return;
            }

            switch (vo.getPermission()) {
                case COURIER:
                    commonPanel = new CourierPanel();
                    break;
                case MANAGER:
                    commonPanel = new ManagerPanel();
                    break;
                case FINANCIAL:
                    commonPanel = new FinancePanel();
                    break;
                case ADMIN:
                    commonPanel = new AdminPanel();
                    break;
                case TRANSFERMAN:
                    commonPanel = new TransfermanPanel();
                    break;
                case SALESMAN:
                    commonPanel = new SalesmanPanel();
                    break;
                case STOCKMANAGER:
                    commonPanel = new StockManagerPanel();
                    break;
                case FINANCIALPRO:
                    commonPanel = new FinancePanel();
                    break;
                default:
                    System.out.println("login failed....");
                    break;
            }

            commonPanel.initUpper(vo);
            commonPanel.getExit().addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    bigCard.show(BigCardPanel.this, "登录");
                }
            });
            this.add("菜单", commonPanel);
            bigCard.show(this, "菜单");
        }

    }
}