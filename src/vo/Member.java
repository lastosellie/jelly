package vo;


public class Member {

	private String id;  //사번이자 회원가입시 ID
    private String name;
    private int gender;
    private int project_id;
    private int deptno;
    private String pw;
    
    
    
    public static final int Male = 0;
    public static final int Female = 1;

	public Member(String name, int gender, String id, String pw, int project_id, int deptno) {
		super();
    	this.id = id;
		this.name = name;
		this.gender = gender;
		this.project_id = project_id;
		this.deptno = deptno;
		this.pw=pw;
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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


	public String getPw() {
		return pw;
	}

	public void setpw(String pw) {
		this.pw = pw;
	}

}


