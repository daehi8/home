package home.board;
import java.sql.Timestamp;

public class BoardDTO{

	private int num; 			// �Խñ� ��ȣ
    private String writer;		// �Խñ� �ۼ���
    private String subject;		// �Խñ� ����
    private String email;		// �̸���
    private String content;		// �Խñ� ����
    private String pw;			// �Խñ� ��й�ȣ
    private Timestamp reg_date;	// �ۼ� �ð�
    private int readcount;		// ��ȸ��
    private String ip;
    private int ref;			// �� ��ȣ
    private int re_step;		// �亯�� ���� ����
    private int re_level;		// �亯�� �亯 Ȯ��

	public void setNum(int num){
    	this.num=num;
    }
    public void setWriter (String writer) {
        this.writer = writer;
    }
    public void setSubject (String subject) {
        this.subject = subject;
    }
    public void setEmail (String email) {
        this.email = email;
    }
    public void setContent (String content) {
        this.content = content;
    }
    public void setPw (String pw) {
        this.pw = pw;
    }
    public void setReg_date (Timestamp reg_date) {
        this.reg_date = reg_date;
    }
	public void setReadcount(int readcount){
	  	this.readcount=readcount;
	}
    public void setIp (String ip) {
        this.ip = ip;
    }
	public void setRef (int ref) {
        this.ref = ref;
    }
	public void setRe_level (int re_level) {
        this.re_level=re_level;
    }
	public void setRe_step (int re_step) {
        this.re_step=re_step;
    }
    
    public int getNum(){
    	return num;
    }
    public int getReadcount(){
   	    return readcount;
    }
    public String getWriter () {
        return writer;
    }
    public String getSubject () {
        return subject;
    }
    public String getEmail () {
        return email;
    }
    public String getContent () {
        return content;
    }
    public String getPw () {
        return pw;
    }
    public Timestamp getReg_date () {
        return reg_date;
    }
    public String getIp () {
        return ip;
    }
    public int getRef () {
        return ref;
    }
	public int getRe_level () {
        return re_level;
    }
	public int getRe_step () {
        return re_step;
    }
    
}
