package tag_wrapper_gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import tag_wrapper_operation.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class gui_frame extends JFrame{
	
	
	// JComponent
	public static String content = ""; // can be changed to private
	public static String result = "";
	public static String selected_text = "";
	private JTextArea content_area = new JTextArea(30, 30);
	private JTextArea result_area = new JTextArea(30, 30);
	private JButton wrapper_button = new JButton("Wrap content");
	private JButton h1_tag_button = new JButton("<h1>");
	private JButton h2_tag_button = new JButton("<h2>");
	private JButton h3_tag_button = new JButton("<h3>");
	private JButton p_tag_button = new JButton("<p>");
	
	// JPanel
	private JPanel content_panel = new JPanel();
	private JPanel operater_panel = new JPanel();
	private JPanel result_panel = new JPanel();
	
	private JScrollPane scroll_content_pane = new JScrollPane(content_area);
	private JScrollPane scroll_result_pane = new JScrollPane(result_area);
	private JFrame frame = new JFrame("HTML tag wrapper");

	public gui_frame(){
		
		// Break words
		this.content_area.setLineWrap(true);
		this.result_area.setLineWrap(true);
		this.result_area.setEditable(false);
		
		// Panel Layout
		this.content_panel.setBorder(new TitledBorder(new EtchedBorder(), "You can paste content here:"));
		this.result_panel.setBorder(new TitledBorder(new EtchedBorder(), "Your wrapped content is here:"));
		
		// Scroll pane policy
		this.scroll_content_pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.scroll_content_pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.scroll_result_pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.scroll_result_pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// JComponent event
		content_area.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				update_content();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				update_content();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				update_content();
			}
			public void update_content() {
				content = content_area.getText();
				
			}
		});
		// Wrapper button event
		this.wrapper_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				content_wrapper wrapper = new content_wrapper();
				result = wrapper.wrap(content);
				gui_frame.this.show_result(820, 600, content, result);// Should be configurable
			}
		});
		// Mouse event
		content_area.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				selected_text = content_area.getSelectedText();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		// h1 tag
		this.h1_tag_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(selected_text != null) {
					content_wrapper wrapper = new content_wrapper();
					String updated_result = wrapper.update_content(result, selected_text, "h1");
					gui_frame.this.show_result(820, 600, content, updated_result);// Should be configurable
				}
			}
		});
		// h2 tag
		this.h2_tag_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(selected_text != null) {
					content_wrapper wrapper = new content_wrapper();
					String updated_result = wrapper.update_content(result, selected_text, "h2");
					gui_frame.this.show_result(820, 600, content, updated_result);// Should be configurable
				}
			}
		});
		// h3 tag
		this.h3_tag_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(selected_text != null) {
					content_wrapper wrapper = new content_wrapper();
					String updated_result = wrapper.update_content(result, selected_text, "h3");
					gui_frame.this.show_result(820, 600, content, updated_result);// Should be configurable
				}
			}
		});
		// p tag
		this.p_tag_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(selected_text != null) {
					content_wrapper wrapper = new content_wrapper();
					String updated_result = wrapper.update_content(result, selected_text, "p");
					gui_frame.this.show_result(820, 600, content, updated_result);// Should be configurable
				}
			}
		});
		
		this.content_panel.add(this.scroll_content_pane);
		this.result_panel.add(this.scroll_result_pane);
		this.operater_panel.add(this.wrapper_button);
		this.operater_panel.add(this.h1_tag_button);
		this.operater_panel.add(this.h2_tag_button);
		this.operater_panel.add(this.h3_tag_button);
		this.operater_panel.add(this.p_tag_button);

		this.frame.add(content_panel);
		this.frame.add(result_panel);
		this.frame.add(operater_panel);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void gui_show() {
		
		// Do not show result area when open
		this.result_panel.setVisible(false);
		this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.Y_AXIS));
		this.frame.setSize(400, 600);
		this.frame.setVisible(true);
	}
	
	public void show_result(int width,int height, String content, String wrapped_content){
		this.content_area.setText(content);
		this.content_area.setCaretPosition(0);
		this.result_area.setText(wrapped_content);
		this.result_area.setCaretPosition(0);
		this.result_panel.setVisible(true);
		this.frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.frame.resize(width, height);
		this.frame.setVisible(true);
	}
}
