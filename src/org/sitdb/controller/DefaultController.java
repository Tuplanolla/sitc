package org.sitdb.controller;

import org.sitdb.Controller;
import org.sitdb.Model;
import org.sitdb.View;

/**
Represents a pointless controller.

@author Sampsa "Tuplanolla" Kiiskinen
**/
public final class DefaultController extends Controller {
	/**
	Constructs a new view and links it with a model and a view.
	
	@param model The model.
	@param view The view.
	**/
	public DefaultController(final Model model, final View view) {
		super(model, view);
	}

	/*
	@Deprecated
	private void addTestControls() {//TODO move into the controller
		mainPanel.getInstrumentManagerPanel().getFilePanel().getBrowseButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument file browser goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mainPanel.getInstrumentManagerPanel().getLoadButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument file loader goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mainPanel.getInstrumentManagerPanel().getSaveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument file saver goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mainPanel.getInstrumentManagerPanel().getListPanel().getSearchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument list searcher goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mainPanel.getInstrumentManagerPanel().getListPanel().getNewButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument list item creator goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mainPanel.getInstrumentManagerPanel().getListPanel().getDeleteButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument list item disposer goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});

		mainPanel.getInstrumentEditorPanel().getSidePanel().getRevertButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument editor change reverter goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mainPanel.getInstrumentEditorPanel().getSidePanel().getApplyButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The instrument editor change applier goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});

		menuPanel.getExitMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				MainFrame.this.dispose();
			}
		});
		menuPanel.getManualMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The manual goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
		menuPanel.getAboutMenuItem().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				JOptionPane.showMessageDialog(MainFrame.this, "The about dialog goes here.", "Note", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}
	*/

	@Override
	public void activate() {
		//addTestControls();
	}
}
