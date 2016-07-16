/**
 * Deal with all of the string manipulation
 * @author xingda
 * @since 14/07/2016
 */

package tag_wrapper_operation;

import java.io.FileReader;
import java.io.ObjectOutputStream.PutField;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class content_wrapper {
	
	/**
	 * Remove duplicated next line and return lines number
	 * @param content
	 * 		original content text
	 * @return
	 * 		content without line separator
	 */
	public String pre_process_content(String content) {
		String new_content = "";
		for (String each_line: content.split(System.lineSeparator())){
			if (!each_line.isEmpty()) {
				new_content += (each_line + System.lineSeparator());
			}
		}
		// Remove last "\n"
		if(new_content.length() >= 1)
			return new_content.substring(0, new_content.length() - 1);
		else
			return null;
	}
	
	/**
	 * Initial wrapping content with p and h2 tags
	 * @param content
	 * 		processed content without multiple 'next lines'
	 * @return
	 * 		wrapped content
	 */
	public String wrap(String content, int title_length) {
		String processed_content = this.pre_process_content(content);
		
		// Wrap content with h2 and p tag
		String wrapped_content = "";
		for (String each_line_content: processed_content.split(System.lineSeparator())) {
			if (each_line_content.split(" ").length == 1 && each_line_content =="") {
			} else if (each_line_content.split(" ").length < title_length) {// should be configurable
				wrapped_content += ("<h2>" + each_line_content + "</h2>");
			} else {
				wrapped_content += ("<p>" + each_line_content + "</p>");
			}
			wrapped_content += (System.lineSeparator() + System.lineSeparator());
		}
		// Remove last "\n"
		if(wrapped_content.length() >= 2)
			wrapped_content = wrapped_content.substring(0, wrapped_content.length() - 2);
		return wrapped_content;
	}
	
	/**
	 * Replace the content with given tag
	 * @param original_content
	 * 		content with initialized tags
	 * @param changed_content
	 * 		content without any tags
	 * @param target
	 * 		target tag
	 * @return
	 * 		content with updated tags
	 */
	public String update_content(String original_content, String changed_content, String target) {
		this.pre_process_content(original_content);
		String updated_content = "";//final result
		if(changed_content != null){
			for (String original_line: original_content.split(System.lineSeparator())){
				for (String changed_line: changed_content.split(System.lineSeparator())){
					String trimed_original_line = original_line.replaceAll("<[^>]*>", "");
					if(changed_line.length() > 0 && trimed_original_line.equals(changed_line)) {
						original_line = update_line(original_line, target);
						break;
					}
				}
				updated_content = updated_content + original_line + System.lineSeparator();
			}
		}
		return updated_content;
	}
	
	/**
	 * Replace content with the given target
	 * @param content
	 * 		line of content witch needs to be replaced
	 * @param target
	 * 		tag target
	 * @return
	 * 		wrapped line of content with target tag
	 */
	public String update_line(String content, String target) {
		String result = content.replaceAll("<\\w+>", "<" + target + ">");
		result = result.replaceAll("<\\/\\w+>", "</" + target + ">");
		return result;
	}
	
	/**
	 * Load configuration from JSON file
	 * @return
	 * 		Configuration in hash format
	 */
	public HashMap load_config(){
		// Put config in Hashmap
		HashMap<String, String> config = new HashMap<String, String>();
		try {
			FileReader config_file = new FileReader("src/config.json");
			JsonParser parser = new JsonParser();
			JsonObject json_object = (JsonObject) parser.parse(config_file);
			// Gui
			JsonObject gui = (JsonObject) json_object.get("gui");
			// Gui -> framework_size_init
			config.put("gui_framework_size_init_width", gui.get("framework_size_init_width").toString());
			config.put("gui_framework_size_init_height", gui.get("framework_size_init_height").toString());
			// Gui -> framework_resized
			config.put("gui_framework_resized_width", gui.get("framework_resized_width").toString());
			config.put("gui_framework_resized_height", gui.get("framework_resized_height").toString());
			// Gui ->result_editable
			config.put("gui_result_editable", gui.get("result_editable").toString());
			// Calculation
			JsonObject calculation = (JsonObject) json_object.get("calculation");
			// Calculation -> title_length
			config.put("calculation_title_length", calculation.get("title_length").toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return config;
	}
}
