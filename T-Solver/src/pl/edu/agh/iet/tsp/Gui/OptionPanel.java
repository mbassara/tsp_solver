package pl.edu.agh.iet.tsp.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import pl.edu.agh.iet.tsp.Algorithm.IResultListener;
import pl.edu.agh.iet.tsp.Algorithm.OptionsForAlgorithm;
import pl.edu.agh.iet.tsp.Algorithm.RunAlgorithm;
import pl.edu.agh.iet.tsp.Model.TSPData;
import pl.edu.agh.iet.tsp.Model.TSPDataSerialization;

public class OptionPanel extends JPanel implements IResultListener {

	private static final long serialVersionUID = -1588798219506287543L;
	private final ProgressBar progress;
	private final MapPanel mapPanel;
	private final JButton save;
	private final JButton calculate;
	private final IResultListener this_instance = this;

	private final JTextField bcmax = new JTextField();
	private final JTextField alpha = new JTextField();
	private final JTextField gamma = new JTextField();
	private final JTextField k = new JTextField();
	private final JTextField beta = new JTextField();

	private final double[][] defaultValue = { { 0.0, 0.95, 0.8 },
			{ 0.95, 0.975, 0.2 }, { 0.975, 0.99, 0.02 }, { 0.99, 1.0, 0.0 },
			{ 0.0, 0.0, 0.0 } };

	public OptionPanel(MapPanel _mapPanel) {
		super(new BorderLayout());

		mapPanel = _mapPanel;

		final JPanel main = new JPanel();
		DesignGridLayout layout = new DesignGridLayout(main);
		layout.labelAlignment(LabelAlignment.RIGHT);

		JLabel bcmaxLabel = new JLabel("BC max");
		JLabel alphaLabel = new JLabel("alpha");
		JLabel betaLabel = new JLabel("beta");
		JLabel gammaLabel = new JLabel("gamma");
		JLabel kLabel = new JLabel("k");

		bcmax.setText("13");
		alpha.setText("0.3");
		beta.setText("0.9");
		gamma.setText("1.0");
		k.setText("0.4");

		layout.row().grid(bcmaxLabel).add(bcmax);
		layout.row().grid(alphaLabel).add(alpha);
		layout.row().grid(betaLabel).add(beta);
		layout.row().grid(gammaLabel).add(gamma);
		layout.row().grid(kLabel).add(k);

		layout.emptyRow();

		// JComboBox opt = new JComboBox();
		// opt.addItem("option 1");
		// opt.addItem("option 2");
		// opt.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent a) {
		//
		// if (((JComboBox) a.getSource()).getSelectedItem().equals(
		// "option 1"))
		// alpha.setText("11");
		// beta.setText("11");
		// k.setText("11");
		//
		// }
		// });
		// layout.row().grid(new JLabel("options")).add(opt);

		final JTextField a1 = new JTextField();
		final JTextField a2 = new JTextField();
		final JTextField a3 = new JTextField();
		final JTextField a4 = new JTextField();
		final JTextField a5 = new JTextField();
		final JTextField b1 = new JTextField();
		final JTextField b2 = new JTextField();
		final JTextField b3 = new JTextField();
		final JTextField b4 = new JTextField();
		final JTextField b5 = new JTextField();
		final JTextField c1 = new JTextField();
		final JTextField c2 = new JTextField();
		final JTextField c3 = new JTextField();
		final JTextField c4 = new JTextField();
		final JTextField c5 = new JTextField();

		a1.setText(Double.toString(defaultValue[0][0]));
		a2.setText(Double.toString(defaultValue[1][0]));
		a3.setText(Double.toString(defaultValue[2][0]));
		a4.setText(Double.toString(defaultValue[3][0]));
		a5.setText(Double.toString(defaultValue[4][0]));
		b1.setText(Double.toString(defaultValue[0][1]));
		b2.setText(Double.toString(defaultValue[1][1]));
		b3.setText(Double.toString(defaultValue[2][1]));
		b4.setText(Double.toString(defaultValue[3][1]));
		b5.setText(Double.toString(defaultValue[4][1]));
		c1.setText(Double.toString(defaultValue[0][2]));
		c2.setText(Double.toString(defaultValue[1][2]));
		c3.setText(Double.toString(defaultValue[2][2]));
		c4.setText(Double.toString(defaultValue[3][2]));
		c5.setText(Double.toString(defaultValue[4][2]));

		layout.row().grid(3).grid(new JLabel("")).grid(new JLabel(""))
				.grid(new JLabel(""));
		layout.row().grid(new JLabel("1")).grid().add(a1).add(b1).add(c1);
		layout.row().grid(new JLabel("2")).grid().add(a2).add(b2).add(c2);
		layout.row().grid(new JLabel("3")).grid().add(a3).add(b3).add(c3);
		layout.row().grid(new JLabel("4")).grid().add(a4).add(b4).add(c4);
		layout.row().grid(new JLabel("5")).grid().add(a5).add(b5).add(c5);

		layout.emptyRow();

		save = new JButton("Save");
		save.setVisible(false);

		calculate = new JButton("Calculate");

		calculate.setBackground(Color.gray);
		progress = new ProgressBar();
		calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				OptionsForAlgorithm optionsForAlgorithm = new OptionsForAlgorithm();

				if (!bcmax.getText().equals(""))
					optionsForAlgorithm.setBcmax(Integer.parseInt(bcmax
							.getText()));
				if (!alpha.getText().equals(""))
					optionsForAlgorithm.setAlpha(Float.parseFloat(alpha
							.getText()));
				if (!beta.getText().equals(""))
					optionsForAlgorithm.setBeta(Float.parseFloat(beta.getText()));
				if (!gamma.getText().equals(""))
					optionsForAlgorithm.setGamma(Float.parseFloat(gamma
							.getText()));
				if (!k.getText().equals(""))
					optionsForAlgorithm.setK(Float.parseFloat(k.getText()));

				float[][] t = new float[5][3];

				if (!a1.getText().equals(""))
					t[0][0] = Float.parseFloat(a1.getText());
				if (!a2.getText().equals(""))
					t[1][0] = Float.parseFloat(a2.getText());
				if (!a3.getText().equals(""))
					t[2][0] = Float.parseFloat(a3.getText());
				if (!a4.getText().equals(""))
					t[3][0] = Float.parseFloat(a4.getText());
				if (!a5.getText().equals(""))
					t[4][0] = Float.parseFloat(a5.getText());

				if (!b1.getText().equals(""))
					t[0][1] = Float.parseFloat(b1.getText());
				if (!b2.getText().equals(""))
					t[1][1] = Float.parseFloat(b2.getText());
				if (!b3.getText().equals(""))
					t[2][1] = Float.parseFloat(b3.getText());
				if (!b4.getText().equals(""))
					t[3][1] = Float.parseFloat(b4.getText());
				if (!b5.getText().equals(""))
					t[4][1] = Float.parseFloat(b5.getText());

				if (!c1.getText().equals(""))
					t[0][2] = Float.parseFloat(c1.getText());
				if (!c2.getText().equals(""))
					t[1][2] = Float.parseFloat(c2.getText());
				if (!c3.getText().equals(""))
					t[2][2] = Float.parseFloat(c3.getText());
				if (!c4.getText().equals(""))
					t[3][2] = Float.parseFloat(c4.getText());
				if (!c5.getText().equals(""))
					t[4][2] = Float.parseFloat(c4.getText());

				optionsForAlgorithm.setTab(t);

				optionsForAlgorithm.setN(mapPanel.data.tspdata.getNodes()
						.size());

				mapPanel.data.tspdata.nodesToGraph();
				RunAlgorithm.runAlgoThread(optionsForAlgorithm,
						mapPanel.data.tspdata, progress, this_instance);
				calculate.setEnabled(false);
			}

		});
		layout.row().grid().add(calculate).add(save);
		layout.row().center().add(progress);

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showSaveDialog(OptionPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					// //////////////////
					// //////////do zmiany jak bedzie algorytm

					TSPDataSerialization.serialize(mapPanel.data.getTspdata(),
							file);
					save.setText("Saved!");
					// na razie zapisuje losowe dane ( TSP DATA )

				} else {
				}
			}
		});
		// layout.row().center().add(save);

		add(main);

	}

	@Override
	public void receiveResult(TSPData result, int best_iteration,
			double distance) {

		MainWindow.setIterationLabelText("Best iteration: " + best_iteration
				+ " of " + bcmax.getText());
		MainWindow.setDistanceLabelText("Shortest path (length): "
				+ String.format("%.2f", distance));

		// System.out.println(result);
		mapPanel.data.readData(result);

		mapPanel.setSolution(true);
		save.setVisible(true);

		mapPanel.repaint();
		calculate.setEnabled(true);
	}
}
