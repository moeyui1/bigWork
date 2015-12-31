/*
 * 营业厅业务员添加司机信息界面
 */

package presentation.manageui;

import businesslogic.managebl.ManageController;
import businesslogicservice.ManageblService;
import presentation.commonui.DateChooser;
import presentation.commonui.RunTip;
import presentation.commonui.isAllEntered;
import presentation.commonui.swing.MyDialog;
import presentation.exception.NumExceptioin;
import util.ExistException;
import vo.DriverVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

public class addDriverDialog extends MyDialog {

	private DriverPanel parent;
	private String saveValue = null;

	public addDriverDialog(DriverPanel parent) {

		this.parent = parent;
		this.setContentPane(new addDriverPanel());
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	private int x = 15, y = 40, addx = 120, addy = 55, addx2 = 80,
			jl_width = 100, jtf_width = 200, height = 25, jtf_width2 = 100;

	// 设置文字的字体
	private Font font = new Font("宋体", Font.PLAIN, 20);
	private Font font2 = new Font("宋体", Font.PLAIN, 16);
	// 定义添加司机信息，司机姓名，性别，编号，出生日期，身份证号，手机，车辆单位，行驶证期限的label
	private JLabel addInfo, driverName, sex, driverNum, birthDate, idNum,
			phone, vehicleInstitution, licenseTime;
	// 定义对应的文本框
	private JTextField jtf_driverName, jtf_driverNum, jtf_birthDate, jtf_idNum,
			jtf_phone, jtf_Institution, jtf_licenseTime;
	// 定义单选按钮
	private JRadioButton jrb_male, jrb_female;
	private ButtonGroup group;
	// 定义确定，取消按钮
	private JButton sure, cancel;
	// 定义错误提示的label
	private JLabel tip1, tip2, tip3, tip4;
	// 定义用来存放用户输入信息的数组
	private String[] rowContent;
	// 定义文本框的数组
	private JTextField[] driverJtf;
	// 定义日期选择器
	private DateChooser datechooser;
	// 修改前的vo
	DriverVO preVo = null;

	class addDriverPanel extends JPanel {

		RadioButtonListener radioButtonListener = new RadioButtonListener();

		addDriverPanel() {
			this.setLayout(null);

			addInfo = new JLabel("添加司机信息", JLabel.CENTER);
			addInfo.setFont(new Font("楷体", Font.PLAIN, 25));
			addInfo.setBounds(100, 5, jtf_width, height);

			driverName = new JLabel("姓名", JLabel.CENTER);
			driverName.setFont(font);
			driverName.setBounds(x, y, jl_width, height);

			jtf_driverName = new JTextField();
			jtf_driverName.setFont(font);
			jtf_driverName.setBounds(x + addx2, y, jtf_width2, height);

			sex = new JLabel("性别", JLabel.CENTER);
			sex.setFont(font);
			sex.setBounds(x + addx2 + addx, y, jl_width, height);

			jrb_male = new JRadioButton("男");
			jrb_male.setFont(font);
			jrb_male.addActionListener(radioButtonListener);
			jrb_male.setBounds(x + addx + 2 * addx2, y, jtf_width2 / 2, height);

			jrb_female = new JRadioButton("女");
			jrb_female.setFont(font);
			jrb_female.addActionListener(radioButtonListener);
			jrb_female.setBounds(x + addx + 2 * addx2 + 50, y, jtf_width2 / 2,
					height);

			group = new ButtonGroup();
			group.add(jrb_male);
			group.add(jrb_female);

			driverNum = new JLabel("司机编号", JLabel.CENTER);
			driverNum.setFont(font);
			driverNum.setBounds(x, y + addy, jl_width, height);

			jtf_driverNum = new JTextField();
			jtf_driverNum.setFont(font);
			jtf_driverNum.setBounds(x + addx, y + addy, jtf_width, height);
			jtf_driverNum.addFocusListener(new TextFocus());

			birthDate = new JLabel("出生日期", JLabel.CENTER);
			birthDate.setFont(font);
			birthDate.setBounds(x, y + 2 * addy, jl_width, height);

			jtf_birthDate = new JTextField();
			jtf_birthDate.setFont(font);
			jtf_birthDate.setEditable(false);
			jtf_birthDate.setBounds(x + addx, y + 2 * addy, jtf_width - 30,
					height);

			datechooser = new DateChooser("yyyy-MM-dd", jtf_birthDate);
			datechooser.setBounds(x + addx + jtf_width - 30, y + 2 * addy, 30,
					height);
			jtf_birthDate.setText(datechooser.commit());
			datechooser.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {
					datechooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			});

			idNum = new JLabel("身份证号", JLabel.CENTER);
			idNum.setFont(font);
			idNum.setBounds(x, y + 3 * addy, jl_width, height);

			jtf_idNum = new JTextField();
			jtf_idNum.setFont(font);
			jtf_idNum.setBounds(x + addx, y + 3 * addy, jtf_width, height);
			jtf_idNum.addFocusListener(new TextFocus());

			phone = new JLabel("手机", JLabel.CENTER);
			phone.setFont(font);
			phone.setBounds(x, y + 4 * addy, jl_width, height);

			jtf_phone = new JTextField();
			jtf_phone.setFont(font);
			jtf_phone.setBounds(x + addx, y + 4 * addy, jtf_width, height);
			jtf_phone.addFocusListener(new TextFocus());

			vehicleInstitution = new JLabel("车辆单位", JLabel.CENTER);
			vehicleInstitution.setFont(font);
			vehicleInstitution.setBounds(x, y + 5 * addy, jl_width, height);

			jtf_Institution = new JTextField();
			jtf_Institution.setFont(font);
			jtf_Institution
					.setBounds(x + addx, y + 5 * addy, jtf_width, height);

			licenseTime = new JLabel("行驶证期限", JLabel.CENTER);
			licenseTime.setFont(font);
			licenseTime.setBounds(x, y + 6 * addy, jl_width, height);

			jtf_licenseTime = new JTextField();
			jtf_licenseTime.setFont(font);
			jtf_licenseTime
					.setBounds(x + addx, y + 6 * addy, jtf_width, height);
			jtf_licenseTime.addFocusListener(new TextFocus());

			sure = new JButton("确定");
			sure.setFont(font);
			sure.setBounds(80, y + 7 * addy, 80, height);
			sure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					performSure();
				}
			});

			cancel = new JButton("取消");
			cancel.setFont(font);
			cancel.setBounds(120 + addx, y + 7 * addy, 80, height);
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});

			this.add(addInfo);
			this.add(driverName);
			this.add(jtf_driverName);
			this.add(sex);
			this.add(jrb_male);
			this.add(jrb_female);
			this.add(driverNum);
			this.add(jtf_driverNum);
			this.add(birthDate);
			this.add(jtf_birthDate);
			this.add(datechooser);
			this.add(idNum);
			this.add(jtf_idNum);
			this.add(phone);
			this.add(jtf_phone);
			this.add(vehicleInstitution);
			this.add(jtf_Institution);
			this.add(licenseTime);
			this.add(jtf_licenseTime);
			this.add(sure);
			this.add(cancel);
		}
	}

	protected void performSure() {
		driverJtf = new JTextField[]{jtf_driverNum,
				jtf_driverName, jtf_idNum, jtf_phone,
				jtf_Institution, jtf_licenseTime};
		boolean isOk = NumExceptioin.isDriverValid(jtf_driverNum)
				&& NumExceptioin.isIdValid(jtf_idNum)
				&& NumExceptioin.isPhoneValid(jtf_phone)
				&& NumExceptioin.isInt(jtf_licenseTime);
		boolean isenter = isAllEntered.isEntered(driverJtf)
				&& (jrb_male.isSelected() || jrb_female
				.isSelected());
		if (isOk && isenter) {
			DriverVO driver_vo = new DriverVO(jtf_driverNum
					.getText(), jtf_driverName.getText(),
					jtf_birthDate.getText(), jtf_idNum.getText(),
					jtf_phone.getText(), jtf_Institution.getText(),
					saveValue, jtf_licenseTime.getText());

			ManageblService bl;
			try {
				bl = new ManageController();
				try {
					bl.addDriver(driver_vo);
				} catch (ExistException e) {
					RunTip.makeTip("该司机信息已存在", false);
					return;
				}
			} catch (RemoteException e) {
				RunTip.makeTip("网络异常", false);
				return;
			}

			rowContent = new String[]{jtf_driverNum.getText(),
					jtf_driverName.getText(), saveValue,
					jtf_birthDate.getText(), jtf_idNum.getText(),
					jtf_phone.getText(), jtf_Institution.getText(),
					jtf_licenseTime.getText()};

			parent.addAfterConfirm(rowContent);
			RunTip.makeTip("添加成功", true);
			dispose();

		} else if ((!isOk) && isenter) {
			RunTip.makeTip("请输入正确格式的信息", false);
		} else if (isOk && !isenter) {
			RunTip.makeTip("仍有信息未输入", false);
		} else if (!isOk && !isenter) {
			RunTip.makeTip("请输入所有正确格式的信息", false);
		}
	}

	class RadioButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JRadioButton temp = (JRadioButton) e.getSource();

			if (temp.isSelected()) {
				saveValue = temp.getText();
			}
		}

	}

	// 错误提示信息是否已经被添加
	boolean isDriverNumAdd = false;
	boolean isIdNumAdd = false;
	boolean isPhoneAdd = false;
	boolean isLimitAdd = false;

	/**
	 * 监听焦点
	 *
	 * @author Administrator
	 */
	class TextFocus implements FocusListener {

		public void focusGained(FocusEvent e) {
		}

		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			JTextField temp = (JTextField) e.getSource();
			if (temp == jtf_driverNum) {
				if (!NumExceptioin.isDriverValid(jtf_driverNum)) {
					isDriverNumAdd = true;
					if (tip1 == null) {
						tip1 = new JLabel("司机编号为9位0~9整数", JLabel.CENTER);
						tip1.setBounds(x + addx, y + addy + 25, jtf_width,
								height);
						tip1.setFont(font2);
						tip1.setForeground(Color.RED);
						addTip(tip1);
					}

				} else {
					if (isDriverNumAdd
							&& !"".equalsIgnoreCase(jtf_driverNum.getText()
							.trim())) {
						isDriverNumAdd = false;
						removeTip(tip1);
						tip1 = null;
					}
				}
			}

			if (temp == jtf_idNum) {
				if (!NumExceptioin.isIdValid(jtf_idNum)) {
					isIdNumAdd = true;
					if (tip2 == null) {
						tip2 = new JLabel("身份证号为18位0~9整数", JLabel.CENTER);
						tip2.setBounds(x + addx, y + 3 * addy + 25, jtf_width,
								height);
						tip2.setFont(font2);
						tip2.setForeground(Color.RED);
						addTip(tip2);
					}

				} else {
					if (isIdNumAdd
							&& !"".equalsIgnoreCase(jtf_idNum.getText().trim())) {
						isIdNumAdd = false;
						removeTip(tip2);
						tip2 = null;
					}
				}
			}

			if (temp == jtf_phone) {
				if (!NumExceptioin.isPhoneValid(jtf_phone)) {
					isPhoneAdd = true;
					if (tip3 == null) {
						tip3 = new JLabel("手机号为11位0~9整数", JLabel.CENTER);
						tip3.setBounds(x + addx, y + 4 * addy + 25, jtf_width,
								height);
						tip3.setFont(font2);
						tip3.setForeground(Color.RED);
						addTip(tip3);
					}

				} else {
					if (isPhoneAdd
							&& !"".equalsIgnoreCase(jtf_phone.getText().trim())) {
						isPhoneAdd = false;
						removeTip(tip3);
						tip3 = null;
					}
				}
			}
			if (temp == jtf_licenseTime) {
				if (!NumExceptioin.isInt(jtf_licenseTime)) {
					isLimitAdd = true;
					if (tip4 == null) {
						tip4 = new JLabel("请输入整数", JLabel.CENTER);
						tip4.setBounds(x + addx, y + 6 * addy + height,
								jtf_width, height);
						tip4.setFont(font2);
						tip4.setForeground(Color.RED);
						addTip(tip4);
					}

				} else {
					if (isLimitAdd
							&& !"".equalsIgnoreCase(jtf_licenseTime.getText()
							.trim())) {
						isLimitAdd = false;
						removeTip(tip4);
						tip4 = null;
					}
				}
			}
		}

	}

	/**
	 * 添加错误提示信息
	 *
	 * @param tip
	 */
	public void addTip(JLabel tip) {
		this.add(tip);
		this.repaint();
	}

	/**
	 * 移除错误提示信息
	 *
	 * @param tip
	 */
	public void removeTip(JLabel tip) {
		this.remove(tip);
		this.repaint();
	}

	/**
	 * 获取原来的vo
	 *
	 * @return
	 */
	public DriverVO getVo() {
		return preVo;
	}


}
