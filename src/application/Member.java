package application;

public class Member {

    private int id;
    private String name;
    private GENDER gender;

    enum GENDER {
        MALE,
        FEMALE
    }

	public Member(String name, GENDER gender) {
		super();
		this.name = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GENDER getGender() {
		return gender;
	}

	public void setGender(GENDER gender) {
		this.gender = gender;
	}
	
	
	
}
