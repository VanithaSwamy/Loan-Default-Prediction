package LinearRegressionModel;

public class QueryLR {
	public static String AvgIncome="select avg(i.annual_income) from income  i inner join borrowerloanjoin  blj on i.income_id=blj.income_id;";
	public static String AvgLoanAmt="select avg(l.amount) from loan_amt  l inner join borrowerloanjoin  blj on l.loan_id=blj.loan_id;";
	public static String AvgCredScore="select avg(c.score) from credit_score  c inner join borrowerloanjoin  blj on c.cred_id=blj.cred_id;";
	public static String AvgAge="SELECT avg(TIMESTAMPDIFF(YEAR,b.dob, CURDATE())) as age from borrower b inner join borrowerloanjoin blj on b.borrower_id=blj.borrower_id;";
	public static String AvgStatus=" select avg(status) from borrowerloanjoin;";
}
