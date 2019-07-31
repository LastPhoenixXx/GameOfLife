package GameOfLife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TimerListener implements ActionListener {
	boolean flop = false;

	public void actionPerformed(ActionEvent arg0) {
		flop = !flop;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(flop) {
					
				} else {
					
				}
			}
		}
	}

}
