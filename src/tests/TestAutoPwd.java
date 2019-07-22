package tests;

public class TestAutoPwd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String name = "Hebestreit";
		String vorname = "Soeren";
		int smonth = 7;
		int bday = 15;
		int personalnummer = 2;
		
		String username = name+personalnummer;
		String password = vorname.substring(0,1).toLowerCase()+name.substring(0,1).toUpperCase()+"_"+smonth+bday+vorname.charAt(vorname.length()/2)+name.charAt(name.length()/3);
		
		System.out.println(username+"\t"+password);
	}

}
