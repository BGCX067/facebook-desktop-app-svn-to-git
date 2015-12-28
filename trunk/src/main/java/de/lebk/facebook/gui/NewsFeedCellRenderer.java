package de.lebk.facebook.gui;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import facebook4j.Post;

public class NewsFeedCellRenderer implements ListCellRenderer<Post> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Post> list,
			Post value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel feedText = new JLabel();
		
		feedText.setFont(new Font("Consolas", Font.PLAIN, 14));
		feedText.setBounds(10, 10, 385, 300);
		StringBuilder sb = new StringBuilder();
		if(null != value.getMessage()) {
			sb.append("Message: "+value.getMessage());
		}
		feedText.setText(sb.toString());
		
		return feedText;
	}

}
