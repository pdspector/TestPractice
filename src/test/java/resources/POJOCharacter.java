package resources;

public class POJOCharacter {
	
	private int char_id;
    private String name;
    private String birthday;
    private String[] occupation;
    private String img;
    private String status;
    private String nickname;
    private int[] appearance;
    private String portrayed;
    private String category;
    private int[] better_call_saul_appearance;
    
	public int getChar_id() {
		return char_id;
	}
	public void setChar_id(int char_id) {
		this.char_id = char_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String[] getOccupation() {
		return occupation;
	}
	public void setOccupation(String[] occupation) {
		this.occupation = occupation;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int[] getAppearance() {
		return appearance;
	}
	public void setAppearance(int[] appearance) {
		this.appearance = appearance;
	}
	public String getPortrayed() {
		return portrayed;
	}
	public void setPortrayed(String portrayed) {
		this.portrayed = portrayed;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int[] getBetter_call_saul_appearance() {
		return better_call_saul_appearance;
	}
	public void setBetter_call_saul_appearance(int[] better_call_saul_appearance) {
		this.better_call_saul_appearance = better_call_saul_appearance;
	}
	
	@Override
	public String toString() {
		return "Character name: \"" + getName() + "\"\r\n" + 
				"\r\n" + 
				"Portrayed: \"" + getPortrayed() + "\"\r\n" +
				"\r\n" +
				"------------------------------------------------------\r\n";
	}
    
}
