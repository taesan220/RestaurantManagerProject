package DAODTO;

import java.sql.Date;


public class AnorderDTO {

	int num;
	int tablecode;
	String kind;


	String name;
	int amount;
	int anorder;
	Date time;
	int condition;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getTablecode() {
		return tablecode;
	}
	public void setTablecode(int tablecode) {
		this.tablecode = tablecode;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAnorder() {
		return anorder;
	}
	public void setAnorder(int anorder) {
		this.anorder = anorder;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}

}
