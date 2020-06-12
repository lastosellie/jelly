package vo;


public class Member {

	private int id;
    private String name;
    private int gender;
    private int project_id;
    private int deptno;
    
    public static final int Male = 0;
    public static final int Female = 1;

    public Member(String name, int gender) {
		super();
    	this.id = id;
		this.name = name;
		this.gender = gender;
	}
    
	public Member(int id, String name, int gender, int project_id, int deptno) {
		super();
    	this.id = id;
		this.name = name;
		this.gender = gender;
		this.project_id = project_id;
		this.deptno = deptno;
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

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

}


