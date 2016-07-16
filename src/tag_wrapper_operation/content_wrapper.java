package tag_wrapper_operation;
/**
 * Deal with all of the string manipulation
 * @author xingda
 * @since 14/07/2016
 */
public class content_wrapper {
	

	
	/**
	 * Remove duplicated next line and return lines number
	 * @param content: the original input text
	 * @return lines number
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
	 * Wrap content with p and h2
	 * @param content: processed content without multiple 'next lines'
	 * @return wrapped content
	 */
	public String wrap(String content) {
		String processed_content = this.pre_process_content(content);
		
		// Wrap content with h2 and p tag
		String wrapped_content = "";
		for (String each_line_content: processed_content.split(System.lineSeparator())) {
			if (each_line_content.split(" ").length == 1 && each_line_content =="") {
			} else if (each_line_content.split(" ").length < 10) {// should be configurable
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
	
	public String update_content(String original_content, String changed_content, String target) {
		this.pre_process_content(original_content);
		String updated_content = "";//final result
		if(changed_content != null){
			for (String original_line: original_content.split(System.lineSeparator())){
				for (String changed_line: changed_content.split(System.lineSeparator())){
					if(changed_line.length() > 0 && original_line.contains(changed_line)) {
						original_line = update_line(original_line, target);
						break;
					}
				}
				updated_content = updated_content + original_line + System.lineSeparator();
			}
		}
		return updated_content;
	}
	
	public String update_line(String content, String target) {
		return "<" + target + ">" + content.subSequence(4, content.length() - 4) + "<" + target + "/>";
	}
}
