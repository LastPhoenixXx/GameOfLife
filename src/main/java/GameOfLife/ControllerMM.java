package GameOfLife;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
//import java.util.Timer;
//import java.util.TimerTask;

import javax.swing.Timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ControllerMM implements Initializable {
	@FXML
	private Button but1, but2, but3, but4, but5, but6, but7, but8, but9, but10, but11, but12, but13, but14, but15,
			but16, but17, but18, but19, but20, but21, but22, but23, but24, but25, but26, but27, but28, but29, but30,
			but31, but32, but33, but34, but35, but36, but37, but38, but39, but40, but41, but42, but43, but44, but45,
			but46, but47, but48, but49, but50, but51, but52, but53, but54, but55, but56, but57, but58, but59, but60,
			but61, but62, but63, but64, but65, but66, but67, but68, but69, but70, but71, but72, but73, but74, but75,
			but76, but77, but78, but79, but80, but81, but82, but83, but84, but85, but86, but87, but88, but89, but90,
			but91, but92, but93, but94, but95, but96, but97, but98, but99, but100;
	@FXML
	private Button startButton;

	public Button[][] butList;
	public int[][] neighList;
	public String[][] statusList;

	boolean flop = false;

	public void initialize(URL arg0, ResourceBundle arg1) {
		butList = getButArr();
		statusList = getStatusArr();
		neighList = getNeighboursArr(statusList);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				butList[i][j].setText("");

			}
	}

	@FXML
	public void startGen(ActionEvent e) {
//		startButton.setVisible(false);
		neighList = getNeighboursArr(statusList);
		statusList = getNewStatus(statusList, neighList);
		initTime();
	}

	@FXML
	public void chooseCell(ActionEvent e) {
		Button but = (Button) e.getSource();
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				if (but.equals(butList[i][j])) {
					if (statusList[i][j].equals("NONE")) {
						statusList[i][j] = "LIVE";
						but.setStyle(getColor(statusList[i][j]));
					} else if (statusList[i][j].equals("LIVE")) {
						statusList[i][j] = "NONE";
						but.setStyle(getColor(statusList[i][j]));
					}
				}

			}
	}

	public String[][] getNewStatus(String[][] status, int[][] neigh) {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				status[i][j] = prepairStep(status[i][j], neigh[i][j]);
		return status;
	}

	public Button[][] getButArr() {
		Button[] butList1 = { but1, but2, but3, but4, but5, but6, but7, but8, but9, but10 };
		Button[] butList2 = { but11, but12, but13, but14, but15, but16, but17, but18, but19, but20 };
		Button[] butList3 = { but21, but22, but23, but24, but25, but26, but27, but28, but29, but30 };
		Button[] butList4 = { but31, but32, but33, but34, but35, but36, but37, but38, but39, but40 };
		Button[] butList5 = { but41, but42, but43, but44, but45, but46, but47, but48, but49, but50 };
		Button[] butList6 = { but51, but52, but53, but54, but55, but56, but57, but58, but59, but60 };
		Button[] butList7 = { but61, but62, but63, but64, but65, but66, but67, but68, but69, but70 };
		Button[] butList8 = { but71, but72, but73, but74, but75, but76, but77, but78, but79, but80 };
		Button[] butList9 = { but81, but82, but83, but84, but85, but86, but87, but88, but89, but90 };
		Button[] butList10 = { but91, but92, but93, but94, but95, but96, but97, but98, but99, but100 };
		Button[][] butList = { butList1, butList2, butList3, butList4, butList5, butList6, butList7, butList8, butList9,
				butList10 };
		return butList;
	}

	public String[][] getStatusArr() {
		String[][] statList = new String[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				statList[i][j] = "NONE";
		return statList;
	}

	public String prepairStep(String status, int neighbours) {
		if (status.equals("NONE") && (neighbours == 3))
			return "BORN";
		else if (status.equals("LIVE") && (neighbours <= 1 || neighbours >= 4))
			return "DIED";
		else
			return status;

	}

	public String finishStep(String status) {
		if (status.equals("BORN"))
			return "LIVE";
		else if (status.equals("DIED"))
			return "NONE";
		else
			return status;
	}

	public String getColor(String status) {
		if (status.equals("NONE"))
			return "-fx-background-color: white";
		else if (status.equals("BORN"))
			return "-fx-background-color: blue";
		else if (status.equals("LIVE"))
			return "-fx-background-color: red";
		else if (status.equals("DIED"))
			return "-fx-background-color: gray";
		return status;
	}

	public int[][] getNeighboursArr(String[][] s) {
		int[][] neighList = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				neighList[i][j] = countNeighbours(s, i, j);
			}
		}
		return neighList;
	}

	public int countNeighbours(String[][] s, int i, int j) {
		int count = 0;
		for (int sx = -1; sx <= 1; sx++) {
			for (int sy = -1; sy <= 1; sy++) {
				if (!(sx == 0 && sy == 0)) {
					if (s[(i + sx + 10) % 10][(j + sy + 10) % 10].equals("LIVE")
							|| s[(i + sx + 10) % 10][(j + sy + 10) % 10].equals("DIED"))
						count++;
				}
			}
		}
		return count;
	}

	public void initTime() {
		int delay = 2000;
//			Timer timer = new Timer(true);	
//			TimerTask tt = new TimerTask() {
//				@Override
//				public void run() {
//					flop = !flop;
//					for (int i = 0; i < 10; i++) {
//						for (int j = 0; j < 10; j++) {
//							if (flop) {
//								statusList[i][j] = prepairStep(statusList[i][j], neighList[i][j]);
//								butList[i][j].setStyle(getColor(statusList[i][j]));
//							} else {
//								statusList[i][j] = finishStep(statusList[i][j]);
//								butList[i][j].setStyle(getColor(statusList[i][j]));
//							}
//						}
//					}
//				}
//			};
//			timer.scheduleAtFixedRate(tt, 3000, 100);

		Timer timer = new Timer(delay, new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent e) {
				flop = !flop;
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (flop) {
							System.out.println("is flop");
							statusList[i][j] = prepairStep(statusList[i][j], neighList[i][j]);
							butList[i][j].setStyle(getColor(statusList[i][j]));
						} else {
							System.out.println("not flop");
							statusList[i][j] = finishStep(statusList[i][j]);
							butList[i][j].setStyle(getColor(statusList[i][j]));
						}
					}
				}
			}

		});
		timer.setRepeats(true);
		timer.start();
		
	}

}