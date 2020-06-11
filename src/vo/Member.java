package vo;

public class Member {

    public static final int Female = 0;
    public static final int Male = 1;
	private int id;
    private String name;
    private int gender;

    

	public Member(String name, int gender) {
		super();
		this.name = name;
		this.gender = gender;
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
}
