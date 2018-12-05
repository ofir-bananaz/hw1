package homework1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * A JDailog GUI for choosing a GeoSegemnt and adding it to the route shown
 * by RoutDirectionGUI.
 * <p>
 * A figure showing this GUI can be found in homework assignment #1.
 */
public class GeoSegmentsDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	// the RouteDirectionsGUI that this JDialog was opened from
	private RouteFormatterGUI parent;

	// a control contained in this
	private JList<GeoSegment> lstSegments;

	//a scroll pane
	private JScrollPane segPane;

	private int highestIndexSelected=-1;


	/**
	 * Creates a new GeoSegmentsDialog JDialog.
	 * @effects Creates a new GeoSegmentsDialog JDialog with owner-frame
	 * 			owner and parent pnlParent
	 */

	public GeoSegmentsDialog(Frame owner, RouteFormatterGUI pnlParent) {
			// create a modal JDialog with the an owner Frame (a modal window
			// in one that doesn't allow other windows to be active at the
			// same time).
			super(owner, "Please choose a GeoSegment", true);
			pack();
			this.parent = pnlParent;

			// create components
			lstSegments = new JList<>(new DefaultListModel<GeoSegment>());
			GeoSegment[] segments = ExampleGeoSegments.segments;
			JLabel lblSegments = new JLabel("GeoSegments:");
			lblSegments.setLabelFor(segPane);
			JButton btnAddSegment = new JButton("Add");
			JButton btnCancel = new JButton("Cancel");

			//Create model and fill with segments:

			DefaultListModel<GeoSegment> model = new DefaultListModel<GeoSegment>();

			for (int i = 0, n = segments.length; i < n; i++)
				model.addElement(segments[i]);

			//Fill model into list and add to scroll pane.
			lstSegments.setModel(model);
			segPane= new JScrollPane(lstSegments);
			segPane.setPreferredSize(new Dimension(500,200));
			pack();

			//Pressing the add button.
			btnAddSegment.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				/*Adding a new segment must cope with the term of segment order.
				//If the segment chosen appears before the last segment in the route,
				an error dialog box appears and alerts the user.
				*/
					int indexInMap=0;
					for(int i=0;i<segments.length;i++){
						if(segments[i].equals(lstSegments.getSelectedValue())){
							indexInMap = i;
							break;
						}
					}

					if(highestIndexSelected!=-1&& indexInMap<highestIndexSelected)
							JOptionPane.showMessageDialog(owner, "Segment order error!");
					else {
						indexInMap= lstSegments.getSelectedIndex();
						pnlParent.addSegment(lstSegments.getSelectedValue());
						highestIndexSelected=Math.max(indexInMap,highestIndexSelected);
						model.removeElementAt(indexInMap);
						lstSegments.setModel(model);

				}
			}});


			//Pressing the cancel button
			btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
			});

			// arrange components on grid
			GridBagLayout gridbag = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			this.setLayout(gridbag);

			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(0,0,0,0);
			gridbag.setConstraints(lblSegments, c);
			this.add(lblSegments);

			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 3;
			c.gridheight = 3;
			gridbag.setConstraints(segPane, c);
			this.add(segPane);

			c.gridx = 0;
			c.gridy = 4 ;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(20,0,0,0);
			c.anchor= GridBagConstraints.EAST;
			gridbag.setConstraints(btnAddSegment, c);
			this.add(btnAddSegment);

			c.gridx = 2;
			c.gridy = 4;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(20,800,0,0);
			c.anchor= GridBagConstraints.WEST;
			gridbag.setConstraints(btnCancel, c);
			this.add(btnCancel);

	}

}
